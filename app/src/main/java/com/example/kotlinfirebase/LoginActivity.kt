package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinfirebase.databinding.ActivityLoginBinding
import com.example.kotlinfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var auth = FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root);

        binding.btEntrar.setOnClickListener { view ->
            val email = binding.etLoginEmail.text.toString();
            val senha = binding.etLoginSenha.text.toString();
            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos",Toast.LENGTH_LONG);
            }else{
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent(this,telaPrincipalActivity::class.java);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "erro ao Entrar",Toast.LENGTH_LONG);
                    }
                }
            }

        }

    }
}