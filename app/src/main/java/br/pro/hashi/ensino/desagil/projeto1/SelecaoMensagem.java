package br.pro.hashi.ensino.desagil.projeto1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

public class SelecaoMensagem extends AppCompatActivity {
    private int at = 0;
    public static String name;
    private LinkedList<String> list = new LinkedList<>();
    private LinkedList<TextView> views = new LinkedList<>();
    private static final int REQUEST_SEND_SMS = 0;

    private void startMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);


        startActivity(intent);
    }

    private void startSMSActivity() {

        Intent intent = new Intent(this, SMSActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_mensagem);

        list.add("Estou com frio");
        list.add("Estou com calor");
        list.add("Estou com fome");
        list.add("Estou com sede");
        list.add("Estou com dor");
        list.add("Estou com sono");
        list.add("Me leve para a sala");
        list.add("Me leve para a cozinha");
        list.add("Me leve para o quintal");
        list.add("Me leve para o quarto");
        list.add("Me leve para o banheiro");
        list.add("Preciso me coçar");
        list.add("Preciso me limpar");
        list.add("Não estou bem, chame um médico!");
        list.add("Não estou bem, venha me ajudar");
        list.add("Bom dia!");
        list.add("Boa tarde!");
        list.add("Boa noite!");
        list.add("Tudo bem com você?");
        list.add("Estou bem");

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


        buttonChoice.setOnClickListener((view) -> {
            setName();

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                startSMSActivity();
            } else {

                String[] permissions = new String[]{
                        Manifest.permission.SEND_SMS,
                };

                ActivityCompat.requestPermissions(this, permissions, REQUEST_SEND_SMS);
            }

        });

    }

    public void setName() { this.name = list.get(at); }

    public String getName() { return name; }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_SEND_SMS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startSMSActivity();

        }
    }
}
