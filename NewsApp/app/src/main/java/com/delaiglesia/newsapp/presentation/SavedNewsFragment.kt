package com.delaiglesia.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.delaiglesia.newsapp.R
import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.utils.Resource
import com.delaiglesia.newsapp.databinding.FragmentSavedNewsBinding
import com.delaiglesia.newsapp.presentation.adapter.NewsAdapter
import com.delaiglesia.newsapp.presentation.viewModel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        viewNewsList(NewsFragment.Action.SHOW)
       // setSearchView()
    }

    private fun initRecyclerView() {
        binding.savedNewsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
        }
    }

    private fun viewNewsList(action: NewsFragment.Action) {

        viewModel.getSavedNews().observe(viewLifecycleOwner) {
            newsAdapter.differ.submitList(it)
        }
    }

    /*//search view listener
    private fun setSearchView() {
        binding.searchViewNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.getSearchedNews(country, page, query)
                    viewNewsList(NewsFragment.Action.SEARCH)
                }
                //false para que no se cierre el teclado al pulsar enter en el searchView y true para que se cierre
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    if (newText != null) {
                        //delay para que no se haga la petición cada vez que se pulse una tecla en el searchView, mas eficiente
                        delay(2000)
                        viewModel.getSearchedNews(country, page, newText)
                        viewNewsList(NewsFragment.Action.SEARCH)
                    }
                }
                return false
            }
        })

        binding.searchViewNews.setOnCloseListener {
            initRecyclerView()
            viewNewsList(NewsFragment.Action.SHOW)
            false
        }
    }*/
}