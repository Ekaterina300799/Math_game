package com.example.math_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogend;

    public int numLeft; //var for left pic
    public int numRight;//var for right pic
    Array array = new Array(); //new object from Array class
    Random random = new Random();
    public int count = 0; //counter right answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);


        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        // code that rounds corners
        img_left.setClipToOutline(true);
                // this java code only works when the emulator starts
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        // code that rounds corners
        img_right.setClipToOutline(true);

        // way to the left and right text
        final TextView text_left = findViewById(R.id.textLeft);
        final TextView text_right = findViewById(R.id.textRight);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //dialog window
        dialog = new Dialog(this); //create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide the title
        dialog.setContentView(R.layout.previewdialog); // path to layout
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent background
        dialog.setCancelable(false); // window cant to be close with the  system back btn

        // button close'x'
        TextView btn_close = (TextView)dialog.findViewById(R.id.button_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialog.dismiss();//close the dia window
            }
        });
        //

        //button 'continue'
        Button btn_continue = (Button)dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        // //button 'continue'-end
        dialog.show();

        //__________________________________
        //dialog window in the end of level1
        dialogend = new Dialog(this); //create new dialog window
        dialogend.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide the title
        dialogend.setContentView(R.layout.dialogend); // path to layout
        dialogend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent background
        dialogend.setCancelable(false); // window cant to be close with the  system back btn

        // button close'x'
        TextView btn_close2 = (TextView)dialogend.findViewById(R.id.button_close);
        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialogend.dismiss();//close the dia window
            }
        });
        //

        //button 'continue'
        Button btn_continue2 = (Button)dialogend.findViewById(R.id.btn_continue);
        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialogend.dismiss();
            }
        });
        // //button 'continue'-end
        //__________________________________

        // button "back"
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                   Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch(Exception e){

                }
            }
        });
        // button "back" - end

        //array for game progress - start
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6,
                R.id.point7, R.id.point8, R.id.point9,R.id.point10,
        };
        //array for game progress - end

        //connect animation - start
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
        // connect animation - end

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]); // get from array the pic
        text_left.setText(array.texts1[numLeft]); // get from array the text

        numRight = random.nextInt(10);
        //equality loop - start
        while (numRight == numLeft){
            numRight = random.nextInt(10);
        }
        //equality loop - end
        img_right.setImageResource(array.images1[numRight]); // get from array the pic
        text_right.setText(array.texts1[numRight]); // get from array the text


        //process clicking on the left pictures - start
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //picture touch condition
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ // if you touch the pic
                    img_right.setEnabled(false); //block right btn
                    if(numLeft>numRight){
                        img_left.setImageResource(R.drawable.correct);
                    }
                    else {
                        img_left.setImageResource(R.drawable.wrong);
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ // if you let go of your finger
                    if (numLeft > numRight) {
                        if (count<10){
                            count += 1;
                        }
                        // paint over progress
                        for(int i=0; i<10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // paint over progress - end

                        //paint over right answers
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //paint over right answers-end
                    }
                    else {
                        if (count>0){
                            if (count == 1){
                                count=0;
                            }else {
                                count -= 2;
                            }
                        }
                        // paint over progress
                        for(int i=0; i<9; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // paint over progress - end

                        //paint over right answers
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //paint over right answers-end
                    }
                    if (count == 10){
                        dialogend.show();
                    }else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]); // get from array the pic
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]); // get from array the text

                        numRight = random.nextInt(10);
                        //equality loop - start
                        while (numRight == numLeft){
                            numRight = random.nextInt(10);
                        }
                        //equality loop - end
                        img_right.setImageResource(array.images1[numRight]); // get from array the pic
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]); // get from array the text

                        img_right.setEnabled(true);//turn on right pic
                    }
                } // if you let go of your finger -end
                return true;
            }
        });
        //process clicking on the left pictures - end

        //process clicking on the right pictures - start
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //picture touch condition
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){ // if you touch the pic
                    img_left.setEnabled(false); //block left btn
                    if(numLeft<numRight){
                        img_right.setImageResource(R.drawable.correct);
                    }
                    else {
                        img_right.setImageResource(R.drawable.wrong);
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){ // if you let go of your finger
                    if (numLeft < numRight) {
                        if (count<10){
                            count += 1;
                        }
                        // paint over progress
                        for(int i=0; i<10; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // paint over progress - end

                        //paint over right answers
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //paint over right answers-end
                    }
                    else {
                        if (count>0){
                            if (count == 1){
                                count=0;
                            }else {
                                count -= 2;
                            }
                        }
                        // paint over progress
                        for(int i=0; i<9; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // paint over progress - end

                        //paint over right answers
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //paint over right answers-end
                    }
                    if (count == 10){
                        //ВЫХОД ИЗ УРОВНЯ
                        dialogend.show();
                    }else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]); // get from array the pic
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]); // get from array the text

                        numRight = random.nextInt(10);
                        //equality loop - start
                        while (numRight == numLeft){
                            numRight = random.nextInt(10);
                        }
                        //equality loop - end
                        img_right.setImageResource(array.images1[numRight]); // get from array the pic
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]); // get from array the text

                        img_left.setEnabled(true);//turn on left pic
                    }
                } // if you let go of your finger -end
                return true;
            }
        });
        //process clicking on the right pictures - end
    }

    // System button - start
    @Override
    public void onBackPressed(){
        //start
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);finish();
        }catch (Exception e){

        }
        //end
    }
    //System button - end
}