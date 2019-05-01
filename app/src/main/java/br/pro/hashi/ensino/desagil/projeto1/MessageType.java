package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MessageType extends AppCompatActivity {

    private void startMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startSelecaoMensagem(){

        Intent intent = new Intent(this, SelecaoMensagem.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_type);


        Button newMessage = findViewById(R.id.buttonNewMessage);
        Button preMessage = findViewById(R.id.buttonOldMessages);
        Button buttonBack = findViewById(R.id.buttonBack);
        Intent morseTranslateIntent = new Intent(this, MorseTranslate.class);

        newMessage.setOnClickListener((view) -> {
            morseTranslateIntent.putExtra("morseTranslateActivity", "newMessage");
            startActivity(morseTranslateIntent);


        });

        buttonBack.setOnClickListener((view) -> {
            startMainActivity();
        });

        preMessage.setOnClickListener((view) -> {
            startSelecaoMensagem();

        });
    }
}
