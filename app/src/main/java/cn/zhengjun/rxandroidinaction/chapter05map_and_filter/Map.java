package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  12:55
 * Summary : 在这里描述Class的主要功能
 */

public class Map {
    public static void main(String[] args) {
        Observable.just("HELLO")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s.toLowerCase();
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s+" world";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.print("Map.accept  " + "s = [" + s + "]");
                    }
                });
    }
}
