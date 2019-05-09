package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    private final SelecaoMensagem preDefined = new SelecaoMensagem();
    private final MorseTranslate newMessage = new MorseTranslate();
    private final ContatosActivity contactNumber = new ContatosActivity();

    private void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);


        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        //EditText textMessage = findViewById(R.id.text_message);
        TextView textExample = findViewById(R.id.text_message);
        TextView textPhone = findViewById(R.id.text_phone);
        Button buttonSend = findViewById(R.id.buttonSend);
        Button buttonBack = findViewById(R.id.buttonBack);
        Intent contatosActivity = new Intent(this, ContatosActivity.class);

        Intent contatosActivityIntent = getIntent();
        String prevActivity = contatosActivityIntent.getStringExtra("SMSActivity");


        ////////selection

        if (prevActivity.equals("selectMessages")) {


            textExample.setText(preDefined.getMessage());
            textPhone.setText(contactNumber.getNumber());

            buttonBack.setOnClickListener((view) -> {
                contatosActivity.putExtra("contatosActivityIntent", "messageSendSelection");
                startActivity(contatosActivity);

                textPhone.setText("");

            });

            buttonSend.setOnClickListener((view) -> {

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(contactNumber.getNumber(), null, preDefined.getMessage(), null, null);
                textExample.setText("");
                textPhone.setText("");
                startMainActivity();
                showToast("Mensagem enviada com sucesso!");

            });

        }

        ////////selection

        if (prevActivity.equals("newMessages")) {

            System.out.println(contactNumber.getNumber());

            textExample.setText(newMessage.getMessage());
            textPhone.setText(contactNumber.getNumber());

            buttonBack.setOnClickListener((view) -> {
                contatosActivity.putExtra("contatosActivityIntent", "messageSendType");
                startActivity(contatosActivity);

                textPhone.setText("");

            });

            buttonSend.setOnClickListener((view) -> {

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(contactNumber.getNumber(), null, newMessage.getMessage(), null, null);
                textExample.setText("");
                textPhone.setText("");
                startMainActivity();
                showToast("Mensagem enviada com sucesso!");

            });

        }


    }
}
