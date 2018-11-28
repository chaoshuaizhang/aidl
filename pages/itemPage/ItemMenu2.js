/**
 * Created by changePosition on 2018/11/27.
 */

import React from 'react';
import {View, Text, FlatList, Image, TouchableOpacity, StyleSheet, Dimensions} from 'react-native';

const screenW = Dimensions.get('window').width;
// 一些常量设置
const cols = 3; // 列数
const left = 15; // 左右边距
const top = 10; // 上下边距
const ImageWH = (screenW - (cols + 1) * left) / cols; // 图片大小
const _SALE = 'Sale'
const _REFUND = 'Refund'
const _TOPGOODS = 'TopGoods'
const _STOCK = 'Stock'
const _PROMOTION = 'Promotion'
const _CATEGORY = 'Category'
const _SUPPLY = 'Supply'
const _BRAND = 'Brand'
export default class ItemMenu2 extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            dataArr: [
                {
                    img: 'http://e.hiphotos.baidu.com/image/pic/item/574e9258d109b3de887b4161cebf6c81810a4cf6.jpg',
                    desc: _SALE
                },
                {
                    img: 'http://f.hiphotos.baidu.com/image/pic/item/eac4b74543a98226ea4cc9088982b9014a90eb14.jpg',
                    desc: _REFUND
                },
                {
                    img: 'http://g.hiphotos.baidu.com/image/pic/item/b999a9014c086e063bb3fc6f01087bf40ad1cbff.jpg',
                    desc: _TOPGOODS
                },
                {
                    img: 'http://g.hiphotos.baidu.com/image/pic/item/d009b3de9c82d158dad3fcb0820a19d8bc3e4259.jpg',
                    desc: _STOCK
                },
                {
                    img: 'http://c.hiphotos.baidu.com/image/pic/item/d31b0ef41bd5ad6e6f3573e883cb39dbb7fd3ce7.jpg',
                    desc: _PROMOTION
                },
                {
                    img: 'http://e.hiphotos.baidu.com/image/pic/item/b219ebc4b74543a9eea95fc51c178a82b901140c.jpg',
                    desc: _CATEGORY
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/pic/item/8601a18b87d6277fcfa5c43a2a381f30e924fc79.jpg',
                    desc: _SUPPLY
                },
                {
                    img: 'http://g.hiphotos.baidu.com/image/pic/item/21a4462309f79052a6158f330ef3d7ca7bcbd5b1.jpg',
                    desc: _BRAND
                }
            ]
        }
    }

    static navigationOptions = {
        title: 'Menu',
        header: null
    };

    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
                <FlatList
                    data={this.state.dataArr}
                    renderItem={this.rengerMenuIcon.bind(this)}
                    horizontal={false}
                    numColumns={3}
                    style={MenuStyle.list}
                />
            </View>
        );
    }

    rengerMenuIcon(arrItem) {
        return (
            <View style={{marginLeft: 10}}>
                <TouchableOpacity
                    style={MenuStyle.item}
                    onPress={() => {
                        this.menuItemClick(arrItem.item)
                    }}>
                    <Image source={{uri: arrItem.item.img}} style={MenuStyle.menuIcon}/>
                    <Text>{arrItem.item.desc}</Text>
                </TouchableOpacity>
            </View>
        )
    }

    menuItemClick(item) {
        let desc = item.desc;
        this.props.navigation.navigate(desc, {
            itemId: 86,
            otherParam: item.desc,
        });
    }

}

var MenuStyle = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#cccccc',
        marginBottom: 2
    },
    rightContainer: {
        flex: 1,
        justifyContent: 'center',
        height: 81,
        alignItems: 'center',
        backgroundColor: '#FFFFFF'
    },
    menuIcon: {
        width: ImageWH,
        height: ImageWH,
        marginBottom:5,
        borderRadius: 17
    },
    title: {
        fontSize: 20,
        marginBottom: 0,
        textAlign: 'center',
    },
    year: {
        textAlign: 'center',
    },
    list: {
        paddingTop: 10,
    },
    item: {
        alignItems: 'center'
    }
});

module.exports = ItemMenu2;

