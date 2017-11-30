/**
 * Created by pmcho on 2017/7/16.
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
    Dimensions,
    TouchableOpacity,
} from 'react-native';
import {
    MyIntentModule,
    ToastNative,
} from './utils/NativeModules';
import MyText from './MyText';
import NetworkUtil from "./utils/NetworkUtil";
import Activity from "./config/Activity";

const {width: screenWidth, height: screenHeight} = Dimensions.get('window');
const TAG = "StatusBar:";

export default class StatusBar extends Component {

    constructor(props) {
        super(props);
        const networkState = NetworkUtil.checkNetworkState();
        console.log(TAG + "networkState=" + networkState);
    }

    state = {
        date: new Date().toLocaleTimeString(),
    };

    handleConnectivityChange(status) {
        console.log(TAG + 'status change:' + status);
        //监听第一次改变后, 可以取消监听.或者在componentUnmount中取消监听
        // NetInfo.removeEventListener('change', this.handleConnectivityChange);
    }


    componentWillMount() {
        NetworkUtil.addEventListener(NetworkUtil.TAG_NETWORK_CHANGE, this.handleConnectivityChange)
    }

    componentDidMount() {

        this.timer = setInterval(() => {
            let now = new Date();

            this.setState({
                date: now.toLocaleTimeString(),
            });
        }, 1000);
    }

    componentWillUnmount() {
        clearInterval(this.timer);
        NetworkUtil.removeEventListener(NetworkUtil.TAG_NETWORK_CHANGE, this.handleConnectivityChange)
    }

    onPress = (label) => {
        this.props.callback(label);
    };

    render() {
        return (
            <View style={styles.status_bar_container}>
                <View style={styles.status_bar}>
                    <View style={styles.status_bar_title}>
                        {/*<Text style={styles.status_bar_text}>
                         正在获取天气信息。。。
                         </Text>*/}
                        <MyText text="热更新第二次。。。"
                                style={styles.status_bar_text}/>
                    </View>
                    <View style={styles.status_bar_middle}>
                        <TouchableOpacity
                            onPress={() => MyIntentModule.startActivity(Activity.kidsmode.pkg, Activity.kidsmode.activityName, Activity.kidsmode.action, 100)}>
                            <Image style={styles.status_bar_icon}
                                   source={require('../img/children_mode.png')}/>
                        </TouchableOpacity>

                        <TouchableOpacity
                            onPress={() => MyIntentModule.startActivity(Activity.userspace.pkg, Activity.userspace.activityName, Activity.userspace.action, 100)}>
                            <Image style={styles.status_bar_icon}
                                   source={require('../img/search.png')}/>
                        </TouchableOpacity>
                    </View>
                    <View style={styles.status_bar_right}>
                        <TouchableOpacity
                            onPress={() => MyIntentModule.startActivity(Activity.netWork.pkg, Activity.netWork.activityName, Activity.netWork.action, 100)}>
                            <Image style={styles.status_bar_icon}
                                   source={require('../img/status_eth_con_focus.png')}/>
                        </TouchableOpacity>

                        <Text style={styles.status_bar_text}>
                            {this.state.date}
                        </Text>
                    </View>
                </View>
            </View>

        );
    }
}

const
    styles = StyleSheet.create({
        status_bar_container: {
            flex: 1,
            justifyContent: 'center',
            alignItems: 'center',
            elevation: 500,
        },
        status_bar: {
            flex: 1,
            justifyContent: 'flex-start',
            alignItems: 'center',
            backgroundColor: '#f4f0ff',
            flexDirection: 'row',
            height: 50,
        },
        status_bar_title: {
            justifyContent: 'center',
            alignItems: 'flex-start',
            flex: 1,
            paddingLeft: 40,
        },
        status_bar_middle: {
            justifyContent: 'center',
            alignItems: 'center',
            flex: 1,
            flexDirection: 'row',
        },
        status_bar_right: {
            justifyContent: 'flex-end',
            alignItems: 'center',
            flex: 1,
            flexDirection: 'row',
            paddingRight: 40
        },
        status_bar_icon: {
            width: 50,
            height: 50,
            marginHorizontal: 30,
            resizeMode: 'center',
        },
        status_bar_text: {
            fontSize: 24,
            textAlign: 'center',
        },
    });