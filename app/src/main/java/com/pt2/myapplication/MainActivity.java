package com.pt2.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView czas;
    TextView punkty;
    Button start;
    int ileSekund;
    int punktyLiczba = 0;
    private CountDownTimer countDownTimer;
    GridLayout grid;
    View[] japka;
    int random;
    int zycieJapka;
    TextView koniec;
    LinearLayout czasLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        czas = findViewById(R.id.czas);
        punkty = findViewById(R.id.punkty);
        grid = findViewById(R.id.grid);
        koniec = findViewById(R.id.koniec);
        czasLayout = findViewById(R.id.czasLayout);
        japka = new View[] {
                findViewById(R.id.j1),
                findViewById(R.id.j2),
                findViewById(R.id.j3),
                findViewById(R.id.j4),
                findViewById(R.id.j5),
                findViewById(R.id.j6),
                findViewById(R.id.j7),
                findViewById(R.id.j8),
                findViewById(R.id.j9)
        };


        for (int j = 0; j < japka.length; j++)
        {
            japka[j].setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            punktyLiczba++;
                            punkty.setText(String.valueOf(punktyLiczba));
                            for (int i = 0; i < japka.length; i++)
                            {
                                japka[i].setVisibility(View.INVISIBLE);
                            }
                            random = new Random().nextInt(9);
                            japka[random].setVisibility(View.VISIBLE);
                            zycieJapka = ileSekund;
                        }
                    }
            );
        }


        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        punkty.setText("0");
                        ileSekund = 60;
                        grid.setVisibility(View.VISIBLE);
                        czasLayout.setVisibility(View.VISIBLE);
                        start.setVisibility(View.INVISIBLE);
                        random = new Random().nextInt(9);
                        japka[random].setVisibility(View.VISIBLE);
                        koniec.setVisibility(View.GONE);
                        zycieJapka = ileSekund;

                        countDownTimer = new CountDownTimer(ileSekund * 1000, 1000) {
                            @Override
                            public void onTick(long l) {
                                ileSekund = (int) (l/1000);
                                if (zycieJapka >= ileSekund + 2){
                                    japka[random].setVisibility(View.INVISIBLE);

                                    japka[random].setVisibility(View.INVISIBLE);
                                    start.setVisibility(View.VISIBLE);
                                    countDownTimer.cancel();
                                    grid.setVisibility(View.GONE);
                                    koniec.setVisibility(View.VISIBLE);
                                    czasLayout.setVisibility(View.INVISIBLE);
                                    punktyLiczba = 0;
                                }
                                czas.setText(ileSekund+"s");
                            }

                            @Override
                            public void onFinish() {
                                japka[random].setVisibility(View.INVISIBLE);
                                start.setVisibility(View.VISIBLE);
                                countDownTimer.cancel();
                                grid.setVisibility(View.GONE);
                                koniec.setVisibility(View.VISIBLE);
                                czasLayout.setVisibility(View.INVISIBLE);
                                punktyLiczba = 0;
                            }
                        };
                        countDownTimer.start();
                    }
                }
        );



    }
}