/**
 * Created by changePosition on 2018/11/27.
 */
import React from 'react';
import {Button, View, Text, FlatList, Image, TouchableOpacity, StyleSheet} from 'react-native';
import {Container, Header, Content, Icon, Picker, Form, DatePicker} from "native-base";
import DateUtil from '../../utils/DateUtil';
import moment from 'moment';
import Echarts from 'native-echarts';
import DimensUtil from '../../utils/DimensUtil'
import {SERVER_URL} from '../../Constants'
import Cans from '../../Constants'
const SALE_DAILY_URL = SERVER_URL + 'sale/querySalesByDailyNew';
let shouldRefresh = false
export default class Sale extends React.Component {
    static navigationOptions = ({navigation}) => {
        return {
            title: navigation.getParam('otherParam', 'A Nested Details Screen'),
            headerTintColor: '#FFF',
            headerStyle: {
                backgroundColor: '#447BE8',
            }
        };
    };

    constructor(props) {
        super(props)
        this.state = {
            // supplies: ['100521', '100434', '101547'],
            posi: 0,
            supplies: Cans.userInfo.lstSupply,
            selectedSupply: Cans.userInfo.lstSupply[0].supplyId,
            // brands: ['AAAA', 'XXXX', 'CCCC', 'VVVV', 'ZZZZ', 'PPPP'],
            brands: Cans.userInfo.lstSupply[0].brandDtos,
            selectedBrand: Cans.userInfo.lstSupply[0].brandDtos[0].brandSid,
            startDate: DateUtil.formatDate(new Date().getMilliseconds()),
            endDate: null,
            saleData: null,
            saleHistory: null,
            saleTypes: ['netSaleMoney', 'padSaleMoney', 'saleMoneySum', 'saleSum', 'stockSum', 'weixinSaleMoney'],
            saleDatas: [0, 0, 0, 0, 0, 0],
            /*注意善变设置了一个同名的let shouldRefresh*/
            shouldRefresh: false
        }
    }

