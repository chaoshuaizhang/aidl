/**
 * Created by changePosition on 2018/11/27.
 */
import React, {Component} from 'react';
import {createStackNavigator, createAppContainer} from 'react-navigation';

import ItemMenu2 from './itemPage/ItemMenu2'
import Sale from '../pages/itemPage/Sale'

const RootStack = createStackNavigator(
    {
        ItemMenu2: ItemMenu2,
        Sale: Sale,
    },
    {
        initialRouteName: 'ItemMenu2',
        defaultNavigationOptions: {
            headerStyle: {
                backgroundColor: '#f4511e',
            },
            headerTintColor: '#fff',
            headerTitleStyle: {
                fontWeight: 'bold',
            },
        },
    }
);
const AppContainer = createAppContainer(RootStack);

export default class MenuRouter extends React.Component {
    render() {
        return <AppContainer/>;
    }
}
