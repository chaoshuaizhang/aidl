/**
 * Created by changePosition on 2018/11/28.
 */

import React, {Component} from "react";

//定义一个常量（值）,这里写export关键字的目的是为了在别的js中直接使用
//如果这里不使用export，则上边无法直接import {SERVER_URL}，
//即使在export default中导出了SERVER_URL，仍不行(这里的不行指的是 import {SERVER_URL})。
export const SERVER_URL = 'http://172.16.100.158:8080/pdaware/'

//定义一个常量（对象），里边有多个属性（属性值是可以改变的）
//GlobalInfo不可改变，但其中的属性可以修改，可以用来存储一些全局可变的参数，如 用户信息
const GlobalInfo = {
    userInfo: null,
    globalStatus: true
}

export default {
    GlobalInfo
}
