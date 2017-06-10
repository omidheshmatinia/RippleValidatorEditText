package me.omidh.rvelib.sample;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.omidh.rvelib.sample.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

  View rootView;
  ViewPager viewPager;
  int lastColor =  Color.parseColor("#ff64b5f6");
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    rootView = findViewById(R.id.rootView);
    viewPager = (ViewPager) findViewById(R.id.viewPager);

    viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        changeColorOfBackground(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().setStatusBarColor(lastColor);
    }
  }

  private void changeColorOfBackground(int position) {
    int newColor;
    switch (position){
      default:
      case 0:
        newColor = Color.parseColor("#ff64b5f6");
        break;
      case 1:
        newColor = Color.parseColor("#ffe57373");
        break;
    }
    ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), lastColor, newColor);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animator) {
        int color = (int) animator.getAnimatedValue();
        lastColor = color ;
        rootView.setBackgroundColor(color);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          getWindow().setStatusBarColor(color);
        }
      }

    });
    animator.start();

  }
}
