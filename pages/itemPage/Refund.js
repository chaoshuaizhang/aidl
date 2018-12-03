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
import Cans from '../../Constants'
var SALE_DAILY_URL = 'http://172.16.100.158:8080/pdaware/sale/querySalesByDailyNew';
export default class Refund extends React.Component {
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
            endDate: null
        }
    }

    render() {
        const {navigation} = this.props;
        const itemId = navigation.getParam('itemId', 'NO-ID');
        const otherParam = navigation.getParam('otherParam', 'some default value');

        option = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['tiled']},
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'邮件营销',
                    type:'line',
                    stack: '总量',
                    data:[120, 300, 20, 1000, 90, 230, 210]
                },
                {
                    name:'联盟广告',
                    type:'line',
                    stack: '总量',
                    data:[220, 182, 3000, 234, 290, 330, 310]
                },
                {
                    name:'视频广告',
                    type:'line',
                    stack: '总量',
                    data:[150, 232, 20, 154, 600, 330, 410]
                },
                {
                    name:'直接访问',
                    type:'line',
                    stack: '总量',
                    data:[1000, 332, 301, 334, 390, 330, 320]
                },
                {
                    name:'搜索引擎',
                    type:'line',
                    stack: '总量',
                    data:[820, 932, 20, 934, 1290, 300, 1320]
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
                <View style={{flexDirection: 'row'}}>
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
                    <Button title={'查询'} onPress={this.getSaleHistory.bind(this)}/>
                </View>
                <Echarts option={option} height={300}/>
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
    }

}
const RefundStyle = StyleSheet.create({})

module.exports = Refund;

