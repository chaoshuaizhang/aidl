/**
 * Created by changePosition on 2018/11/25.
 */

import React, {Component} from "react";
import {StyleSheet, Image} from "react-native";
import TabNavigator from "react-native-tab-navigator";
import My from "./My";
import MenuRouter from "./MenuRouter";
import ItemMenu2 from "./itemPage/ItemMenu2";

const TAB_TITLE_MY = '我的';
const TAB_TAG_MY = 'my';
const TAB_ICON_MY_NORMAL = require('../imgs/slices/tab/my.png');
const TAB_ICON_MY_SELECTED = require('../imgs/slices/tab/my_ed.png');
const TAB_TITLE_SET = '首页';
const TAB_TAG_SET = 'set';
const TAB_ICON_SET_NORMAL = require('../imgs/slices/tab/set.png');
const TAB_ICON_SET_SELECTED = require('../imgs/slices/tab/set_ed.png');


export class TabPage extends Component {
    static navigationOptions = ({navigation, screenProps}) => ({
        header: null
    });

    constructor(props) {
        super(props)

        this.state = {
            //默认选中的
            selectedTab: TAB_TAG_MY
        }
    }

    render() {
        return (
            <TabNavigator tabBarStyle={TabStyle.tab_container} style={{flex:1}}>
                {this.renderTabItem(TAB_TAG_MY, TAB_TITLE_MY, TAB_ICON_MY_NORMAL, TAB_ICON_MY_SELECTED)}
                {this.renderTabItem(TAB_TAG_SET, TAB_TITLE_SET, TAB_ICON_SET_NORMAL, TAB_ICON_SET_SELECTED)}
            </TabNavigator>
        )
    }

    renderTabItem(tabTag, tabTitle, iconNormal, iconSelected) {
        return (
                <TabNavigator.Item
                    marginBottom={100}
                    selected={this.state.selectedTab === tabTag}
                    title={tabTitle}
                    titleStyle={TabStyle.tab_title}
                    selectedTitleStyle={TabStyle.tab_title_selected}
                    renderIcon={() => <Image source={iconNormal} style={TabStyle.tab_icon}/>}
                    renderSelectedIcon={() => <Image source={iconSelected}
                                                     style={TabStyle.tab_icon}/>}
                    onPress={() => this.setState({selectedTab: tabTag})}>
                    {this.getItemPage(tabTag)}
                </TabNavigator.Item>
        );
    };

    getItemPage(tabTag) {

        switch (tabTag) {
            case TAB_TAG_MY:
                return (<My/>);
            case TAB_TAG_SET:
                //为了能在ItemMenu2中跳转(使用this.props.navigation)
                return (<ItemMenu2 {...this.props}/>);
        }

    };

}

const TabStyle = StyleSheet.create({
    tab_container: {
        height: 50,
    },
    tab_icon: {
        width: 30,
        height: 30,
        resizeMode: 'center',
    },
    tab_title: {
        color: "#929292",
        fontSize: 8,
    },
    tab_title_selected: {
        color: "#FF0000",
        fontSize: 8,
    }
})
//此处可改成别的，后期再说吧
module.exports = TabPage;