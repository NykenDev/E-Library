package com.gabniel.elibrary.screen.auth.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gabniel.elibrary.database.dao.AccountDao
import com.gabniel.elibrary.database.entities.AccountEntity
import com.gabniel.elibrary.databinding.ActivityRegisterBinding
import com.gabniel.elibrary.screen.auth.AccountViewModel
import com.gabniel.elibrary.screen.auth.login.LoginActivity
import com.gabniel.elibrary.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var accountDao: AccountDao

    lateinit var binding: ActivityRegisterBinding

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickListener()
    }

    private fun onClickListener() {
        binding.apply {
            btnRegister.setOnClickListener {
                val username = edtUsername.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Form Tidak Boleh Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        accountDao.saveAccount(AccountEntity(null, username, email, password))
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                        finish()
                    }
                    showToast("Registrasi Berhasil")
                }
            }
        }
    }

}