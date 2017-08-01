//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package Eventos;

import android.content.DialogInterface;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import DataBase.DBHelper;
import Models.Pessoa;

/**
 * Created by Ruan on 20/06/2017.
 */

public class ApagarPessoa implements DialogInterface.OnClickListener {
    //Este evento faz a esclusão de uma pessoa do banco.
    private DBHelper dbHelper;
    private Pessoa pessoa;
    private ArrayList<Pessoa> listPessoas;
    private ArrayAdapter<Pessoa> adapter;

    public ApagarPessoa(DBHelper dbHelper, Pessoa pessoa, ArrayList<Pessoa> listPessoas, ArrayAdapter<Pessoa> adapter ){
        this.dbHelper = dbHelper;
        this.pessoa = pessoa;
        this.adapter = adapter;
        this.listPessoas = listPessoas;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        //Deleção do registro no banco
        this.dbHelper.delete(pessoa);
        //remoção do objeto na lista
        this.listPessoas.remove(pessoa);
        //o trecho abaixo faz "atualizar" os registros da Lista no adapter, atualizando a exibição
        //do ListView
        adapter.notifyDataSetChanged();
    }
}
