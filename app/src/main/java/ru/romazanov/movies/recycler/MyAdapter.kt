package ru.romazanov.movies.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.romazanov.movies.R
import ru.romazanov.movies.data.models.Result
import ru.romazanov.movies.databinding.ItemBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var dataList: List<Result> = listOf()
    fun setList(list: List<Result>) {
        dataList = list
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemBinding
        init {
            binding = itemBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBinding = ItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.displayTitle.text = dataList[position].display_title
        holder.binding.summaryShort.text = dataList[position].summary_short
        Glide.with(holder.binding.imageView)
            .load(dataList[position].multimedia.src)
            .apply(RequestOptions.centerCropTransform())
            .placeholder(R.drawable.ic_two)
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}