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

    private SQLiteStatement insertStmt, deleteStmt;
    private static final String INSERT = "insert into "+TABLE_NAME+" (nome, cpf, idade, telefone, email) values (?, ?, ?, ?, ?)";
    private static final String DELETE = "delete from "+TABLE_NAME+" where id = ?";

    public DBHelper(Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
        this.deleteStmt = this.db.compileStatement(DELETE);
    }

    public long insert(Pessoa pessoa){
        this.insertStmt.bindString(1,pessoa.getNome());
        this.insertStmt.bindString(2,pessoa.getCpf());
        this.insertStmt.bindLong(3, pessoa.getIdade());
        this.insertStmt.bindString(4, pessoa.getTelefone());
        this.insertStmt.bindString(5, pessoa.getEmail());
        return this.insertStmt.executeInsert();
    }

    public int delete(Pessoa pessoa){
        this.deleteStmt.bindLong(1,pessoa.getId());
        return this.deleteStmt.executeUpdateDelete();
    }

    public void deleteAll(){
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<Pessoa> queryGetAll(){
        List<Pessoa> list = new ArrayList<Pessoa>();
        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"id", "nome", "cpf", "idade", "telefone", "email"},
                    null, null, null, null, null, null);
            int nregistros = cursor.getCount();

            if (nregistros != 0){
                cursor.moveToFirst();
                do {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(cursor.getInt(0));
                    pessoa.setNome(cursor.getString(1));
                    pessoa.setCpf(cursor.getString(2));
                    pessoa.setIdade(cursor.getInt(3));
                    pessoa.setTelefone(cursor.getString(4));
                    pessoa.setEmail(cursor.getString(5));

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
                    "idade INTEGER, " +
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
