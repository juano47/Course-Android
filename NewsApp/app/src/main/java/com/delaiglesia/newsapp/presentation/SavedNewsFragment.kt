package com.delaiglesia.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.delaiglesia.newsapp.R
import com.delaiglesia.newsapp.databinding.FragmentSavedNewsBinding
import com.delaiglesia.newsapp.presentation.adapter.NewsAdapter
import com.delaiglesia.newsapp.presentation.viewModel.NewsViewModel

class SavedNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
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
        binding.savedNewsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
    }

    private fun viewNewsList() {

        viewModel.getSavedNews().observe(viewLifecycleOwner) {
            newsAdapter.differ.submitList(it)
        }
    }
}