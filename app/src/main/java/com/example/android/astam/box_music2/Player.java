package com.example.android.astam.box_music2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
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
    private JSONArray tempmp;
    public int trek = 1;
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
    String musicID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_player);
        initControls();

        btnPlay = (ImageView) findViewById(R.id.BtnPlay);
        btnReplay = (ImageView) findViewById(R.id.btnReplay);
        btnLike = (Button) findViewById (R.id.btnLike);
        btnUnlike = (Button) findViewById(R.id.btnUnlike);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        lineName = (TextView) findViewById(R.id.lineNametr);
        linearLayoutProgress = (LinearLayout) findViewById(R.id.progress);
        imgVolumeImage = (ImageView) findViewById(R.id.volumeimage);
        musicID = getIntent().getStringExtra("musicID");
        trek = Integer.parseInt(musicID);

        imgVolumeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (volumeImage) {
                    volumeImage = false;
                    seekBarVolume.setProgress(0);
                    imgVolumeImage.setImageResource(R.drawable.red_line);

                } else {
                    volumeImage = true;
                    seekBarVolume.setProgress(100);
                    imgVolumeImage.setImageResource(R.drawable.vois);
                }

            }
        });

        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBarVolume.setProgress(seekBarVolume.getProgress() - 1);
            }
        });

        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBarVolume.setProgress(seekBarVolume.getProgress() + 1);
            }
        });

        tempmp = new parseJSON("https://api.vk.com/method/audio.get?owner_id=20111260&&access_token=8b9c746a06252d374feb71641aacc858a6d902136783354f65d314a9397784556e27ff182fe4a36e55c95&album_id=61631342", "response").getJsonArray();

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
                    if(trek >= tempmp.length()-1){
                        trek = 1;
                    }else {
                        trek += 1;
                    }
                    backNext();
                }
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener(){

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


        mediaPlayer = RSing.getMedia() /*new MediaPlayer()*/;
        Play();
        progressBar();


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
     * @param mp
     *
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        linearLayoutProgress.addView(progressBar);
        if(trek < tempmp.length()-1){
            trek += 1;
            backNext();
        }else {
            trek = 1;
            backNext();
        }
    }

    /**
     * start mp
     * @param mp
     */
    @Override
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
        if(mediaPlayer.isPlaying()) {
            SeekBar sb = (SeekBar) v;
            mediaPlayer.seekTo(sb.getProgress());

        }
    }

    /**
     * Start progress bar
     */
    private void progressBar(){
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
    private void backNext(){
        mediaPlayer.stop();
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
                    //Log.d("589", "http://muz.returnt.ru/mp/" + tempmp.getJSONObject(trek).optString("muz_music_name_def"));
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(tempmp.getJSONObject(trek).optString("url")));
                    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnCompletionListener(this);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    startProgressBar();

                    play = false;

                    lineName.setText(tempmp.getJSONObject(trek).optString("artist")+" || "+tempmp.getJSONObject(trek).optString("title"));

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
        }else {
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
        if(mediaPlayer.isPlaying()) {
            Runnable rn = new Runnable() {
                @Override
                public void run() {
                    startProgressBar();
                    Log.d("myMusic:", "------ was started thred from method: startProgressBar.");
                }
            };
            handler.postDelayed(rn, 1000);
        } else {
            mediaPlayer.pause();
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
        }
    }

    /**
     * method updateReplay
     */
    private void updateReplayButton() {
        mReplay = !mReplay;
    }

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



