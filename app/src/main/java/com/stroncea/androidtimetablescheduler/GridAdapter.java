package com.stroncea.androidtimetablescheduler;

/**
 * Excluded from tests because it is a view class.
 */
/*
Taken from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java

This Class is an overwrite of the Base Adapter class
It is designed to aid setting the textView sizes and positions in the GridView
 */


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * adapter for the TimeTable UI
 */
public class GridAdapter extends BaseAdapter {
    /**
     * ArrayList of the textViews
     */
    private ArrayList<TextView> mTextViews;
    /**
     * Height and width of the column
     */
    private int mColumnWidth, mColumnHeight;

    /**
     * Instantiates custom adapter object using textViews, column width and column height.
     * @param textViews - list of textViews
     * @param columnWidth - width of the column
     * @param columnHeight - height of the column
     */
    public GridAdapter(ArrayList<TextView> textViews, int columnWidth, int columnHeight) {
        mTextViews = textViews;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
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
//TODO this works but not efficient as we're not recycling views.
    //If there is time, try to recycle it, and make sure it works and doesn't return random
    //positions as it does with the current implementation
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        textView = mTextViews.get(position);
////    if (convertView == null) {
////    }
//      else {
////    textView = (TextView) convertView;
//      }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        textView.setLayoutParams(params);

        return textView;
    }
}
