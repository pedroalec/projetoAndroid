package br.com.pedro.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val btCadastro: Button = findViewById(R.id.btCadastro)
        val btAlterar: Button = findViewById(R.id.btAlterar)
        val btListar: Button = findViewById(R.id.btListar)
        val btDeletar: Button = findViewById(R.id.btDeletar)


        btCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }


        btAlterar.setOnClickListener {
            val intent = Intent(this, AlterarActivity::class.java)
            startActivity(intent)
        }


        btListar.setOnClickListener {
            val intent = Intent(this, ListarActivity::class.java)
            startActivity(intent)
        }

        btDeletar.setOnClickListener {
            val intent = Intent(this, DeletarActivity::class.java)
            startActivity(intent)
        }

    }
}
