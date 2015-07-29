package com.example.android.astam.box_music2;

import android.app.Activity;
import android.annotation.TargetApi;


import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import libraryjava.parseJSON;
import libraryjava.RSing;

public class Player extends ActionBarActivity implements OnPreparedListener, OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private Boolean play = true;
    private ImageView btnPlay;
    /* private Button btnStop;
     private Button btnRestart;*/
    private Button btnLike;
    private Button btnUnlike;
    private ImageView btnReplay;
    boolean like = true;
    private JSONArray tempmp = null;
    private int trek = 1;
    private SeekBar seekBar;
    private Handler handler;
    private TextView lineName;
    private ProgressBar progressBar;
    private LinearLayout linearLayoutProgress;
    private SeekBar seekBarVolume = null;
    private AudioManager audioManager = null;
    private Boolean volumeImage = true;
    private ImageView imgVolumeImage = null;
    boolean replay = true;
    boolean mReplay = true;
    private String musicID;
    private String category = "61878890";
    private TextView timeall;
    private TextView timesec;
    int pointVolume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_player);
        initControls();


        timesec = (TextView) findViewById(R.id.audiostart);
        timeall = (TextView) findViewById(R.id.audiostop);


        btnPlay = (ImageView) findViewById(R.id.BtnPlay);
        btnReplay = (ImageView) findViewById(R.id.btnReplay);
        btnLike = (Button) findViewById(R.id.btnLike);
        btnUnlike = (Button) findViewById(R.id.btnUnlike);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        lineName = (TextView) findViewById(R.id.lineNametr);
        linearLayoutProgress = (LinearLayout) findViewById(R.id.progress);
        imgVolumeImage = (ImageView) findViewById(R.id.volumeimage);
        timeall = (TextView) findViewById(R.id.audiostop);
        timesec = (TextView) findViewById(R.id.audiostart);


        if (getIntent().getStringExtra("category") != null)
            category = getIntent().getStringExtra("category");
        tempmp = new parseJSON("https://api.vk.com/method/audio.get?owner_id=20111260&&access_token=8b9c746a06252d374feb71641aacc858a6d902136783354f65d314a9397784556e27ff182fe4a36e55c95&album_id=" + category, "response").getJsonArray();
        mediaPlayer = RSing.getMedia();
        musicID = getIntent().getStringExtra("musicID");
        if (musicID != null) {
            trek = Integer.parseInt(musicID);
            mediaPlayer = RSing.getMedia();
            if (mediaPlayer.isPlaying()) {
                backNext();
                Log.d("второй раз", "второй раз");
            } else {
                backNext2();
                Log.d("Первый раз запуск", "Первый раз запуск");
            }
            Log.d("Nazvanie pesni--------", musicID);
        } else {
            if (mediaPlayer.isPlaying()) {
                btnPlay.setBackgroundResource(R.drawable.pause);
                play = false;
                startProgressBar();
                seekBar.setMax(mediaPlayer.getDuration());

            } else {
                backNext2();
                startProgressBar();
                seekBar.setMax(mediaPlayer.getDuration());

            }
            /*Play();
            progressBar();*/
            //backNext2();
            Log.d("Nazvanie pesni--------", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }


        imgVolumeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (volumeImage) {
                    volumeImage = false;
                    pointVolume = seekBarVolume.getProgress();
                    seekBarVolume.setProgress(0);
                    imgVolumeImage.setImageResource(R.drawable.red_line);

                } else {
                    volumeImage = true;
                    seekBarVolume.setProgress(pointVolume);
                    imgVolumeImage.setImageResource(R.drawable.vois);
                }

            }
        });

        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBarVolume.setProgress(seekBarVolume.getProgress() - 1);
                if (seekBarVolume.getProgress() == 0) {
                    imgVolumeImage.setImageResource(R.drawable.red_line);
                }
            }
        });


        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBarVolume.setProgress(seekBarVolume.getProgress() + 1);
                imgVolumeImage.setImageResource(R.drawable.vois);
            }
        });


        //Log.d("898", tempmp.toString());

        /**
         * play media
         */
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Play();
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    like = false;
                    btnLike.setBackgroundResource(R.drawable.previus);
                    btnUnlike.setBackgroundResource(R.drawable.next); // Произошел лайк, обращаемся к серверу.
                } else {
                    if (trek == 1) {
                        trek = tempmp.length() - 1;
                    } else {
                        trek -= 1;
                    }
                    backNext();
                }
            }
        });

        btnUnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    like = false;
                    btnLike.setBackgroundResource(R.drawable.previus);
                    btnUnlike.setBackgroundResource(R.drawable.next); // Произошел лайк, обращаемся к серверу.
                } else {
                    if (trek >= tempmp.length() - 1) {
                        trek = 1;
                    } else {
                        trek += 1;
                    }
                    backNext();
                }
            }
        });

        /*btnReplay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(replay){
                    replay = false;
                    btnReplay.setBackgroundResource(R.drawable.replay);
                }else{
                    replay = true;
                    btnReplay.setBackgroundResource(R.drawable.replayoff);
                }
            }
        });


        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/




/*
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickk(v);
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickk(v);
            }
        });
*/
    }

    private void timeFormat() {
    }


    /**
     * seekBarVolume changes volume on the app's display
     */

    private void initControls() {
        try {
            seekBarVolume = (SeekBar) findViewById(R.id.seekBarVolume);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            seekBarVolume.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            seekBarVolume.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            seekBarVolume.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBarVolume) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBarVolume) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                    imgVolumeImage.setImageResource(R.drawable.vois);
                    if (seekBarVolume.getProgress() == 0) {
                        imgVolumeImage.setImageResource(R.drawable.red_line);
                    }


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    /**
     * back menu
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * end mp
     *
     * @param mp
     */

    public void onCompletion(MediaPlayer mp) {


        linearLayoutProgress.addView(progressBar);
        if (trek < tempmp.length() - 1) {

            //linearLayoutProgress.addView(progressBar);
            if (trek < tempmp.length() - 1) {
                trek += 1;
                backNext();
            } else {
                trek = 1;
                backNext();
            }
        }
    }

    /**
     * start mp
     *
     * @param mp
     */

    public void onPrepared(MediaPlayer mp) {
        linearLayoutProgress.removeView(progressBar);


        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekBarChange(v);
                return false;
            }
        });
    }


    private void seekBarChange(View v) {
        if (mediaPlayer.isPlaying()) {
            SeekBar sb = (SeekBar) v;
            mediaPlayer.seekTo(sb.getProgress());

        }
    }

    /**
     * Start progress bar
     */
    private void progressBar() {
        progressBar = new ProgressBar(this, null,
                android.R.attr.progressBarStyleLarge);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        progressBar.setProgress(100);

    }


    /**
     * Click back+
     * Click Next
     * start
     */
    private void backNext() {
        mediaPlayer.pause();
        mediaPlayer = RSing.setMedia();
        mediaPlayer = RSing.getMedia();
        play = true;
        Play();
    }

    /**
     * Click back+
     * Click Next
     * start
     */
    private void backNext2() {

        mediaPlayer = RSing.setMedia();
        mediaPlayer = RSing.getMedia();
        play = true;
        Play();
    }

    /**
     * method start play
     */
    private void Play() {

        if (!mediaPlayer.isPlaying()) {
            btnPlay.setBackgroundResource(R.drawable.pause);
            if (play) {
                try {
                    //Log.d("589", "" + tempmp.getJSONObject(trek).getString("url"));
                    mediaPlayer.setDataSource(tempmp.getJSONObject(trek).getString("url"));
                    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnCompletionListener(this);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    startProgressBar();
                    timeall.setText(new Date(mediaPlayer.getDuration()).getMinutes() + ":" + new Date(mediaPlayer.getDuration()).getSeconds() + "");


                    play = false;
                    Log.d("qwqwqwq", new Date(mediaPlayer.getDuration()).getSeconds() + "");
                    lineName.setText(tempmp.getJSONObject(trek).getString("artist") + " || " + tempmp.getJSONObject(trek).getString("title"));

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                mediaPlayer.start();
                startProgressBar();
                Log.d("33", "33");
            }
        } else if (!play) {
            mediaPlayer.pause();
            btnPlay.setBackgroundResource(R.drawable.play);
            Log.d("22", "22");
        } else {
            btnPlay.setBackgroundResource(R.drawable.pause);
            play = false;
        }
    }

    /**
     * auto start ProgressBar
     */
    public void startProgressBar() {
        handler = new Handler();
        seekBar.setProgress(mediaPlayer.getCurrentPosition());


        timesec.setText(new Date(mediaPlayer.getCurrentPosition()).getMinutes() + ":" + new Date(mediaPlayer.getCurrentPosition()).getSeconds() + "");
        if (mediaPlayer.isPlaying()) {

            if (mediaPlayer.isPlaying()) {
                Runnable rn = new Runnable() {
                    @Override
                    public void run() {
                        startProgressBar();
                        Log.d("myMusic:", "------ was started thred from method: startProgressBar.");
                        String minutes = new Date(mediaPlayer.getCurrentPosition()).getSeconds() < 10 ? "0" + new Date(mediaPlayer.getCurrentPosition()).getSeconds() : new Date(mediaPlayer.getCurrentPosition()).getSeconds() + "";
                        timesec.setText(new Date(mediaPlayer.getCurrentPosition()).getMinutes() + ":" + minutes + "");
                    }
                };
                handler.postDelayed(rn, 1000);
            } else {
                mediaPlayer.pause();
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }

            if (mediaPlayer.isPlaying()) {
                Runnable rn = new Runnable() {
                    @Override
                    public void run() {
                        startProgressBar();
                        Log.d("myMusic:", "------ was started thred from method: startProgressBar.");
                        String minutes = new Date(mediaPlayer.getCurrentPosition()).getSeconds() < 10 ? "0" + new Date(mediaPlayer.getCurrentPosition()).getSeconds() : new Date(mediaPlayer.getCurrentPosition()).getSeconds() + "";
                        timesec.setText(new Date(mediaPlayer.getCurrentPosition()).getMinutes() + ":" + minutes + "");
                    }
                };
                handler.postDelayed(rn, 1000);
                timeall.setText(new Date(mediaPlayer.getDuration()).getMinutes() + ":" + new Date(mediaPlayer.getDuration()).getSeconds() + "");
            } else {
                mediaPlayer.pause();
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }


            /**
             * method updateReplay
             */
    /*private void updateReplayButton() {
        mReplay = !mReplay;
    }*/

/*
    private void onClickk(View view) {

        switch (view.getId()) {
            case R.id.BtnStop:
                mediaPlayer.stop();
                play = true;
                btnPlay.setText("Play");
                mediaPlayer = new MediaPlayer();
                Log.d("44", "44");
                break;
            case R.id.BtnRestart:
                mediaPlayer.reset();
                mediaPlayer = new MediaPlayer();
                play = true;
                Play();
                break;
        }
    }
*/
        }
    }
}




