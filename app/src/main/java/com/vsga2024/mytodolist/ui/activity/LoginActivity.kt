package com.vsga2024.mytodolist.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.vsga2024.mytodolist.R
import com.vsga2024.mytodolist.databinding.ActivityLoginBinding
import com.vsga2024.mytodolist.ui.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        observeViewModel()
        setButtonsOnClickListener()
    }

    private fun observeViewModel() {
        viewModel.getLoading().observe(this) { isLoading ->
            if (isLoading) {
                binding.loginProgressBar.visibility = View.VISIBLE
            } else {
                binding.loginProgressBar.visibility = View.GONE
            }
        }

        viewModel.getError().observe(this) { error ->
            if (error != null) {
                binding.usernameInput.error = "Username Anda sepertinya salah"
                binding.passwordInput.error = "Password Anda sepertinya salah"
            }
        }

        viewModel.getSuccess().observe(this) { result ->
            if (result) {
                MainActivity.startActivity(this)
                finish();
            }
        }
    }

    private fun setButtonsOnClickListener() {
        binding.submitBtn.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                binding.usernameInput.error = "Username tidak boleh kosong"
                binding.passwordInput.error = "Password tidak boleh kosong"
            } else {
                viewModel.userLogin(username, password)
            }
        }
    }
}