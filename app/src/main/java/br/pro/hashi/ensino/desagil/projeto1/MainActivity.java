package br.pro.hashi.ensino.desagil.projeto1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private void startSelecaoMensagem() {

        Intent intent = new Intent(this, SelecaoMensagem.class);


        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonExample = findViewById(R.id.button_example);

        buttonExample.setOnClickListener((view) -> {

            startSelecaoMensagem();

        });

    }
}
