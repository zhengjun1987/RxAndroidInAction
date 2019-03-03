package cn.zhengjun.rxandroidinaction.chapter02basicals;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/3/4  00:19
 * Summary : 在这里描述Class的主要功能
 */

public class MaybeTest {
    private static final String TAG = "MaybeTest";

    public static void main(String[] args) {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> maybeEmitter) throws Exception {
                maybeEmitter.onComplete();
                maybeEmitter.onSuccess(TAG.toUpperCase());
                maybeEmitter.onSuccess(TAG.toLowerCase());
            }
        })
                .subscribe(new MaybeObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LogUtils.print("MaybeTest.onSuccess  " + "s = [" + s + "]");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.print("MaybeTest.onError  " + "throwable = [" + throwable + "]");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.print("MaybeTest.onComplete  " + "");
                    }
                });
    }
}
