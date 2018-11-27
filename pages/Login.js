/**
 * Created by changePosition on 2018/11/21.
 */
import React, {Component} from 'react';
import {
    View, Text, Image, ImageBackground, StyleSheet,
    TextInput, KeyboardAvoidingView, ScrollView, CheckBox,
    Button, Platform, TouchableOpacity
} from 'react-native';
import PropTypes from 'prop-types'
import TabPage from "./TabPage";
import {
    createAppContainer,
    createStackNavigator,
    StackActions,
    NavigationActions,
} from 'react-navigation';
var PDAURL = 'http://172.16.100.158:8080/pdaware/user/rnLogin';
/*
 * 账号密码输入框
 * */
class NamePwdView extends Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <KeyboardAvoidingView style={BgStyle.name_pwd_position} behavior='position'>
                <View style={BgStyle.name_pwd_container}>
                    <Text style={{justifyContent: 'center', color: '#FFF', fontSize: 17}}>账号</Text>
                    <TextInput style={[BgStyle.name_pwd_input_text]}
                               keyboardType={'number-pad'}
                               multiline={false}
                               onChangeText={
                                   this.props.receive_name
                               }
                    />
                </View>
                <View style={BgStyle.name_pwd_container}>
                    <Text style={{justifyContent: 'center', color: '#FFF', fontSize: 17}}>密码</Text>
                    <TextInput style={[BgStyle.name_pwd_input_text]}
                               keyboardType={'number-pad'}
                               secureTextEntry={true}
                               multiline={false}
                               onChangeText={
                                   this.props.receive_pwd
                               }
                    />
                </View>
            </KeyboardAvoidingView>
        )
    }
}

class LoginBtn extends Component {
    click() {
        //调用父组件的click
        this.props.click()
    }

    render() {
        return (
            <View style={{alignItems: 'center', marginTop: 20}}>
                <TouchableOpacity style={[BgStyle.login_btn]} onPress={() => this.click()}>
                    <Text style={{color: '#4A95F1'}}>登录</Text>
                </TouchableOpacity>
            </View>
        )
    }
}

class RedioButton extends Component {
//https://blog.csdn.net/weixin_40166364/article/details/77948879

    constructor(props) {
        super(props)
        this.state = ({
            isSaved: true,
            img_arr: [require('../imgs/slices/login/pwd_save.png'), require('../imgs/slices/login/pwd_unsave.png')]
        })
    }

    click() {
        this.state.isSaved = !this.state.isSaved
        alert(this.state.isSaved)
        // this.props.click(this.state.isSaved)
    }

    render() {
        return (
            <TouchableOpacity onPress={() => {
                this.click.bind(this)
            }}>
                <Image style={{width: 12, height: 12}}
                       source={this.state.isSaved ? this.state.img_arr[0] : this.state.img_arr[1]}/>
            </TouchableOpacity>
        )
    }
}

export class Login extends Component {
    static navigationOptions = ({navigation, screenProps}) => ({
        header: null
    });

    constructor(props) {
        super(props)
        this.state = {
            userName: null,
            userPwd: null,
            isSavePwd: true,
            userInfo: null
        }
    }

    render() {
        return (
            <ImageBackground style={[BgStyle.bg]}>
                <View style={{flexDirection: 'row', justifyContent: 'space-between'}}>
                    <Text style={[BgStyle.versionHelpText]}>Version：P1.0.1</Text>
                    <Text style={[BgStyle.versionHelpText]} onPress={this.helpClick}>帮助</Text>
                </View>
                <View style={[BgStyle.logo]}>
                    <Image source={require('../imgs/slices/login/logo.png')} style={{width: 50, height: 50}}/>
                    <View style={{
                        flexDirection: 'column',
                        justifyContent: 'center',
                        marginLeft: 1
                    }}>
                        <Text style={{color: '#FFFFFF', fontSize: 14, fontWeight: 'bold'}}>上品折扣</Text>
                        <Text style={{color: '#FFFFFF', fontSize: 15, fontWeight: 'bold'}}>SHOPIN</Text>
                    </View>
                </View>
                {/*注意理解下下边的绑定和receive_name：相当于是receive_name交给receiveName处理？*/}
                <NamePwdView receive_name={this.receiveName.bind(this)} receive_pwd={this.receivePwd.bind(this)}/>
                <LoginBtn click={() => this.loginClick()}/>
                <View style={{alignItems: 'center', flexDirection: 'row', justifyContent: 'center', marginTop: 15}}>
                    <RedioButton click={this.savePwd.bind(this)}/>
                    <Text style={{marginLeft: 10, color: '#FFF'}}>记住密码</Text>
                </View>
            </ImageBackground>
        )
    }

    helpClick() {
        alert("Sorry,NO HELP");
    }

    loginClick() {
        // if (this.state.userName == null || this.state.userPwd == null) {
        //     alert("用户密码不能为空！")
        // }
        let formData = new FormData();
        formData.append("userName", "S083779");
        formData.append("password", "3779super");
        formData.append("macAddress", "11-11-11-11-11-11");
        fetch(PDAURL, {
            method: 'POST',
            headers: {},
            body: formData
        }).then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    userInfo: responseData
                });
                if (this.state.userInfo.code == 'SUCCESS') {
                    //登录成功
                    //alert(JSON.stringify(this.state.userInfo.data))
                    this.props.navigation.navigate('TabPage')
                } else {
                    alert(JSON.stringify(this.state.userInfo.codeInfo))
                }

            })
    }

    receiveName(name) {
        //获取子控件传过来的值
        this.state.userName = name
    }

    receivePwd(pwd) {
        this.state.userPwd = pwd
    }

    savePwd(isSavePwd) {
        this.state.isSavePwd = isSavePwd
        alert(this.state.isSavePwd ? '保存密码' : '不保存密码')
    }

}

const BgStyle = StyleSheet.create({
    bg: {
        flex: 1,
        backgroundColor: '#4A95F1'
    },
    whiteColor: {
        color: '#000'
    },
    versionHelpText: {
        fontSize: 15,
        color: '#FFFFFF',
        marginLeft: 15,
        marginRight: 15,
        marginTop: 20
    },
    logo: {
        flexDirection: 'row',
        justifyContent: 'center',
        marginTop: 90
    },
    name_pwd_position: {
        flexDirection: 'row',
        justifyContent: 'center',
        marginTop: 125
    },
    name_pwd_input_text: {
        textAlign: 'right',
        color: '#FFFFFF',
        width: 180,
        height: 50,
    },
    name_pwd_container: {
        flexDirection: 'row',
        alignItems: 'center',
        borderBottomWidth: 1,
        borderBottomColor: '#FFF',
        marginBottom: 10
    },
    login_btn: {
        backgroundColor: '#FFF',
        borderRadius: 17,
        width: 150,
        height: 35,
        justifyContent: 'center',
        alignItems: 'center'
    }
})
module.exports = Login;
