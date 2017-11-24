/**
 * Created by pm on 17-7-20.
 */
import React, {Component} from 'react';
import {
    BackAndroid,
    AppState,
    StyleSheet,
    View,
    Navigator,
    ToastAndroid,
    Text,
    Image,
    Dimensions
} from 'react-native';

export default class Footer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            selected: this.props.selected,
            index: this.props.index,
        }
    }

    setSelected(selected,index) {
        this.setState({selected:selected,index:index});
    }

    render() {
        return (
            <View style={styles.footer}>
                <Image
                    source={this.state.selected && this.state.index === 0 ? require("../img/navigation_focus.png") :
                        require('../img/navigaton_normal.png')}/>
                <Image
                    source={this.state.selected && this.state.index === 1 ? require("../img/navigation_focus.png") :
                        require('../img/navigaton_normal.png')}/>
                <Image
                    source={this.state.selected && this.state.index === 2 ? require("../img/navigation_focus.png") :
                        require('../img/navigaton_normal.png')}/>
            </View>);
    }
}

const styles = StyleSheet.create({
    footer: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: "center",
        alignItems: 'center',
        width: 1920,
        // backgroundColor: '#9aff75',
    },
});