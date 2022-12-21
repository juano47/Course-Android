package com.delaiglesia.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.delaiglesia.newsapp.R
import com.delaiglesia.newsapp.databinding.FragmentInfoNewsBinding

class InfoNewsFragment : Fragment() {

    private lateinit var binding: FragmentInfoNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoNewsBinding.bind(view)
        val args : InfoNewsFragmentArgs by navArgs()
        val article = args.selectedArticle

       binding.webViewInfoNews.apply {
            webViewClient = WebViewClient()
            if (article.url != null) {
                loadUrl(article.url)
            }
        }
    }
}