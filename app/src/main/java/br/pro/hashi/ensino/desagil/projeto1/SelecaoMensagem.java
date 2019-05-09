package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class SelecaoMensagem extends AppCompatActivity implements ValueEventListener {
    private static String name;
    private static LinkedList<String> list = new LinkedList<>();
    private static HashMap<String, String> map = new HashMap<>();
    private final LinkedList<TextView> views = new LinkedList<>();
    private int at = 0;
    private String lista = "Aguardando Firebase";

    private void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startMessageType() {

        Intent intent = new Intent(this, MessageType.class);


        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_mensagem);

        Intent morseTranslateIntent = new Intent(this, MorseTranslate.class);
        Intent contatosActivity = new Intent(this, ContatosActivity.class);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference referenceMensagens = database.getReference("mensagensProntas");

        referenceMensagens.addValueEventListener(this);


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
        Button buttonUp = findViewById(R.id.button_down);
        Button buttonDown = findViewById(R.id.button_up);
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonDel = findViewById(R.id.button_del);

        if (list.size() == 0) {
            views.get(0).setText(lista);
        } else {
            if (list.size() > views.size()) {
                for (int i = 0; i < views.size(); i++) {
                    views.get(i).setText(list.get(i));
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    views.get(i).setText(list.get(i));
                }

            }
        }
        buttonUp.setOnClickListener((view) -> {
            if (at < list.size() - 1) {
                at += 1;
                updateList();
            }

        });

        buttonDown.setOnClickListener((view) -> {
            if (at > 0) {
                at -= 1;
                updateList();
            }
        });

        buttonBack.setOnClickListener((view) -> startMessageType());

        buttonAdd.setOnClickListener((view) -> {

            morseTranslateIntent.putExtra("morseTranslateActivity", "buttonAdd");
            startActivity(morseTranslateIntent);


        });

        buttonChoice.setOnClickListener((view) -> {
            if (list.size() > 0) {
                setMessage();
                contatosActivity.putExtra("contatosActivityIntent", "messageSendSelection");
                startActivity(contatosActivity);
            }

        });

        buttonDel.setOnClickListener((view) -> {
            if (list.size() == 1) {
                updateList();
                showToast("Uma mensagem é necessária!");
            } else {
                referenceMensagens.child(list.get(at)).removeValue();
                if (at > 0) {
                    at -= 1;
                }
                updateList();
                showToast("Mensagem removida com sucesso!");
            }
        });

    }

    private void setMessage() {
        name = list.get(at);
    }

    public String getMessage() {
        return name;
    }

    private void updateList() {
        list = new LinkedList<>();
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        if (list.size() == 0) {
            views.get(0).setText("Lista Vazia");
        } else if (list.size() - at > views.size()) {
            for (int i = 0; i < views.size(); i++) {
                views.get(i).setText(list.get(i + at));
            }
        } else {
            for (int i = 0; i < list.size() - at; i++) {
                views.get(i).setText(list.get(i + at));
            }
            for (int i = list.size() - at; i < views.size(); i++) {
                views.get(i).setText("");
            }
        }

    }

    // Este método é chamado uma vez durante a chamada
    // de addValueEventListener acima e depois sempre
    // que algum valor em /b sofrer alguma mudança.
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        try {

            // O método getValue recebe como parâmetro uma
            // classe Java que representa o tipo de dado
            // que você acredita estar lá. Se você errar,
            // esse método vai lançar uma DatabaseException.
            map = (HashMap<String, String>) dataSnapshot.getValue();
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
