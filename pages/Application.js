/**
 * Created by changePosition on 2018/11/25.
 */

import {createAppContainer, createStackNavigator, StackActions, NavigationActions} from 'react-navigation';
import React, {Component} from 'react';
import {View} from 'react-native';
/*在需要关闭启动页的js文件中引入*/
import SplashScreen from 'react-native-splash-screen'

// const Login = require('./Login')
// const TabPage = require('./TabPage');
// const Sale = require('./itemPage/Sale');
// const Refund = require('./itemPage/Refund');

import LoadingDialog from './commonComponent/LoadingDialog'
import DatePicker from './commonComponent/DatePicker'
import Router from './MyRouter'
let self; //将App组件中的this赋给全局的self

// global.showLoading = false; //所有子页面均可直接调用global.showLoading()来展示Loading
// global.closeLoading = false; //所有子页面均可直接调用global.closeLoading()来关闭Loading
// global.toast = false; //所有子页面均可直接调用global.toast("")来吐司提示消息


export default class SetUp extends Component {

    constructor(props) {
        super(props);
    }

    /*
     * 组件将要被加载到视图之前调用
     * 定义几个方法（关于global哪来的？不知道）
     * */
    componentDidMount() {
        self = this;
        global.showLoading = function () {
            self.LoadingDialog.show();
        };
        global.closeLoading = function () {
            self.LoadingDialog.close();
        };

        global.showDatePicker = function (year, month, day, cb) {
            self.DatePicker.show(year, month, day, cb);
        };
        global.closeDatePicker = function () {
            self.DatePicker.close();
        };
        global.toast = function (message) {
            self.refs.toast.show(message);
        };
    }

    render() {
        return (
            <View style={[{flex: 1}]}>
                <Router onNavigationStateChange={this.handleNavigationChange}/>

                {/*这里的作用就是先让modal先加载，但是不显示*/}
                <LoadingDialog ref={r => {
                    this.LoadingDialog = r
                }} hide={true}/>

                <DatePicker ref={dp => {
                    this.DatePicker = dp
                }} hide={true}
                />

            </View>
        );
    }

    handleNavigationChange(prevState, newState, action) {
        console.log("---" + JSON.stringify(prevState))
        console.log("+++" + JSON.stringify(newState))
        console.log("===" + JSON.stringify(action))
    }
}