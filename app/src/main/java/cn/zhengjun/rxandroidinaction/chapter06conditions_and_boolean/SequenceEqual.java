package cn.zhengjun.rxandroidinaction.chapter06conditions_and_boolean;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  18:06
 * Summary : 在这里描述Class的主要功能
 */

public class SequenceEqual {
    public static void main(String[] args) {
        Observable.sequenceEqual(
                Observable.range(4,3),
                Observable.range(4,3)
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                LogUtils.print("SequenceEqual.accept  " + "aBoolean = [" + aBoolean + "]");
            }
        });
        LogUtils.print("===========================");
        Observable.sequenceEqual(
                Observable.range(4, 3),
                Observable.range(4, 4),
                new BiPredicate<Integer, Integer>() {
                    @Override
                    public boolean test(Integer integer, Integer integer2) throws Exception {
                        LogUtils.print("SequenceEqual.test  " + "integer = [" + integer + "], integer2 = [" + integer2 + "]");
                        return integer.equals(integer2);
                    }
                }
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                LogUtils.print("SequenceEqual.accept  " + "aBoolean = [" + aBoolean + "]");
            }
        });
    }
}
