package com.quickeats.shared.rx;


import rx.Observable;

public interface RxSchedulersApplier {
    <T> Observable.Transformer<T, T> apply();
}
