package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/20  00:55
 * Summary : 在这里描述Class的主要功能
 */

public class Test {
    private static final String TAG = "Test";

    public static void main(String[] args) {
        ConnectableObservable<Long> connectableObservable = Observable.interval(1, TimeUnit.SECONDS)
                .take(6)
                .publish();
        connectableObservable
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));
        LogUtils.print("+++++++++++++++++");
        connectableObservable.connect();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
