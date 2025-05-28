package br.com.pedro.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AlterarActivity : AppCompatActivity() {
    private lateinit var editCodigo: EditText
    private lateinit var editNome: EditText
    private lateinit var editDescricao: EditText
    private lateinit var editEstoque: EditText
    private lateinit var btnAlterar: Button
    private lateinit var btnLimpar: Button
    private lateinit var gerenciadorBD: GerenciadorBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alterar)

        //inicializando banco e variaveis
        editCodigo = findViewById(R.id.etnCodigoProduto)
        editNome = findViewById(R.id.etNomeProduto)
        editDescricao = findViewById(R.id.etDescricao)
        editEstoque = findViewById(R.id.etnEstoque)
        btnAlterar = findViewById(R.id.btSalvar)
        btnLimpar = findViewById(R.id.btLimpar)
        gerenciadorBD = GerenciadorBD(this)

        btnAlterar.setOnClickListener {
            val codigo = editCodigo.text.toString()
            val nome = editNome.text.toString()
            val descricao = editDescricao.text.toString()
            val estoque = editEstoque.text.toString()

            //checagem dos campos
            if (codigo.isNotEmpty() && nome.isNotEmpty() && descricao.isNotEmpty() && estoque.isNotEmpty()) {
                val produto = Produto(
                    codigo = codigo.toInt(),
                    nome = nome,
                    descricao = descricao,
                    estoque = estoque.toInt()
                )

                //atualização no banco
                val resultado = gerenciadorBD.alterarProduto(produto)
                //se deu certo
                if (resultado > 0) {
                    Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_SHORT).show()
                    limparCampos()
                }
                //se deu errado
                else {
                    Toast.makeText(this, "Produto não encontrado ou erro ao alterar", Toast.LENGTH_SHORT).show()
                }
            }
            //campos não preenchidos corretamente
            else {
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
