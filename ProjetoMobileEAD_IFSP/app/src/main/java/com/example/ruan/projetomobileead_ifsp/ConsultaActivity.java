//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package com.example.ruan.projetomobileead_ifsp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ConsultaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnVoltar;
    private ListView listViewRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        listViewRegistros = (ListView)findViewById(R.id.listViewRegistros);
        btnVoltar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btnVoltar.isPressed()){
            Intent intent = new Intent();
            intent.setClass(ConsultaActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
