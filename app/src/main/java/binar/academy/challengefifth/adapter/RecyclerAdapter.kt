package binar.academy.challengefifth.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import binar.academy.challengefifth.databinding.ItemRecyclerBinding
import binar.academy.challengefifth.modal.Result
import binar.academy.challengefifth.ui.HomeFragmentDirections
import com.bumptech.glide.Glide

//class RecyclerAdapter(private val onItemClick: onClickListener) :
//    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
//
//    private val diffCallback = object :DiffUtil.ItemCallback<Result>(){
//        override fun areItemsTheSame(
//            oldItem: Result,
//            newItem: Result
//        ): Boolean = oldItem.id == newItem.id
//
//        override fun areContentsTheSame(
//            oldItem: Result,
//            newItem: Result
//        ): Boolean = oldItem.hashCode()==newItem.hashCode()
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return ViewHolder(ItemRecyclerBinding.inflate(inflater, parent,false))
//    }
//    private val differ = AsyncListDiffer(this, diffCallback)
//    fun submitData(value: List<Result>?)=differ.submitList(value)
//    inner class ViewHolder(private val binding: ItemRecyclerBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: Result) {
//            binding.apply {
//                tvName.text = data.title
//            }
//        }
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val data = differ.currentList[position]
//        data.let { holder.bind(data) }
//    }
//    override fun getItemCount(): Int = differ.currentList.size
//    interface onClickListener {
//        fun onClickItem(data: Result)
//    }
//}

class RecyclerAdapter(private val movie:List<Result>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    class ViewHolder( val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){
//        fun bind(data:Result){
//            binding.apply{
//                tvName.text = data.title
//            }
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemRecyclerBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load("https://image.tmdb.org/t/p/original/"+ movie[position].posterPath)
                .into(recyclerImageView)
            tvTitle.text = movie[position].title
            tvGenreSide.text = movie[position].releaseDate
            itemConstraintlayout.setOnClickListener {
                val movie =Result(posterPath = movie[position].posterPath,title= movie[position].title, releaseDate = movie[position].releaseDate)
                it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie))
            }
        }
    }

    override fun getItemCount(): Int {
       return movie.size
    }
}