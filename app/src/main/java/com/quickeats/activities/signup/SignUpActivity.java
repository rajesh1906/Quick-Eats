package com.quickeats.activities.signup;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.quickeats.AuthenticateComponent;
import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.SignupComponent;
import com.quickeats.activities.signin.SignInActivity;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.shared.CallbackService;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends MvpBaseActivity<SignUpPresenter,SignupComponent> implements SignUpView,CallbackService{

    @BindView(R.id.edt_fname)
    EditText fName;
    @BindView(R.id.edt_lname)
    EditText lName;;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @Override
    public int getLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected SignupComponent setupActivityComponent() {
        return  getApplicationComponent().plus(new SignUpModule());
    }
    @OnClick(R.id.tv_next)
    void launchDashboardScreen(){
        String firstName = fName.getText().toString();
        String lastName = lName.getText().toString();
        String phoneNumeber = edt_phone.getText().toString();
        String email = edt_email.getText().toString();
       getPresenter().handleSignUpRequest(firstName,lastName,phoneNumeber,email);

    }
    @OnClick(R.id.txtsignin)
    void launchSignInScreen(){
       getPresenter().handleLaunchSignInscreen();
    }

    @Override
    public void showErrorMessage(int errorMessage) {
        switch (errorMessage){
            case 1:
                showToastMessage(getResources().getString(R.string.error_generic_valid_fname));
                break;
            case 2:
                showToastMessage(getResources().getString(R.string.error_generic_valid_lname));
                break;
            case 3:
                showToastMessage(getResources().getString(R.string.error_generic_valid_phone));
                break;
            case 4:
                showToastMessage(getResources().getString(R.string.error_generic_valid_email));
                break;
        }

    }

    @Override
    public void showEmailFieldError(boolean show) {

    }

    @Override
    public void showPhoneNumberError() {

    }

    private void showToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void callBackActivity() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }
}
