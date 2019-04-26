package br.pro.hashi.ensino.desagil.projeto1;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import android.widget.Button;
import android.widget.TextView;

public class MorseTranslate extends AppCompatActivity {

    private String stringTranslated = "";
    private String preTranslated = "";
    private String wordTranslated;
    public boolean word;

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
        Button buttonBack = findViewById(R.id.buttonBack);

        buttonSet.setOnClickListener((view) -> {

            word = true;

            wordTranslated = String.valueOf(translator.morseToChar(preTranslated));

            if(!"!".equals(wordTranslated) && !"*".equals(wordTranslated)){ stringTranslated += wordTranslated; }

            morseText.setText(stringTranslated);
            preTranslated = "";

        });

        buttonSpace.setOnClickListener((view) -> {
            if(preTranslated.length() == 0){
                word = true;
            }
            if(word && stringTranslated.length() != 0){
                stringTranslated += " ";
                morseText.setText(stringTranslated);
            }
        });

        butonDel.setOnClickListener((view) -> {

            if(preTranslated.length() == 0){
                word = true;
            }

            if(word && stringTranslated.length() != 0){
                stringTranslated = stringTranslated.substring(0, stringTranslated.length() - 1);
                morseText.setText(stringTranslated);
            }
            if(!word && preTranslated.length() != 0) {
                preTranslated = preTranslated.substring(0, preTranslated.length() - 1);
                morseText.setText(stringTranslated);
                morseText.append(preTranslated);
            }
        });

        buttonMorse.setOnClickListener((view) -> {
            word = false;
            preTranslated += ".";
            morseText.append(".");
            });


        buttonMorse.setOnLongClickListener((view) -> {
            word = false;
            preTranslated += "-";
            morseText.append("-");

            return true;

        });

    }
}
