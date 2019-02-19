package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  15:03
 * Summary : 在这里描述Class的主要功能
 */

public class Distinct {
    private static final String TAG = "Distinct";
    public static void main(String[] args) {
        Observable.just(1,2,1,2,3,4,5,5,6)
//                .distinctUntilChanged()
                .distinct()
                .subscribe(new DefaultSubscriberImpl<Integer>(TAG));
    }
}
//        2019-02-09 15:05:33:161  main  Distinct.onStart
//        2019-02-09 15:05:33:162  main  Distinct.onNext  t = [1]
//        2019-02-09 15:05:33:162  main  Distinct.onNext  t = [2]
//        2019-02-09 15:05:33:162  main  Distinct.onNext  t = [3]
//        2019-02-09 15:05:33:163  main  Distinct.onNext  t = [4]
//        2019-02-09 15:05:33:163  main  Distinct.onNext  t = [5]
//        2019-02-09 15:05:33:163  main  Distinct.onNext  t = [6]
//        2019-02-09 15:05:33:164  main  Distinct.onComplete
//
//        Process finished with exit code 0
