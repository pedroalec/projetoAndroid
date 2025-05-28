package br.com.pedro.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GerenciadorBD(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "produtos.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "produtos"
        const val COLUMN_CODIGO = "codigo"
        const val COLUMN_NOME = "nome"
        const val COLUMN_DESCRICAO = "descricao"
        const val COLUMN_ESTOQUE = "estoque"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_CODIGO INTEGER PRIMARY KEY,
                $COLUMN_NOME TEXT,
                $COLUMN_DESCRICAO TEXT,
                $COLUMN_ESTOQUE INTEGER
            )
        """
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //fazendo um tratamento simples para a versão do banco
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun cadastrarProduto(produto: Produto): Long {
        //puxa o banco para cadastrar os produtos
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_CODIGO, produto.codigo)
            put(COLUMN_NOME, produto.nome)
            put(COLUMN_DESCRICAO, produto.descricao)
            put(COLUMN_ESTOQUE, produto.estoque)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun listarProdutos(): Cursor {
        //puxa o banco para listar os produtos
        val db = readableDatabase
        return db.query(
            TABLE_NAME,
            arrayOf(COLUMN_CODIGO, COLUMN_NOME, COLUMN_ESTOQUE),
            null, null, null, null, null
        )
    }

    fun alterarProduto(produto: Produto): Int {
        //vai puxar o banco e definir as variaveis
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, produto.nome)
            put(COLUMN_DESCRICAO, produto.descricao)
            put(COLUMN_ESTOQUE, produto.estoque)
        }
        //acha o produto e atualiza com os valores novos
        return db.update(TABLE_NAME, values, "$COLUMN_CODIGO = ?", arrayOf(produto.codigo.toString()))
    }
    // a função de delete
    fun deletarProduto(codigo: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_CODIGO = ?", arrayOf(codigo.toString()))
    }
}
