package cn.zhengjun.rxandroidinaction.chapter07merge_and_concate;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/19  01:01
 * Summary : 在这里描述Class的主要功能
 */

public class StartWith {
    private static final String TAG = "StartWith";

    public static void main(String[] args) {
        Observable.just("Hello Java","Hello Kotlin","Hello C++")
                .startWith("Hello Rx")
                .subscribe(new DefaultSubscriberImpl<String>(TAG));

        Observable.just("Hello Java","Hello Kotlin","Hello C++")
                .startWithArray("Hello Rx","Hello,Clojure")
                .startWith("Hello World")
                .subscribe(new DefaultSubscriberImpl<String>(TAG));

    }
}
//
//        2019-02-20 00:20:57:984  main  StartWith.onStart
//        2019-02-20 00:20:57:985  main  StartWith.onNext  t = [Hello Rx]
//        2019-02-20 00:20:57:985  main  StartWith.onNext  t = [Hello Java]
//        2019-02-20 00:20:57:986  main  StartWith.onNext  t = [Hello Kotlin]
//        2019-02-20 00:20:57:986  main  StartWith.onNext  t = [Hello C++]
//        2019-02-20 00:20:57:992  main  StartWith.onComplete
//        2019-02-20 00:20:57:993  main  StartWith.onStart
//        2019-02-20 00:20:57:993  main  StartWith.onNext  t = [Hello World]
//        2019-02-20 00:20:57:994  main  StartWith.onNext  t = [Hello Rx]
//        2019-02-20 00:20:57:994  main  StartWith.onNext  t = [Hello,Clojure]
//        2019-02-20 00:20:57:994  main  StartWith.onNext  t = [Hello Java]
//        2019-02-20 00:20:57:994  main  StartWith.onNext  t = [Hello Kotlin]
//        2019-02-20 00:20:57:994  main  StartWith.onNext  t = [Hello C++]
//        2019-02-20 00:20:57:995  main  StartWith.onComplete