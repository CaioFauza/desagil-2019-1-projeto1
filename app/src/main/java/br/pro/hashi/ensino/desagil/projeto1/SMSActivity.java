package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    SelecaoMensagem name = new SelecaoMensagem();


    private void startSelecaoMensagem() {

        Intent intent = new Intent(this, SelecaoMensagem.class);


        startActivity(intent);
    }

    private void showToast(String text) {

        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);

        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        System.out.println(name.getName());

        //EditText textMessage = findViewById(R.id.text_message);
        TextView textExample = findViewById(R.id.text_message);
        EditText textPhone = findViewById(R.id.text_phone);
        Button buttonSend = findViewById(R.id.button_send);
        Button buttonBack = findViewById(R.id.button_back);
        textExample.setText(name.getName());

        buttonBack.setOnClickListener((view) -> {
            textPhone.setText("");
            startSelecaoMensagem();
        });

        buttonSend.setOnClickListener((view) -> {
            //String message = textExample.getText().toString();

            String phone = textPhone.getText().toString();

            if (!PhoneNumberUtils.isGlobalPhoneNumber(phone)) {
                showToast("Número inválido!");
                return;
            }

            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(phone, null, name.getName(), null, null);
            textExample.setText("");
            textPhone.setText("");
        });
    }
}
