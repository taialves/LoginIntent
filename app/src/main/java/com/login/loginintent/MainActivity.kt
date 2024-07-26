package com.login.loginintent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var campoMatricula : EditText
    lateinit var campoSenha : EditText
    lateinit var btnLogin : Button

    private val usuarios = listOf(
        Usuario("Tailson Alves", "123", "senha123"),
        Usuario("Yara Braga", "456", "senha456"),
        Usuario("Bruna Costa", "789", "senha789")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        campoMatricula = findViewById(R.id.campo_matricula)
        campoSenha = findViewById(R.id.campo_senha)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener{
            val matricula = campoMatricula.text
            val senha = campoSenha.text.toString()

            if(matricula.isNotBlank() and senha.isNotBlank()){
                validarLogin(matricula.toString(), senha)
            }
        }
    }

    private fun validarLogin(matricula: String, senha: String) {
        val usuario = usuarios.find { it.matricula == matricula && it.senha == senha }
        if (usuario != null) {

            val intent = Intent(this, TelaInicial::class.java).apply {
                putExtra("NOME_COMPLETO", usuario.nomeCompleto)
            }
            startActivity(intent)
        } else {
            // Login falhou, mostrar mensagem de erro
            Toast.makeText(this, "Matrícula ou senha incorretos", Toast.LENGTH_SHORT).show()
        }
    }



}

data class Usuario(val nomeCompleto: String, val matricula: String, val senha: String)
