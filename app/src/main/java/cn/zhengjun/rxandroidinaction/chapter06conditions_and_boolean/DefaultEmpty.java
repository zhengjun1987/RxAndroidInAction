package cn.zhengjun.rxandroidinaction.chapter06conditions_and_boolean;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/12  17:55
 * Summary : 在这里描述Class的主要功能
 */

public class DefaultEmpty {
    private static final String TAG = "DefaultEmpty";
    public static void main(String[] args) {
        Observable.empty()
                .defaultIfEmpty(9)
                .subscribe(new DefaultSubscriberImpl<>(TAG));

        LogUtils.print("=======================");
        Observable.empty()
                .switchIfEmpty(Observable.range(1,5))
                .subscribe(new DefaultSubscriberImpl<>(TAG));
    }
}

//        2019-02-12 17:59:08:531  main  DefaultEmpty.onStart
//        2019-02-12 17:59:08:538  main  DefaultEmpty.onNext  t = [9]
//        2019-02-12 17:59:08:538  main  DefaultEmpty.onComplete
//        2019-02-12 17:59:08:538  main  =======================
//        2019-02-12 17:59:08:539  main  DefaultEmpty.onStart
//        2019-02-12 17:59:08:540  main  DefaultEmpty.onNext  t = [1]
//        2019-02-12 17:59:08:541  main  DefaultEmpty.onNext  t = [2]
//        2019-02-12 17:59:08:541  main  DefaultEmpty.onNext  t = [3]
//        2019-02-12 17:59:08:541  main  DefaultEmpty.onNext  t = [4]
//        2019-02-12 17:59:08:542  main  DefaultEmpty.onNext  t = [5]
//        2019-02-12 17:59:08:542  main  DefaultEmpty.onComplete
//
//        Process finished with exit code 0