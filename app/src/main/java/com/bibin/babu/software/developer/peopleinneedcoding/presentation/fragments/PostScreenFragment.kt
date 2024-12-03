package com.bibin.babu.software.developer.peopleinneedcoding.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bibin.babu.software.developer.peopleinneedcoding.adapter.PostRecyclerListAdapter
import com.bibin.babu.software.developer.peopleinneedcoding.databinding.FragmentPostScreenBinding
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem
import com.bibin.babu.software.developer.peopleinneedcoding.util.ResultErrorHandling
import com.bibin.babu.software.developer.peopleinneedcoding.util.mHide
import com.bibin.babu.software.developer.peopleinneedcoding.util.mShow
import com.bibin.babu.software.developer.peopleinneedcoding.util.mToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostScreenFragment : Fragment(), PostRecyclerListAdapter.OnClickListener {
    private var _binding: FragmentPostScreenBinding? = null
    private val binding: FragmentPostScreenBinding
        get() = _binding!!
    private val postViewModel: PostViewModel by viewModels()
    private lateinit var postRecyclerListAdapter: PostRecyclerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observePosts()
    }

    private fun observePosts() {
        postViewModel.posts.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultErrorHandling.Failure -> {
                    binding.progressBar.mHide()
                    requireContext().mToast(result.message)
                }

                is ResultErrorHandling.Loading -> {
                    binding.progressBar.mShow()
                }

                is ResultErrorHandling.Success -> {
                    binding.progressBar.mHide()
                    postRecyclerListAdapter.setPostItemList(result.value)
                }
            }

        }

    }

    private fun setUpRecyclerView() {
        postRecyclerListAdapter = PostRecyclerListAdapter(this)
        binding.recyclerViewList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postRecyclerListAdapter
            setHasFixedSize(true)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPostItemClick(postItem: PostItem) {

    }


}