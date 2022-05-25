package com.example.rpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;


public class MainGame extends AppCompatActivity {
    TextView mtext;
    ImageButton btnnext, btnback;
    int point = 0;
    ImageView bgr;



    String[] plot = {"Прошло много лет с того момента, как ты вместе со своим напарником АВГУСТОМ стали бороться со злом в королевстве Валандар Вас прозвали Защитниками Королевства и сегодня у вас начинается самое важное задание в вашей жизни- преодолеть подземелье ДРАКОНА и принести оттуда Знамя Веры. Сможете ли вы это сделать?",
            "Вы собрались, взяли с собой меч и медикоменты. Вам дали карту подземелья и вы отправились в путь"


    };

    @Override
    public void onBackPressed() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        btnback = (ImageButton) findViewById(R.id.btnback);
        btnnext = (ImageButton) findViewById(R.id.btnnext);
        mtext = (TextView) findViewById(R.id.mtext);
        mtext.setText(plot[point]);
        bgr = (ImageView) findViewById(R.id.bgr);
        wdo();



    }

    public void wdo() {



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (point==0) {

                    btnback.setVisibility(View.VISIBLE);
                    point+=1;

                    mtext.setText(plot[point]);
                }

                else if (point==1) {

                    btnback.setVisibility(View.VISIBLE);




                    Intent intent = new Intent(MainGame.this, gamemap.class);
                    startActivity(intent);



                }


            }
        });


        btnback.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (point!=0) {
                    point-=1;
                    mtext.setText(plot[point]);

                    btnback.setVisibility(View.GONE);
                }



                else {
                    mtext.setText(plot[point]);
                }

            }
        }));

    }

}