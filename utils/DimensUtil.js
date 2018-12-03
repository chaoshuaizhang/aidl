/**
 * Created by changePosition on 2018/11/29.
 */
import {Dimensions} from 'react-native';

const screenW = Dimensions.get('window').width;
// 一些常量设置

export default {
    getItemWidth
}
function getItemWidth(col, margin) {
    let width = (screenW - (col + 1) * margin) / col
    return width;
}