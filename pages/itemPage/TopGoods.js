/**
 * Created by changePosition on 2018/11/27.
 * 日历
 */
import React from 'react';
import {View, Text, Button, ViewPagerAndroid} from 'react-native';
import DatePicker from '../commonComponent/DatePicker'
export default class TopGoods extends React.Component {

    //
    static navigationOptions = ({navigation}) => {
        return {
            title: navigation.getParam('otherParam', 'A Nested Details Screen')
        };
    };

    constructor(props) {
        super(props)
        this.state = ({})
    }

    render() {
        return (
            <View style={{flex: 1}}>
                {/*这里需要用bind，否则下边无法使用this.props.navigation，应该是 ‘this’ 不同*/}
                <Button title={"打开日历"} onPress={this.popDatePicker.bind(this)}/>
            </View>
        );
    }

    popDatePicker() {
        global.showDatePicker()
        // 不用导航了，导航相当于是跳转到了新的界面，那样的话就不是覆盖效果，而是新页面效果
        // this.props.navigation.push('DatePicker', {
        //     itemId: Math.floor(Math.random() * 100),
        //     desc: '日历'
        // })
    }
}

