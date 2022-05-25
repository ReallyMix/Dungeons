package com.example.rpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LastLvl extends AppCompatActivity {

    ImageView you, friend, fenemy;
    TextView plotn;
    ImageButton next;
    Button urbtnattack, urbtnheal, fsbtnattack, fsbtnheal, uprotect, fprotect;
    ProgressBar epbar, upbar, fpbar;
    int n = 0;
    double punch;
    double who;
    int fubasicattack = 40;
    int fusuperattack = 80;
    int ffbasicattack = 40;
    int ffsuperattack = 80;

    String[] plotstr = {"АВГУСТ: ТРОЛЛЬ был не простым противником, но...", "И тут ВЫ видете ДРАКОНА", "ДРАКОН: Добро пожаловать ко мне в логово", "ДРАКОН: самое жуткое и опасное место нашего королевства", "ДРАКОН: ЗНАМЯ ВЕРЫ питает мои силы... ", "ТЫ: МЫ заберём его. МЫ одолели всех твоих защитников", "ДРАКОН: что же... битва покажет... КТО ИЗ НАС СИЛЬНЕЕ"};


    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_lvl);

        you = (ImageView) findViewById(R.id.you);
        uprotect = (Button) findViewById(R.id.urbtnprotect);
        fprotect = (Button) findViewById(R.id.fbtnprotect);
        urbtnheal = (Button) findViewById(R.id.urheal);
        epbar = (ProgressBar) findViewById(R.id.epbar);
        upbar = (ProgressBar) findViewById(R.id.upbar);
        fpbar = (ProgressBar) findViewById(R.id.fpbar);
        urbtnattack = (Button) findViewById(R.id.urattack);
        friend = (ImageView) findViewById(R.id.friend);
        fenemy = (ImageView) findViewById(R.id.senemy);
        next = (ImageButton) findViewById(R.id.next);
        plotn = (TextView) findViewById(R.id.plotn);
        fsbtnattack = (Button) findViewById(R.id.fsattack);
        fsbtnheal = (Button) findViewById(R.id.fsheal);
        plotn.setText(plotstr[n]);
        o();
    }

    public void o() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (n < 6) {
                    n += 1;
                    plotn.setText(plotstr[n]);
                }

                else if (n == 6) {

                    n += 1;

                    urbtnattack.setVisibility(View.VISIBLE);
                    urbtnheal.setVisibility(View.VISIBLE);
                    uprotect.setVisibility(View.VISIBLE);

                    next.setVisibility(View.GONE);

                    plotn.setText("Что ТЫ будешь делать");

                }
                else if (n==7 && epbar.getProgress()==0){

                    Intent intent = new Intent(LastLvl.this, Ending.class);
                    startActivity(intent);


                }

            }
        });



        fenemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fenemy.setClickable(false);

                if (epbar.getProgress() <= 20) {
                    gamemap.completedLvls += 1;
                    plotn.setText("ДРАКОН: не может быть... ВЫ меня победили... В честной битве... Забирайте ЗНАМЯ ВЕРЫ... Теперь оно по праву ваше...");

                    next.setVisibility(View.VISIBLE);

                    urbtnattack.setVisibility(View.GONE);
                    urbtnheal.setVisibility(View.GONE);
                    uprotect.setVisibility(View.GONE);

                    fsbtnattack.setVisibility(View.GONE);
                    fsbtnheal.setVisibility(View.GONE);
                    fprotect.setVisibility(View.GONE);

                    epbar.setProgress(epbar.getProgress() - 20);


                }
                else {

                    //ты нажал свою кнопку атаковать, все живы
                    if (!urbtnattack.isClickable() && upbar.getProgress() > 0 && fpbar.getProgress() > 0) {

                        urbtnattack.setVisibility(View.GONE);
                        urbtnheal.setVisibility(View.GONE);
                        uprotect.setVisibility(View.GONE);

                        urbtnattack.setClickable(true);
                        urbtnheal.setClickable(true);
                        uprotect.setClickable(true);

                        fsbtnattack.setVisibility(View.VISIBLE);
                        fsbtnheal.setVisibility(View.VISIBLE);
                        fprotect.setVisibility(View.VISIBLE);

                        plotn.setText("Что будет делать АВГУСТ");

                        epbar.setProgress(epbar.getProgress() - 20);


                    }

                    //друг нажал кнопку атаки, все живы
                    else if (!fsbtnattack.isClickable() && upbar.getProgress() > 0 && fpbar.getProgress() > 0) {

                        epbar.setProgress(epbar.getProgress() - 20);

                        fattack();


                        if (upbar.getProgress() != 0) {

                            fsbtnattack.setVisibility(View.GONE);
                            fsbtnheal.setVisibility(View.GONE);
                            fprotect.setVisibility(View.GONE);

                            fsbtnattack.setClickable(true);
                            fsbtnheal.setClickable(true);
                            fprotect.setClickable(true);

                            urbtnattack.setVisibility(View.VISIBLE);
                            urbtnheal.setVisibility(View.VISIBLE);
                            uprotect.setVisibility(View.VISIBLE);

                            plotn.setText("Что ТЫ будешь делать");

                            //если ты умер после этого удара
                        } else {

                            plotn.setText("Что будет делать АВГУСТ");

                            fsbtnattack.setVisibility(View.VISIBLE);
                            fsbtnheal.setVisibility(View.VISIBLE);
                            fprotect.setVisibility(View.VISIBLE);

                            urbtnattack.setVisibility(View.GONE);
                            urbtnheal.setVisibility(View.GONE);
                            uprotect.setVisibility(View.GONE);

                            fsbtnattack.setClickable(true);
                            fsbtnheal.setClickable(true);
                            fprotect.setClickable(true);

                        }


                    }
                    //ты нажал на кнопку атаки, друг умер
                    else if (!urbtnattack.isClickable() && fpbar.getProgress() == 0) {

                        fattack();

                        if (upbar.getProgress() != 0) {

                            urbtnattack.setVisibility(View.VISIBLE);
                            urbtnheal.setVisibility(View.VISIBLE);
                            uprotect.setVisibility(View.VISIBLE);

                            fsbtnattack.setVisibility(View.GONE);
                            fsbtnheal.setVisibility(View.GONE);
                            fprotect.setVisibility(View.GONE);

                            urbtnattack.setClickable(true);
                            urbtnheal.setClickable(true);
                            uprotect.setClickable(true);

                            epbar.setProgress(epbar.getProgress() - 20);

                            plotn.setText("Что ТЫ будешь делать");
                        } else {
                            Intent intent = new Intent(LastLvl.this, GameOver.class);
                            startActivity(intent);
                        }

                    }
                    //друг нажал на кнопку атаки, ты умер
                    else if (!fsbtnattack.isClickable() && upbar.getProgress() == 0 && fpbar.getProgress() > 0) {

                        fattack();

                        if (fpbar.getProgress() != 0) {

                            fsbtnattack.setVisibility(View.VISIBLE);
                            fsbtnheal.setVisibility(View.VISIBLE);
                            fprotect.setVisibility(View.VISIBLE);

                            urbtnattack.setVisibility(View.GONE);
                            urbtnheal.setVisibility(View.GONE);
                            uprotect.setVisibility(View.GONE);

                            fsbtnattack.setClickable(true);
                            fsbtnheal.setClickable(true);
                            fprotect.setClickable(true);


                            epbar.setProgress(epbar.getProgress() - 20);

                            plotn.setText("Что будет делать АВГУСТ");
                        } else {
                            Intent intent = new Intent(LastLvl.this, GameOver.class);
                            startActivity(intent);
                        }
                    }

                }

            }

        });


        urbtnattack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                urbtnattack.setClickable(false);
                urbtnheal.setClickable(false);
                uprotect.setClickable(false);

                if (upbar.getProgress()!=0) {

                    fenemy.setClickable(true);
                    plotn.setText("Кого атаковать?");

                }
            }
        });


        fsbtnattack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fsbtnattack.setClickable(false);
                fsbtnheal.setClickable(false);
                fprotect.setClickable(false);

                if (fpbar.getProgress()!=0) {


                    fenemy.setClickable(true);
                    plotn.setText("Кого атаковать?");
                }

            }
        });

        urbtnheal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ты лечишься, все живы
                if (upbar.getProgress()>0 && fpbar.getProgress()>0) {

                    urbtnattack.setVisibility(View.GONE);
                    urbtnheal.setVisibility(View.GONE);
                    uprotect.setVisibility(View.GONE);

                    urbtnattack.setClickable(true);
                    urbtnheal.setClickable(true);
                    uprotect.setClickable(true);

                    fsbtnattack.setVisibility(View.VISIBLE);
                    fsbtnheal.setVisibility(View.VISIBLE);
                    fprotect.setVisibility(View.VISIBLE);

                    plotn.setText("Что будет делать АВГУСТ");

                    upbar.setProgress(upbar.getProgress()+15);


                }
                //ты лечишся, друг умер
                else if (fpbar.getProgress()==0 && upbar.getProgress()>0) {

                    fattack();

                    if (upbar.getProgress()!=0) {
                        urbtnattack.setVisibility(View.VISIBLE);
                        urbtnheal.setVisibility(View.VISIBLE);
                        uprotect.setVisibility(View.VISIBLE);

                        fsbtnattack.setVisibility(View.GONE);
                        fsbtnheal.setVisibility(View.GONE);
                        fprotect.setVisibility(View.GONE);

                        urbtnattack.setClickable(true);
                        urbtnheal.setClickable(true);
                        uprotect.setClickable(true);

                        plotn.setText("Что ТЫ будешь делать");
                        upbar.setProgress(upbar.getProgress()+15);
                    }
                    else {
                        Intent intent = new Intent(LastLvl.this,GameOver.class);
                        startActivity(intent);
                    }

                }

            }
        });

        fsbtnheal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //друг лечится, все живы
                if (upbar.getProgress()>0 && fpbar.getProgress()>0) {

                    fpbar.setProgress(fpbar.getProgress()+15);
                    fattack();


                    if (upbar.getProgress()!=0) {
                        fsbtnattack.setClickable(true);
                        fsbtnheal.setClickable(true);
                        fprotect.setClickable(true);


                        fsbtnattack.setVisibility(View.GONE);
                        fsbtnheal.setVisibility(View.GONE);
                        fprotect.setVisibility(View.GONE);

                        plotn.setText("Что ТЫ будешь делать");


                        urbtnattack.setVisibility(View.VISIBLE);
                        urbtnheal.setVisibility(View.VISIBLE);
                        uprotect.setVisibility(View.VISIBLE);
                    }
                    //если ты умер от этого удара
                    else {

                        plotn.setText("Что будет делать АВГУСТ");

                        fsbtnattack.setVisibility(View.VISIBLE);
                        fsbtnheal.setVisibility(View.VISIBLE);
                        fprotect.setVisibility(View.VISIBLE);

                        urbtnattack.setVisibility(View.GONE);
                        urbtnheal.setVisibility(View.GONE);
                        uprotect.setVisibility(View.GONE);

                        fsbtnattack.setClickable(true);
                        fsbtnheal.setClickable(true);
                        fprotect.setClickable(true);
                    }


                }
                //друг лечится, ты умер
                else if (upbar.getProgress()==0 && fpbar.getProgress()>0) {

                    fattack();

                    if (fpbar.getProgress()!=0) {

                        fsbtnattack.setVisibility(View.VISIBLE);
                        fsbtnheal.setVisibility(View.VISIBLE);

                        urbtnattack.setVisibility(View.GONE);
                        urbtnheal.setVisibility(View.GONE);
                        uprotect.setVisibility(View.GONE);

                        fsbtnattack.setClickable(true);
                        fsbtnheal.setClickable(true);
                        fprotect.setClickable(true);

                        fpbar.setProgress(fpbar.getProgress()+15);
                        plotn.setText("Что будет делать АВГУСТ");
                    }

                    else {
                        Intent intent = new Intent(LastLvl.this,GameOver.class);
                        startActivity(intent);
                    }


                }

            }
        });

        uprotect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fubasicattack = fubasicattack/2;
                fusuperattack = fusuperattack/2;

                //ты защищаешься, все живы
                if (upbar.getProgress()>0 && fpbar.getProgress()>0) {

                    urbtnattack.setVisibility(View.GONE);
                    urbtnheal.setVisibility(View.GONE);
                    uprotect.setVisibility(View.GONE);

                    urbtnattack.setClickable(true);
                    urbtnheal.setClickable(true);
                    uprotect.setClickable(true);

                    fsbtnattack.setVisibility(View.VISIBLE);
                    fsbtnheal.setVisibility(View.VISIBLE);
                    fprotect.setVisibility(View.VISIBLE);

                    plotn.setText("Что будет делать АВГУСТ");



                }
                //ты защищаешься, друг умер
                else if (fpbar.getProgress()==0 && upbar.getProgress()>0) {

                    fattack();

                    if (upbar.getProgress()!=0) {

                        urbtnattack.setVisibility(View.VISIBLE);
                        urbtnheal.setVisibility(View.VISIBLE);
                        uprotect.setVisibility(View.VISIBLE);

                        fsbtnattack.setVisibility(View.GONE);
                        fsbtnheal.setVisibility(View.GONE);
                        fprotect.setVisibility(View.GONE);

                        urbtnattack.setClickable(true);
                        urbtnheal.setClickable(true);
                        uprotect.setClickable(true);

                        plotn.setText("Что ТЫ будешь делать");
                    }
                    else {
                        Intent intent = new Intent(LastLvl.this,GameOver.class);
                        startActivity(intent);
                    }

                }

            }
        });

        fprotect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ffbasicattack = ffbasicattack/2;
                ffsuperattack = ffsuperattack/2;

                //друг защищается, все живы
                if (upbar.getProgress()>0 && fpbar.getProgress()>0) {


                    fattack();

                    //если ты умер от этого удара
                    if (upbar.getProgress()!=0) {

                        fsbtnattack.setClickable(true);
                        fsbtnheal.setClickable(true);
                        fprotect.setClickable(true);


                        fsbtnattack.setVisibility(View.GONE);
                        fsbtnheal.setVisibility(View.GONE);
                        fprotect.setVisibility(View.GONE);

                        plotn.setText("Что ТЫ будешь делать");


                        urbtnattack.setVisibility(View.VISIBLE);
                        urbtnheal.setVisibility(View.VISIBLE);
                        uprotect.setVisibility(View.VISIBLE);
                    }

                    else {

                        plotn.setText("Что будет делать АВГУСТ");

                        fsbtnattack.setVisibility(View.VISIBLE);
                        fsbtnheal.setVisibility(View.VISIBLE);
                        fprotect.setVisibility(View.VISIBLE);

                        urbtnattack.setVisibility(View.GONE);
                        urbtnheal.setVisibility(View.GONE);
                        uprotect.setVisibility(View.GONE);

                        fsbtnattack.setClickable(true);
                        fsbtnheal.setClickable(true);
                        fprotect.setClickable(true);
                    }



                }
                //друг защищается, ты умер
                else if (upbar.getProgress()==0 && fpbar.getProgress()>0) {

                    fattack();

                    if (fpbar.getProgress()!=0) {

                        fsbtnattack.setVisibility(View.VISIBLE);
                        fsbtnheal.setVisibility(View.VISIBLE);
                        fprotect.setVisibility(View.VISIBLE);

                        urbtnattack.setVisibility(View.GONE);
                        urbtnheal.setVisibility(View.GONE);
                        uprotect.setVisibility(View.GONE);

                        fsbtnattack.setClickable(true);
                        fsbtnheal.setClickable(true);
                        fprotect.setClickable(true);

                        plotn.setText("Что будет делать АВГУСТ");
                    }

                    else {
                        Intent intent = new Intent(LastLvl.this,GameOver.class);
                        startActivity(intent);
                    }



                }

            }


        });
    }





    public void fattack() {

        punch=Math.random()*100;
        who=Math.random()*100;

        // все живы
        if (upbar.getProgress()!=0 && fpbar.getProgress()!=0) {

            if (punch>20 && who>50) {

                Toast.makeText(this,"ДРАКОН бьёт ТЕБЯ хвостом", Toast.LENGTH_SHORT).show();
                upbar.setProgress(upbar.getProgress()-fubasicattack);
                fubasicattack = 40;
                fusuperattack = 80;
            }

            else if (punch>20 && who<=50) {

                Toast.makeText(this, "ДРАКОН бьёт АВГУСТА хвостом", Toast.LENGTH_SHORT).show();
                fpbar.setProgress(fpbar.getProgress()-ffbasicattack);
                ffbasicattack = 40;
                ffsuperattack = 80;
            }

            else if (punch<=20 && who>50) {
                Toast.makeText(this,"ДРАКОН извергает на ВАС пламя", Toast.LENGTH_SHORT).show();
                upbar.setProgress(upbar.getProgress()-fubasicattack);
                fpbar.setProgress(fpbar.getProgress()-ffbasicattack);
                fubasicattack = 40;
                fusuperattack = 80;
            }

            else if (punch<=20 && who<=50) {
                Toast.makeText(this, "ДРАКОН извергает на ВАС пламя", Toast.LENGTH_SHORT).show();
                fpbar.setProgress(fpbar.getProgress()-ffbasicattack);
                upbar.setProgress(upbar.getProgress()-ffbasicattack);
                ffbasicattack = 40;
                ffsuperattack = 80;
            }
        }

        else if (upbar.getProgress()==0 && fpbar.getProgress()!=0) {

            if (punch>20) {

                Toast.makeText(this, "ДРАКОН бьёт АВГУСТА хвостом", Toast.LENGTH_SHORT).show();
                fpbar.setProgress(fpbar.getProgress()-ffbasicattack);
                ffbasicattack = 40;
                ffsuperattack = 80;
            }

            else if (punch<=20) {
                Toast.makeText(this, "ДРАКОН извергает на ВАС пламя", Toast.LENGTH_SHORT).show();
                fpbar.setProgress(fpbar.getProgress()-ffbasicattack);
                upbar.setProgress(upbar.getProgress()-fubasicattack);
                ffbasicattack = 40;
                ffsuperattack = 80;
            }
        }

        else if (upbar.getProgress()!=0 && fpbar.getProgress()==0) {

            if (punch>20) {

                Toast.makeText(this,"ДРАКОН бьёт ТЕБЯ хвостом", Toast.LENGTH_SHORT).show();
                upbar.setProgress(upbar.getProgress()-fubasicattack);
                fubasicattack = 40;
                fusuperattack = 80;
            }

            else if (punch<=20) {
                Toast.makeText(this,"ДРАКОН извергает на ВАС пламя", Toast.LENGTH_SHORT).show();
                upbar.setProgress(upbar.getProgress()-ffbasicattack);
                fpbar.setProgress(fpbar.getProgress()-fubasicattack);
                fubasicattack = 40;
                fusuperattack = 80;
            }
        }
    }
}