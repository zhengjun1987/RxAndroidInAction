package cn.zhengjun.rxandroidinaction.chapter05map_and_filter;

import java.util.ArrayList;
import java.util.List;

import cn.zhengjun.rxandroidinaction.LogUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author  : Zheng Jun
 * Email   : zhengjun1987@outlook.com
 * Date    : 2019/2/9  12:59
 * Summary : 在这里描述Class的主要功能
 */

public class FlatMap {
    static class User {
        private String userName;
        private List<Address> addrs;

        public User(String userName, List<Address> addrs) {
            this.userName = userName;
            this.addrs = addrs;
        }

        public String getUserName() {
            return userName;
        }

        public List<Address> getAddrs() {
            return addrs;
        }

        public static class Address {
            private String city;
            private String street;

            public Address(String city, String street) {
                this.city = city;
                this.street = street;
            }

            public String getCity() {
                return city;
            }

            public String getStreet() {
                return street;
            }
        }
    }
    public static void main(String[] args) {
        User.Address suzhou = new User.Address("Suzhou", "Renmin Road");
        User.Address dongwu = new User.Address("Suzhou", "Dongwubei Road");
        ArrayList<User.Address> addrs = new ArrayList<>(2);
        addrs.add(suzhou);
        addrs.add(dongwu);
        User tony = new User("Tony", addrs);

        Observable.just(tony)
                .flatMap(new Function<User, ObservableSource<User.Address>>() {
                    @Override
                    public ObservableSource<User.Address> apply(User user) throws Exception {
                        return Observable.fromIterable(user.getAddrs());
                    }
                })
                .subscribe(new Consumer<User.Address>() {
                    @Override
                    public void accept(User.Address address) throws Exception {
                        LogUtils.print("FlatMap.accept  " + "address = [" + address.city + "]"+ " address = [" + address.street + "]");
                    }
                });


        Observable.just(tony)
                .concatMap(new Function<User, ObservableSource<User.Address>>() {
                    @Override
                    public ObservableSource<User.Address> apply(User user) throws Exception {
                        return Observable.fromIterable(user.getAddrs());
                    }
                })
                .subscribe(new Consumer<User.Address>() {
//                    @Override
                    public void accept(User.Address address) throws Exception {
                        LogUtils.print("FlatMap.concatMap  " + "address = [" + address.city + "]"+ " address = [" + address.street + "]");
                    }
                });
    }
}
