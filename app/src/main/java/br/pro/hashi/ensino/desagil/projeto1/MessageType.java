package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MessageType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_type);


        Button newMessage = findViewById(R.id.buttonNewMessage);
        Intent morseTranslateIntent = new Intent(this, MorseTranslate.class);

        newMessage.setOnClickListener((view) -> {

            morseTranslateIntent.putExtra("morseTranslateActivity", "newMessage");
            startActivity(morseTranslateIntent);


        });
    }
}
