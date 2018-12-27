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

const dateArr = {
    yearSelected: false,
    monthSelected: false,
    daySelected: false,
    yearRed: false,
    monthRed: false,
    dayRed: false,
    monthArr: [0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1]
}
const datePickerCallBack = {}
// const dateArr2 = [false, false, false]
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
            years: [2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2028],
            months: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            bigDays: [],
            year: null,
            month: null,
            day: null,
            datas: null,
            shouldSelect: null
        })
    }

    /**
     * 组件即将加载在视图上调用
     * */
    componentWillMount() {
        console.log("----------------componentWillMount---------------------")
        // let arr = []
        // arr.push(...this.state.years)
        // let currArr = this.getShowingDatas()
        // this.setState({
        //     //为什么不能直接使用 datas:this.state.years呢？
        //     datas: this.getShowingDatas(),
        //     shouldSelect: 'year'
        // })
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
                {console.log("================================DatePicker的MODAL已经加载了================================")}
                <View style={{
                    alignItems: 'flex-end',
                    flex: 1,
                    flexDirection: 'row'
                }}>
                    <View style={{height: 204, flex: 1}}>
                        <View style={{
                            flex: 1,
                            height: 34,
                            backgroundColor: '#CCCCCC',
                            justifyContent: 'space-around',
                            flexDirection: 'row'
                        }}>
                            <TouchableOpacity
                                activeOpacity={1}
                                style={{justifyContent: 'center'}}
                                onPress={() => {
                                    if (dateArr.yearRed)
                                        return
                                    dateArr.yearSelected = false
                                    dateArr.yearRed = true
                                    dateArr.monthRed = false
                                    dateArr.dayRed = false
                                    this.setState({
                                        shouldSelect: 'year',
                                        datas: this.state.years
                                    })
                                }}>
                                <Text style={{
                                    width: 100,
                                    color: dateArr.yearRed ? '#FF0000' : '#000000',
                                    fontSize: 12,
                                    textAlign: 'center'
                                }}>{this.state.year == null ? '选择年' : this.state.year}</Text>
                            </TouchableOpacity>
                            <TouchableOpacity
                                activeOpacity={1}
                                style={{justifyContent: 'center'}}
                                onPress={() => {
                                    if (dateArr.monthRed)
                                        return
                                    dateArr.yearRed = false
                                    dateArr.monthRed = true
                                    dateArr.dayRed = false
                                    dateArr.monthSelected = false
                                    this.setState({
                                        shouldSelect: 'month',
                                        datas: this.state.months
                                    })
                                }}>
                                <Text style={{
                                    width: 100,
                                    color: dateArr.monthRed ? '#FF0000' : '#000000',
                                    fontSize: 12,
                                    textAlign: 'center'
                                }}>{this.state.month == null && this.state.year != null ? '选择月' : this.state.month}</Text>
                            </TouchableOpacity>
                            <TouchableOpacity
                                activeOpacity={1}
                                style={{justifyContent: 'center'}}
                                onPress={() => {
                                    if (dateArr.dayRed)
                                        return
                                    dateArr.daySelected = false
                                    dateArr.yearRed = false
                                    dateArr.monthRed = false
                                    dateArr.dayRed = true
                                    this.setState({
                                        shouldSelect: 'day',
                                        datas: this.getDaysByYearAndMonth(this.state.year, this.state.month)
                                    })
                                }}>
                                <Text style={{
                                    width: 100,
                                    color: dateArr.dayRed ? '#FF0000' : '#000000',
                                    fontSize: 12,
                                    textAlign: 'center'
                                }}>{this.state.day == null && this.state.month != null ? '选择日' : this.state.day}</Text>
                            </TouchableOpacity>
                            <TouchableOpacity
                                activeOpacity={1}
                                style={{justifyContent: 'center'}}
                                onPress={() => {
                                    this.removeAllStatus(this.state.year + "-" + this.state.month + "-" + this.state.day)
                                    datePickerCallBack.callBack(this.state.year + "-" + this.state.month + "-" + this.state.day)
                                }}>
                                <Text style={{
                                    width: 100,
                                    color: '#000000',
                                    fontSize: 13,
                                    textAlign: 'center'
                                }}>确定</Text>
                            </TouchableOpacity>
                        </View>
                        {/*很奇怪，如果不加<View>这一行的话，布局就会有问题，flatlist高度会有问题*/}
                        <View>
                            <FlatList
                                style={{height: 170}}
                                data={this.state.datas}
                                ItemSeparatorComponent={() => <View style={{height: 1, backgroundColor: '#CCCCCC'}}>
                                </View>}
                                renderItem={({item}) => this.renderYearItem(item)}
                            />
                        </View>
                    </View>
                </View>
            </Modal>
        )
    }

    renderYearItem(item) {
        return (
            <TouchableOpacity
                style={PickerStyle.yearContainer}
                onPress={() => {
                    this.yearItemClick(item)
                }}>
                <Text style={{
                    color: '#000',
                    fontSize: 15,
                    marginLeft: 20,
                    textAlign: 'left'
                }}>{item}</Text>
            </TouchableOpacity>
        )
    }

    yearItemClick(item) {
        // alert(item + " " + this.state.shouldSelect)
        if (this.state.shouldSelect == 'year') {
            if (this.state.month == null) {
                //年份选完后，月份没有选
                /*
                 * 1. 置红月份颜色
                 * 2. 加载月份数据
                 * 3. 设置年份已选
                 * 4. 置灰年份颜色
                 * 5. 设置年份已选
                 * */
                dateArr.yearSelected = true
                dateArr.yearRed = false
                dateArr.monthRed = true
                this.setState({
                    year: item,
                    datas: this.state.months,
                    shouldSelect: 'month'
                })
            } else {
                //年份选完后，月份选过了
                /*
                 * 1. 无需加载月份（除非手动点击）
                 * */
                this.setState({
                    year: item
                })
            }
        } else if (this.state.shouldSelect == 'month') {
            if (this.state.day == null) {
                //年份选完后，日没有选
                /*
                 * 1. 置红  日颜色
                 * 2. 加载  日数据
                 * 3. 设置月份已选
                 * 4. 置灰月份颜色
                 * 5. 设置月份已选
                 * */
                dateArr.dayRed = true
                dateArr.monthSelected = true
                dateArr.monthRed = false
                this.setState({
                    month: item,
                    datas: this.getDaysByYearAndMonth(this.state.year, item),
                    shouldSelect: 'day'
                })
            } else {
                this.setState({
                    month: item
                })
            }
        } else if (this.state.shouldSelect == 'day') {
            dateArr.daySelected = true
            this.setState({
                day: item
            })
        }
    }

    removeAllStatus() {
        global.closeDatePicker()
        this.setState({
            year: null,
            month: null,
            day: null,
            datas: null,
            shouldSelect: null
        })
        dateArr.yearSelected = false
        dateArr.monthSelected = false
        dateArr.daySelected = false
        dateArr.yearRed = false
        dateArr.monthRed = false
        dateArr.dayRed = false
    }

    /*
     * 根据指定参数获得日期的数据源
     * isExist：是否传过来指定日期
     * */
    getShowingDatas(s, year, month, day) {
        console.log(s)
        if (day == null) {
            console.log("day == null")
            if (month == null) {
                console.log("month == null")
                if (year == null) {
                    console.log("year == null")
                    this.setState({shouldSelect: 'year', year: '选择年'})
                    dateArr.yearSelected = false
                    dateArr.yearRed = true
                    return this.state.years
                } else {
                    this.setState({shouldSelect: 'month'})
                    dateArr.yearSelected = true
                    dateArr.yearRed = false
                    dateArr.monthRed = true
                    return this.state.months
                }
            } else {
                dateArr.yearSelected = true
                dateArr.yearRed = false
                dateArr.monthSelected = true
                dateArr.monthRed = false
                dateArr.dayRed = true
                this.setState({shouldSelect: 'day'})
                return this.getDaysByYearAndMonth(year, month)
            }
        } else {
            dateArr.daySelected = true
            dateArr.dayRed = true
            this.setState({shouldSelect: 'day'})
            return this.getDaysByYearAndMonth(year, month)
        }
    }

    getDaysByYearAndMonth(year, month) {
        var ms = []
        console.log('-------------------' + year + '-------------------' + month)
        if (dateArr.monthArr[month] == 1) {//大月
            if (month == 2) {//2月
                if (year % 4 == 0 && year % 100 != 0 || year % 400 != 0) {
                    console.log('闰年闰月')
                    ms = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
                    //闰年
                    // this.setState({
                    //     bigDays: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
                    // })
                } else {
                    // this.setState({
                    //     bigDays: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28]
                    // })
                    console.log('不是闰月')
                    ms = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28]
                }
            } else {
                // this.setState({
                //     bigDays: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]
                // })
                console.log('大月大月')
                ms = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]
            }
        } else {//小月
            // this.setState({
            //     bigDays: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
            // })
            console.log('小月小月')
            ms = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
        }
        return ms
    }

    close() {
        if (Platform.OS === 'android') {
            this.setState({modalVisible: false});
        } else {
            this.setState({modalVisible: false});
        }
    }

    show(year, month, day, callBack) {
        console.log(year + "   " + month + "   " + day)
        datePickerCallBack.callBack = callBack
        this.setState({
            modalVisible: true,
            year: year,
            month: month,
            day: day,
            datas: this.getShowingDatas('uuuuuuuuuuuuu', year, month, day)
        });
        // this.setState({
        //     //为什么不能直接使用 datas:this.state.years呢？
        //     datas: this.getShowingDatas()
        // })
    }

}
const PickerStyle = StyleSheet.create({
    yearContainer: {
        height: 34,
        flex: 1,
        justifyContent: 'center'
    }
})
export default DatePicker