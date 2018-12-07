package com.stroncea.androidtimetablescheduler;

/**
 * Excluded from tests because it is a view class.
 */
/*
Taken from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java

This Class is an overwrite of the Base Adapter class
It is designed to aid setting the button sizes and positions in the GridView
 */


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    /**
     * ArrayList of the buttons
     */
    private ArrayList<ArrayList<Button>> mButtons;
    /**
     * Height and width of the column
     */
    private int mColumnWidth, mColumnHeight;

    /**
     * Instantiates custom adapter object using buttons, column width and column height.
     * @param buttons - list of buttons
     * @param columnWidth - width of the column
     * @param columnHeight - height of the column
     */
    public GridAdapter(ArrayList<ArrayList<Button>> buttons, int columnWidth, int columnHeight) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }
//    public  int getRow(int position) {
//        return position / board.getDimension();
//    }

    /**
     * get the position, given a row and a column
     * @param row
     * @param col
     * @return
     */
//    public int getPosition(int row, int col){
//        return row* board.getDimension() + col;
//    }

    /**
     * @return the column number(Starts at 0).
     */
//    public int getCol(int position) {
//        return position % board.getDimension();
//    }

    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mButtons.get(position % mButtons.size()).get(position / mButtons.size());
        } else {
            button = (Button) convertView;
        }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        button.setLayoutParams(params);

        return button;
    }
}
