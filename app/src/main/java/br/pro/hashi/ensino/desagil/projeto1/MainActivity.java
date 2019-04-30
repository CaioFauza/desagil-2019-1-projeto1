package br.pro.hashi.ensino.desagil.projeto1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private void startContatosActivity() {
        Intent intent = new Intent(this, ContatosActivity.class);


        startActivity(intent);
    }

    private  void startMessageType() {
        Intent intent = new Intent(this, MessageType.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonContacts = findViewById(R.id.buttonContacts);

        Button buttonSendMessage = findViewById(R.id.buttonSendMessage);

        buttonContacts.setOnClickListener((view) -> {
            startContatosActivity();

        });

        buttonSendMessage.setOnClickListener((view) -> {
            startMessageType();

        });
    }
}
