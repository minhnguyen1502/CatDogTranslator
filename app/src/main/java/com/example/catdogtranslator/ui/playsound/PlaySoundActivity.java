package com.example.catdogtranslator.ui.playsound;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.example.catdogtranslator.R;
import com.example.catdogtranslator.base.BaseActivity;
import com.example.catdogtranslator.databinding.ActivityPlaySoundBinding;

public class PlaySoundActivity extends BaseActivity<ActivityPlaySoundBinding> {

    String[] items = {"15s", "30s", "45s", "1m", "2m", "5m"};
    ArrayAdapter<String> arrayAdapter;
    MediaPlayer mediaPlayer;


    @Override
    public ActivityPlaySoundBinding getBinding() {
        return ActivityPlaySoundBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ClickableViewAccessibility")
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

        binding.img.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startPlayingSound();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        stopPlayingSound();
                        break;
                }
                return true;
            }
        });
        binding.Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    stopSound();
                    binding.loop.setChecked(false);
                } else {
                    playSound();
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
                Countdown(millisUntilFinished);
            }

            public void onFinish() {
                playSound();
            }
        }.start();
    }

    private void Countdown(long millisUntilFinished) {
        int seconds = (int) (millisUntilFinished / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        binding.countDown.setText(timeFormatted);
    }

    boolean isPlaying = false;

    private void playSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.guzzz);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    isPlaying = false;
                    binding.Play.setText("Play");
                }
            });
        }
        mediaPlayer.start();
        isPlaying = true;
        binding.Play.setText("Pause");

    }

    private void stopSound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0); // Reset to the beginning of the sound
        }
        isPlaying = false;
        binding.Play.setText("Play");

    }

    private void startPlayingSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.guzzz);
            mediaPlayer.setLooping(true); // Enable looping
        }
        mediaPlayer.start();
        isPlaying = true;
        binding.Play.setText("Pause");

    }

    private void stopPlayingSound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
            isPlaying = false;
            mediaPlayer.setLooping(false);
        }
        binding.Play.setText("Play");

    }

}