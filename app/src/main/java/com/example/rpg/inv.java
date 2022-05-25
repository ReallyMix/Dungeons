package com.example.rpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class inv extends AppCompatActivity {
    ImageButton back, sds, aid;
    TextView about;
    public static ImageView key, enchsword;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv);
        back = (ImageButton) findViewById(R.id.back);
        sds = (ImageButton) findViewById(R.id.sds);
        about = (TextView) findViewById(R.id.about);
        aid = (ImageButton) findViewById(R.id.aid);
        key = (ImageView) findViewById(R.id.key);
        enchsword = (ImageView) findViewById(R.id.enchsword);
        btn();
        if (gamemap.completedLvls >=3) {

            key.setVisibility(View.VISIBLE);
            aid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    about.setText("Медикаменты 2 уровня, которые ты забрал в тяжёлой битве с МАГОМ. Вылечат в трудную минуту");
                }
            });

            if (gamemap.completedLvls>=4) {

                key.setVisibility(View.VISIBLE);
                enchsword.setVisibility(View.VISIBLE);
                aid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        about.setText("Медикаменты 2 уровня, которые ты забрал в тяжёлой битве с МАГОМ. Вылечат в трудную минуту");
                    }
                });

                enchsword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        about.setText("ЗАЧАРОВАННЫЙ КИНЖАЛ, который ТЫ нашёл в СУНДУКЕ. Награда за битву с ТРОЛЛЕМ");
                    }
                });
            }
        }

    }

    public void btn() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(inv.this, gamemap.class);
                startActivity(intent);
            }
        });

        sds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.setText("Это твой меч, с помощью него ты одолел много врагов. Он достался тебе от лучшего кузнеца королевства Валандар.");
            }
        });

        aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.setText("Медикаменты, которые ты взял в путь. Вылечат в трудную минуту");
            }
        });

        key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                about.setText("Ключ, который ты получил в тяжёлой битве с МАГОМ. Открывает дверь в ГЛУБИННОЕ ПОДЗЕМЕЛЬЕ");
            }
        });
    }
}