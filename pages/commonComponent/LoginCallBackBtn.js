/**
 * Created by changePosition on 2018/12/7.
 */
import React, {Component}from 'react'
import {
    View, StyleSheet, Text, TouchableOpacity
} from 'react-native';
export default class LoginCallBackBtn extends Component {
    click() {
        this.props.click()
    }

    render() {
        return (
            <View style={{alignItems: 'center', marginTop: 20}}>
                {/*关于props，在父组件使用时定义了click，则此处点击事件指明用父组件的那个click*/}
                <TouchableOpacity style={[LcbStyle.login_btn]} onPress={this.click.bind(this)}>
                    <Text style={{color: '#4A95F1'}}>{this.props.title}</Text>
                </TouchableOpacity>
            </View>
        )
    }
}
const LcbStyle = StyleSheet.create({
    login_btn: {
        backgroundColor: '#FFF',
        borderRadius: 17,
        width: 150,
        height: 35,
        justifyContent: 'center',
        alignItems: 'center'
    }
})