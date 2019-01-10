package com.stroncea.androidtimetablescheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class GridAdapter extends BaseAdapter {

    /**
     * ArrayList of the textViews
     */
    private ArrayList<TextView> mTextViews;
    /**
     * Height and width of the column
     */
    private int mColumnWidth, mColumnHeight;

    private Context context;
    private String[] items;
    LayoutInflater inflater;
    /**
     * Instantiates custom adapter object using textViews, column width and column height.
     * @param textViews - list of textViews
     * @param columnWidth - width of the column
     * @param columnHeight - height of the column
     */
    public GridAdapter(ArrayList<TextView> textViews, int columnWidth, int columnHeight, Context mContext) {
        this.mTextViews = textViews;
        this.mColumnWidth = columnWidth;
        this.mColumnHeight = columnHeight;
        this.context = mContext;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LinearLayout l  = new LinearLayout(context);
            l.setLayoutParams(new LinearLayout.LayoutParams(mColumnWidth, mColumnHeight));
            convertView = inflater.inflate(R.layout.cell, l);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.specialColumn);
        textView.setText(((TextView) getItem(position)).getText());
        textView.setBackgroundColor(((TextView) getItem(position)).getDrawingCacheBackgroundColor());



        return convertView;
    }

    @Override
    public int getCount() {
        return mTextViews.size();
    }

    @Override
    public Object getItem(int position) {
        return mTextViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
