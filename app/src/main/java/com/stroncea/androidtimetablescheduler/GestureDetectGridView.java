package com.stroncea.androidtimetablescheduler;


/*
Adapted from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/GestureDetectGridView.java

This extension of GridView contains built in logic for handling swipes between buttons
 */
        
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;


public class GestureDetectGridView extends GridView {
    public static final int SWIPE_MIN_DISTANCE = 100;
    public static final int SWIPE_MAX_OFF_PATH = 100;
    public static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private GestureDetector gDetector;
    private boolean mFlingConfirmed = false;
    private float mTouchX;
    private float mTouchY;
    private SwipeGestureCallBack callBack;


    public GestureDetectGridView(Context context) {
        super(context);
        init(context);
    }

    public GestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21
    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
                super(context, attrs, defStyleAttr, defStyleRes);
                init(context);
    }
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_MIN_DISTANCE = 20;
        private static final int SWIPE_THRESHOLD_VELOCITY = 100;

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY())) {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    callBack.onSwipe(Direction.LEFT);
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    callBack.onSwipe(Direction.RIGHT);
                }
            }

            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    private void init(final Context context) {
        gDetector = new GestureDetector(context, new MyGestureDetector());
    }

            @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
                int action = ev.getActionMasked();
                gDetector.onTouchEvent(ev);
        
                        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                        mFlingConfirmed = false;
                    } else if (action == MotionEvent.ACTION_DOWN) {
                        mTouchX = ev.getX();
                        mTouchY = ev.getY();
                    } else {
            
                                if (mFlingConfirmed) {
                                return true;
                            }
            
                                float dX = (Math.abs(ev.getX() - mTouchX));
                        float dY = (Math.abs(ev.getY() - mTouchY));
                        if ((dX > SWIPE_MIN_DISTANCE) || (dY > SWIPE_MIN_DISTANCE)) {
                                mFlingConfirmed = true;
                                return true;
                            }
                    }
        
                        return super.onInterceptTouchEvent(ev);
            }

            @Override
    public boolean onTouchEvent(MotionEvent ev) {
                return gDetector.onTouchEvent(ev);
            }
    public void setSwipeGestureCallBack(SwipeGestureCallBack callBack) {
        this.callBack = callBack;
    }

}