package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:08
 * Summary : 在这里描述Class的主要功能
 */

public class First {
    public static void main(String[] args) {
//        Observable.range(1,10)
        Observable.<Integer>empty()
                .first(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.print("First.accept  " + "integer = [" + integer + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.print("First.accept  " + "throwable = [" + throwable + "]");
                    }
                });
    }
}
