package com.tinhvv.shakeoa.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tinhvv.shakeoa.R;

/**
 * Created by ray-jason on 10/03/2016.
 */
public class ShakeOAViewFactory {

    static public class ApplicationRow extends LinearLayout implements View.OnClickListener {

        public Context mContext;

        public ImageView mImageIcon;

        public TextView mAppName;

        public ApplicationRow(Context context) {
            super(context);
            try{
                init(context);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        public ApplicationRow(Context context, AttributeSet attrs) {
            super(context, attrs);
            try{
                init(context);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        public ApplicationRow(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            try{
                init(context);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public ApplicationRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            try{
                init(context);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {

        }

        private void init(Context context) throws Exception {
            try {
                mContext = context;
                LayoutInflater.from(context).inflate(R.layout.app_item_row, this, true);

                //
                mImageIcon = (ImageView) findViewById(R.id.app_icon_image_view);

                mAppName = (TextView) findViewById(R.id.app_name_txt);

            }catch (Exception ex){
                throw new Exception(ex);
            }
        }

        public void clearContent(){
            mImageIcon.setImageResource(R.color.outside_color_trans);
            mAppName.setText(null);
        }
    }


    //Loading class
    public static class LoadingView extends RelativeLayout {

        public LoadingView(Context context) {
            super(context);
            try {
                init(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void init(Context context) throws Exception {
            try {
                LayoutInflater.from(context).inflate(R.layout.loading_view, this, true);
            } catch (Exception e) {
                throw new Exception(e);
            }
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return true;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
