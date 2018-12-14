/**
 * Created by changePosition on 2018/11/27.
 */
import React from 'react';
import {View, Text, FlatList, ScrollView, TouchableOpacity} from 'react-native';

class MyTable extends React.Component {
    //1. table的主体时可滑动的
    //2. 第一列是固定的

    constructor(props) {
        super(props)
        this.state = {
            tables: [{
                title: '行1',
                jine: '253.12',
                yusuan: '456.75',
                liandailv: '56.56',
                tongbi: '456.78',
                shuliang: '8903'
            },
                {
                    title: '行2',
                    jine: '652.98',
                    yusuan: '121.43',
                    liandailv: '789.456',
                    tongbi: '456.3456',
                    shuliang: '6783'
                },
                {
                    title: '行3',
                    jine: '123.09',
                    yusuan: '56743.543',
                    liandailv: '346.4',
                    tongbi: '476.373',
                    shuliang: '1748'
                },
                {
                    title: '行4',
                    jine: '970.87',
                    yusuan: '457.456',
                    liandailv: '567.34',
                    tongbi: '854.345',
                    shuliang: '4324'
                }
            ],
            headers: ["金额（万）", "预算", "连带率", "同比", "数量"]
        }
    }

    render() {
        //得到第一列的标题
        let titleArr = [];
        this.state.tables.map((item, i) => {
            titleArr[i] = {tit: item.title}
            delete item.title
        })
        let biaotou = [{tit: '合计'}]
        biaotou.push(...titleArr)
        console.log(JSON.stringify(biaotou))
        return (
            <View style={{flexDirection: 'row'}}>
                <View style={{width: 75}}>
                    <FlatList
                        data={biaotou}
                        //一定要注意下边的写法，特别是括号
                        renderItem={({item}) => <Text
                            style={{
                                backgroundColor: '#CCC',
                                color: '#000',
                                fontSize: 20,
                                height: 40
                            }}>{item.tit}</Text>}
                    />
                </View>
                <ScrollView horizontal>
                    <View>
                        <View style={{height: 40, flexDirection: 'row'}}>
                            {this.state.headers.map((headerItem) => (
                                <Text
                                    style={{
                                        borderBottomWidth: 1,
                                        borderRightWidth: 1,
                                        width: 100,
                                        color: '#FF0000',
                                        fontSize: 20
                                    }}>{headerItem}</Text>
                            ))}
                        </View>
                        <FlatList
                            data={this.state.tables}
                            renderItem={item => this.rengerTableItem(item)}
                        />
                    </View>
                </ScrollView>
            </View>
        )
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
                            style={{borderBottomWidth: 1, borderRightWidth: 1, borderColor: '#CCCCCC', height: 40}}>
                            <Text style={{width: 100, fontSize: 20}}>{n}</Text>
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
            <MyTable/>
        );
    }
}

