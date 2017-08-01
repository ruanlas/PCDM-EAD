//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package com.example.ruan.projetomobileead_ifsp_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DataBase.DBHelper;
import Models.Pessoa;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGravar, btnVoltar;
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
//        btn = (Button)findViewById(R.id.btn);
//        btn.setOnClickListener(this);
        btnVoltar.setOnClickListener(this);
        btnGravar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btnGravar.isPressed()){
            this.cadastrar();
        }
        if (btnVoltar.isPressed()){
            this.voltar();
        }
//        if (btn.isPressed()){
//            for (Pessoa p : dbHelper.queryGetAll()) {
//                AlertDialog.Builder adb = new AlertDialog.Builder(CadastroActivity.this);
//                adb.setTitle("Registro");
//                adb.setMessage(p.toString());
//                adb.setPositiveButton("OK", null);
//                adb.show();
//            }
//        }
    }

    private void cadastrar(){
        //este if verifica se todos os campos foram preenchidos. O cadastro somente será realizado
        //se todos os campos forem devidamente preenchidos
        if(edtTexNome.getText().length() > 0 && edtTexCpf.getText().length() > 0
                && edtTexIdade.getText().length() > 0 && edtTexTelefone.getText().length() > 0
                && edtTexEmail.getText().length() > 0){
            //Criação do objeto Pessoa
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(edtTexNome.getText().toString());
            pessoa.setCpf(edtTexCpf.getText().toString());
            pessoa.setIdade(Integer.parseInt(edtTexIdade.getText().toString()));
            pessoa.setTelefone(edtTexTelefone.getText().toString());
            pessoa.setEmail(edtTexEmail.getText().toString());
            //Inserção do objeto Pessoa no Banco
            dbHelper.insert(pessoa);
            //Limpando os campos digitados
            edtTexNome.getText().clear();
            edtTexCpf.getText().clear();
            edtTexIdade.getText().clear();
            edtTexTelefone.getText().clear();
            edtTexEmail.getText().clear();
            //Move o cursor para o campo nome
            edtTexNome.requestFocus();

            AlertDialog.Builder adb = new AlertDialog.Builder(CadastroActivity.this);
            adb.setTitle("Status");
            adb.setMessage("Cadastro efetuado com sucesso!!");
            adb.setPositiveButton("OK", null);
            adb.show();
        }else {
            AlertDialog.Builder adb = new AlertDialog.Builder(CadastroActivity.this);
            adb.setTitle("Campos em branco");
            adb.setMessage("Existem campos que não foram preenchidos. " +
                    "Preencha devidamente todos os campos e tente novamente.");
            adb.setPositiveButton("OK", null);
            adb.show();
        }
    }

    private void voltar(){
        Intent intent = new Intent();
        intent.setClass(CadastroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
