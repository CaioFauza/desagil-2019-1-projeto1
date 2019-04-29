package br.pro.hashi.ensino.desagil.projeto1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MorseTranslate extends AppCompatActivity {

    private String stringTranslated = "";
    private String preTranslated = "";
    private String wordTranslated;
    private static String nameContact;
    private static String numberContact;
    public boolean word;
    ContatosActivity c = new ContatosActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_translate);
        Translator translator = new Translator();
        Intent morseTranslateIntent = getIntent();
        String prevActivity = morseTranslateIntent.getStringExtra("morseTranslateActivity");
        Intent contactActivityIntent = new Intent(this, ContatosActivity.class);
        Intent selecaoActivityIntent = new Intent(this, SelecaoMensagem.class);



        Button buttonMorse = findViewById(R.id.buttonMorse);
        TextView morseText = findViewById(R.id.morseText);
        Button buttonSet = findViewById(R.id.buttonSet);
        Button butonDel = findViewById(R.id.buttonDel);
        Button buttonSpace = findViewById(R.id.buttonSpace);
        Button buttonBack = findViewById(R.id.buttonBack);
        TextView nameText = findViewById(R.id.nameText);
        TextView numberText = findViewById(R.id.numberText);
        TextView newMessageText = findViewById(R.id.newMessageText);
        Button finishButton = findViewById(R.id.buttonFinish);

        if(prevActivity.equals("contactListName")){
            nameText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                this.nameContact = morseText.getText().toString();
                morseTranslateIntent.putExtra("morseTranslateActivity", "contactListNumber");
                startActivity(morseTranslateIntent);

            });

        }

        if(prevActivity.equals("contactListNumber")){
            numberText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                this.numberContact = morseText.getText().toString();
                contactActivityIntent.putExtra("morseTranslateActivity","contact");
                startActivity(contactActivityIntent);
                addOnContactList();

            });

        }

        if(prevActivity.equals("buttonAdd")){
            SelecaoMensagem mesageList = new SelecaoMensagem();

            newMessageText.setVisibility(View.VISIBLE);
            finishButton.setOnClickListener((view) -> {
                mesageList.getList().add(stringTranslated);
                startActivity(selecaoActivityIntent);

            });

            buttonBack.setOnClickListener((view) -> {
                startActivity(selecaoActivityIntent);

            });
        }



        buttonSet.setOnClickListener((view) -> {

            word = true;

            wordTranslated = String.valueOf(translator.morseToChar(preTranslated));

            if(!"!".equals(wordTranslated) && !"*".equals(wordTranslated)){ stringTranslated += wordTranslated; }

            morseText.setText(stringTranslated);
            preTranslated = "";

        });

        buttonSpace.setOnClickListener((view) -> {
            if(preTranslated.length() == 0){
                word = true;
            }
            if(word && stringTranslated.length() != 0){
                stringTranslated += " ";
                morseText.setText(stringTranslated);
            }
        });

        butonDel.setOnClickListener((view) -> {

            if(preTranslated.length() == 0){
                word = true;
            }

            if(word && stringTranslated.length() != 0){
                stringTranslated = stringTranslated.substring(0, stringTranslated.length() - 1);
                morseText.setText(stringTranslated);
            }
            if(!word && preTranslated.length() != 0) {
                preTranslated = preTranslated.substring(0, preTranslated.length() - 1);
                morseText.setText(stringTranslated);
                morseText.append(preTranslated);
            }
        });

        buttonMorse.setOnClickListener((view) -> {
            word = false;
            preTranslated += ".";
            morseText.append(".");
        });


        buttonMorse.setOnLongClickListener((view) -> {
            word = false;
            preTranslated += "-";
            morseText.append("-");

            return true;

        });

    }

    public String getNameContact() {
        return nameContact;
    }

    public String getNumberContact() {
        return numberContact;
    }

    public void addOnContactList(){
        c.contatos.put(getNameContact(), getNumberContact());
    }
}