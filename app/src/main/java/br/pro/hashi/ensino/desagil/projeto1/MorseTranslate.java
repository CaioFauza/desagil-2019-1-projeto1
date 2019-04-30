package br.pro.hashi.ensino.desagil.projeto1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;


public class MorseTranslate extends AppCompatActivity {

    private String stringTranslated = "";
    private String preTranslated = "";
    private String wordTranslated;
    private static String nameContact;
    private static String numberContact;
    public boolean word;
    ContatosActivity c = new ContatosActivity();
    private HashMap<String, String> map;
    private LinkedList<String> morse;
    private LinkedList<String> alfaNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_translate);
        Translator translator = new Translator();
        Intent morseTranslateIntent = getIntent();
        String prevActivity = morseTranslateIntent.getStringExtra("morseTranslateActivity");
        Intent contactActivityIntent = new Intent(this, ContatosActivity.class);
        Intent selecaoActivityIntent = new Intent(this, SelecaoMensagem.class);
        Intent translateActivityIntent = new Intent(this, MorseTranslate.class);
        Intent messageTypeActivityIntent = new Intent(this, MessageType.class);
        morse = new LinkedList<>();
        alfaNum = new LinkedList<>();

        morse = translator.getCodes();
        for(String code: morse){
            alfaNum.add(Character.toString(translator.morseToChar(code)));
        }
        Collections.sort(alfaNum);

        for (int i = 0; i < alfaNum.size(); i++) {
            alfaNum.set(i, alfaNum.get(i) + " : " + translator.charToMorse((alfaNum.get(i)).charAt(0)));
        }
        for (int i = 0; i < morse.size(); i++) {
            morse.set(i, morse.get(i) + " : " + translator.morseToChar((morse.get(i))));
        }


        Button buttonMorse = findViewById(R.id.buttonMorse);
        TextView morseText = findViewById(R.id.morseText);
        Button buttonSet = findViewById(R.id.buttonSet);
        Button butonDel = findViewById(R.id.buttonDel);
        Button buttonSpace = findViewById(R.id.buttonSpace);
        Button buttonBack = findViewById(R.id.buttonBack);
        TextView nameText = findViewById(R.id.nameText);
        TextView numberText = findViewById(R.id.numberText);
        TextView newMessageText = findViewById(R.id.newMessageText);
        Button finishButton = findViewById(R.id.buttonFinish);

        if(prevActivity.equals("contactList") || prevActivity.equals("contactNumber")){
            nameText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if(stringTranslated.length() > 0) {
                    this.nameContact = stringTranslated;
                    morseTranslateIntent.putExtra("morseTranslateActivity", "contactName");
                    startActivity(morseTranslateIntent);
                }else{
                    //Mostra que string está vazia
                }
            });

            buttonBack.setOnClickListener((view) -> {
                startActivity(contactActivityIntent);

            });

        }

        if(prevActivity.equals("contactName")){
            numberText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if(stringTranslated.length() > 0) {
                    // Checa se e a string só tem números e é do tamanho certo
                    if (stringTranslated.matches("[0-9]+") && stringTranslated.length() >= 2 && stringTranslated.length() <= 12) {
                        this.numberContact = stringTranslated;
                        contactActivityIntent.putExtra("morseTranslateActivity", "contactNumber");
                        startActivity(contactActivityIntent);
                        addOnContactList();
                    }else{
                        //Mostra que número é inválido
                    }
                }else{
                    //Mostra que string está vazia
                }
            });

            buttonBack.setOnClickListener((view) -> {
                translateActivityIntent.putExtra("morseTranslateActivity", "contactNumber");
                startActivity(translateActivityIntent);

            });

        }

        if(prevActivity.equals("buttonAdd")){
            SelecaoMensagem mesageList = new SelecaoMensagem();

            newMessageText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if(stringTranslated.length() > 0) {
                    mesageList.getList().add(stringTranslated);
                    startActivity(selecaoActivityIntent);
                }else{
                    //Mostra que string está vazia
            }
            });

            buttonBack.setOnClickListener((view) -> {
                startActivity(selecaoActivityIntent);

            });


        }
        if(prevActivity.equals("messageType")){
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if(stringTranslated.length() > 0) {
                    messageTypeActivityIntent.putExtra("morseTranslateActivity", "newMessage");
                    startActivity(messageTypeActivityIntent);
                }else{
                    //Mostra que string está vazia
                }
            });

            buttonBack.setOnClickListener((view) -> {
                startActivity(messageTypeActivityIntent);

            });

        }



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

    public String getNameContact() {
        return nameContact;
    }

    public String getNumberContact() {
        return numberContact;
    }

    public void addOnContactList(){
        c.contatos.put(getNameContact(), getNumberContact());
    }

    private void showToast(String text) {

        // Constrói uma bolha de duração curta.
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);

        // Mostra essa bolha.
        toast.show();
    }
}