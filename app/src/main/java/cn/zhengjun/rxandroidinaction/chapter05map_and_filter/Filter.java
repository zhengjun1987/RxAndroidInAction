package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  15:06
 * Summary : 在这里描述Class的主要功能
 */

public class Filter {
    private static final String TAG = "Filter";
    public static void main(String[] args) {
        Observable.just(2,30,22,5,60,1)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 10;
                    }
                })
                .subscribe(new DefaultSubscriberImpl<Integer>(TAG));
    }
}
//        2019-02-09 15:16:59:505  main  Filter.onStart
//        2019-02-09 15:16:59:506  main  Filter.onNext  t = [30]
//        2019-02-09 15:16:59:506  main  Filter.onNext  t = [22]
//        2019-02-09 15:16:59:506  main  Filter.onNext  t = [60]
//        2019-02-09 15:16:59:506  main  Filter.onComplete
