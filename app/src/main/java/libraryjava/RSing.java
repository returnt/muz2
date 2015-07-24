package libraryjava;

import android.media.MediaPlayer;

/**
 * Created by Professor on 14.07.2015.
 */
public class RSing {

    private static MediaPlayer media;

    private RSing(){

    }

    public static MediaPlayer getMedia(){
        if(media == null) media = new MediaPlayer();
        return media;
    }

    public static MediaPlayer setMedia(){
        media = null;
        return null;
    }
}
