package com.delaiglesia.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.delaiglesia.roomdemo.databinding.ListItemBinding
import com.delaiglesia.roomdemo.db.Suscriber

class MyRecyclerViewAdapter(private val suscribersList: List<Suscriber>,
                            private val clickListener:(Suscriber) -> Unit)
    : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(suscribersList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return suscribersList.size
    }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(suscriber: Suscriber, clickListener:(Suscriber) -> Unit){
        binding.nameTextview.text = suscriber.name
        binding.emailTextview.text = suscriber.email

        binding.listItemLayout.setOnClickListener {
            clickListener(suscriber)
        }
    }
}