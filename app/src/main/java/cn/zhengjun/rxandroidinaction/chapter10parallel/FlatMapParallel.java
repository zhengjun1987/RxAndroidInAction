package cn.zhengjun.rxandroidinaction.chapter10parallel;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/3  19:37
 * Summary : 在这里描述Class的主要功能
 */

public class FlatMapParallel {
    private static final String TAG = "FlatMapParallel";

    public static void main(String[] args) {
        io.reactivex.Observable.range(1,100)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(String.valueOf(integer)).delay(2, TimeUnit.SECONDS)
                                .subscribeOn(Schedulers.newThread());
                    }
                })
                .subscribe(new DefaultSubscriberImpl<String>(TAG));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
