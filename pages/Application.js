/**
 * Created by changePosition on 2018/11/25.
 */

import {createAppContainer, createStackNavigator, StackActions, NavigationActions} from 'react-navigation';
import React from 'react';

const Login = require('./Login')
const TabPage = require('./TabPage');

const Application = createStackNavigator({

    Login: {
        screen: Login,
    },
    TabPage: {
        screen: TabPage,
    }
}, {initialRouteName: 'Login'});

export default createAppContainer(Application);