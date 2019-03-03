package cn.zhengjun.rxandroidinaction.chapter09finalization;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.ObservableSubscribeProxy;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/3  18:53
 * Summary : 在这里描述Class的主要功能
 */

public class AutoDisposeTest {
    private static final String TAG = "AutoDisposeTest";

    public static void main(String[] args) {
        ObservableSubscribeProxy<Long> subscribeProxy = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("doOnDispose ");
                    }
                })
                .as(AutoDispose.<Long>autoDisposable(Completable.timer(12, TimeUnit.SECONDS)));

        subscribeProxy
                .subscribe(new DefaultSubscriberImpl<Long>(TAG));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.print("==========END==========");
    }
}
//
//        2019-03-03 18:59:07:764  main  AutoDisposeTest.onStart
//        2019-03-03 18:59:08:781  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [0]
//        2019-03-03 18:59:09:781  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [1]
//        2019-03-03 18:59:10:779  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [2]
//        2019-03-03 18:59:11:778  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [3]
//        2019-03-03 18:59:12:780  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [4]
//        2019-03-03 18:59:13:777  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [5]
//        2019-03-03 18:59:14:780  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [6]
//        2019-03-03 18:59:15:776  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [7]
//        2019-03-03 18:59:16:778  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [8]
//        2019-03-03 18:59:17:779  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [9]
//        2019-03-03 18:59:18:777  RxComputationThreadPool-2  AutoDisposeTest.onNext  t = [10]
//        2019-03-03 18:59:19:775  RxComputationThreadPool-1  doOnDispose
//        2019-03-03 18:59:22:779  main  ==========END==========
//
//        Process finished with exit code 0