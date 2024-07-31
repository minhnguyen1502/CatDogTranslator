package com.example.catdogtranslator.ui.language;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.catdogtranslator.R;
import com.example.catdogtranslator.base.BaseActivity;
import com.example.catdogtranslator.databinding.ActivityLanguageBinding;
import com.example.catdogtranslator.ui.home.HomeActivity;
import com.example.catdogtranslator.ui.language.adapter.LanguageAdapter;
import com.example.catdogtranslator.ui.language.model.LanguageModel;
import com.example.catdogtranslator.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageActivity extends BaseActivity<ActivityLanguageBinding> {

    List<LanguageModel> listLanguage;
    String codeLang;

    @Override
    public ActivityLanguageBinding getBinding() {
        return ActivityLanguageBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        initData();
        codeLang = Locale.getDefault().getLanguage();

        binding.viewTop.tvToolBar.setText(getString(R.string.language));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LanguageAdapter languageAdapter = new LanguageAdapter(listLanguage, code -> codeLang = code, this);


        languageAdapter.setCheck(SystemUtil.getPreLanguage(getBaseContext()));

        binding.rcvLang.setLayoutManager(linearLayoutManager);
        binding.rcvLang.setAdapter(languageAdapter);
    }

    @Override
    public void bindView() {
        binding.viewTop.ivCheck.setOnClickListener(view -> {
            SystemUtil.saveLocale(getBaseContext(), codeLang);
            startNextActivity(HomeActivity.class, null);
            finishAffinity();
        });

        binding.viewTop.ivBack.setOnClickListener(v -> onBack());
    }

    @Override
    public void onBack() {
        finishThisActivity();
    }

    private void initData() {
        listLanguage = new ArrayList<>();
        listLanguage.add(new LanguageModel("English", "en"));
        listLanguage.add(new LanguageModel("Portuguese", "pt"));
        listLanguage.add(new LanguageModel("Spanish", "es"));
        listLanguage.add(new LanguageModel("German", "de"));
        listLanguage.add(new LanguageModel("French", "fr"));
        listLanguage.add(new LanguageModel("Hindi", "hi"));
        listLanguage.add(new LanguageModel("Indonesia", "in"));
    }
}
