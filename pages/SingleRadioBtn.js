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
//自定义圆形checkBox
export default class SingleRadioBtn extends Component {

    constructor(props) {
        super(props);
        this.state = {pressStatus: 'normal'};
    }

    _onPress() {
        if (this.state.pressStatus == 'pressin') {
            this.setState({pressStatus: 'normal'});
        } else
            this.setState({pressStatus: 'pressin'});
        this.props.setSaved(this.state.pressStatus)
    }

    render() {
        return (
            <TouchableOpacity
                onPress={
                    this._onPress.bind(this)
                }
                activeOpacity={1}>
                <Image
                    source={this.state.pressStatus == 'pressin' ?
                        require('../imgs/slices/login/pwd_save.png') :
                        require('../imgs/slices/login/pwd_unsave.png')}
                    style={{
                        height: 33,
                        borderRadius: 5
                    }}/>
                <Text style={{color: 'white', fontSize: 13, textAlign: 'center', alignSelf: 'center', marginTop: 10}
                }>登录</Text>
            </TouchableOpacity>
        )
    }
}
