/**
 * Created by pmcho on 2017/7/9.
 */
/**
 * Created by pmcho on 2017/7/9.
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
    FlatList,
    ViewPagerAndroid,
    TouchableOpacity,
    TouchableHighlight,
} from 'react-native';
import{
    StackNavigator,
}from 'react-navigation';
import StatusBar from './StatusBar';
import Footer from './Footer';
import VodContent from './VodContent';
import AppContent from './AppContent';
import TvContent from './TvContent';
import ToastNative from './utils/NativeModules';
import Item from './Item';
// import movies from '../json/movies.json';

const PAGES = 3;
const {width: screenWidth, height: screenHeight} = Dimensions.get('window');
var clickDelay = Date.now();
export default class App extends Component {

    static navigationOptions = {
        title: null,
        header: null
    };

    constructor(props) {
        super(props);
        this.state = {
            selectedPoint: true,
            page: 2,
            animationsAreEnabled: true,//动画是否开启
            progress: {
                position: 0,
                offset: 0,
            },
            focus: false,
        };
    }

    componentWillMount() {
        console.log("componentWillMount");
    }

    onPageSelected = (e) => {
        //这个回调会在页面切换完成后（当用户在页面间滑动）调用
        //回调参数中的event.nativeEvent对象
        this.setState({page: e.nativeEvent.position});
    };

    onPageScroll = (e) => {
        //当在页间切换时（不论是由于动画还是由于用户在页间滑动/拖拽）执行。

        //回调参数中的event.nativeEvent对象会包含如下数据：
        //position 从左数起第一个当前可见的页面的下标。
        //offset 一个在[0,1)（大于等于0，小于1）之间的范围，代表当前页面切换的状态。值x表示现在"position"所表示的页有(1 - x)的部分可见，而下一页有x的部分可见。

        // this.setState({progress: e.nativeEvent});
    };

    move(delta) {
        let clickStart = Date.now();
        if ((clickStart-clickDelay) < 500) {
            return;
        }
        let page = this.state.page + delta;
        if (page < 0) {
            page = PAGES - 1;
            this.go(page);
        } else if (page > PAGES - 1) {
            page = 0;
            this.go(page);
        } else {
            this.go(page);
        }
        clickDelay = clickStart;
    }

    go(page) {
        if (this.state.animationsAreEnabled) {
            this.viewPager.setPage(page);
        } else {
            this.viewPager.setPageWithoutAnimation(page);
        }
        let selectedPoint = this.state.selectedPoint;
        //调用Footer切换指示器图标的方法。
        this.refs.footer.setSelected(selectedPoint, page);
        //刷新了
        this.setState({page, selectedPoint});
    }

    onShowUnderlay = () => {
        // this.state.focus = true;
        // this.setState({focus:true});
        console.log("this.state.focus=" + this.state.focus);
    };

    onHideUnderlay = () => {
        // this.state.focus = false;
        // this.setState({focus:false});
        console.log("this.state.focus=" + this.state.focus);
    };

    onPressIn = () => {

    };
    onPressOut = () => {

    };


    //statusBar　回调函数，由子组件调用
    callback = (label) => {
        alert(label);
    };

    render() {
        // let rightIcon = {};
        // this.state.focus ? rightIcon = require("../img/right_big_arrow.png") : rightIcon = require("../img/right_small_arrow.png");
        const {navigate} = this.props.navigation;
        return (
            <View style={[styles.flex, styles.root_container]}>

                <Image source={require("../img/launcher_bj.png")}>

                    <StatusBar
                        ref={(statusBar) => {
                            this.statusBar = statusBar
                        }}
                        callback={this.callback}
                    />
                    {/*<StatusBar onPress={ToastNative.showPopWindow()}/>*/}

                    <View style={[styles.content_container]}>
                        <TouchableOpacity style={styles.button}
                                          onPress={() => this.move(-1)}
                                          activeOpacity={0.5}>
                            <Image style={styles.button_image}
                                   source={require('../img/left_big_arrow.png')}/>
                        </TouchableOpacity>

                        <ViewPagerAndroid style={styles.view_pager}
                                          initialPage={this.state.page}
                                          onPageScroll={() => this.onPageScroll()}
                                          onPageSelected={() => this.onPageSelected()}
                                          scrollEnabled={false}
                                          ref={(viewPager) => {
                                              this.viewPager = viewPager;
                                          }}>
                            <View>
                                <TvContent/>
                            </View>
                            <View>
                                <VodContent navigation={navigate}
                                            onPress={() => {
                                                navigate('Details', {user: 'VOD'});
                                            }}/>
                            </View>
                            <View>
                                <AppContent navigation={navigate}
                                            onPress={() => navigate('Details', {user: 'APP'})}/>
                            </View>
                        </ViewPagerAndroid>

                        <TouchableHighlight style={styles.button}
                                            onPress={() => this.move(+1)}
                                            onPressIn={() => this.onPressIn()}
                                            onPressOut={() => this.onPressOut()}
                                            onHideUnderlay={() => this.onHideUnderlay()}
                                            onShowUnderlay={() => this.onShowUnderlay()}
                                            underlayColor={"#ffffff"}>
                            <Image style={styles.button_image}
                                   source={this.state.focus ? require("../img/right_big_arrow.png") : require("../img/right_small_arrow.png")}/>
                        </TouchableHighlight>
                    </View>
                    <Footer ref="footer"
                            selected={this.state.selectedPoint}
                            index={this.state.page}/>

                </Image>

            </View>
        );
    }

    componentDidMount() {
        console.log("componentDidMount");
    }

    componentWillReceiveProps() {
        console.log("componentWillReceiveProps");
    }

   /* shouldComponentUpdate() {
        console.log("shouldComponentUpdate");
    }*/

    componentWillUpdate() {
        console.log("componentWillUpdate");

    }

    componentDidUpdate() {
        console.log("componentDidUpdate");

    }

    componentWillUnmount() {
        console.log("componentWillUnmount");
    }
}

const styles = StyleSheet.create({
    flex: {
        flex: 1,
    },
    root_container: {
        justifyContent: 'flex-start',
        alignItems: 'center',
        // backgroundColor: "#ffdce6",
    },

    content_container: {
        marginVertical: 40,
        flex: 13,
        flexDirection: 'row',
        justifyContent: 'flex-start',
        alignItems: 'flex-start',
        width: screenWidth,
        // backgroundColor: '#27ff2c'

    },

    view_pager: {
        flex: 1,
        height: 1000,
        // backgroundColor: '#ff0000',
    },

    button: {
        width: 100,
        height: 800,
        justifyContent: 'center',
        alignItems: 'center',
    },

    button_image: {
        resizeMode: 'center',
    },

});
