package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root);
        binding.btCadastrar.setOnClickListener { view ->
            TelaCadastrar();
        }
        binding.btLogin.setOnClickListener { view ->
            TelaLogin();
        }

    }
    private fun TelaCadastrar(){
        val intent = Intent(this,cadastroActivity::class.java);
        startActivity(intent);
    }

    private fun TelaLogin(){
        val intent = Intent(this,LoginActivity::class.java);
        startActivity(intent);
    }
}