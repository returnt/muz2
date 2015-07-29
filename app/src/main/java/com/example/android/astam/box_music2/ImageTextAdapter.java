package com.example.android.astam.box_music2;

/**
 * Created by Ruslan1 on 29.07.2015.
 */

import android.content.Context;
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        TextView textView = (TextView) grid.findViewById(R.id.textpart);
        imageView.setImageResource(mThumbIds[position]);
        textView.setText("Image" + String.valueOf(position));

        return grid;
    }


    public Integer[] mThumbIds = { R.drawable.alternative, R.drawable.blues,
            R.drawable.classical, R.drawable.dnb, R.drawable.dubstep,
            R.drawable.hip_hop, R.drawable.house, R.drawable.jazz,
            R.drawable.metal, R.drawable.pop, R.drawable.rap,
            R.drawable.reggae, R.drawable.techno, R.drawable.trance,
            R.drawable.rock };
}

