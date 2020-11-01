package com.myprojects.twitter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.myprojects.twitter.R
import com.myprojects.twitter.databinding.TweetListItemBinding
import com.myprojects.twitter.model.Tweet
import com.myprojects.twitter.viewModel.TweetListItemViewModel
import com.squareup.picasso.Picasso


class TweetsAdapter(val context: Context?, var tweetList: MutableList<Tweet>) :
    RecyclerView.Adapter<TweetsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: TweetListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            var viewModel = TweetListItemViewModel(tweetList[position])
            binding.listItemVM = viewModel
            Picasso.get().load(tweetList[position].profileImageUrl).fit().into(binding.imageView1);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = DataBindingUtil.inflate<TweetListItemBinding>(
            LayoutInflater.from(parent.context), R.layout.tweet_list_item, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return tweetList.size
    }

    fun updateList(list:ArrayList<Tweet>){
        this.tweetList = list
    }
}