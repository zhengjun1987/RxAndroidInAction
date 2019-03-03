package cn.zhengjun.rxandroidinaction.chapter10parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/3  19:54
 * Summary : 在这里描述Class的主要功能
 */

public class ExecutorServiceParallel {
    private static final String TAG = "ExecutorServiceParallel";

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        LogUtils.print("processors = " + processors);
        final ExecutorService executorService = Executors.newFixedThreadPool(processors);
        Observable.intervalRange(1,10,1,1,TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(final Long integer) throws Exception {
                        return Observable.timer(2, TimeUnit.SECONDS).map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                return String.valueOf(integer);
                            }
                        }).subscribeOn(Schedulers.from(executorService));
                    }
                }).subscribe(new DefaultSubscriberImpl<String>(TAG));

        try {
            Thread.sleep(24000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}

//        2019-03-03 20:06:21:808  main  processors = 4
//        2019-03-03 20:06:22:866  main  ExecutorServiceParallel.onStart
//        2019-03-03 20:06:25:897  RxComputationThreadPool-2  ExecutorServiceParallel.onNext  t = [1]
//        2019-03-03 20:06:26:888  RxComputationThreadPool-3  ExecutorServiceParallel.onNext  t = [2]
//        2019-03-03 20:06:27:885  RxComputationThreadPool-4  ExecutorServiceParallel.onNext  t = [3]
//        2019-03-03 20:06:28:882  RxComputationThreadPool-1  ExecutorServiceParallel.onNext  t = [4]
//        2019-03-03 20:06:29:882  RxComputationThreadPool-2  ExecutorServiceParallel.onNext  t = [5]
//        2019-03-03 20:06:30:882  RxComputationThreadPool-3  ExecutorServiceParallel.onNext  t = [6]
//        2019-03-03 20:06:31:886  RxComputationThreadPool-4  ExecutorServiceParallel.onNext  t = [7]
//        2019-03-03 20:06:32:882  RxComputationThreadPool-1  ExecutorServiceParallel.onNext  t = [8]
//        2019-03-03 20:06:33:885  RxComputationThreadPool-2  ExecutorServiceParallel.onNext  t = [9]
//        2019-03-03 20:06:34:884  RxComputationThreadPool-3  ExecutorServiceParallel.onNext  t = [10]
//        2019-03-03 20:06:34:886  RxComputationThreadPool-3  ExecutorServiceParallel.onComplete
//
//        Process finished with exit code 0
