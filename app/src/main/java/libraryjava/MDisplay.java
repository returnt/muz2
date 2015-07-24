package libraryjava;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by Professor on 08.07.2015.
 */
public class MDisplay{


    private int size;
    private Boolean ScreenOrientation = true;

    public Boolean getScreenOrientation(Activity activity){
        /*if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            ScreenOrientation =  true;
        else*/

        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            ScreenOrientation = false;

        return ScreenOrientation;
    }

    public int getSizeScrean(Activity activity, int i){

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point sizePoint = new Point();
        display.getSize(sizePoint);
        switch (i){
            case 1:
                size = sizePoint.x;
            case 2:
                size = sizePoint.y;
            case 3:
                size = display.getHeight();
            case 4:
                size = display.getWidth();
        }
        return size;
    }
}
