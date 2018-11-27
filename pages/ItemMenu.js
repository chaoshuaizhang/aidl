/**
 * Created by changePosition on 2018/11/27.
 */
import React from 'react';
import {Button, View, Text, FlatList, Image, TouchableOpacity, StyleSheet, Dimensions} from 'react-native';
import {createStackNavigator, createAppContainer} from 'react-navigation'; // Version can be specified in package.json
import DetailsScreen from '../pages/DetailsScreen'
const screenW = Dimensions.get('window').width;
// 一些常量设置
const cols = 3; // 列数
const left = 10; // 左右边距
const top = 10; // 上下边距
const ImageWH = (screenW - (cols + 1) * left) / cols; // 图片大小
class Menu extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            dataArr: [
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA1'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA2'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA3'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA4'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA5'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA6'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA7'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA8'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA9'
                },
                {
                    img: 'http://b.hiphotos.baidu.com/image/w%3D310/sign=7bb10d07768da9774e2f802a8051f872/908fa0ec08fa513d17b6a2ea386d55fbb2fbd9e2.jpg',
                    desc: 'AAA10'
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
        switch (item.desc) {
            case 'AAA1':
                alert('AAA1')
                break;
            case 'AAA2':
                alert('AAA2')
                break;
            case 'AAA3':
                alert('AAA3')
                break;
            case 'AAA4':
                alert('AAA4')
                break;
        }
        this.props.navigation.navigate('Details', {
            itemId: 86,
            otherParam: 'anything you want here',
        });
    }

}
const RootStack = createStackNavigator(
    {
        Menu: Menu,
        Details: DetailsScreen,
    },
    {
        initialRouteName: 'Menu',
        defaultNavigationOptions: {
            headerStyle: {
                backgroundColor: '#f4511e',
            },
            headerTintColor: '#fff',
            headerTitleStyle: {
                fontWeight: 'bold',
            },
        },
    }
);
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
        height: 81,
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
    }
});
const AppContainer = createAppContainer(RootStack);

export default class ItemMenu extends React.Component {
    render() {
        return <AppContainer/>;
    }
}
