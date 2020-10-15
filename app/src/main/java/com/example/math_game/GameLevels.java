package com.example.math_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        Window w = getWindow(); // fullscreen without battery and time etc
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button)findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start
                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }//end
            }
        });

        //Button to level 1 - start
        TextView textView1 = (TextView)findViewById(R.id.textView1);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Button to level 1 -end

        //Button to level 2 - start
        TextView textView2 = (TextView)findViewById(R.id.textView2);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level2.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Button to level 2 -end

        //Button to level 3 - start
        TextView textView3 = (TextView)findViewById(R.id.textView3);

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level3.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Button to level 3 -end

        //Button to level 4 - start
        TextView textView4 = (TextView)findViewById(R.id.textView4);

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level4.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Button to level 4 -end

        //Button to level 5 - start
        TextView textView5 = (TextView)findViewById(R.id.textView5);

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level5.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Button to level 5 -end

        //Button to level 6 - start
        TextView textView6 = (TextView)findViewById(R.id.textView6);

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(GameLevels.this, Level6.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
            }
        });
        //Button to level 6 -end
    }

    // System button - start
    @Override
    public void onBackPressed(){
        //start
        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);finish();
        }catch (Exception e){

        }
        //end
    }
    //System button - end
}