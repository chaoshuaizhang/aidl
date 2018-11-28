/**
 * Created by changePosition on 2018/11/25.
 */
import React, {Component} from 'react';
import {View, Image, Text, StyleSheet, FlatList, Dimensions, TouchableOpacity, Button} from 'react-native';
import {
    createAppContainer,
    createStackNavigator,
    StackActions,
    NavigationActions,
} from 'react-navigation';
import {TabNavigator, StackNavigator} from 'react-navigation';
import DetailsScreen from '../DetailsScreen'
const screenW = Dimensions.get('window').width;
// 一些常量设置
const cols = 3; // 列数
const left = 10; // 左右边距
const top = 10; // 上下边距
const ImageWH = (screenW - (cols + 1) * left) / cols; // 图片大小

class Menu extends Component {
    static navigationOptions = {
        title: 'MMMENU',
    };
    constructor(props) {
        super(props);
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

    render() {
        return (
            <View>
                <Button title={'点击'} onPress={() => {

                }}/>
                <FlatList
                    data={this.state.dataArr}
                    renderItem={this.rengerMenuIcon.bind(this)}
                    horizontal={false}
                    numColumns={3}
                    style={MenuStyle.list}
                />
            </View>
        )
    }

    rengerMenuIcon(arrItem) {
        return (
            <View style={{marginLeft: 10}}>
                <TouchableOpacity
                    onPress={() => {
                        this.menuItemClick(arrItem.item)
                        // alert(JSON.stringify(this.props.menuItemClick))
                    }}>
                    <Image source={{uri: arrItem.item.img}} style={MenuStyle.menuIcon}/>
                    <Text>{arrItem.item.desc}</Text>
                </TouchableOpacity>
            </View>
        )
    }

    menuItemClick(item) {
        if (item.desc == 'AAA5') {
            this.props.navigation.navigate('Details', {
                itemId: 86,
                otherParam: 'anything you want here',
            });
        } else {
            alert('未选中')
        }
    }

}

const RootStack = createStackNavigator(
    {
        Menu: Menu,
        Details: DetailsScreen,
    },
    {
        initialRouteName: 'Menu',
        /* The header config from HomeScreen is now here */
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

const AppContainer = createAppContainer(RootStack);

export default class MenuApp extends React.Component {
    render() {
        return <AppContainer/>;
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
module.exports = Menu;