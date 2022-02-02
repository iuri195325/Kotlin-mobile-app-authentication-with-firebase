package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinfirebase.databinding.ActivityMainBinding
import com.example.kotlinfirebase.databinding.ActivityTelaPrincipalBinding

class telaPrincipalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTelaPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root);

        binding.btCadastrarEndereco.setOnClickListener { view ->
            val intent = Intent(this,EnderecoActivity::class.java);
            startActivity(intent);
        }
    }
}