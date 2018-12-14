/**
 * Created by changePosition on 2018/12/7.
 */
import React, {Component} from 'react';
import {
    Image, TouchableOpacity
} from 'react-native';

export const aaa = '111'
class SavePwdRb extends Component {
//https://blog.csdn.net/weixin_40166364/article/details/77948879

    constructor(props) {
        super(props)
        this.state = ({
            isSaved: false,
            img_arr: [require('../../imgs/slices/login/pwd_save.png'), require('../../imgs/slices/login/pwd_unsave.png')]
        })
    }

    click() {
        this.props.click()
    }

    render() {
        return (
            <TouchableOpacity onPress={this.click.bind(this)}>
                <Image style={{width: 12, height: 12}}
                       source={!this.props.defStatus ? this.state.img_arr[0] : this.state.img_arr[1]}/>
            </TouchableOpacity>
        )
    }
}

class MySavePwdRb extends Component {
//https://blog.csdn.net/weixin_40166364/article/details/77948879

    constructor(props) {
        super(props)
        this.state = ({
            isSaved: false,
            img_arr: [require('../../imgs/slices/login/pwd_save.png'), require('../../imgs/slices/login/pwd_unsave.png')]
        })
    }

    click() {
        this.props.click()
    }

    render() {
        return (
            <TouchableOpacity onPress={this.click.bind(this)}>
                <Image style={{width: 12, height: 12}}
                       source={!this.props.defStatus ? this.state.img_arr[0] : this.state.img_arr[1]}/>
            </TouchableOpacity>
        )
    }
}

export default {
    SavePwdRb,MySavePwdRb
}