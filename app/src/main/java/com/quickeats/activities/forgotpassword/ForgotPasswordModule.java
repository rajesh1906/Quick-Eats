package com.quickeats.activities.forgotpassword;

import com.quickeats.utils.CommonValidations;

import dagger.Module;
import dagger.Provides;

@Module
public class ForgotPasswordModule {

        @Provides
        ForgotPresenter getForgotPresenter(){ return new ForgotPresenterImpl();}

        @Provides
        CommonValidations getValiadtion() {
                return new CommonValidations();
        }
}
