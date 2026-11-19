package com.example.bancodedadoslocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {
    //Context: Contem o local de execução de uma tela
    //com ele é possivel enviar mensagem para a tela que chamou
    //name: name do database
    //CursorFactory: classe para personalizar SELECT
    //version: contrla as versões e atualizações
    //string sql
    private final String criartable = "CREATE TABLE produto(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nome VARCHAR(40) NOT NULL," + "preco REAL NOT NULL," +
            "quantidade INTEGER);";

    public BancoDeDados(@Nullable Context context, int version) {
        super(context, "banco01", null, version);
    }
//E metdodo é executdo antes do Oncreate
//Ultilizado para configurar o SQLite
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //Habilitar a chave estrangeira
        db.execSQL("PRAGMA foreign_keys=ON;");


    }
    //Método para cadastrar um produto na tabela
    public boolean cadastrar(Produto p){
        //Utilizar a classe SQLiteDatabase
        SQLiteDatabase banco = getWritableDatabase();//Abre a conexão
        //Passar os valores de cada coluna
        ContentValues valores = new ContentValues();
        valores.put("nome", p.getNome());
        valores.put("preco", p.getPreco());
        valores.put("quantidade", p.getQuantidade());

        //Chamar o métod insert e passar os valores
        //null
        if(banco.insert( "produto", null,valores) != -1){
            banco.close(); //Fecho a conexao
            return true;
        }else{
            banco.close(); //Fcha a conexao
            return false;
        }
    }
    //remover um produto pelo ID
    public boolean apagar (int id) {
        SQLiteDatabase banco = getWritableDatabase(); //abre a conexao
        //SQL DELETE FROM profuto WHERE id = ?
        //O dlete() possui 3 parametros
        //nome da tabela, condição do WHERE, valorda condiçao
        if (banco.delete("produto",
                "_id=?", //Condiça WHERE (_id)
                new String[]{id + ""}) != 0) {//Valor o id e possibilita varios itens
            banco.close();
            return true;
        } else { //se não apagou
            banco.close();
            return false;
        }
    }

    //Buscar 1 produto usando o ID (PK)
    // SEQL SELECT FROM prduto WHERE_id=?
    public Produto buscarID (int id){
        SQLiteDatabase banco = getWritableDatabase();
        //A Classe Currsor armazea o resultado do SELECT
        //query() é o que executa SELECT
        Cursor resultado = banco.query("produto",//nome da tabela
                null, //colunas do SELECT o null é o *
                "_id=?", //condição do WHERE
                new String[]{ id+ ""}, //valor da condição
                null, //Group By
                null,//Having Group By
                null,//Order By
                null);//Limit


        //Criar um produto
        Produto p = null;


        //Tstar se encontrou o Produto
        if (resultado.moveToFirst()){
            do{
                //Recuperara os valores do produto
                p = new Produto(
                        resultado.getInt(0), //coluna_id
                        resultado.getString(1), //Nome
                        resultado.getDouble(2),//Preço
                        resultado.getInt(3));//Quantidade
            }while (resultado.moveToNext());
        }
        return p; //se estiver null é porque nãoa chou o ID
    }
    //atualizar
    public boolean atualizar (Produto p) {
        SQLiteDatabase banco = getWritableDatabase();
        //Para podermos atualizar, indicar os valores via ContentValues
        ContentValues valores = new ContentValues();
        valores.put("nome", p.getNome());
        valores.put("preco", p.getPreco());
        valores.put("quantidade", p.getQuantidade());
        //SQL UPDATE produto SET nome=?, preco=? quantiade=?

        if (banco.update("produto", valores,
                "_id=?",
                new String[]{p.getId() + ""}) != 0) {
            banco.close();
            return true;
        } else { //se não apagou
            banco.close();
            return false;
        }

    }
    public List<Produto> buscarTodos(){
        List<Produto> listaProdutos = new ArrayList<>();

        SQLiteDatabase banco = getWritableDatabase();
        //A Classe Currsor armazea o resultado do SELECT
        //query() é o que executa SELECT
        Cursor resultado = banco.query("produto",//nome da tabela
                null, //colunas do SELECT o null é o *
                null, //condição do WHERE
                null, //valor da condição
                null, //Group By
                null,//Having Group By
                null,//Order By
                null);//Limit


        //Criar um produto
        Produto p = null;


        //Tstar se encontrou o Produto
        if (resultado.moveToFirst()){
            do{
                //Recuperara os valores do produto
                p = new Produto(
                        resultado.getInt(0), //coluna_id
                        resultado.getString(1), //Nome
                        resultado.getDouble(2),//Preço
                        resultado.getInt(3));//Quantidade
                //Adiciona o prodto P na lista
                listaProdutos.add(p);
            }while (resultado.moveToNext());
        }
        return listaProdutos; //
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Executar p CREATE TABLE
        db.execSQL(criartable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
