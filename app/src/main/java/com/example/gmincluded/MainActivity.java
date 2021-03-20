package com.example.gmincluded;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtDestinatario, txtAsunto, txtContenido;
    Button btEnviar;
    String destinatario, asunto, contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEnviar = (Button) findViewById(R.id.btnEnviar);
        txtDestinatario = (EditText) findViewById(R.id.edtDestinatario);
        txtAsunto = (EditText) findViewById(R.id.edtAsunto);
        txtContenido = (EditText) findViewById(R.id.edtContenido);

        btEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                destinatario = txtDestinatario.getText().toString();
                asunto = txtAsunto.getText().toString();
                contenido = txtContenido.getText().toString();

                if (destinatario.isEmpty() || asunto.isEmpty() || contenido.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Oops, parece que falta de rellenar algún campo :)", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "Enviando mensaje...", Toast.LENGTH_SHORT).show();
                    new SendMailTask(MainActivity.this, asunto, contenido).execute();
                    txtDestinatario.setText(null);
                    txtAsunto.setText(null);
                    txtContenido.setText(null);

                }
            }
        });
    }
}