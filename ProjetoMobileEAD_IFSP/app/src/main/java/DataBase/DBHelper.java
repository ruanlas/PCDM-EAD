//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Ruan Luiz Alves da Silva
//******************************************************
package DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import Models.Pessoa;

/**
 * Created by Ruan on 19/06/2017.
 */

public class DBHelper {
    private static final String DATABASE_NAME = "bancodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pessoa";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into "+TABLE_NAME+" (nome, cpf, idade, telefone, email) values (?, ?, ?, ?, ?)";

    public DBHelper(Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(Pessoa pessoa){
        this.insertStmt.bindString(1,pessoa.getNome());
        this.insertStmt.bindString(2,pessoa.getCpf());
        this.insertStmt.bindString(3, pessoa.getIdade());
        this.insertStmt.bindString(4, pessoa.getTelefone());
        this.insertStmt.bindString(5, pessoa.getEmail());
        return this.insertStmt.executeInsert();
    }

    public void deleteAll(){
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<Pessoa> queryGetAll(){
        List<Pessoa> list = new ArrayList<Pessoa>();
        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nome", "cpf", "idade", "telefone", "email"},
                    null, null, null, null, null, null);
            int nregistros = cursor.getCount();

            if (nregistros != 0){
                cursor.moveToFirst();
                do {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(cursor.getString(0));
                    pessoa.setCpf(cursor.getString(1));
                    pessoa.setIdade(cursor.getString(2));
                    pessoa.setTelefone(cursor.getString(3));
                    pessoa.setEmail(cursor.getString(4));

                    list.add(pessoa);
                }while (cursor.moveToNext());

                if (cursor != null && !cursor.isClosed()){
                    cursor.close();
                }
                return list;
            }else {
                return null;
            }
        }catch (Exception err){
            return null;
        }

    }

    private static class OpenHelper extends SQLiteOpenHelper{
        OpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "cpf TEXT, " +
                    "idade TEXT, " +
                    "telefone TEXT, " +
                    "email TEXT);";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }
}
