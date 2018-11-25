/**
 * Created by changePosition on 2018/11/21.
 */

'use strict';
import React, {Component} from 'react';
import {
    View, Text, Image, ImageBackground, StyleSheet,
    TextInput, KeyboardAvoidingView, ScrollView, CheckBox,
    Button, Platform, TouchableOpacity
} from 'react-native';

//点击按钮时，改变按钮的背景色
export default class UpdateButtonStatus extends Component {

    constructor(props) {
        super(props);
        this.state = {pressStatus: 'normal'};
    }

    _onPressIn() {
        this.setState({pressStatus: 'pressin'});
    }

    _onPressOut() {
        this.setState({pressStatus: 'normal'});
    }

    render() {
        return (
            <TouchableOpacity
                onPressIn={this._onPressIn.bind(this)}
                onPressOut={this._onPressOut.bind(this)}
                activeOpacity={1}>
                <Image
                    style={{
                        height: 33,
                        borderRadius: 5,
                        backgroundColor: this.state.pressStatus == 'pressin' ?
                            '#AAA' :
                            '#00A'
                    }}/>
                <Text style={{color: 'white', fontSize: 13, textAlign: 'center', alignSelf: 'center', marginTop: 10}
                }>登录</Text>
            </TouchableOpacity>
        )
    }
}