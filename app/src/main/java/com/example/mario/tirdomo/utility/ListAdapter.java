package com.example.mario.tirdomo.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.tirdomo.R;

import java.util.ArrayList;

/**
 * Created by TMAX on 10/12/2017.
 */

public class ListAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    ArrayList<?> titles;
    ArrayList<Integer> imagens;
    LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<?> titulos, ArrayList<Integer> imagenes) {
        this.context = context;
        this.titles = titulos;
        this.imagens = imagenes;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView txtTitle;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_format, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.titleList);
        imgImg = (ImageView) itemView.findViewById(R.id.iconList);

        // Capture position and set to the TextViews
        txtTitle.setText((CharSequence) titles.get(position));
        imgImg.setImageResource(imagens.get(position));

        return itemView;
    }
}

