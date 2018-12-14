/**
 * Created by changePosition on 2018/12/9.
 */
import React, {Component} from 'react';
import {View, Text, Button} from 'react-native'
//这样导入的话，Constants中必须有一个同名的SERVER_URL
import {SERVER_URL} from '../../Constants'
//导入的是 export default导出的那些属性
import MyDefaultCans from '../../Constants'
//全量导入（确保导入的属性已经被全部export了，否则这里拿不到）
import * as Cans from '../../Constants'
export default class MyTestImportExport extends Component {
    constructor(props) {
        super(props)
        this.state = {
            //改变状态后，才会重新渲染
            change: false
        }
    }

    render() {
        return (
            <View>
                <Text>{SERVER_URL}</Text>
                <Text>{MyDefaultCans.ABCD}</Text>
                {/*这里就获取不到值了*/}
                {/*<Text>{MyDefaultCans.EFGH}</Text>*/}
                <Text>{Cans.EFGH}</Text>
                {/*这里也获取不到值*/}
                {/*<Text>{Cans.ABCD}</Text>*/}
                <Text>{Cans.SERVER_URL}</Text>
                <Text>{MyDefaultCans.GlobalInfo.userInfo}</Text>
                {/*因为：GlobalInfo不是export的，这里就无法使用咯，所以.userInfo是.不出来，指向指不到，运行时就错了 */}
                {/*<Text>{Cans.GlobalInfo.userInfo}</Text>*/}
                <Button title={'点击'} onPress={this.btnClick.bind(this)}/>
            </View>
        )
    }

    btnClick() {
        //这个属性名，压根儿不存在，Why？
        MyDefaultCans.ABCD = '这个根本不存在'
        MyDefaultCans.GlobalInfo.userInfo = '用户信息'
        //这个属性名，也压根儿不存在，Why？
        Cans.EFGH = '也不存在'
        //以下的修改会报错，提示不能修改常量
        // Cans.GlobalInfo = '不可修改'
        //那么为什么下边的就可以修改呢？
        //两者的不同就是：一个是普通导入，一个是export default导出对应的导入。
        //我的理解是：导出的是default这个属性，这个属性不可以改，但是其下的属性，方法是可以修改的
        //MyDefaultCans.GlobalInfo = '可修改'
        this.setState({
            change: !this.state.change
        })
    }
}
