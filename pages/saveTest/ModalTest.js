/**
 * Created by changePosition on 2018/12/5.
 */


import React, {Component} from "react";
import {Modal, Text, TouchableHighlight, View, ProgressBarAndroid} from "react-native";

export default class ModalExample extends Component {
    state = {
        modalVisible: false
    };

    setModalVisible(visible) {
        this.setState({modalVisible: visible});
    }

    render() {
        return (
            <View style={{marginTop: 22}}>
                <Modal
                    animationType="none"
                    transparent={true}
                    visible={this.state.modalVisible}
                    onRequestClose={() => {
                        /*按返回键时提示*/
                        // alert("Modal has been closed.");
                        this.setModalVisible(!this.state.modalVisible);
                    }}>
                    <View style={{flex: 1, justifyContent: 'center', backgroundColor: '#AAA'}}>
                        <ProgressBarAndroid/>
                    </View>
                </Modal>

                <TouchableHighlight
                    onPress={() => {
                        this.setModalVisible(true);
                    }}>
                    <Text>Show Modal</Text>
                </TouchableHighlight>
            </View>
        );
    }
}

