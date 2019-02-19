package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:14
 * Summary : 在这里描述Class的主要功能
 */

public class Last {
    public static void main(String[] args) {
        Observable.range(1,10)
                .last(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.print("Last.accept  " + "integer = [" + integer + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.print("Last.accept  " + "throwable = [" + throwable + "]");
                    }
                });
    }
}
