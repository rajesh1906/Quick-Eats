package com.quickeats.dashboard.fragments.booking;

import android.support.annotation.StringRes;

import com.quickeats.shared.MvpView;

/**
 * Created by Rajesh kumar on 02-09-2018.
 */

public interface EatsInView extends MvpView<EatsInPresnter> {
    void showErrorMessage(@StringRes final int errorMessage);
    void showEmailFieldError(boolean show);
}
