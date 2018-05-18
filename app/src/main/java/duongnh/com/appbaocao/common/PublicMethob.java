package duongnh.com.appbaocao.common;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import duongnh.com.appbaocao.R;


/**
 * Created by Thien Thien on 7/6/2017.
 */

public class PublicMethob {

    public static void animationShowUp(Context mContext, View view){
        view.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.show_up);
        view.startAnimation(animation);
    }
    public static void animationShowDown(Context mContext, View view){
        view.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.show_down);
        view.startAnimation(animation);
    }

    public static void animationHideDown(Context mContext, final View view){
        Animation slide_down = AnimationUtils.loadAnimation(mContext,
                R.anim.hide_down);
        view.startAnimation(slide_down);
        slide_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public static void animationHideUP(Context mContext, final View view){
        Animation slide_down = AnimationUtils.loadAnimation(mContext,
                R.anim.hide_up);
        view.startAnimation(slide_down);
        slide_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void animationZoomIn(Context mContext, final View view){
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.zoom_in);
        view.startAnimation(animation);
    }

    public static void animationZoomOut(Context mContext, final View view){

        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.zoom_out);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void animationRotate(final Context mContext, final View view){
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.animation_rotate);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void animationShowLeft(Context mContext, View view){
        view.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.show_left);
        view.startAnimation(animation);
    }

    public static void animationHideLeft(Context mContext, final View view){
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.hide_left);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
                return;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                return;
            }
        });
    }

    public static void anamationAlphaDark(Context mContext, View view){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.alpha_dark);
        view.startAnimation(animation);
    }

    public static void anamationAlphaDarkSlow(Context mContext, View view){
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.alpha_dark_slow);
        view.startAnimation(animation);
    }

    public static Typeface setTypeFace(Context mContext){
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "UTM_Helve.ttf");
        return typeface;
    }
}
