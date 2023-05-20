package com.example.projetandroidprg17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText nom;
    private EditText prenom;
    private EditText telephone;
    private EditText adresse;
    private EditText login;
    private EditText password;
    private Button enregistrer;
    private Button annuler;
    private Connection connection;

    private String strNom = "";
    private String strPrenom = "";
    private String strTelephone = "";
    private String strAdresse = "";
    private String strLogin = "";
    private String strPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom = findViewById(R.id.Txtnom);
        prenom = findViewById(R.id.Txtprenom);
        telephone = findViewById(R.id.Txttel);
        adresse = findViewById(R.id.Txtadresse);
        login = findViewById(R.id.Txtlogin);
        password = findViewById(R.id.Txtpwd);
        enregistrer = findViewById(R.id.btnenregistrer);
        annuler = findViewById(R.id.btnannuler);

        enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strNom = nom.getText().toString();
                strPrenom = prenom.getText().toString();
                strTelephone = telephone.getText().toString();
                strAdresse = adresse.getText().toString();
                strLogin = login.getText().toString();
                strPassword = password.getText().toString();

                if(strNom.equalsIgnoreCase("") || strPrenom.equalsIgnoreCase("")
                || strTelephone.equalsIgnoreCase("") || strAdresse.equalsIgnoreCase("")
                || strLogin.equalsIgnoreCase("") || strPassword.equalsIgnoreCase(""))
                {
                    Toast.makeText(MainActivity.this,"Veuillez remplir tout les champs",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    connection = new Connection();
                    connection.setNom(nom.getText().toString());
                    connection.setPrenom(prenom.getText().toString());
                    connection.setTelephone(telephone.getText().toString());
                    connection.setAdresse(adresse.getText().toString());
                    connection.setLogin(login.getText().toString());
                    connection.setPassword(password.getText().toString());
                }

            }
        });

    }
}