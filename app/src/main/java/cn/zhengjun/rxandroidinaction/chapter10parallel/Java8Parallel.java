package cn.zhengjun.rxandroidinaction.chapter10parallel;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import cn.zhengjun.rxandroidinaction.LogUtils;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/23  20:49
 * Summary : 在这里描述Class的主要功能
 */

public class Java8Parallel {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 18; i++) integers.add(i);
        integers.parallelStream()
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return String.valueOf(integer);
                    }
                })
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        LogUtils.print("Java8Parallel.accept  " + "s = [" + s + "]");
                    }
                });
    }
}
