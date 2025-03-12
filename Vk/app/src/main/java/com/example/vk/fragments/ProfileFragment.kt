package com.example.vk.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.vk.R
import com.example.vk.adapter.PostAdapter
import com.example.vk.databinding.FragmentProfileBinding
import com.example.vk.models.Post
import com.example.vk.models.UserProfile

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProfileHeader()
        setupRecyclerView()
    }

    private fun setupProfileHeader() {
        val userProfile = UserProfile(
            avatarUrl = "https://i.pravatar.cc/150?img=1",
            name = "Андрюха Рыжиков",
            username = "@andruha",
            followersCount = 1500,
            followingCount = 750,
            postsCount = 120
        )

        with(binding.headerProfile) {
            Glide.with(requireContext())
                .load(userProfile.avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(avatarImageView)

            nameTextView.text = userProfile.name
            usernameTextView.text = userProfile.username
            postsCountTextView.text = "${userProfile.postsCount}\nPosts"
            followersCountTextView.text = "${userProfile.followersCount}\nFollowers"
            followingCountTextView.text = "${userProfile.followingCount}\nFollowing"
        }
    }

    private fun setupRecyclerView() {
        postAdapter = PostAdapter(
            onLikeClickListener = {}
        )

        binding.postsRecyclerView.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val posts = listOf(
            Post(
                "Сегодня ходил гулять с козлятками",
                "https://i.pinimg.com/736x/28/0b/19/280b193918124265533e1c95b7dfa119.jpg",
                10,
                2
            ),
            Post(
                "Вот такие печеньки получилось испечь",
                "https://i.pinimg.com/736x/ad/0e/8a/ad0e8a74fdf8237828bd5855330b041f.jpg",
                5,
                1
            ),
            Post(
                "Моя любимая кружка",
                "https://i.pinimg.com/736x/e2/b6/c1/e2b6c1956f8821a40a187f5945d8f352.jpg",
                20,
                5
            )
        )

        postAdapter.submitList(posts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

