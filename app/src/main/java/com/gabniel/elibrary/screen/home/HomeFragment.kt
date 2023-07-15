package com.gabniel.elibrary.screen.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabniel.elibrary.database.entities.EbookEntity
import com.gabniel.elibrary.databinding.FragmentHomeBinding
import com.gabniel.elibrary.screen.detail.DetailBookActivity
import com.gabniel.elibrary.utils.AppManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.Listener {

    @Inject
    lateinit var appManager: AppManager

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EbookViewModel by viewModels()

    private val romanceAdapter: HomeAdapter by lazy { HomeAdapter(this) }
    private val philosophyAdapter: HomeAdapter by lazy { HomeAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        getRomanceBook()
        getPhilosophyBook()
        setupView()
        setupRomanceRecyclerView()
        setupPhilosophyRecyclerView()
        return view
    }

    private fun getRomanceBook() {
        viewModel.getRomanceBook.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                romanceAdapter.differ.submitList(it)

            }
        }
    }

    private fun getPhilosophyBook() {
        viewModel.getPhilosophyBook.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                philosophyAdapter.differ.submitList(it)
            }
        }
    }

    private fun setupView() {
        binding.txtUsername.text = "Hello...\nSelamat Datang\n${appManager.isLogin()}!"
    }


    private fun setupRomanceRecyclerView() {
        binding.rvRomance.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = romanceAdapter
        }
    }

    private fun setupPhilosophyRecyclerView() {
        binding.rvPhilosophy.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = philosophyAdapter
        }
    }

    override fun onClickListener(data: EbookEntity) {
        startActivity(
            Intent(context, DetailBookActivity::class.java).putExtra("book", data)
        )
    }


}