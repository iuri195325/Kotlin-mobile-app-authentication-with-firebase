package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinfirebase.databinding.ActivityEnderecoBinding
import com.example.kotlinfirebase.databinding.ActivityLoginBinding
import com.example.kotlinfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class EnderecoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnderecoBinding
    private  val db = FirebaseFirestore.getInstance()
    private var usuarioID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnderecoBinding.inflate(layoutInflater)

        setContentView(binding.root);
        binding.btCompletar.setOnClickListener { view ->

            val cep = binding.etCep.text.toString();
            val url = "https://viacep.com.br/ws/$cep/json/"

            doAsync {
                val url = URL(url)
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 7000
                val content = urlConnection.inputStream.bufferedReader().use(BufferedReader::readText)
                var json = JSONObject(content);
                uiThread {
                    binding.etEstado.setText(json.getString("uf"))
                    binding.etCidade.setText(json.getString("localidade"))
                    binding.etLogradouro.setText(json.getString("logradouro"))
                    binding.etBairro.setText(json.getString("bairro"))
                }
            }

        }
        binding.btAdicionar.setOnClickListener { view ->
            var cep = binding.etCep.text.toString();
            var estado = binding.etEstado.text.toString();
            var cidade = binding.etCidade.text.toString();
            var logradouro = binding.etLogradouro.text.toString();
            var bairro = binding.etBairro.text.toString();
            var rua = binding.etRua.text.toString();
            var numero = binding.etNumero.text.toString();

            if(cep.isEmpty() || estado.isEmpty() || cidade.isEmpty() || logradouro.isEmpty() || bairro.isEmpty() || rua.isEmpty() || numero.isEmpty()){
                Toast.makeText(this,"Preencha todos os campos",Toast.LENGTH_LONG).show();
            }else{
                val enderecoMap = hashMapOf(
                    "cep" to cep,
                    "estado" to estado,
                    "cidade" to cidade,
                    "logradouro" to logradouro,
                    "bairro" to bairro,
                    "rua" to rua,
                    "numero" to numero
                )
                usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
                db.collection("Endereco").document(usuarioID).set(enderecoMap).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent(this,telaPrincipalActivity::class.java);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "erro",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }








}