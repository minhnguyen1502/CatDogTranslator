package com.example.catdogtranslator.ui.language;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.catdogtranslator.R;
import com.example.catdogtranslator.base.BaseActivity;
import com.example.catdogtranslator.databinding.ActivityLanguageStartBinding;
import com.example.catdogtranslator.ui.intro.IntroActivity;
import com.example.catdogtranslator.ui.language.adapter.LanguageStartAdapter;
import com.example.catdogtranslator.ui.language.model.LanguageModel;
import com.example.catdogtranslator.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageStartActivity extends BaseActivity<ActivityLanguageStartBinding> {

    List<LanguageModel> listLanguage;
    String codeLang;

    @Override
    public ActivityLanguageStartBinding getBinding() {
        return ActivityLanguageStartBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        initData();
        codeLang = Locale.getDefault().getLanguage();

        binding.viewTop.ivBack.setVisibility(View.INVISIBLE);
        binding.viewTop.tvToolBar.setText(getString(R.string.language));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LanguageStartAdapter languageStartAdapter = new LanguageStartAdapter(listLanguage, code -> codeLang = code, this);


        languageStartAdapter.setCheck(SystemUtil.getPreLanguage(getBaseContext()));

        binding.rcvLangStart.setLayoutManager(linearLayoutManager);
        binding.rcvLangStart.setAdapter(languageStartAdapter);
    }

    @Override
    public void bindView() {
        binding.viewTop.ivCheck.setOnClickListener(view -> {
            SystemUtil.saveLocale(getBaseContext(), codeLang);
            startNextActivity(IntroActivity.class, null);
            finishAffinity();
        });
    }

    @Override
    public void onBack() {
        finishAffinity();
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
