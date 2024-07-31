package com.example.catdogtranslator.ui.permission;

import com.example.catdogtranslator.base.BaseActivity;
import com.example.catdogtranslator.databinding.ActivityPermissionBinding;
import com.example.catdogtranslator.ui.home.HomeActivity;

public class PermissionActivity extends BaseActivity<ActivityPermissionBinding> {


    @Override
    public ActivityPermissionBinding getBinding() {
        return ActivityPermissionBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

    }

    @Override
    public void bindView() {
        binding.tvContinue.setOnClickListener(v -> {
            onClickAnimation(v);
            startNextActivity(HomeActivity.class, null);
            finishAffinity();
        });
    }

    @Override
    public void onBack() {
        finishAffinity();
    }
}
