package com.delaiglesia.newsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

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
        newsAdapter.setOnItemClickListener { article ->
            val bundle = Bundle().apply {
                putSerializable("selected_article", article)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoNewsFragment,
                bundle
            )
        }
        initRecyclerView()
        viewNewsList()
    }

    private fun initRecyclerView() {
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
        binding.newsRecyclerView.addOnScrollListener(this@NewsFragment.onScrollListener)
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadlines(country, page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        pages = if (newsResponse.totalResults % 20 == 0) {
                            newsResponse.totalResults / 20
                        } else {
                            newsResponse.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
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
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val hasReachedEnd = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val shouldPaginate = !isLoading && !isLastPage && hasReachedEnd && isScrolling
            if (shouldPaginate) {
                page++
                viewModel.getNewsHeadlines(country, pages)
                isScrolling = false
            }
        }
    }
}