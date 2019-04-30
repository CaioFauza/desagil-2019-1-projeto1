package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class ContatosActivity extends AppCompatActivity {
    private int at = 0;
    public static String name;
    public static HashMap<String, String> contatos = new HashMap<>();
    private LinkedList<TextView> views = new LinkedList<>();
    private LinkedList<String> keys;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        Button addContactButton = findViewById(R.id.buttonAddContact);
        Intent morseTranslateIntent = new Intent(this, MorseTranslate.class);
        Intent mainIntent = new Intent(this, MainActivity.class);
        MorseTranslate m = new MorseTranslate();

        Intent contactActivityIntent = getIntent();
        String prevActivity = contactActivityIntent.getStringExtra("morseTranslateActivity");

        addContactButton.setOnClickListener((view) -> {

            morseTranslateIntent.putExtra("morseTranslateActivity", "contactList");
            startActivity(morseTranslateIntent);


        });





        keys = new LinkedList<>(contatos.keySet());
        Collections.sort(keys);

        TextView textList0 = findViewById(R.id.text_list0);
        views.add(textList0);
        TextView textList1 = findViewById(R.id.text_list1);
        views.add(textList1);
        TextView textList2 = findViewById(R.id.text_list2);
        views.add(textList2);
        TextView textList3 = findViewById(R.id.text_list3);
        views.add(textList3);
        TextView textList4 = findViewById(R.id.text_list4);
        views.add(textList4);
        TextView textList5 = findViewById(R.id.text_list5);
        views.add(textList5);
        TextView textList6 = findViewById(R.id.text_list6);
        views.add(textList6);
        TextView textList7 = findViewById(R.id.text_list7);
        views.add(textList7);
        TextView textList8 = findViewById(R.id.text_list8);
        views.add(textList8);



        Button buttonDel = findViewById(R.id.button_del);
        Button buttonBack = findViewById(R.id.button_back);
        Button buttonUp = findViewById(R.id.button_up);
        Button buttonDown = findViewById(R.id.button_down);

        if(contatos.size() == 0){
            views.get(0).setText("Lista Vazia");
        }else{
            if(contatos.size() > views.size()){
                for (int i = 0; i < views.size(); i++) {
                    views.get(i).setText(keys.get(i)+ " " + contatos.get(keys.get(i)));
                }
            }else{
                for (int i = 0; i < contatos.size(); i++){
                    views.get(i).setText(keys.get(i)+ " " + contatos.get(keys.get(i)));
                }

            }
        }

        buttonUp.setOnClickListener((view) -> {
            if(at < contatos.size()-1){
                at += 1;
                updateList();
            }

        });

        buttonDown.setOnClickListener((view) -> {
            if(at > 0){
                at -= 1;
                updateList();
            }
        });




        buttonDel.setOnClickListener((view) -> {
            if(contatos.size() == 0){
                updateList();
            }
            else{
                this.contatos.remove(keys.get(at));
                updateList();
            }
        });

        buttonBack.setOnClickListener((view) -> {
            mainIntent.putExtra("morseTranslateActivity", "contactList");
            startActivity(mainIntent);
        });

    }

    public void setName() { this.name = keys.get(at); }

    public String getName() { return name; }

    private void updateList(){
        keys = new LinkedList<>(contatos.keySet());
        Collections.sort(keys);
        if(contatos.size() == 0) {
            views.get(0).setText("Lista Vazia");
        }else if(contatos.size() - at > views.size()){
            for (int i = 0; i < views.size(); i++){
                views.get(i).setText(keys.get(i + at)+ " " + contatos.get(keys.get(i + at)));
            }
        }else{
            for (int i = 0; i < contatos.size() - at; i++){
                views.get(i).setText(keys.get(i + at)+ " " + contatos.get(keys.get(i + at)));
            }
            for (int i = contatos.size() - at; i < views.size(); i++){
                views.get(i).setText("");
            }
        }

    }
}