package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.List;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  13:43
 * Summary : 在这里描述Class的主要功能
 */

public class Buffer {
    public static void main(String[] args) {
        Observable.range(1,10)
                .buffer(3)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        LogUtils.print("Buffer.accept  " + "integers = [" + integers + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.print("Buffer.accept  " + "throwable = [" + throwable + "]");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.print("Buffer.onCompleted");
                    }
                });
    }
}
