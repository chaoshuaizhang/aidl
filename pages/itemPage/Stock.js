/**
 * Created by changePosition on 2018/11/27.
 */
import React from 'react';
import {View, Text, Image, FlatList, ScrollView, TouchableOpacity, StyleSheet} from 'react-native';

class MyTable extends React.Component {
    //1. table的主体时可滑动的
    //2. 第一列是固定的

    constructor(props) {
        super(props)
        this.state = {
            tables: [{
                title: '行1', shopSid: '1001',
                jine: '253.12',
                yusuan: '456.75',
                liandailv: '56.56',
                tongbi: '456.78',
                shuliang: '8903'
            },
                {
                    title: '行2', shopSid: '1002',
                    jine: '652.98',
                    yusuan: '121.43',
                    liandailv: '789.456',
                    tongbi: '456.3456',
                    shuliang: '6783'
                },
                {
                    title: '行3', shopSid: '1003',
                    jine: '123.09',
                    yusuan: '56743.543',
                    liandailv: '346.4',
                    tongbi: '476.373',
                    shuliang: '1748'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                }, {
                    title: '行1', shopSid: '1001',
                    jine: '253.12',
                    yusuan: '456.75',
                    liandailv: '56.56',
                    tongbi: '456.78',
                    shuliang: '8903'
                },
                {
                    title: '行2', shopSid: '1002',
                    jine: '652.98',
                    yusuan: '121.43',
                    liandailv: '789.456',
                    tongbi: '456.3456',
                    shuliang: '6783'
                },
                {
                    title: '行3', shopSid: '1003',
                    jine: '123.09',
                    yusuan: '56743.543',
                    liandailv: '346.4',
                    tongbi: '476.373',
                    shuliang: '1748'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                }, {
                    title: '行1', shopSid: '1001',
                    jine: '253.12',
                    yusuan: '456.75',
                    liandailv: '56.56',
                    tongbi: '456.78',
                    shuliang: '8903'
                },
                {
                    title: '行2', shopSid: '1002',
                    jine: '652.98',
                    yusuan: '121.43',
                    liandailv: '789.456',
                    tongbi: '456.3456',
                    shuliang: '6783'
                },
                {
                    title: '行3', shopSid: '1003',
                    jine: '123.09',
                    yusuan: '56743.543',
                    liandailv: '346.4',
                    tongbi: '476.373',
                    shuliang: '1748'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                },
                {
                    title: '行4', shopSid: '1004',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                }
            ],
            headers: ["金额（万）", "预算", "连带率", "同比", "数量"],
            mainTitle: '合计',
            jine_upsort: false,
            yusuan_upsort: true,
            titles: null,
            poiY: 0.0,
            leftTouch: false,
            rightTouch: false
        }

    }

    getDefaultProps() {
        console.log("------------------------------------getDefaultProps")
    }

    getInitialState() {
        console.log("------------------------------------getInitialState")
    }

    componentWillMount() {
        //根据已有数据设置标题
        let titlesTmp = [];
        this.state.tables.map((item, i) => {
            titlesTmp[i] = {tit: item.title, shopSid: item.shopSid}
            delete item.title
            delete item.shopSid
        })
        // let titles = [{tit: this.state.mainTitle, shopSid: 1000}]
        let titles = []
        titles.push(...titlesTmp)
        this.setState({
            titles: titles
        })
        console.log("------------------------------------componentWillMount")
    }

    shouldComponentUpdate() {
        console.log("------------------------------------shouldComponentUpdate")
        return true
    }

    componentWillUpdate() {
        console.log("------------------------------------componentWillUpdate")
    }

    componentDidUpdate() {
        console.log("------------------------------------componentDidUpdate")
    }

    render() {
        console.log("-------------------------------------render-----------")
        return (
            <View style={{flexDirection: 'row'}}>
                <View style={{width: 100}}>
                    <Text
                        style={[TableStyle.textStyle, TableStyle.textContainerStyle]
                        }>{this.state.mainTitle}</Text>
                    <FlatList
                        data={this.state.titles}
                        //一定要注意下边的写法，特别是括号
                        renderItem={({item}) => this.renderTitleItem(item)}
                        onScroll={this.myLeftScroll.bind(this)}
                        ref="leftFlatRef"
                        onTouchStart={() => {
                            this.setState({leftTouch: true, rightTouch: false})
                        }}
                    />
                </View>
                <ScrollView horizontal>
                    <View>
                        <View style={{flexDirection: 'row'}}>
                            {this.state.headers.map((headerItem) => (
                                <TouchableOpacity style={[TableStyle.textContainerStyle, {flexDirection: 'row'}]}
                                                  activeOpacity={0.7} onPress={() => this.sortClick(headerItem)}>
                                    <Text
                                        style={[TableStyle.textStyle, {color: '#FF0000'}]}>{headerItem}</Text>
                                    {this.sortIconSet(headerItem)}
                                </TouchableOpacity>
                            ))}
                        </View>
                        <FlatList
                            data={this.state.tables}
                            renderItem={item => this.rengerTableItem(item)}
                            ref={rightFlatRef => this.rightFlatRef = rightFlatRef}
                            onScroll={this.myRightScroll.bind(this)}
                            onTouchStart={() => {
                                this.setState({leftTouch: false, rightTouch: true})
                            }}
                            // 关于设置getItemLayout后，如何使用？
                            // getItemLayout={(data, index) => {
                            //     let length = 40;
                            //     let totalOffset = 0;
                            //     for (let i = 0; i < index; i++) {
                            //         totalOffset += 40;
                            //     }
                            //     // console.log('当前偏移 =' + totalOffset);
                            //     return {length: length, offset: totalOffset, index: index};
                            // }}
                        />
                    </View>
                </ScrollView>
            </View>
        )
    }

