//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package com.example.ruan.projetomobileead_ifsp_v3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Eventos.ApagarPessoa;
import DataBase.DBHelper;
import Models.Pessoa;

public class ConsultaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private Button btnVoltar;
    private ListView listViewRegistros;

    ArrayList<Pessoa> listaPessoas = null; //Lista para armazenar os registros do banco
    private ArrayAdapter<Pessoa> adapter = null; //Adapter para exibir os dados da lista no ListView
    private DBHelper dbHelper;
    private Pessoa pessoaEscolhida = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        //Inicialização do banco
        this.dbHelper = new DBHelper(this);
        //Este trecho está obtendo os registros do banco e armazenando no Array
        listaPessoas = (ArrayList<Pessoa>) dbHelper.queryGetAll();

        btnVoltar = (Button)findViewById(R.id.btnVoltar);
        listViewRegistros = (ListView)findViewById(R.id.listViewRegistros);
        listViewRegistros.setOnItemClickListener(this);
        btnVoltar.setOnClickListener(this);
        //O trecho abaixo verifica se o Array contém registros
        if (listaPessoas != null){
            //criação do adapter que "converterá" o Array em itens do ListView
            //Os parâmetros que estão sendo passados para o construtor do adapter são:
            // 1- referência para esta activity; 2- identificador do ListView; 3- Lista que será "convertida"/adaptada
            adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, listaPessoas);
            listViewRegistros.setAdapter(adapter);
        }else {
            AlertDialog.Builder adb = new AlertDialog.Builder(ConsultaActivity.this);
            adb.setMessage("Não existem dados armazenados.");
            adb.setPositiveButton("OK", null); //ref. which: -1
            adb.show();
        }

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

    //Este trecho se refere ao evento de clicar em algum item do ListView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(ConsultaActivity.this);
        adb.setTitle("Detalhes");
        //o trecho abaixo seleciona o objeto que foi clicado no ListView
        pessoaEscolhida = adapter.getItem(position);
        //o trecho abaixo mostra os detalhes do objeto selecionado em uma caixa de diálogo
        adb.setMessage(pessoaEscolhida.pessoaDetails());
        //O trecho abaixo exibe duas opções para ser clicado na caixa de diálogo
        adb.setPositiveButton("VOLTAR", null); //ref. which: -1
        adb.setNeutralButton("APAGAR?", this); //ref. which: -3
//        adb.setNegativeButton("NO", this); //ref. which: -2
        adb.show();
    }

    //O trecho abaixo se refere ao evento de quando é clicado sobre alguma opção da caixa de diálogo
    @Override
    public void onClick(DialogInterface dialog, int which) {
        //O trecho abaixo verifica se foi pressionado o a opção "APAGAR?" da caixa de diálogo, cujo
        // o tipo é NeutralButton, e sua referência é -3
        if(which == -3){
            //Este trecho faz a checagem se realmente foi selecionado uma pessoa no ListView
            //E pede a confirmação do usuário para a exclusão
            if (pessoaEscolhida != null){
                AlertDialog.Builder adb = new AlertDialog.Builder(ConsultaActivity.this);
                adb.setMessage("Deseja realmente apagar o cadastro?");
                //Caso o usuário selecione a opção "SIM", é acionado o evento ApagarPessoa que faz a esclusão
                //do registro no banco
                adb.setPositiveButton("SIM", new ApagarPessoa(dbHelper, pessoaEscolhida, listaPessoas, adapter));
                adb.setNegativeButton("NÃO", null);
                adb.show();

                pessoaEscolhida = null;
            }
        }


    }
}
