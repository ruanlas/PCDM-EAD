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
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCadastrar, btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnConsultar = (Button)findViewById(R.id.btnConsultar);


        //o trecho abaixo está atribuindo o evento de click aos botões
        btnConsultar.setOnClickListener(this);
        btnCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //O trecho abaixo irá verificar qual botão foi pressionado
        if (btnCadastrar.isPressed()){
            this.carregarActivityCadastro();
        }
        if (btnConsultar.isPressed()){
            this.carregarActivityConsulta();
        }
    }

    private void carregarActivityCadastro(){
        //este método irá chamar a activity de cadastro
        Intent intent = new Intent();
        //Os parâmetros que são passados são respectivamente a classe que irá chamar a outra activity
        //e a classe da activity que será chamada
        intent.setClass(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
        finish();
    }
    private void carregarActivityConsulta(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ConsultaActivity.class);
        startActivity(intent);
        finish();
    }
}
