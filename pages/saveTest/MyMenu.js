/**
 * Created by changePosition on 2018/11/27.
 */
import React from 'react';
import {Button, View, Text, FlatList, Image, TouchableOpacity} from 'react-native';
import {createStackNavigator, createAppContainer} from 'react-navigation'; // Version can be specified in package.json
import DetailsScreen from '../DetailsScreen'


// class DetailsScreen extends React.Component {
//     static navigationOptions = ({navigation}) => {
//         return {
//             title: navigation.getParam('otherParam', 'A Nested Details Screen'),
//         };
//     };
//
//     render() {
//         /* 2. Get the param, provide a fallback value if not available */
//         const {navigation} = this.props;
//         const itemId = navigation.getParam('itemId', 'NO-ID');
//         const otherParam = navigation.getParam('otherParam', 'some default value');
//
//         return (
//             <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
//                 <Text>Details Screen</Text>
//                 <Text>itemId: {JSON.stringify(itemId)}</Text>
//                 <Text>otherParam: {JSON.stringify(otherParam)}</Text>
//                 <Button
//                     title="Go to Details... again"
//                     onPress={() =>
//                         this.props.navigation.push('Details', {
//                             itemId: Math.floor(Math.random() * 100),
//                         })}
//                 />
//                 <Button
//                     title="Update the title"
//                     onPress={() =>
//                         this.props.navigation.setParams({otherParam: 'Updated!'})}
//                 />
//                 <Button
//                     title="Go to Home"
//                     onPress={() => this.props.navigation.navigate('Home')}
//                 />
//                 <Button
//                     title="Go back"
//                     onPress={() => this.props.navigation.goBack()}
//                 />
//             </View>
//         );
//     }
// }

class HomeScreen extends React.Component {
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
                }]
        }
    }

    static navigationOptions = {
        title: 'MyMenu',
    };

    render() {
        return (
            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center'}}>
                <Text>Home Screen</Text>
                <FlatList
                    data={this.state.dataArr}
                    renderItem={this.rengerMenuIcon.bind(this)}
                    horizontal={false}
                />
                <Button
                    title="Go to Details"
                    onPress={() => {
                        /* 1. Navigate to the Details route with params */
                        this.props.navigation.navigate('Details', {
                            itemId: 86,
                            otherParam: 'anything you want here',
                        });
                    }}
                />
            </View>
        );
    }

    rengerMenuIcon(arrItem) {
        return (
            <View style={{marginLeft: 10}}>
                <TouchableOpacity
                    onPress={() => {
                        this.menuItemClick()
                    }}>
                    <Image source={{uri: arrItem.item.img}}/>
                    <Text>{arrItem.item.desc}</Text>
                </TouchableOpacity>
            </View>
        )
    }

    menuItemClick() {
        /* 1. Navigate to the Details route with params */
        this.props.navigation.navigate('Details', {
            itemId: 86,
            otherParam: 'anything you want here',
        });
    }

}
const RootStack = createStackNavigator(
    {
        Home: HomeScreen,
        Details: DetailsScreen,
    },
    {
        initialRouteName: 'Home',
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

export default class Appqqqq extends React.Component {
    render() {
        return <AppContainer/>;
    }
}
