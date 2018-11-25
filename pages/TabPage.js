/**
 * Created by changePosition on 2018/11/25.
 */


import React, {Component} from 'react';
import {View, Image, Text, StyleSheet} from 'react-native';
import {TabNavigator} from 'react-navigation';
const TAB_TITLE_MY = '我的';
const TAB_TAG_MY = 'my';
const TAB_ICON_MY_NORMAL = require('../imgs/slices/tab/my.png');
const TAB_ICON_MY_SELECTED = require('../imgs/slices/tab/my_ed.png');
const TAB_TITLE_SET = '主页';
const TAB_TAG_SET = 'menu';
const TAB_ICON_SET_NORMAL = require('../imgs/slices/tab/set.png');
const TAB_ICON_SET_SELECTED = require('../imgs/slices/tab/set_ed.png');
import My from "./My";
import Menu from "./Menu";

const TabPage = TabNavigator({
    Menu: {
        screen: Menu,
        navigationOptions: {
            tabBarLabel: TAB_TITLE_SET,
            tabBarIcon: ({tintColor}) => (
                <Image source{require('../imgs/slices/tab/set.png')} style={[mystyles.icon, {tintColor: tintColor}]}/>
            )
        }
    },
    My: {
        screen: My,
        navigationOptions: {
            tabBarLabel: TAB_TITLE_MY,
            tabBarIcon: ({tintColor}) => (
                <Image source{require('../imgs/slices/tab/my_ed.png')} style={[mystyles.icon, {tintColor: tintColor}]}/>
            )
        }
    }
})
export default TabPage

const mystyles = StyleSheet.create({
    icon: {
        width: 30,
        height: 26,
    },
});