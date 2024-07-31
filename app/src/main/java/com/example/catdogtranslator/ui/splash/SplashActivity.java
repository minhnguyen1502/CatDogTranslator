package com.example.catdogtranslator.ui.splash;

import android.os.Handler;

import com.example.catdogtranslator.base.BaseActivity;
import com.example.catdogtranslator.databinding.ActivitySplashBinding;
import com.example.catdogtranslator.ui.language.LanguageStartActivity;
import com.example.catdogtranslator.util.SharePrefUtils;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {


    @Override
    public ActivitySplashBinding getBinding() {
        return ActivitySplashBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        SharePrefUtils.increaseCountOpenApp(this);

        new Handler().postDelayed(() -> {
            startNextActivity(LanguageStartActivity.class, null);
            finishAffinity();
        }, 3000);

    }

    @Override
    public void bindView() {

    }

    @Override
    public void onBack() {

    }
}