    render() {
        const {navigation} = this.props;
        const itemId = navigation.getParam('itemId', 'NO-ID');
        const otherParam = navigation.getParam('otherParam', 'some default value');

        option = {
            title: {
                text: '销售日报',
                subtext: '数据',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                // orient: 'vertical',
                // top: 'middle',
                bottom: 10,
                left: 'center',
                data: this.state.saleTypes
            },
            series: [
                {
                    type: 'pie',
                    radius: '65%',
                    center: ['50%', '50%'],
                    selectedMode: 'single',
                    data: [
                        {value: this.state.saleDatas[0], name: this.state.saleTypes[0]},
                        {value: this.state.saleDatas[1], name: this.state.saleTypes[1]},
                        {value: this.state.saleDatas[2], name: this.state.saleTypes[2]},
                        {value: this.state.saleDatas[3], name: this.state.saleTypes[3]},
                        {value: this.state.saleDatas[4], name: this.state.saleTypes[4]}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        return (
            <View>
                <View style={{flexDirection: 'row'}}>
                    <Picker
                        mode="dropdown"
                        iosIcon={<Icon name="ios-arrow-down-outline"/>}
                        placeholder={this.state.chosenDate}
                        placeholderStyle={{color: "#bfc6ea"}}
                        placeholderIconColor="#007aff"
                        style={{width: DimensUtil.getItemWidth(2, 25)}}
                        selectedValue={this.state.selectedSupply}
                        onValueChange={this.onSupplySelected.bind(this)}>
                        {this.state.supplies.map((supply) => <Picker.Item label={supply.companyName}
                                                                          value={supply.supplyId}/>)}
                    </Picker>
                    <Picker
                        mode="dropdown"
                        iosIcon={<Icon name="ios-arrow-down-outline"/>}
                        style={{width: DimensUtil.getItemWidth(2, 25)}}
                        selectedValue={this.state.selectedBrand}
                        onValueChange={this.onBrandSelected.bind(this)}>
                        {this.state.brands.map((brand) => <Picker.Item label={brand.brandName}
                                                                       value={brand.brandSid}/>)}
                    </Picker>
                </View>
                <View style={{flexDirection: 'row', justifyContent: 'center'}}>
                    <DatePicker
                        defaultDate={new Date()}
                        locale={"zh"}
                        timeZoneOffsetInMinutes={undefined}
                        modalTransparent={false}
                        animationType={"fade"}
                        androidMode={"calendar"}
                        placeHolderText="选择开始时间"
                        textStyle={{color: "green"}}
                        placeHolderTextStyle={{color: "#d3d3d3"}}
                        onDateChange={this.onStartDateSelected.bind(this)}
                    />
                    <DatePicker
                        defaultDate={new Date()}
                        locale={"zh"}
                        timeZoneOffsetInMinutes={undefined}
                        modalTransparent={false}
                        animationType={"fade"}
                        androidMode={"calendar"}
                        placeHolderText="选择结束时间"
                        textStyle={{color: "green"}}
                        placeHolderTextStyle={{color: "#d3d3d3"}}
                        onDateChange={this.onEndDateSelected.bind(this)}
                    />
                    <View style={{width: 100, height: 50, marginLeft: 25, marginRight: 2}}>
                        <Button title={'查询'}
                                onPress={this.getSaleHistory.bind(this)}/>
                    </View>
                </View>
                <Echarts option={option} height={300}/>
                {/*默认设置不允许渲染，只有点击查询时才允许渲染机制*/}
                {/*对于在这里设置状态shouldRefresh，会有警告，即使换成调取一个方法设置仍有警告（意义一样）：Cannot update during an existing state transition (such as within 'render')*/}
                {/*{this.setState({shouldRefresh:false})}*/}
                {/*于是就设置成这种方式*/}
                {shouldRefresh = false}
            </View>
        );
    }

    onSupplySelected(value, poi) {
        this.setState({
            selectedSupply: value,
            brands: this.state.supplies[poi].brandDtos
        })
    }

    onBrandSelected(value) {
        this.setState({selectedBrand: value})
    }

    onStartDateSelected(date: Date) {
        let year = date.getFullYear();
        let month = date.getUTCMonth();
        let day = date.getUTCDate()
        let formatDate = moment(date.getTime().valueOf()).format('YYYY-MM-DD');
        //alert(year + " " + month + " " + day + " " + m + " " + s)
        this.setState({startDate: formatDate});
    }

    onEndDateSelected(date: Date) {
        let year = date.getFullYear();
        let month = date.getUTCMonth();
        let day = date.getUTCDate()
        let formatDate = moment(date.getTime().valueOf()).format('YYYY-MM-DD');
        //alert(year + " " + month + " " + day + " " + m + " " + s)
        this.setState({endDate: formatDate});
    }

    getSaleHistory() {
        //alert(this.state.selectedSupply + "   " + this.state.selectedBrand)
        // alert(JSON.stringify(Cans.userInfo.lstSupply.length))
        if (this.state.selectedSupply == null || this.state.selectedBrand == null) {
            alert('请选择供应商或品牌！')
            // return
        }
        let formData = new FormData();
        formData.append("supplyInfoSid", this.state.selectedSupply);
        formData.append("brandSidSAP", this.state.selectedBrand);
        formData.append("shopSid", Cans.userInfo.shopSid);
        formData.append("daytimeStart", this.state.startDate);
        formData.append("daytimeEnd", this.state.endDate);
        let promise = new Promise(function (resolve, reject) {
            fetch(SALE_DAILY_URL, {
                method: 'POST',
                headers: {},
                body: formData
            }).then((response) => {
                if (response.ok) {
                    resolve(response);
                } else {
                }
            }).catch((err) => {
                reject('网络异常，请检查网络后再试。' + JSON.stringify(err));
            })
        });
        promise.then((response) => response.json()).then((responseData) => {
            if (responseData.code == 'SUCCESS') {
                this.setState({
                    saleHistory: responseData.data
                });
                shouldRefresh = true
                this.setState({
                    saleDatas: [this.state.saleHistory.list[0].netSaleMoney,
                        this.state.saleHistory.list[0].padSaleMoney,
                        this.state.saleHistory.list[0].saleMoneySum,
                        this.state.saleHistory.list[0].saleSum,
                        this.state.saleHistory.list[0].stockSum,
                        this.state.saleHistory.list[0].weixinSaleMoney]
                });
                // this.state.saleDatas = [this.state.saleHistory.list[0].netSaleMoney,
                //     this.state.saleHistory.list[0].padSaleMoney,
                //     this.state.saleHistory.list[0].saleMoneySum,
                //     this.state.saleHistory.list[0].saleSum,
                //     this.state.saleHistory.list[0].stockSum,
                //     this.state.saleHistory.list[0].weixinSaleMoney]

            } else {

                alert("FAIL：" + JSON.stringify(responseData))
            }
        }).catch((err) => {
            alert("异常：" + err);
        })
    }

    shouldComponentUpdate() {
        return shouldRefresh
    }

}
const SaleStyle = StyleSheet.create({})

// module.exports = Sale;

