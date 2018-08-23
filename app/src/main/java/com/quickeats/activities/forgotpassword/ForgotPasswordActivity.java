package com.quickeats.activities.forgotpassword;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.di.ForgotPasswordComponet;
import com.quickeats.utils.CommonValidations;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordActivity extends MvpBaseActivity<ForgotPresenter,ForgotPasswordComponet> implements ForgotView{


    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.edt_email)
    EditText edt_email;

    @Inject
    CommonValidations validations;

    @Override
    public int getLayout() {
        return R.layout.activity_forgot_pwd;
    }

    @Override
    protected ForgotPasswordComponet setupActivityComponent() {
        return getApplicationComponent().plus(new ForgotPasswordModule());
    }

    @OnClick(R.id.tv_next)
    void nextButtonImp(){

        String email = edt_email.getText().toString();
        getPresenter().handleForgotPasswordRequest(email);

    }

    @Override
    protected void onCreateAfterSetContentView(Bundle savedInstanceState) {
        super.onCreateAfterSetContentView(savedInstanceState);
        getPresenter().setValidatoinInjection(validations);
    }
    @Override
    public void showErrorMessage(int errorMessage) {
        switch (errorMessage){
            case 1:
                showToastMessage(getResources().getString(R.string.error_generic_valid_email));
                break;
            case 2:
                showToastMessage(getResources().getString(R.string.error_generic_network_connection));
                break;
            case 3:
                showToastMessage(getResources().getString(R.string.error_generic_internal_server_error));
                break;
        }
    }

    @Override
    public void showEmailFieldError() {
        showToastMessage(getResources().getString(R.string.error_generic_valid_email));
    }

    private void showToastMessage(String value){
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
    }


}
