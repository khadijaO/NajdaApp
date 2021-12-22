package com.example.najdaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LinearLayout dotsContainer;
    private ViewPager sliderViewPager;
private TextView[]dots;
private Button start,Login;
private int numCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        dotsContainer = findViewById(R.id.dots);
        sliderViewPager = findViewById(R.id.slideViewPager);
        SliderAdapter sa =new SliderAdapter(this);
        sliderViewPager.setAdapter(sa);
        addDotsIndicator(0);
        sliderViewPager.addOnPageChangeListener(viewListner);
       start=findViewById(R.id.btn);

       start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),LoginActivity.class);
               startActivity(i);
//               Intent i=new Intent(getApplicationContext(),ProfilActivity.class);
//                startActivity(i);
           }
       });
//        prevB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sliderViewPager.setCurrentItem(numCurrentPage-1);
//
//            }
//        });
    }
    public void addDotsIndicator(int position){
        dots=new TextView[3];
        dotsContainer.removeAllViews();
        for(int i=0;i<dots.length;i++){

            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dotDisactivated));
            dotsContainer.addView(dots[i]);

        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.dotActivated));
    }}
    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
addDotsIndicator(i);

numCurrentPage=i;
//if(i==0){
//    next.setEnabled(true);
//    prev.setEnabled(false);
//    prev.setVisibility(View.INVISIBLE);
//    next.setText("Next");
//    prev.setText("");
//}
//           else if(i==dots.length-1
//            ){
//                prev.setEnabled(true);
//                next.setEnabled(false);
//                prev.setVisibility(View.VISIBLE);
//                next.setText("Next");
//                prev.setText("Previous");
//            }
//            else{
//    prev.setEnabled(true);
//    next.setEnabled(true);
//    prev.setVisibility(View.INVISIBLE);
//    next.setText("Finish");
//    prev.setText("Back");
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
//    sliderViewPager.OnPageChangeListener viewListner=new  sliderViewPager.OnPageChangeListener(){

    }


