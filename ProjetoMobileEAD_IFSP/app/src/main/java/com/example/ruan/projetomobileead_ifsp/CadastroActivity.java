//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package com.example.ruan.projetomobileead_ifsp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DataBase.DBHelper;
import Models.Pessoa;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGravar, btnVoltar, btn;
    private EditText edtTexNome, edtTexCpf, edtTexIdade, edtTexTelefone, edtTexEmail;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.dbHelper = new DBHelper(this);

        edtTexNome = (EditText)findViewById(R.id.edtTexNome);
        edtTexCpf = (EditText)findViewById(R.id.edtTexCpf);
        edtTexIdade = (EditText)findViewById(R.id.edtTexIdade);
        edtTexTelefone = (EditText)findViewById(R.id.edtTexTelefone);
        edtTexEmail = (EditText)findViewById(R.id.edtTexEmail);

        btnGravar = (Button)findViewById(R.id.btnGravar);
        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
        btnGravar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btnGravar.isPressed()){
            if(edtTexNome.getText().length() > 0 && edtTexCpf.getText().length() > 0
                    && edtTexIdade.getText().length() > 0 && edtTexTelefone.getText().length() > 0
                    && edtTexEmail.getText().length() > 0){

                Pessoa pessoa = new Pessoa();
                pessoa.setNome(edtTexNome.getText().toString());
                pessoa.setCpf(edtTexCpf.getText().toString());
                pessoa.setIdade(edtTexIdade.getText().toString());
                pessoa.setTelefone(edtTexTelefone.getText().toString());
                pessoa.setEmail(edtTexEmail.getText().toString());

                dbHelper.insert(pessoa);

                edtTexNome.getText().clear();
                edtTexCpf.getText().clear();
                edtTexIdade.getText().clear();
                edtTexTelefone.getText().clear();
                edtTexEmail.getText().clear();

                AlertDialog.Builder adb = new AlertDialog.Builder(CadastroActivity.this);
                adb.setTitle("Sucesso");
                adb.setMessage("Cadastro efetuado com sucesso!!");
                adb.show();
            }


        }
        if (btnVoltar.isPressed()){
            this.voltar();
        }
        if (btn.isPressed()){
            for (Pessoa p : dbHelper.queryGetAll()) {
                AlertDialog.Builder adb = new AlertDialog.Builder(CadastroActivity.this);
                adb.setTitle("Registro");
                adb.setMessage(p.toString());
                adb.setPositiveButton("OK", null);
                adb.show();
            }
        }
    }

    private void voltar(){
        Intent intent = new Intent();
        intent.setClass(CadastroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
