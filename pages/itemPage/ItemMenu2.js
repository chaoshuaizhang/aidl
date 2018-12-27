/**
 * Created by changePosition on 2018/11/27.
 */

import React from 'react';
import {View, Text, FlatList, Image, TouchableOpacity, StyleSheet, Dimensions} from 'react-native';
import DimensUtil from '../../utils/DimensUtil'

const screenW = Dimensions.get('window').width;
// 一些常量设置
const ImageWH = DimensUtil.getItemWidth(3, 15)
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
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
                    desc: _SALE
                },
                {
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
                    desc: _REFUND
                },
                {
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
                    desc: _TOPGOODS
                },
                {
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
                    desc: _STOCK
                },
                {
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
                    desc: _PROMOTION
                },
                {
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
                    desc: _CATEGORY
                },
                {
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
                    desc: _SUPPLY
                },
                {
                    img: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545900789892&di=7360ad997e829d632e344da30dc7cfb4&imgtype=0&src=http%3A%2F%2Fcdn.clm02.com%2Fezvivi.com%2F290127%2F290127_1.jpg',
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
                    // columnWrapperStyle={{borderWidth: 1, borderColor: '#FF0000', borderRadius: 5}}
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
        // alert(JSON.stringify(item))
        let desc = item.desc;
        this.props.navigation.navigate(desc, {
            itemId: 86,
            otherParam: item.desc
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
        marginBottom: 5,
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

