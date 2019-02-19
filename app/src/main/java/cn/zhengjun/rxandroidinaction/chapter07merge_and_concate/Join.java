package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/16  01:43
 * Summary : 在这里描述Class的主要功能
 */

public class Join {
    private static final String TAG = "Join";
    public static void main(String[] args) {
        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 8);

        odds.join(
                evens,
                new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
                    }
                },
                new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
                    }
                },
                new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer, Integer integer2) throws Exception {
                        return integer+":"+integer2;
                    }
                }
        ).subscribe(new DefaultSubscriberImpl<String>(TAG));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//        2019-02-19 00:29:22:740  main  Join.onStart
//        2019-02-19 00:29:22:797  main  Join.onNext  t = [1:2]
//        2019-02-19 00:29:22:797  main  Join.onNext  t = [3:2]
//        2019-02-19 00:29:22:797  main  Join.onNext  t = [5:2]
//        2019-02-19 00:29:22:798  main  Join.onNext  t = [1:4]
//        2019-02-19 00:29:22:798  main  Join.onNext  t = [3:4]
//        2019-02-19 00:29:22:798  main  Join.onNext  t = [5:4]
//        2019-02-19 00:29:22:799  main  Join.onNext  t = [1:8]
//        2019-02-19 00:29:22:799  main  Join.onNext  t = [3:8]
//        2019-02-19 00:29:22:799  main  Join.onNext  t = [5:8]
//        2019-02-19 00:29:22:800  main  Join.onComplete
