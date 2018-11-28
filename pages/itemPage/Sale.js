/**
 * Created by changePosition on 2018/11/27.
 */
import React from 'react';
import {Button, View, Text, FlatList, Image, TouchableOpacity, Picker} from 'react-native';
import {createStackNavigator, createAppContainer} from 'react-navigation'; // Version can be specified in package.json
export default class Sale extends React.Component {
    static navigationOptions = ({navigation}) => {
        return {
            title: navigation.getParam('otherParam', 'A Nested Details Screen'),
            headerTintColor: '#FFF',
            headerStyle: {
                backgroundColor: '#447BE8',
            }
        };
    };

    constructor(props) {
        super(props)
        this.state = {
            // language: [[desc = '选择全部', tag = 1], ['Chinese', 2], ['Franch', 3], ['English', 4], ['Candana', 5], ['哈哈哈', 6]],
            language: [[desc = '选择全部', tag = 1], [desc = '选择全部', tag = 1], [desc = '选择全部', tag = 1], [desc = '选择全部', tag = 1], [desc = '选择全部', tag = 1], [desc = '选择全部', tag = 1]],
            selectedLangunage: null
        }
    }

    render() {
        const {navigation} = this.props;
        const itemId = navigation.getParam('itemId', 'NO-ID');
        const otherParam = navigation.getParam('otherParam', 'some default value');

        return (
            <View>
                <Picker
                    selectedValue={this.state.selectedLangunage}
                    style={{height: 50, flex: 1}}
                    onValueChange={(itemValue, itemIndex) => this.onPickerSelected(itemIndex, itemValue)}>
                    {this.state.language.map((name) => <Picker.Item label={name.tag} value={name.desc}/>)}
                </Picker>
            </View>
        );
    }

    onPickerSelected(index, value) {
        this.setState({selectedLangunage: value})
    }

}
module.exports = Sale;

