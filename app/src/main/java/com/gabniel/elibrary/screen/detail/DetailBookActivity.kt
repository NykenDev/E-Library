package com.gabniel.elibrary.screen.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gabniel.elibrary.database.entities.BookmarkEntity
import com.gabniel.elibrary.database.entities.EbookEntity
import com.gabniel.elibrary.databinding.ActivityDetailbookBinding
import com.gabniel.elibrary.screen.home.EbookViewModel
import com.gabniel.elibrary.screen.main.MainActivity
import com.gabniel.elibrary.utils.showToast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailBookActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailbookBinding

    private var book: EbookEntity? = null
    private var bookmark: BookmarkEntity? = null

    private val viewModel: EbookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromIntent()
        setupView()
        onClickListener()
    }

    private fun getDataFromIntent() {
        book = intent.getParcelableExtra("book")
        bookmark = intent.getParcelableExtra("bookmark")
    }

    private fun setupView() {
        if (book != null) {
            binding.apply {
                Picasso.get().load(book?.image).into(binding.imgBook)
                txtTitle.text = book?.name
                txtSynopsis.text = book?.synopsis
                txtAuthor.text = book?.author
                btnBookmark.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                Picasso.get().load(bookmark?.image).into(binding.imgBook)
                txtTitle.text = bookmark?.name
                txtSynopsis.text = bookmark?.synopsis
                txtAuthor.text = bookmark?.author
                btnDelete.visibility = View.VISIBLE
            }
        }

    }

    private fun onClickListener() {
        binding.apply {
            btnBookmark.setOnClickListener {
                viewModel.getBookmark.observe(this@DetailBookActivity) {
                    viewModel.saveBookmark(
                        BookmarkEntity(
                            book?.name.toString(),
                            book?.image.toString(),
                            book?.genre.toString(),
                            book?.synopsis.toString(),
                            book?.author.toString()
                        )
                    )
                }
                showToast("Buku Berhasil Dipinjam")
                finish()
            }
            btnDelete.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteItemBookmark(bookmark?.name.toString())
                    delay(500)
                    startActivity(Intent(this@DetailBookActivity, MainActivity::class.java))
                }
                showToast("Buku Telah Dikembalikan")
            }
        }
    }


}