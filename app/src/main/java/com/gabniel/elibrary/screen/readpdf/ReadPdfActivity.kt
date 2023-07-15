package com.gabniel.elibrary.screen.readpdf

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.gabniel.elibrary.database.entities.EbookEntity
import com.gabniel.elibrary.databinding.ActivityReadPdfBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadPdfActivity : AppCompatActivity() {

    lateinit var binding: ActivityReadPdfBinding
    private var book: EbookEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromIntent()
        setupViewView()
    }

    private fun getDataFromIntent() {
        book = intent.getParcelableExtra("book")
        Log.d("GABNIEL-WEBVIEW", "getDataFromIntent: $book")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupViewView() {
        binding.apply {
            webView.clearCache(true)
            webView.settings.javaScriptEnabled = true
//            val linkPdf = "https://docs.google.com/gview?embedded=true&url=${book?.link}"
//            Log.d("GABNIEL-PDF", "setupViewView: $linkPdf")
//            webView.loadUrl(linkPdf)
            webView.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                    if (newProgress == 100) {
                        progressBar.visibility = View.GONE
                    }
                    super.onProgressChanged(view, newProgress)
                }
            }

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    if (view?.title.equals("")) {
                        view?.reload()
                    }
                }

            }

        }
    }

}