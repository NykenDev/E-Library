package com.gabniel.elibrary.screen.bookmark

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabniel.elibrary.database.entities.BookmarkEntity
import com.gabniel.elibrary.databinding.FragmentBookmarkBinding
import com.gabniel.elibrary.screen.detail.DetailBookActivity
import com.gabniel.elibrary.screen.home.EbookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment(), BookmarkAdapter.Listener {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EbookViewModel by viewModels()
    private val bookmarkAdapter: BookmarkAdapter by lazy { BookmarkAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val view = binding.root

        getDataBookmark()
        setupRecyclerView()

        return view
    }

    private fun getDataBookmark() {
        viewModel.getBookmark.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.emptyView.root.visibility = GONE
                binding.recyclerView.visibility = VISIBLE
                bookmarkAdapter.differ.submitList(it)
            } else {
                binding.recyclerView.visibility = GONE
                binding.emptyView.root.visibility = VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = bookmarkAdapter
        }
    }

    override fun onClickListener(data: BookmarkEntity) {
        startActivity(Intent(context, DetailBookActivity::class.java).putExtra("bookmark", data))
    }

}