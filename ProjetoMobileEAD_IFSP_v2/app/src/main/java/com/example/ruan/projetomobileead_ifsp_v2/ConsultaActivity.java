//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package com.example.ruan.projetomobileead_ifsp_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import DataBase.DBHelper;
import Models.Pessoa;

public class ConsultaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnVoltar;
    private ListView listViewRegistros;
    private ArrayAdapter<Pessoa> adapter = null;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        this.dbHelper = new DBHelper(this);

        adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, dbHelper.queryGetAll());


        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        listViewRegistros = (ListView)findViewById(R.id.listViewRegistros);
        btnVoltar.setOnClickListener(this);

        listViewRegistros.setAdapter(adapter);
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
