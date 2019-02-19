package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  13:52
 * Summary : 在这里描述Class的主要功能
 */

public class Window {
    public static void main(String[] args) {
        Observable.range(1,10)
                .window(3)
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {
                        LogUtils.print("onNext:");
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                LogUtils.print("Window.accept  " + "integer = [" + integer + "]");
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.print("Window.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("Window.run onComplete");
                    }
                });
    }
}
