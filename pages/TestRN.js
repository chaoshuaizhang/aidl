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
import UpdateBtnBgStatus from './UpdateBtnBgStatus'
import SingleRadioBtn from './SingleRadioBtn'
//
// //点击按钮时，改变按钮的背景色
// class UpdateButtonStatus extends Component {
//
//     constructor(props) {
//         super(props);
//         this.state = {pressStatus: 'normal'};
//     }
//
//     myClick(status) {
//          alert(status)
//      }
//     //
//     // _onPressOut() {
//     //     this.setState({pressStatus: 'normal'});
//     // }
//
//     render() {
//         return (
//             <TouchableOpacity
//                 // onPressIn={this._onPressIn.bind(this)}
//                 // onPressOut={this._onPressOut.bind(this)}
//                 onPress={
//                     this._onPress.bind(this)
//                 }
//                 setSaved={()=>{this.myClick()}}
//                 activeOpacity={1}>
//                 <Image
//                     source={this.state.pressStatus == 'pressin' ?
//                         require('../imgs/slices/login/pwd_save.png') :
//                         require('../imgs/slices/login/pwd_unsave.png')}
//                     style={{
//                         height: 33,
//                         borderRadius: 5,
//                         // backgroundColor:this.state.pressStatus == 'pressin' ?
//                         //     '#AAA' :
//                         //     '#00A'
//                     }}/>
//                 <Text style={{color: 'white', fontSize: 13, textAlign: 'center', alignSelf: 'center', marginTop: 10}
//                 }>登录</Text>
//             </TouchableOpacity>
//         )
//     }
// }
//
export default class LoginBackgroundImg extends Component {
    constructor(props) {
        super(props)
        this.state = {
            userName: null,
            userPwd: null,
            isSavePwd: true
        }
    }

    render() {
        return (
            <View>
                <UpdateBtnBgStatus pressStatus='normal'/>
                <SingleRadioBtn pressStatus='normal' setSaved={() => {
                    this.myClick()
                }}/>
            </View>
        )
    }

    setSaved(status) {
        alert(status)
    }
    myClick(status) {
        alert(status)
    }
}

const BgStyle = StyleSheet.create({
    bg: {
        flex: 1,
        backgroundColor: '#4A95F1'
    }
})
