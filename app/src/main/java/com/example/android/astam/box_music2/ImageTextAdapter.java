package com.example.android.astam.box_music2;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ruslan1 on 28.07.2015.
 */
public class ImageTextAdapter extends BaseAdapter {
    private Context mContext;

    public ImageTextAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public String[] catTitl = {"trance", "techno", "rock", "reggae", "rap",
            "metal", "jazz", "house", "hip hop", "dubstep", "dnb", "classical", "blues",
            "alternative", "pop"};


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.activity_cellgrid, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView) grid.findViewById(R.id.imagepart);

        TextView textView = (TextView) grid.findViewById(R.id.textpart);
        imageView.setImageResource(mThumbIds[position]);
        textView.setText(catTitl[position]);

        return grid;
    }


    public Integer[] mThumbIds = {
            R.drawable.trance,
            R.drawable.techno,
            R.drawable.rock,
            R.drawable.reggae,
            R.drawable.rap,
            R.drawable.metal,
            R.drawable.jazz,
            R.drawable.house,
            R.drawable.hip_hop,
            R.drawable.dubstep,
            R.drawable.dnb,
            R.drawable.classical,
            R.drawable.blues,
            R.drawable.alternative,
            R.drawable.pop,
             };


}