    renderTitleItem(titleItem) {
        return (
            <TouchableOpacity style={TableStyle.textContainerStyle} onPress={() => {
                this.props.titleClick(titleItem)
            }}>
                <Text
                    style={[TableStyle.textStyle]
                    }>{titleItem.tit}</Text>
            </TouchableOpacity>
        )
    }

    sortIconSet(item) {
        console.log("-------------------------------------------sortIconSet---------------" + this.state.jine_upsort)
        if (item == '金额（万）')
            return (
                <Image
                    source={this.state.jine_upsort ? require('../../imgs/slices/downsort_icon.png')
                        : require('../../imgs/slices/upsort_icon.png')}
                    style={{width: 10, height: 15}}></Image>
            )
        if (item == '预算')
            return (
                <Image
                    source={this.state.yusuan_upsort ? require('../../imgs/slices/downsort_icon.png')
                        : require('../../imgs/slices/upsort_icon.png')}
                    style={{width: 10, height: 15}}></Image>
            )
    }

    sortClick(item) {
        if (item == '金额（万）') {
            this.setState({
                jine_upsort: !this.state.jine_upsort
            })
            return
        }
        if (item == '预算') {
            this.setState({
                yusuan_upsort: !this.state.yusuan_upsort
            })
            return
        }
    }

    myLeftScroll(event) {
        //重新设置state
        // this.setState({
        //     poiY: event.nativeEvent.contentOffset.y
        // }, () => {
        //     //写在这里的原因是：修改state后在外边无法立马获取到修改后的值
        //     //这个animated=true,false没用啊，效果一样
        //     console.log("--------------------回调滑动后" + this.state.poiY)
        //     this.rightFlatRef.scrollToOffset({animated: true, offset: this.state.poiY})
        // })
        //上述耗时，直接滑动吧
        if (!this.state.rightTouch)
            this.rightFlatRef.scrollToOffset({animated: false, offset: event.nativeEvent.contentOffset.y})
    }


    myRightScroll(event) {
        if (!this.state.leftTouch)
            this.refs.leftFlatRef.scrollToOffset({animated: false, offset: event.nativeEvent.contentOffset.y})
    }

    // titleClick(){
    //
    // }

    renderHeaderItem() {
    }

    rengerTableItem(arrItem) {
        let vArr = Object.values(arrItem.item)
        let kArr = Object.keys(arrItem.item)
        let items = arrItem.item
        return (
            <View style={{flex: 1}}>
                <View
                    style={{flexDirection: 'row'}}>
                    {vArr.map((n, i) => (
                        <TouchableOpacity
                            style={TableStyle.textContainerStyle}>
                            <Text style={TableStyle.textStyle}>{n}</Text>
                        </TouchableOpacity>
                    ))}
                </View>
            </View>
        )
    }

    /*<Text>{JSON.stringify(arrItem.item.q)}</Text>*/
}

export default class DetailsScreen extends React.Component {
    static navigationOptions = ({navigation}) => {
        return {
            title: navigation.getParam('otherParam', 'A Nested Details Screen'),
        };
    };

    render() {
        return (
            <MyTable titleClick={this.titleClick.bind(this)}/>
        );
    }

    titleClick(item) {
        alert(item.shopSid + "  " + item.tit)
    }

    headerClick(item) {
    }
}

const TableStyle = StyleSheet.create({
    textContainerStyle: {
        width: 100,
        borderBottomWidth: 1,
        borderRightWidth: 1,
        borderColor: '#CCCCCC',
        height: 40,
        justifyContent: 'center'
    },
    textStyle: {
        textAlign: 'center',
        /*
         * 设置了justifyContent: 'center'后，水平竖直都居中
         * 但是加了flexDirection: 'row'后，就只能水平居中了
         * */
        textAlignVertical: 'center',
        fontSize: 20
    },
    titleTextStyle: {
        color: '#000',
        fontSize: 20,
        height: 40,
        textAlign: 'center',
        textAlignVertical: 'center'
    }
})


