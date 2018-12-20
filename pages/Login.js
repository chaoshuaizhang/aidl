/**
 * Created by changePosition on 2018/11/21.
 */
import React, {Component} from 'react';
import {
    View, Text, Image, ImageBackground, StyleSheet,
    TextInput, KeyboardAvoidingView, ScrollView, CheckBox,
    Button, Platform, TouchableOpacity, ProgressBarAndroid, ToastAndroid, Modal
} from 'react-native';
import {StackActions, NavigationActions} from 'react-navigation';
import TabPage from "./TabPage";
import LoginCallBackBtn from './commonComponent/LoginCallBackBtn'
import MyRB from './commonComponent/SavePwdRb'
import {SERVER_URL} from '../Constants'
import Cans from '../Constants'
import SplashScreen from 'react-native-splash-screen'
const PDAURL = SERVER_URL + 'user/rnLogin';
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

export class Login extends Component {
    static navigationOptions = ({navigation, screenProps}) => ({
        header: null
    });

    constructor(props) {
        super(props)
        this.state = {
            userName: null,
            userPwd: null,
            isSavePwd: false,
            userInfo: null,
            isLogining: false
        }
    }

    render() {
        return (
            <ImageBackground style={[BgStyle.bg]}>
                <Modal
                    animationType="none"
                    transparent={true}
                    visible={this.state.isLogining}
                    onRequestClose={() => {
                        //this.setModalVisible(!this.state.isLogining);
                    }}>
                    <View style={{flex: 1, justifyContent: 'center'}}>
                        <ProgressBarAndroid/>
                    </View>
                </Modal>
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
                {/*注意理解下下边的绑定和receive_name：相 当于是receive_name交给receiveName处理？*/}
                <NamePwdView receive_name={this.receiveName.bind(this)} receive_pwd={this.receivePwd.bind(this)}/>
                {/*子组件需要用到父组件的props，所以需要传过去？*/}
                <LoginCallBackBtn click={() => this.loginClick()} title="登陆啦"/>
                <View style={{alignItems: 'center', flexDirection: 'row', justifyContent: 'center', marginTop: 15}}>
                    <MyRB.MySavePwdRb defStatus={this.state.isSavePwd} click={() => this.savePwd()}/>
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
        //     alert("用户密码不能为空！"+PDAURL)
        // }
        //global.showLoading();
        let resetAction = StackActions.reset({
            index: 0,
            actions: [NavigationActions.navigate({routeName: 'TabPage'})],
        });
// this.props.navigation.navigate('TabPage')
        this.props.navigation.dispatch(resetAction)
        return
        // this.setState({
        //     isLogining: true
        // })
        let formData = new FormData();
        formData.append("userName", "S083779");
        formData.append("password", "3779super");
        formData.append("macAddress", "11-11-11-11-11-11");

        //Post请求时使用
        let userInfo = {
            "userName": "S083262",
            "password": "3262super",
            "macAddress": "11-11-11-11-11-11"
        }
        //失败
        // HttpUtil.fetchGet('user/rnLogin', formData).then((responseData) => {
        //     responseData.json().then((responseData) => {
        //         this.setState({
        //             userInfo: responseData
        //         });
        //         if (this.state.userInfo.code == 'SUCCESS') {
        //             //登录成功
        //             // alert(JSON.stringify(this.state.userInfo.data))
        //             Cans.userInfo = this.state.userInfo.data
        //             this.props.navigation.navigate('TabPage')
        //         } else {
        //             alert(JSON.stringify(this.state.userInfo.codeInfo))
        //         }
        //     }).catch((err) => {
        //         alert(err)
        //     })
        // })

        //成功
        const promise = new Promise(function (resolve, reject) {
            fetch(PDAURL, {
                method: 'POST',
                headers: {},
                body: formData
            }).then((response) => {
                if (response.ok) {
                    resolve(response);
                } else {
                }
            }).catch((err) => {
                reject('网络异常，请检查网络后再试。');
            })
        });
        promise.then((response) => response.json()).then((responseData) => {
            this.setState({
                userInfo: responseData
            });
            if (this.state.userInfo.code == 'SUCCESS') {
                //登录成功
                Cans.userInfo = this.state.userInfo.data
                let resetAction = StackActions.reset({
                    index: 0,
                    actions: [NavigationActions.navigate({routeName: 'TabPage'})],
                });
                // this.props.navigation.navigate('TabPage')
                this.props.navigation.dispatch(resetAction)
            } else {
                alert("FAIL：" + JSON.stringify(this.state.userInfo))
            }
        }).catch((err) => {
            alert("异常：" + err);
        }).finally(() => {
            global.closeLoading();
            // this.setState({
            //     isLogining: false
            // });
        })

        // fetch(PDAURL, {
        //     method: 'POST',
        //     headers: {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json',
        //     },
        //     body: formData
        // }).then((response) => response.json())
        //     .then((responseData) => {
        //         this.setState({
        //             userInfo: responseData
        //         });
        //         if (this.state.userInfo.code == 'SUCCESS') {
        //             //登录成功
        //             // alert(JSON.stringify(this.state.userInfo.data))
        //             Cans.userInfo = this.state.userInfo.data
        //             this.props.navigation.navigate('TabPage')
        //         } else {
        //             alert(JSON.stringify(this.state.userInfo.codeInfo))
        //         }
        //     })
    }

    receiveName(name) {
        //获取子控件传过来的值
        this.state.userName = name
    }

    receivePwd(pwd) {
        this.state.userPwd = pwd
    }

    savePwd() {
        this.setState({
            isSavePwd: !this.state.isSavePwd
        })
        alert(this.state.isSavePwd ? '保存密码' : '不保存密码')
    }

    renderLoading() {
        if (this.state.isLogining) {
            return (
                <View style={{justifyContent: 'center', flex: 1, alignSelf: 'center'}}>
                    <ProgressBarAndroid />
                </View>
            );
        } else {
            return null
        }
    }

    componentDidMount() {
        SplashScreen.hide()
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
// module.exports = Login;
export default Login
