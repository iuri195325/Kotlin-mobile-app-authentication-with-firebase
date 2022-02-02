package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinfirebase.databinding.ActivityCadastroBinding
import com.example.kotlinfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.ktx.Firebase

class cadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private var auth = FirebaseAuth.getInstance();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.btRegistrar.setOnClickListener { view ->
            val email = binding.etEmail.text.toString();
            val senha = binding.etSenha.text.toString();

            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this,"preencha todos os campos",Toast.LENGTH_LONG).show();
            }else{
                auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent(this,telaPrincipalActivity::class.java);
                        startActivity(intent);
                    }

                }.addOnFailureListener {exception ->
                    val erro = when(exception){
                        is FirebaseAuthWeakPasswordException -> "Senha deve ter no minimo 6 digitos"
                        is FirebaseAuthInvalidCredentialsException -> "Email invalido"
                        is FirebaseAuthUserCollisionException -> "Email já cadastrada"
                        else -> "Erro ao cadastrar novo usuario"
                    }
                    Toast.makeText(this,erro,Toast.LENGTH_LONG).show();


                }
            }
        }


    }
}