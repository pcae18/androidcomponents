package com.android.supay.test.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.android.supay.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationSampleActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_sample);
        ButterKnife.bind(this);
    }

    public void startAnimation( int animationType ){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), animationType );
        imageView.startAnimation(animation);
    }

    @OnClick(R.id.btnZoom) public void onClickZoom(){
        startAnimation( R.anim.myanimation );
    }


    @OnClick(R.id.btnClockwise) public void onClickClockwise(View view){
        startAnimation( R.anim.clockwise );
    }


    @OnClick(R.id.btnFade)public void onClickFade(View view){
        startAnimation( R.anim.fade );
    }

    @OnClick(R.id.btnBlink)public void onClickBlink(View view){
        startAnimation( R.anim.blink );

    }

    @OnClick(R.id.btnMove)public void onClickClockMove(View view){
        startAnimation( R.anim.move );

    }

    @OnClick(R.id.btnSlide)public void onClickClockSlide(View view){
        startAnimation( R.anim.slide );

    }

}
