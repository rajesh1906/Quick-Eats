package com.quickeats.shared.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DefaultRxSchedulersApplier implements RxSchedulersApplier {

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable.Transformer<T, T> apply() {
        return applySchedulers();
    }
}
