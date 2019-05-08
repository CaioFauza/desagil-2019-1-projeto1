package br.pro.hashi.ensino.desagil.projeto1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ContatosActivity extends AppCompatActivity implements ValueEventListener {
    private int at = 0;
    private String lista = "Aguardando Firebase";
    public static String contacts;
    public String value;
    public static HashMap<String, String> contatos = new HashMap<>();
    private LinkedList<TextView> views = new LinkedList<>();
    private LinkedList<String> keys;
    private static final int REQUEST_SEND_SMS = 0;

    private void showToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
    private void startMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startSMSActivity(String value) {

        Intent SMSActivity = new Intent(this, SMSActivity.class);
        SMSActivity.putExtra("SMSActivity", value);
        startActivity(SMSActivity);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);


        Intent morseTranslateIntent = new Intent(this, MorseTranslate.class);
        Intent selecaoMensagemIntent = new Intent(this, SelecaoMensagem.class);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference referenceContatos = database.getReference("contatos");

        referenceContatos.addValueEventListener(this);


        Intent contatosActivityIntent = getIntent();
        String prevActivity = contatosActivityIntent.getStringExtra("contatosActivityIntent");

        //contatos = referenceContatos;


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

        System.out.println(referenceContatos.getDatabase());



        Button buttonDel = findViewById(R.id.button_del);
        Button buttonBack = findViewById(R.id.button_back);
        Button buttonUp = findViewById(R.id.button_up);
        Button buttonDown = findViewById(R.id.button_down);
        Button addContactButton = findViewById(R.id.buttonAddContact);
        Button choiceButton = findViewById(R.id.button_choice);

        if(contatos.size() == 0){
            views.get(0).setText(lista);
        }else{
            if(contatos.size() > views.size()){
                for (int i = 0; i < views.size(); i++) {
                    views.get(i).setText(keys.get(i)+ ": " + contatos.get(keys.get(i)));
                }
            }else{
                for (int i = 0; i < contatos.size(); i++){
                    views.get(i).setText(keys.get(i)+ ": " + contatos.get(keys.get(i)));
                }

            }

            setNumber(contatos.get(keys.get(at)));
        }

        ///////mainpage


        if(prevActivity.equals("mainPage")){

            addContactButton.setVisibility(View.VISIBLE);
            buttonDel.setVisibility(View.VISIBLE);

            buttonDel.setOnClickListener((view) -> {
                if(contatos.size() == 0){
                    updateList();
                }
                else{
                    this.contatos.remove(keys.get(at));
                    updateList();
                    showToast("Contato removido com sucesso!");
                }
            });

            buttonBack.setOnClickListener((view) -> {
                startMainActivity();
            });


            addContactButton.setOnClickListener((view) -> {

                morseTranslateIntent.putExtra("morseTranslateActivity", "contactList");
                startActivity(morseTranslateIntent);


            });

        }

        //////messagesend

        if(prevActivity.equals("messageSendSelection")){
            choiceButton.setVisibility(View.VISIBLE);
            value = "selectMessages";


            choiceButton.setOnClickListener((view) -> {

                if (contatos.size() != 0){

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        startSMSActivity(value);

                    } else {

                        String[] permissions = new String[]{
                                Manifest.permission.SEND_SMS,
                        };

                        ActivityCompat.requestPermissions(this, permissions, REQUEST_SEND_SMS);
                    }

                }

                else{
                }

            });

            buttonBack.setOnClickListener((view) -> {
                startActivity(selecaoMensagemIntent);
            });

        }

        if(prevActivity.equals("messageSendType")){
            choiceButton.setVisibility(View.VISIBLE);
            value = "newMessages";

            choiceButton.setOnClickListener((view) -> {
                if (contatos.size() != 0) {
                    startSMSActivity(value);
                }

            });

            buttonBack.setOnClickListener((view) -> {
                morseTranslateIntent.putExtra("morseTranslateActivity", "newMessage");
                startActivity(morseTranslateIntent);
            });

        }

        /////general

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



    }

    public void setNumber(String contacts) { this.contacts =  contacts; }

    public String getNumber() { return contacts; }

    private void updateList(){
        keys = new LinkedList<>(contatos.keySet());
        Collections.sort(keys);

        if(contatos.size() == 0) {
            views.get(0).setText("Lista Vazia");
        }else if(contatos.size() - at > views.size()){
            for (int i = 0; i < views.size(); i++){
                views.get(i).setText(keys.get(i + at)+ ": " + contatos.get(keys.get(i + at)));
            }


        }else{
            for (int i = 0; i < contatos.size() - at; i++){
                views.get(i).setText(keys.get(i + at)+ ": " + contatos.get(keys.get(i + at)));
            }
            setNumber(contatos.get(keys.get(at)));

            for (int i = contatos.size() - at; i < views.size(); i++){
                views.get(i).setText("");
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_SEND_SMS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startSMSActivity(value);

        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        try {

            // O método getValue recebe como parâmetro uma
            // classe Java que representa o tipo de dado
            // que você acredita estar lá. Se você errar,
            // esse método vai lançar uma DatabaseException.
            this.contatos = (HashMap<String, String>) dataSnapshot.getValue();
            lista = "Lista vazia";
            updateList();
            //this.contatos = map;
        } catch (DatabaseException exception) {
            lista = "Firebase fora do ar";
        }

    }

    // Este método é chamado caso ocorra algum problema
    // com a conexão ao banco de dados do Firebase.
    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}