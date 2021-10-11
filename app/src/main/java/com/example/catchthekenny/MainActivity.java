package com.example.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText,scoreText;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    int score;
    ImageView [] imageViews;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText= findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        score=0;
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageViews=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();

    }
    CountDownTimer countDownTimer= new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long l) {
            timeText.setText("Time: "+l/1000);

        }

        @Override
        public void onFinish() {
            timeText.setText("Time Off");
            handler.removeCallbacks(runnable);
            for (ImageView i:imageViews){
                i.setVisibility(View.INVISIBLE);
            }
            AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Restart Game?");
            alertDialog.setMessage("Are you sure to restart game?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //restart
                    Intent intent= getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //finish
                    Toast.makeText(MainActivity.this,"Game Over!", Toast.LENGTH_LONG).show();

                }
            });
            alertDialog.show();

        }
    }.start();
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score: "+score);
    }
    public void hideImages(){
        handler= new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                for (ImageView i:imageViews){
                    i.setVisibility(View.INVISIBLE);
                }
                Random random= new Random();
                int r= random.nextInt(9);
                imageViews[r].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);

    }
}