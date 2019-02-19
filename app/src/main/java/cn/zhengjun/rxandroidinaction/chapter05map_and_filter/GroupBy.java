package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  13:31
 * Summary : 在这里描述Class的主要功能
 */

public class GroupBy {
    public static void main(String[] args) {
        Observable.range(1,12)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        LogUtils.print("GroupBy.apply  " + "integer = [" + integer + "]");
                        return integer % 2 == 0?"偶数":"奇数";
                    }
                })
                .subscribe(new Consumer<GroupedObservable<String, Integer>>() {
                    @Override
                    public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                        String key = stringIntegerGroupedObservable.getKey();
                        LogUtils.print("GroupBy.accept  " + "stringIntegerGroupedObservable = [" + key + "]");
                        if (key.equals("偶数")) {
                            stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer integer) throws Exception {
                                    LogUtils.print("GroupBy.accept  " + "integer = [" + integer + "]");
                                }
                            });
                        }
                    }
                });
    }
}
