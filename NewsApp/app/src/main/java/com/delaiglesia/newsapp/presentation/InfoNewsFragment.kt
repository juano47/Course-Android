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
import com.delaiglesia.newsapp.presentation.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoNewsFragment : Fragment() {

    private lateinit var binding: FragmentInfoNewsBinding
    private lateinit var viewModel: NewsViewModel
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
        viewModel = (activity as MainActivity).viewModel

        val args : InfoNewsFragmentArgs by navArgs()
        val article = args.selectedArticle

       binding.webViewInfoNews.apply {
            webViewClient = WebViewClient()
            if (article.url != null) {
                loadUrl(article.url)
            }
        }

        binding.fabSave.setOnClickListener {
            val articlesFromDb = viewModel.getArticle(article.url!!)
            articlesFromDb.observe(viewLifecycleOwner) { articles ->
                if (articles.isEmpty()) {
                    viewModel.saveArticle(article)
                    Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
                    //removemos el observer para que no se siga ejecutando porque sino al guardar un articulo
                    // se ejecutaria de nuevo el observer y se entra al else y se muestra el mensaje de que ya esta guardado
                    articlesFromDb.removeObservers(viewLifecycleOwner)
                } else {
                    Snackbar.make(view, "Article already saved", Snackbar.LENGTH_SHORT).show()

                }
            }
        }
    }
}