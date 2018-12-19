/**
 * Created by changePosition on 2018/11/27.
 * 日历
 */
import React from 'react';
import {View, Text, ViewPagerAndroid} from 'react-native';
export default class TopGoods extends React.Component {
    static navigationOptions = ({navigation}) => {
        return {
            title: navigation.getParam('otherParam', 'A Nested Details Screen'),
        };
    };

    constructor(props) {
        super(props)
        this.state = ({
            // pageSise: Number.MIN_SAFE_INTEGER
            pageSise: 10,
            headers: ["金额（万）", "预算", "连带率", "同比", "数量"]
        })
    }

    render() {
        return (
            <View style={{flex: 1}}>
                <ViewPagerAndroid
                    style={{flex: 1}}
                    initialPage={0}>
                    {
                        this.renderPagerItem
                        // this.state.headers.map((h, i) => (
                        //     <Text>{h}</Text>
                        // ))
                    }
                </ViewPagerAndroid>
            </View>
        );
    }

    renderPagerItem() {
        // console.log("开始初始化ViewPager页面")
        // let arr = []
        // for (let i = 0, size = this.state.pageSise; i < size; i++) {
        //     arr.push(
        //         <Text>第{i}页哈哈哈</Text>
        //     )
        //     console.log('第  ' + i + '  页')
        // }
        return (
            this.state.headers.map((h, i) => (
                <Text>{h}</Text>
            ))
        )
    }
}

