package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

public class SelecaoMensagem extends AppCompatActivity {
    private int at = 0;
    private LinkedList<String> list = new LinkedList<>();
    private LinkedList<TextView> views = new LinkedList<>();

    private void startMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);


        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_mensagem);
        list.add("oi");
        list.add("caca");
        list.add("banana");
        list.add("plane");
        list.add("dado");
        list.add("ULA");
        list.add("PC");
        list.add("Couve");
        list.add("Jaca");
        list.add("Haha");
        list.add("oi");
        list.add("caca");
        list.add("banana");
        list.add("plane");
        list.add("dado");
        list.add("ULA");
        list.add("PC");
        list.add("Couve");
        list.add("Jaca");
        list.add("Haha");


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
        TextView textList9 = findViewById(R.id.text_list9);
        views.add(textList9);
        Button buttonChoice = findViewById(R.id.button_choice);
        Button buttonBack = findViewById(R.id.button_back);
        Button buttonUp = findViewById(R.id.button_up);
        Button buttonDown = findViewById(R.id.button_down);

        if(list.size() == 0){
            views.get(0).setText("Lista Vazia");
        }else{
            if(list.size() > views.size()){
                for (int i = 0; i < views.size(); i++) {
                    views.get(i).setText(list.get(i));
                }
            }else{
                for (int i = 0; i < list.size(); i++){
                    views.get(i).setText(list.get(i));
                }

            }
        }
        buttonUp.setOnClickListener((view) -> {
            if(at < list.size()-1){
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

        buttonBack.setOnClickListener((view) -> {
            startMainActivity();
        });

    }
    private void updateList(){
        if(list.size() - at > views.size()){
            for (int i = 0; i < views.size(); i++){
                views.get(i).setText(list.get(i + at));
            }
        }else{
            for (int i = 0; i < list.size() - at; i++){
                views.get(i).setText(list.get(i + at));
            }
            for (int i = list.size() - at; i < views.size(); i++){
                views.get(i).setText("");
            }
        }

    }
}
