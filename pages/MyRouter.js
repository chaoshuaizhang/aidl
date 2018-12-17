/**
 * Created by changePosition on 2018/12/10.
 * 全局的路由，设置所有需要 路由 的界面
 */
import {createAppContainer, createStackNavigator} from 'react-navigation';

import TabPage from './TabPage';
import Login from './Login'
import Sale from './itemPage/Sale'
import Refund from './itemPage/Refund';
import Stock from './itemPage/Stock';

export const MyRouter = createStackNavigator({

    Login: {
        screen: Login,
    },
    TabPage: {
        screen: TabPage,
    },
    Sale: {
        screen: Sale
    },
    Refund: {
        screen: Refund
    },
    Stock: {
        screen: Stock
    }
}, {initialRouteName: 'Login'});

//Now AppContainer is the main component for React to render
const Router = createAppContainer(MyRouter);

export default Router;