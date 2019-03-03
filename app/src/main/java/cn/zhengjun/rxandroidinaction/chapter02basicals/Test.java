package cn.zhengjun.rxandroidinaction.chapter02basicals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/4  01:11
 * Summary : 在这里描述Class的主要功能
 */

public class Test {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Zheng Jun");
        strings.add("Wang Junling");
        strings.add("Wang Junling2");
        strings.add("Wang Jun Ling2");
        Observable.fromIterable(strings)
                .flatMap(new Function<String, Observable<String>>() {
                    @Override
                    public Observable<String> apply(String s) throws Exception {
                        String[] split = s.split(" ");
                        LogUtils.print("strings = " + Arrays.toString(split));
                        return Observable.fromIterable(Arrays.asList(split)).delay(2, TimeUnit.SECONDS);
                    }
                })
                .distinct()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new DefaultSubscriberImpl<String>("TEST"));

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//        2019-03-04 01:35:31:843  main  TEST.onStart
//        2019-03-04 01:35:31:859  RxNewThreadScheduler-1  strings = [Zheng, Jun]
//        2019-03-04 01:35:31:887  RxNewThreadScheduler-1  strings = [Wang, Junling]
//        2019-03-04 01:35:31:888  RxNewThreadScheduler-1  strings = [Wang, Junling2]
//        2019-03-04 01:35:31:889  RxNewThreadScheduler-1  strings = [Wang, Jun, Ling2]
//        2019-03-04 01:35:33:887  RxComputationThreadPool-1  TEST.onNext  t = [Zheng]
//        2019-03-04 01:35:33:887  RxComputationThreadPool-1  TEST.onNext  t = [Jun]
//        2019-03-04 01:35:33:891  RxComputationThreadPool-2  TEST.onNext  t = [Wang]
//        2019-03-04 01:35:33:892  RxComputationThreadPool-2  TEST.onNext  t = [Junling]
//        2019-03-04 01:35:33:894  RxComputationThreadPool-4  TEST.onNext  t = [Ling2]
//        2019-03-04 01:35:33:894  RxComputationThreadPool-4  TEST.onNext  t = [Junling2]
//        2019-03-04 01:35:33:896  RxComputationThreadPool-4  TEST.onComplete
//
//        Process finished with exit code 0