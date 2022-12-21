package com.delaiglesia.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.databinding.NewsListItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    //usamos DiffUtil para comparar los elementos de la lista vieja con los nuevos y
    //así saber qué elementos han cambiado y actualizarlos en la lista de la UI
    // sin tener que recargarla entera (más eficiente!!)
    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    //AsyncListDiffer es un wrapper de ListAdapter que nos permite usar DiffUtil
    //para comparar los elementos de la lista vieja con los nuevos
    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    inner class NewsViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt
                tvSource.text = article.source.name

                Glide.with(itemView)
                    .load(article.urlToImage)
                    .into(ivArticleImage)

                //cuando se hace click en un elemento de la lista, se llama a la función lambda que
                // se ha pasado como parámetro a setOnItemClickListener y se le pasa el artículo
                // que se ha pulsado como parámetro (para que se pueda usar en el fragmento)
                itemView.setOnClickListener {
                    onItemClickListener?.let { it(article) }
                }
            }
        }
    }
}