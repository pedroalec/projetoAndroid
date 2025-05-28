package br.com.pedro.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val editTextLogin: EditText = findViewById(R.id.editTextLogin)
        val editTextSenha: EditText = findViewById(R.id.editTextSenha)
        val buttonEntrar: Button = findViewById(R.id.buttonEntrar)

        buttonEntrar.setOnClickListener {
            val login = editTextLogin.text.toString()
            val senha = editTextSenha.text.toString()

            if (login == "admin" && senha == "admin") {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Login ou senha inválidos!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
