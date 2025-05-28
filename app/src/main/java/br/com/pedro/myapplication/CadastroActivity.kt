package br.com.pedro.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {
    private lateinit var editCodigo: EditText
    private lateinit var editNome: EditText
    private lateinit var editDescricao: EditText
    private lateinit var editEstoque: EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnLimpar: Button
    private lateinit var gerenciadorBD: GerenciadorBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        //vamos definir os campos e inicializar as operações do banco
        editCodigo = findViewById(R.id.etnCodigoProduto)
        editNome = findViewById(R.id.etNomeProduto)
        editDescricao = findViewById(R.id.etDescricao)
        editEstoque = findViewById(R.id.etnEstoque)
        btnSalvar = findViewById(R.id.btSalvar)
        btnLimpar = findViewById(R.id.btLimpar)
        gerenciadorBD = GerenciadorBD(this)


        btnSalvar.setOnClickListener {
            val codigo = editCodigo.text.toString()
            val nome = editNome.text.toString()
            val descricao = editDescricao.text.toString()
            val estoque = editEstoque.text.toString()

            //checa se os campos foram preenchidos
            if (codigo.isNotEmpty() && nome.isNotEmpty() && descricao.isNotEmpty() && estoque.isNotEmpty()) {
                val produto = Produto(
                    codigo = codigo.toInt(),
                    nome = nome,
                    descricao = descricao,
                    estoque = estoque.toInt()
                )

                //se foi preeenchido certinho ele cadastra
                val resultado = gerenciadorBD.cadastrarProduto(produto)
                if (resultado != -1L) {
                    //emite uma confirmação por pop up e limpa os campos
                    Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    limparCampos()
                } else {
                    //se não ele emite um erro
                    Toast.makeText(this, "Erro ao cadastrar o produto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        btnLimpar.setOnClickListener {
            limparCampos()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun limparCampos() {
        editCodigo.text.clear()
        editNome.text.clear()
        editDescricao.text.clear()
        editEstoque.text.clear()
    }
}
