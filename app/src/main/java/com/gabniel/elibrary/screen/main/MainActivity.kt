package com.gabniel.elibrary.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gabniel.elibrary.R
import com.gabniel.elibrary.databinding.ActivityMainBinding
import com.gabniel.elibrary.screen.account.AccountFragment
import com.gabniel.elibrary.screen.bookmark.BookmarkFragment
import com.gabniel.elibrary.screen.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val defaultFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callFragment(defaultFragment)
        setupFragment()
    }

    private fun setupFragment() {
        binding.botNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    callFragment(HomeFragment())
                }

                R.id.bookmark -> {
                    callFragment(BookmarkFragment())
                }

                R.id.account -> {
                    callFragment(AccountFragment())
                }
            }
            true
        }
    }

    private fun callFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }
}
