package br.pro.hashi.ensino.desagil.projeto1;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.LinkedList;


public class MorseTranslate extends AppCompatActivity {

    private static String nameContact;
    private static String numberContact;
    private static String newMessage;
    private String stringTranslated = "";
    private String preTranslated = "";
    private String wordTranslated;
    private boolean word;
    private LinkedList<String> morse;
    private LinkedList<String> alfaNum;

    private void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_translate);
        Translator translator = new Translator();
        Intent morseTranslateIntent = getIntent();

        String prevActivity = morseTranslateIntent.getStringExtra("morseTranslateActivity");
        Intent contactActivityIntent = new Intent(this, ContatosActivity.class);
        Intent selecaoActivityIntent = new Intent(this, SelecaoMensagem.class);
        Intent messageTypeActivityIntent = new Intent(this, MessageType.class);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referenceMensagens = database.getReference("mensagensProntas");
        DatabaseReference referenceContatos = database.getReference("contatos");


        morse = new LinkedList<>();
        alfaNum = new LinkedList<>();


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
        Button buttonDict = findViewById(R.id.buttonDict);

        morse = translator.getCodes();
        for (String code : morse) {
            alfaNum.add(Character.toString(translator.morseToChar(code)));
        }
        Collections.sort(alfaNum);

        for (int i = 0; i < alfaNum.size(); i++) {
            alfaNum.set(i, alfaNum.get(i) + " : " + translator.charToMorse((alfaNum.get(i)).charAt(0)) + "\n");
        }
        for (int i = 0; i < morse.size(); i++) {
            morse.set(i, morse.get(i) + " : " + translator.morseToChar((morse.get(i))) + "\n");
        }

        buttonDict.setOnClickListener((view) -> {
            String dict = ("Romano para morse: \n " + alfaNum.toString() + "\n \n \n Morse para romano: \n" + morse.toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String dict1 = dict.replace(",", "");
            String dict2 = dict1.replace("[", "");


            builder.setMessage(dict2.replace("]", ""));
            builder.setTitle("Dicionário");
            builder.setPositiveButton("Ok", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        if (prevActivity.equals("contactList") || prevActivity.equals("contactNumber")) {
            nameText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if (stringTranslated.length() > 0) {
                    nameContact = stringTranslated;
                    morseTranslateIntent.putExtra("morseTranslateActivity", "contactName");
                    startActivity(morseTranslateIntent);
                } else {
                    showToast("Nome inválido!");
                }
            });

            buttonBack.setOnClickListener((view) -> {
                contactActivityIntent.putExtra("contatosActivityIntent", "mainPage");
                startActivity(contactActivityIntent);

            });

        }

        if (prevActivity.equals("contactName")) {
            numberText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if (stringTranslated.length() > 0) {
                    // Checa se e a string só tem números e é do tamanho certo
                    if (stringTranslated.matches("[0-9]+") && stringTranslated.length() >= 2 && stringTranslated.length() <= 12) {
                        numberContact = stringTranslated;
                        contactActivityIntent.putExtra("contatosActivityIntent", "mainPage");
                        referenceContatos.child(nameContact).setValue(numberContact);
                        startActivity(contactActivityIntent);
                        showToast("Número adicionado com sucesso!");
                    } else {
                        showToast("Número inválido!");
                    }
                } else {
                    showToast("Número inválido!");
                }
            });

            buttonBack.setOnClickListener((view) -> {
                morseTranslateIntent.putExtra("morseTranslateActivity", "contactNumber");
                startActivity(morseTranslateIntent);

            });

        }

        if (prevActivity.equals("buttonAdd")) {

            newMessageText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if (stringTranslated.length() > 0) {
                    referenceMensagens.child(stringTranslated).setValue(stringTranslated);
                    startActivity(selecaoActivityIntent);
                    showToast("Mensagem adicionada com sucesso!");
                } else {
                    showToast("Mensagem inválida!");
                }
            });

            buttonBack.setOnClickListener((view) -> startActivity(selecaoActivityIntent));


        }
        if (prevActivity.equals("newMessage")) {
            finishButton.setOnClickListener((view) -> {
                // Se a string não estiver vazia
                if (stringTranslated.length() > 0) {
                    setMessage();
                    contactActivityIntent.putExtra("contatosActivityIntent", "messageSendType");
                    startActivity(contactActivityIntent);
                } else {
                    showToast("Mensagem inválida!");
                }
            });

            buttonBack.setOnClickListener((view) -> startActivity(messageTypeActivityIntent));

        }


        buttonSet.setOnClickListener((view) -> {

            word = true;

            wordTranslated = String.valueOf(translator.morseToChar(preTranslated));

            if (!"!".equals(wordTranslated) && !"*".equals(wordTranslated)) {
                stringTranslated += wordTranslated;
            }

            morseText.setText(stringTranslated);
            preTranslated = "";

        });

        buttonSpace.setOnClickListener((view) -> {
            if (preTranslated.length() == 0) {
                word = true;
            }
            if (word && stringTranslated.length() != 0) {
                stringTranslated += " ";
                morseText.setText(stringTranslated);
            }
        });

        butonDel.setOnClickListener((view) -> {

            if (preTranslated.length() == 0) {
                word = true;
            }

            if (word && stringTranslated.length() != 0) {
                stringTranslated = stringTranslated.substring(0, stringTranslated.length() - 1);
                morseText.setText(stringTranslated);
            }
            if (!word && preTranslated.length() != 0) {
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

    public String getMessage() {
        return newMessage;
    }

    private void setMessage() {
        newMessage = stringTranslated;
    }


}