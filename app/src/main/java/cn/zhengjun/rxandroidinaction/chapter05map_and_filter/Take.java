package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.zhengjun.rxandroidinaction.DefaultSubscriberImpl;
import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  14:17
 * Summary : 在这里描述Class的主要功能
 */

public class Take {
    public static void main(String[] args) {
        Observable.range(1,10)
                .take(12)
                .buffer(3)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        LogUtils.print("Take.accept  " + "integers = [" + integers + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.print("Take.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("Take.run  " + "");
                    }
                });
        LogUtils.print("Observable.intervalRange(1,10,1,1, TimeUnit.SECONDS)");
        Observable.intervalRange(1,10,1,1, TimeUnit.SECONDS)
                .take(5,TimeUnit.SECONDS)
                .subscribe(new DefaultSubscriberImpl<Long>("intervalRange"));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
