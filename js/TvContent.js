/**
 * Created by pm on 17-7-28.
 */
import React, {Component} from "react";
import {Dimensions, Text, View,StyleSheet} from "react-native";

const {width: screenWidth, height: screenHeight} = Dimensions.get('window');
const element_width = screenWidth * (90 / 100);
const recommend_height = screenHeight * (40 / 100);
const list_height = screenHeight * (30 / 100);
const classsification_height = screenHeight * (10 / 100);
export default class VodContent extends Component {
    render() {
        return (
            <View style={styles.tv_content}>
                <Text style={{color: "#ffffff"}}>Video</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    tv_content:{
        backgroundColor: '#000000',
        width: element_width,
        height: recommend_height,
    }
});