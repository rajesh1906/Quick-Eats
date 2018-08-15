package com.quickeats;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.quickeats.shared.MvpPresenter;
import com.quickeats.shared.MvpView;

import java.lang.reflect.Method;

import javax.inject.Inject;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Rajesh kumar on 27-06-2018.
 */

public abstract class MvpBaseActivity<P extends MvpPresenter,C> extends AppCompatActivity implements MvpView<P> {

    public abstract int getLayout();
    private P  mPresenter;
    private C mActivityComponent;
    protected abstract C setupActivityComponent();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        onCreateBeforeSuper(savedInstanceState);

        super.onCreate(savedInstanceState);

        // Set activity content view
        int viewId = getLayout();
        if (viewId != 0) {
            mActivityComponent = setupActivityComponent();
            if(mActivityComponent!=null){
                injectActivity();
                if (mPresenter != null) {//getting null
                    Log.e("presenter","not null");
                    mPresenter.onCreate(savedInstanceState);
                }
            }
            onCreateBeforeSetContentView(savedInstanceState);
            setContentView(viewId);
            ButterKnife.bind(this);
            onCreateAfterSetContentView(savedInstanceState);
            setupProgressbar();
        } else {
            throw new RuntimeException("Invalid content view for activity");
        }

    }
 private void setupProgressbar(){
     progressDialog = new ProgressDialog(this);
 }
    protected final C getActivityComponent() {
        return mActivityComponent;
    }


    protected void injectActivity() {
        Object injector = getActivityComponent();
        try {
            Method injectMethod = injector.getClass().getMethod("inject", getClass());
            injectMethod.invoke(injector, this);
        } catch (Exception e) {
            Log.e("error", "Error injecting fragment dependencies", e);
        }
    }
    protected final QuickEatComponent getApplicationComponent() {
        return ((QuickEatsBase) getApplication()).getQuickEatComponent();
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }
    protected void onCreateAfterSetContentView(Bundle savedInstanceState) {
        // Do nothing by default
    }

    protected void onCreateBeforeSuper(Bundle savedInstanceState) {
        // Do nothing by default
    }

    protected void onCreateBeforeSetContentView(Bundle savedInstanceState) {
        // Do nothing by default
    }


    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public Activity getActivityFromView() {
        return this;
    }

    @Inject
    @Override
    public void setupPresenter(P presenter) {
        mPresenter = presenter;
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    @Override
    public String getViewIdentity() {
        return null;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }
}
