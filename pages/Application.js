/**
 * Created by changePosition on 2018/11/25.
 */

import {createAppContainer, createStackNavigator, StackActions, NavigationActions} from 'react-navigation';
import React from 'react';

const Login = require('./Login')
const TabPage = require('./TabPage');
const Sale = require('./itemPage/Sale');
const Refund = require('./itemPage/Refund');

const Application = createStackNavigator({

    Login: {
        screen: Login,
    },
    TabPage: {
        screen: TabPage,
    },
    Sale: {
        screen: Sale
    },
    Refund: {
        screen: Refund
    }
}, {initialRouteName: 'Login'});

export default createAppContainer(Application);