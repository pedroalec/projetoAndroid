package br.com.pedro.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListarActivity : AppCompatActivity() {
    private lateinit var btVoltar: Button
    private lateinit var listViewProdutos: ListView
    private lateinit var gerenciadorBD: GerenciadorBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        //inicializa a view dos produtos e do botão
        btVoltar = findViewById(R.id.btVoltar)
        listViewProdutos = findViewById(R.id.listViewProdutos)

        //inicializa o banco e carrega a view
        gerenciadorBD = GerenciadorBD(this)
        carregarProdutos()

        btVoltar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Função para carregar os produtos no ListView
    private fun carregarProdutos() {
        val cursor = gerenciadorBD.listarProdutos()
        val listaProdutos = mutableListOf<String>()

        // Preenchendo a lista com produtos (apenas código, nome e estoque)
        if (cursor.moveToFirst()) {
            do {
                //verificando se as colunas existem antes de pegar os valores
                val codigoIndex = cursor.getColumnIndex(GerenciadorBD.COLUMN_CODIGO)
                val nomeIndex = cursor.getColumnIndex(GerenciadorBD.COLUMN_NOME)
                val estoqueIndex = cursor.getColumnIndex(GerenciadorBD.COLUMN_ESTOQUE)
                //coluna existe
                if (codigoIndex != -1 && nomeIndex != -1 && estoqueIndex != -1) {
                    val codigo = cursor.getInt(codigoIndex)
                    val nome = cursor.getString(nomeIndex)
                    val estoque = cursor.getInt(estoqueIndex)

                    listaProdutos.add("Código: $codigo | Nome: $nome | Estoque: $estoque")
                } else {
                    //caso a coluna não exista ele emite um erro
                    println("Erro: Colunas não encontradas!")
                }
            } while (cursor.moveToNext())
        }
        cursor?.close()

        // Criar o adaptador e associar ao ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaProdutos)
        listViewProdutos.adapter = adapter

    }

}
