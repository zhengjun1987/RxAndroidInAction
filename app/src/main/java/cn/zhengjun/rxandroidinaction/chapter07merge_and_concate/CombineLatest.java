package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/16  00:44
 * Summary : 在这里描述Class的主要功能
 */

public class CombineLatest {
    private static final String TAG = "CombineLatest";
    public static void main(String[] args) {
        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 8);
        Observable.combineLatest(evens, odds, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(new DefaultSubscriberImpl<Integer>(TAG));
    }
}
