/**
 * Created by pm on 17-7-20.
 */
import React, {Component} from "react";
import {
    Dimensions,
    Image,
    StyleSheet,
    Text,
    FlatList,
    View,
    ViewPagerAndroid,
    TouchableHighlight,
} from "react-native";
import {
    MyIntentModule,
    ToastNative,
} from './utils/NativeModules';
import Dimen from "./config/Dimen";
import Activity from "./config/Activity";
import Button from "./test/Button";
import favorite from '../json/favorite.json';
import ItemFavoriteApp from "./ItemFavoriteApp";


export default class AppContent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            page: 1,
            animationsAreEnabled: true,//动画是否开启
        };
    }

    componentDidMount() {

        this.timer = setInterval(() => {
            // this.move(1);
        }, 5000);
    }

    componentWillUnmount() {
        clearInterval(this.timer);
    }

    move(delta) {
        let page = this.state.page + delta;
        if (this.state.animationsAreEnabled) {
            this.viewPager.setPage(page);
        } else {
            this.viewPager.setPageWithoutAnimation(page);
        }
        //刷新了
        this.setState({page});
    }


    componentDidMount() {
        console.log(AppContent);
        console.log(Dimen.appDimen.getFavoriteAppW);
        console.log(Dimen.appDimen.favoriteAppH);
        console.log(Dimen.appDimen.appStoreH);
        console.log(Dimen.vodHeight);
    }

    render() {
        return (
            <View style={styles.app_content}>
                <View style={styles.app_store_container}>

                    {/*<TouchableHighlight style={styles.app_store_recommend}*/}
                    {/*onPress={() => this.props.onPress()}>*/}
                    <ViewPagerAndroid style={styles.app_store_recommend}
                                      initialPage={this.state.page}>
                        {/*<View style={styles.app_store_spoon}>*/}
                        {/*<Image style={styles.app_store_spoon}*/}
                        {/*source={require('../res/gamecenter_no_card_prompt_sharp.png')}/>*/}
                        {/*</View>*/}
                        {/*<View style={styles.app_store_spoon}>*/}
                        {/*<Image style={styles.app_store_spoon}*/}
                        {/*source={require('../res/gamecenter_no_card_prompt.png')}/>*/}
                        {/*</View>*/}
                        <View style={styles.app_store_spoon}>
                            <Text>Video</Text>
                        </View>
                        <View style={styles.app_store_spoon}>
                            <Text>Video</Text>
                        </View>
                        <View style={styles.app_store_spoon}>
                            <Text>Video</Text>
                        </View>
                    </ViewPagerAndroid>

                    {/*</TouchableHighlight>*/}

                    <View style={styles.app_store_right}>
                        <TouchableHighlight onPress={() => this.props.onPress()}
                                            title="APP">
                            <Image style={styles.topic_image}
                                   source={{uri: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1794894692,1423685501&fm=117&gp=0.jpg'}}>
                            </Image>
                        </TouchableHighlight>
                        <TouchableHighlight onPress={() => this.props.onPress()}
                                            title="APP">
                            <Image style={styles.topic_image}
                                   source={{uri: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1794894692,1423685501&fm=117&gp=0.jpg'}}>
                            </Image>

                        </TouchableHighlight>
                    </View>
                </View>

                <View style={styles.locale_app_container}>
                    <TouchableHighlight style={styles.more_app}
                                        onPress={() => MyIntentModule.startActivity(Activity.localeApp.pkg, Activity.localeApp.activityName, Activity.localeApp.action, 100)}>
                        <Image source={require("../img/app_more.png")}/>
                    </TouchableHighlight>
                    <View style={styles.favorite_app}>
                        <FlatList style={{
                            flex: 1
                        }}
                                  showsVerticalScrollIndicator={false}
                                  numColumns={6}
                                  horizontal={false}
                                  keyExtractor={item => item.key}
                                  data={favorite.favorite}
                                  renderItem={({item}) => {
                                      return (<ItemFavoriteApp
                                          onPress={() => this.props.onPress()}/>);
                                  }
                                  }
                        />
                    </View>

                </View>
            </View>
        );
    }
}
const styles = StyleSheet.create({
    app_content: {
        flex: 1,
        justifyContent: 'flex-start',
        alignItems: 'center'
    },
    app_store_container: {
        height: Dimen.appDimen.appStoreH,
        flexDirection: 'row',
        backgroundColor: '#2f25ff',
        width: Dimen.appWidth,
    },

    app_store_recommend: {
        marginHorizontal: 40,
        width: 920,
        height: 500,
        backgroundColor: '#ff0000',
    },
    app_store_spoon: {
        justifyContent:'center',
        alignItems:'center',
        width: 920,
        height: 400,
    },
    app_store_right: {
        justifyContent: 'center',
        height: 400,
    },
    topic_image: {
        width: 420,
        height: 200,
        justifyContent: 'flex-end',
        alignItems: 'center',
        marginVertical: 20,
    },

    locale_app_container: {
        flexDirection: 'row',
        justifyContent: 'flex-start',
        alignItems: 'center',
        width: Dimen.appWidth,
        height: Dimen.appDimen.localeAppH,
    },
    more_app: {
        justifyContent: 'center',
        alignItems: 'center',
        elevation: 40,
    },
    favorite_app: {
        width: Dimen.appDimen.getFavoriteAppW,
        height: Dimen.appDimen.getFavoriteAppH,
        // width:600,
        // height: 200,
        flex: 1,
        marginHorizontal: 20,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: "#a0ff23",
    },
});