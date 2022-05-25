package com.example.rpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class gamemap extends AppCompatActivity {

    public ImageView fst,scnd,bswords,fve,sx, bg;
    public ImageButton inventory;
    public static int completedLvls = 0;
    public static int isNewPlayer = 0;

    public Button yes,no;
    public TextView eductext;

    @Override
    public void onBackPressed() {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemap);
        fst = (ImageView) findViewById(R.id.fst);
        scnd = (ImageView) findViewById(R.id.scnd);
        fve = (ImageView) findViewById(R.id.fve);
        sx = (ImageView) findViewById(R.id.sx);
        inventory = (ImageButton) findViewById(R.id.inventory);
        bswords = (ImageView) findViewById(R.id.bswords);
        eductext = (TextView) findViewById(R.id.eductext);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        bg = (ImageView) findViewById(R.id.bg);
        mdo();





            eductext.setVisibility(View.VISIBLE);
            no.setVisibility(View.VISIBLE);
            yes.setVisibility(View.VISIBLE);
            bg.setVisibility(View.VISIBLE);



            if (isNewPlayer==0) {

                eductext.setVisibility(View.VISIBLE);
                no.setVisibility(View.VISIBLE);
                yes.setVisibility(View.VISIBLE);
                bg.setVisibility(View.VISIBLE);
            }

            else if (isNewPlayer>=1){
                eductext.setVisibility(View.GONE);
                no.setVisibility(View.GONE);
                yes.setVisibility(View.GONE);
                bg.setVisibility(View.GONE);

            }


        if (completedLvls==1) {

            bswords.setVisibility(View.GONE);
            fst.setImageResource(R.drawable.mechi);
            fst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(gamemap.this,ScndLvl.class);
                    startActivity(intent);
                }
            });

        }

        else if(completedLvls==2) {

            bswords.setVisibility(View.GONE);
            fst.setVisibility(View.GONE);

            scnd.setImageResource(R.drawable.mechi);
            scnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(gamemap.this,ThrdLvl.class);
                    startActivity(intent);
                }
            });
        }

        else if(completedLvls==3) {

            bswords.setVisibility(View.GONE);
            fst.setVisibility(View.GONE);
            scnd.setVisibility(View.GONE);

            fve.setImageResource(R.drawable.mechi);
            fve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(gamemap.this,FoLvl.class);
                    startActivity(intent);
                }
            });
        }

        else if(completedLvls==4) {

            bswords.setVisibility(View.GONE);
            fst.setVisibility(View.GONE);
            scnd.setVisibility(View.GONE);
            fve.setVisibility(View.GONE);

            sx.setImageResource(R.drawable.mechi);
            sx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(gamemap.this,LastLvl.class);
                    startActivity(intent);
                }
            });

        }



    }

    public void mdo() {


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);




        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(gamemap.this,Education.class);
                startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isNewPlayer +=1;
                eductext.setVisibility(View.GONE);
                no.setVisibility(View.GONE);
                yes.setVisibility(View.GONE);
                bg.setVisibility(View.GONE);
            }
        });



        fst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(gamemap.this,"Пройдите уровень 1",Toast.LENGTH_SHORT).show();
            }
        });

        scnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(gamemap.this, "Пройдите уровень 2", Toast.LENGTH_SHORT).show();
            }
        });

        fve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(gamemap.this, "Пройдите уровень 3", Toast.LENGTH_SHORT).show();
            }
        });

        sx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(gamemap.this, "Пройдите уровень 6", Toast.LENGTH_SHORT).show();
            }
        });

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(gamemap.this, inv.class);
                startActivity(intent1);


            }
        });

        bswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(gamemap.this, battleActivity.class);
                startActivity(intent2);
            }
        });

    }

}