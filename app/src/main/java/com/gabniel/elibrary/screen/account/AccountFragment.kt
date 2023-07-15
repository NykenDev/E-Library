package com.gabniel.elibrary.screen.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabniel.elibrary.databinding.FragmentAccountBinding
import com.gabniel.elibrary.screen.auth.login.LoginActivity
import com.gabniel.elibrary.utils.AppManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {

    @Inject
    lateinit var appManager: AppManager

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.txtUsername.text = "Hai ${appManager.isLogin()}"
        binding.btnLogout.setOnClickListener {
            logout()
        }

        return view
    }

    private fun logout() {
        appManager.logout()
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
    }
}