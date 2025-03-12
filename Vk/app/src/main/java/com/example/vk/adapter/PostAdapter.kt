package com.example.vk.adapter

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vk.R
import com.example.vk.databinding.ItemPostBinding
import com.example.vk.models.Post

class PostAdapter(
    private val onLikeClickListener: (Post) -> Unit,
) : ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {

    class PostViewHolder(
        private val binding: ItemPostBinding,
        private val onLikeClickListener: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var isLiked = false
        private var likeCount = 0

        fun bind(post: Post) {
            likeCount = post.likesCount
            binding.postTextView.text = post.text
            binding.likesCountTextView.text = likeCount.toString()
            binding.commentsCountTextView.text = post.commentsCount.toString()

            Glide.with(binding.root.context)
                .load(post.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.postImageView)

            binding.likeButton.setOnClickListener {
                isLiked = !isLiked
                if (isLiked) {
                    likeCount++
                    binding.likeButton.setImageResource(R.drawable.ic_like)
                } else {
                    likeCount--
                    binding.likeButton.setImageResource(R.drawable.ic_unlike)
                }
                binding.likesCountTextView.text = likeCount.toString()
                animateLikeButton(binding.likeButton)
                onLikeClickListener(post)
            }
        }

        private fun animateLikeButton(view: View) {
            val scaleUpX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f)
            val scaleUpY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f)

            ObjectAnimator.ofPropertyValuesHolder(view, scaleUpX, scaleUpY).apply {
                duration = 150
                repeatMode = ObjectAnimator.REVERSE
                repeatCount = 1
                start()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding, onLikeClickListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}