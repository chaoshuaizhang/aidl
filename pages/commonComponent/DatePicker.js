/**
 * Created by changePosition on 2018/12/19.
 */
import React from 'react';
import {
    View,
    Text,
    Button,
    ViewPagerAndroid,
    Modal,
    Platform,
    FlatList,
    StyleSheet,
    TouchableOpacity
} from 'react-native';

class DatePicker extends React.Component {
    static navigationOptions = ({navigation}) => {
        return {
            title: navigation.getParam('otherParam', 'A Nested Details Screen'),
            desc: navigation.getParam('desc', 'A Nested Details Screen'),
            //得到参数但不显示头布局
            header: null
        };
    };

    constructor(props) {
        super(props)
        this.state = ({
            modalVisible: false,
            years: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027]
        })
    }

    /**
     * 组件即将加载在视图上调用
     * */
    componentWillMount() {
    }

    /**
     * 销毁前
     * */
    componentWillUnMount() {
        global.closeDatePicker()
    }

    render() {
        return (
            <Modal
                animationType={"slide"}
                transparent={true}
                visible={this.state.modalVisible}
                onRequestClose={() => {
                    this.close()
                }}>
                {console.log("================================DatePicker MODAL已经加载了==============================")}
                <View style={{
                    alignItems: 'flex-end',
                    flex: 1,
                    flexDirection: 'row'
                }}>

                    <View style={{flex: 1, height: 170}}>
                        <FlatList
                            style={{backgroundColor: '#EEEEEE'}}
                            data={this.state.years}
                            renderItem={({item}) => this.renderYearItem(item)}
                        />
                        <View style={{
                            position: 'absolute',
                            left: 0,
                            right: 0,
                            height: 34,
                            top: 68,
                            bottom: 68,
                            backgroundColor: '#CCCCCC',
                            opacity: 0.6
                        }}>
                        </View>
                    </View>
                </View>
            </Modal>
        )
    }

    renderYearItem(year) {
        return (
            <TouchableOpacity
                style={PickerStyle.yearContainer}>
                <Text style={{
                    color: '#000',
                    fontSize: 15,
                    textAlign: 'center'
                }}>{year}</Text>
            </TouchableOpacity>
        )
    }

    close() {
        if (Platform.OS === 'android') {
            this.setState({modalVisible: false});
        } else {
            this.setState({modalVisible: false});
        }
    }

    show() {
        this.setState({modalVisible: true});
    }

}
const PickerStyle = StyleSheet.create({
    yearContainer: {
        height: 34,
        justifyContent: 'center'
    }
})
export default DatePicker