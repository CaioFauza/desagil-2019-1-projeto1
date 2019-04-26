package br.pro.hashi.ensino.desagil.projeto1;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import android.widget.Button;
import android.widget.TextView;

public class MorseTranslate extends AppCompatActivity {

    String stringTranslated = "";
    String preTranslated = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_translate);
        Translator translator = new Translator();

        Button buttonMorse = findViewById(R.id.buttonMorse);
        TextView morseText = findViewById(R.id.morseText);
        Button buttonSet = findViewById(R.id.buttonSet);
        Button butonDel = findViewById(R.id.buttonDel);
        Button buttonSpace = findViewById(R.id.buttonSpace);

        buttonSet.setOnClickListener((view) -> {

            stringTranslated += String.valueOf(translator.morseToChar(preTranslated));
            morseText.setText(stringTranslated);
            preTranslated = "";




        });




        buttonMorse.setOnClickListener((view) -> {
            preTranslated += ".";
            morseText.append(".");
            });


        buttonMorse.setOnLongClickListener((view) -> {
            preTranslated += "-";
            morseText.append("-");

            return true;

        });

    }
}
