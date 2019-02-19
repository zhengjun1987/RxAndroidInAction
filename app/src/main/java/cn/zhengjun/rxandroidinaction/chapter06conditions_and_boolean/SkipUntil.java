package cn.zhengjun.rxandroidinaction.chapter06conditions_and_boolean;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  18:15
 * Summary : 在这里描述Class的主要功能
 */

public class SkipUntil {
    private static final String TAG = "SkipUntil";

    public static void main(String[] args) {
        Observable.intervalRange(1, 9, 0, 1, TimeUnit.SECONDS)
//                .skipUntil(Observable.timer(4,TimeUnit.SECONDS))
//                .skip(4)
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong % 4 < 3;
                    }
                })
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
