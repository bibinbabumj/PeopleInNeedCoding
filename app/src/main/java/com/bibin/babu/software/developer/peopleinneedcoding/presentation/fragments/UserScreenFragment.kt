package com.bibin.babu.software.developer.peopleinneedcoding.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bibin.babu.software.developer.peopleinneedcoding.adapter.UserRecyclerListAdapter
import com.bibin.babu.software.developer.peopleinneedcoding.databinding.FragmentUserScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserScreenFragment : Fragment() {
    private var _binding: FragmentUserScreenBinding? = null
    private val binding: FragmentUserScreenBinding
        get() = _binding!!

    private lateinit var userRecyclerListAdapter: UserRecyclerListAdapter

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userViewmodel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUpRecyclerView()
        observeUsers()
    }

    private fun observeUsers() {
        userViewModel.mUserList.onEach {userList->
            userRecyclerListAdapter.setUserItemList(userList)
        }.launchIn(lifecycleScope)
    }

    private fun setUpRecyclerView() {
        userRecyclerListAdapter = UserRecyclerListAdapter()
        binding.postRecycleView.recyclerViewList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userRecyclerListAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}