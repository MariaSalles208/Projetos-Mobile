package com.example.bancodedadosagenda;

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
    private final String criartable = "CREATE TABLE agenda(" + "" +
            "nome VARCHAR(40) NOT NULL," + "sobrenome VARCHAR(40) NOT NULL," +
            "telefone VARCHAR);";

    public BancoDeDados(@Nullable Context context, int version) {
        super(context, "banco02", null, version);
    }
    //E metOdodo é executAdo antes do Oncreate
//Ultilizado para configurar o SQLite
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //Habilitar a chave estrangeira
        db.execSQL("PRAGMA foreign_keys=ON;");


    }
    //Método para cadastrar um produto na tabela
    public boolean cadastrar(Agenda a){
        //Utilizar a classe SQLiteDatabase
        SQLiteDatabase banco = getWritableDatabase();//Abre a conexão
        //Passar os valores de cada coluna
        ContentValues valores = new ContentValues();
        valores.put("nome", a.getNome());
        valores.put("Sobrenome", a.getsobrenome());
        valores.put("Telefone", a.getTelefone());

        //Chamar o método insert e passar os valores
        //null
        if(banco.insert( "Agenda", null,valores) != -1){
            banco.close(); //Fecho a conexao
            return true;
        }else{
            banco.close(); //Fecha a conexao
            return false;
        }
    }
    //remover um produto pelo ID
    public boolean apagar (int id) {
        SQLiteDatabase banco = getWritableDatabase(); //abre a conexao
        //SQL DELETE FROM profuto WHERE id = ?
        //O delete() possui 3 parametros
        //nome da tabela, condição do WHERE, valor da condiçao
        if (banco.delete("Agenda",
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
    // SQL SELECT FROM prduto WHERE_id=?
    public Agenda buscarSobrenome (String sobrenome){
        SQLiteDatabase banco = getWritableDatabase();
        //A Classe Cursor armazea o resultado do SELECT
        //query() é o que executa SELECT
        Cursor resultado = banco.query("Agenda",//nome da tabela
                null, //colunas do SELECT o null é o *
                "sobrenome=?", //condição do WHERE
                new String[]{ sobrenome+ ""}, //valor da condição
                null, //Group By
                null,//Having Group By
                null,//Order By
                null);//Limit


        //Criar um produto
        Agenda a = null;


        //Tstar se encontrou o Produto
        if (resultado.moveToFirst()){
            do{
                //Recuperara os valores do produto
                a = new Agenda(
                        resultado.getInt(0), //coluna_id
                        resultado.getString(1), //Nome
                        resultado.getString(2),//sobrenome
                        resultado.getInt(3));//Telefone
            }while (resultado.moveToNext());
        }
        return a; //se estiver null é porque nãoa chou o ID
    }
    //atualizar
    public boolean atualizar (Agenda a ) {
        SQLiteDatabase banco = getWritableDatabase();
        //Para podermos atualizar, indicar os valores via ContentValues
        ContentValues valores = new ContentValues();
        valores.put("nome", a.getNome());
        valores.put("Sobrenome", a.getsobrenome());
        valores.put("Telefone", a.getTelefone());
        //SQL UPDATE agenda SET nome=?, sobrenome=? telefone=?

        if (banco.update("Agenda", valores,
                "_id=?",
                new String[]{a.getId() + ""}) != 0) {
            banco.close();
            return true;
        } else { //se não apagou
            banco.close();
            return false;
        }

    }
    public List<Agenda> buscarTodos(){
        List<Agenda> listaProdutos = new ArrayList<>();

        SQLiteDatabase banco = getWritableDatabase();
        //A Classe Currsor armazea o resultado do SELECT
        //query() é o que executa SELECT
        Cursor resultado = banco.query("Agenda",//nome da tabela
                null, //colunas do SELECT o null é o *
                null, //condição do WHERE
                null, //valor da condição
                null, //Group By
                null,//Having Group By
                null,//Order By
                null);//Limit


        //Criar um produto
        Agenda a  = null;


        //Tstar se encontrou o Produto
        if (resultado.moveToFirst()){
            do{
                //Recuperara os valores do produto
                a = new Agenda(
                        resultado.getInt(0), //coluna_id
                        resultado.getString(1), //Nome
                        resultado.getString(2),//sobrenome
                        resultado.getInt(3));//telefone
                //Adiciona o prodto P na lista
                listaProdutos.add(a);
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

