package br.com.pedro.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DeletarActivity : AppCompatActivity() {
    private lateinit var editCodigo: EditText
    private lateinit var btnDeletar: Button
    private lateinit var btnLimpar: Button
    private lateinit var gerenciadorBD: GerenciadorBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deletar)

        //fase de inicialização
        editCodigo = findViewById(R.id.etnCodigoProduto)
        btnDeletar = findViewById(R.id.btDeletar)
        btnLimpar = findViewById(R.id.btLimpar)
        gerenciadorBD = GerenciadorBD(this)

        // Definindo a ação do botão Deletar
        btnDeletar.setOnClickListener {
            val codigo = editCodigo.text.toString()

            //checagem de campo preenchido
            if (codigo.isNotEmpty()) {
                //puxa o codigo e deleta do banco
                val resultado = gerenciadorBD.deletarProduto(codigo.toInt())

                //se deu certo confirma
                if (resultado > 0) {
                    Toast.makeText(this, "Produto deletado com sucesso!", Toast.LENGTH_SHORT).show()
                    limparCampos()  // Limpando o campo após a exclusão
                }
                //se não deletar é pq não achou
                else {
                    Toast.makeText(this, "Produto não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            //checagem de campo vazio
            else {
                Toast.makeText(this, "Por favor, insira o código do produto", Toast.LENGTH_SHORT).show()
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
    }

}
