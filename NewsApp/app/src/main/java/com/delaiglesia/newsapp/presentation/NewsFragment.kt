package com.delaiglesia.newsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.delaiglesia.newsapp.R
import com.delaiglesia.newsapp.data.utils.Resource
import com.delaiglesia.newsapp.databinding.FragmentNewsBinding
import com.delaiglesia.newsapp.presentation.adapter.NewsAdapter
import com.delaiglesia.newsapp.presentation.viewModel.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val country = "us"
    private val page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        //viewModel es un objeto que se inyecta en el fragmento desde el activity
        viewModel = (activity as MainActivity).viewModel
        //lo mismo con el adapter
        newsAdapter = (activity as MainActivity).newsAdapter
        initRecyclerView()
        viewNewsList()
    }

    private fun initRecyclerView() {
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadlines(country, page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        binding.newsRecyclerView.adapter = newsAdapter
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(
                        activity,
                        "An error occurred: ${response.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    response.message?.let { message ->
                        println("An error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}