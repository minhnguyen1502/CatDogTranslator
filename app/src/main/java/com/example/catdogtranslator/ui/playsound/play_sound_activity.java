package com.example.catdogtranslator.ui.playsound;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.catdogtranslator.R;
import com.example.catdogtranslator.base.BaseActivity;
import com.example.catdogtranslator.databinding.ActivityPlaySoundBinding;

public class play_sound_activity extends BaseActivity<ActivityPlaySoundBinding> {

    String[] items = {"0s", "15s", "30s", "45s", "1m", "2m", "5m"};
    ArrayAdapter<String> arrayAdapter;
    MediaPlayer mediaPlayer;


    @Override
    public ActivityPlaySoundBinding getBinding() {
        return ActivityPlaySoundBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

        binding.loop.setChecked(false);

        binding.fillTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                long timeInMillis = convertToMillis(item);
                startCountdown(timeInMillis);
            }
        });
        binding.loop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mediaPlayer != null) {
                    mediaPlayer.setLooping(isChecked);
                }
            }
        });
    }


    @Override
    public void bindView() {

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_time, items);
        binding.fillTime.setAdapter(arrayAdapter);
    }

    @Override
    public void onBack() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    private long convertToMillis(String time) {
        switch (time) {
            case "15s":
                return 15000;
            case "30s":
                return 30000;
            case "45s":
                return 45000;
            case "1m":
                return 60000;
            case "2m":
                return 120000;
            case "5m":
                return 300000;
            default:
                return 0;
        }
    }
    private void startCountdown(long millis) {
        new CountDownTimer(millis, 1000) {
            public void onTick(long millisUntilFinished) {
                // Update UI if needed
            }

            public void onFinish() {
                playSound();
            }
        }.start();
    }

    private void playSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.guzzz);
        }
        mediaPlayer.start();
    }
}