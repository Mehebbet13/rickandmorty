package com.example.abbtechtestapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abbtechtestapplication.R
import com.example.abbtechtestapplication.databinding.FragmentAllCharactersBinding
import com.example.abbtechtestapplication.ui.main.adapter.AllCharactersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class AllCharactersFragment : Fragment(R.layout.fragment_all_characters) {

    private val viewModel: AllCharactersViewModel by viewModels({ this })
    private val adapter = AllCharactersAdapter()
    lateinit var binding: FragmentAllCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCharactersBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAdapter()
        setData()
        search()
        binding.swipeRefresh.setOnRefreshListener {
            setData()
            binding.searchView.clearFocus()
        }
    }

    private fun setData() {
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
            binding.progress.visibility = View.GONE
            binding.swipeRefresh.isRefreshing = false

        }
    }

    private fun search() {
        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query.toString()).observe(viewLifecycleOwner) {
                    adapter.submitData(lifecycle, it)
                    binding.progress.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false

                }
                binding.searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.search(p0.toString()).observe(viewLifecycleOwner) {
                    adapter.submitData(lifecycle, it)
                    binding.progress.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false

                }
                return true

            }

        })
    }

    private fun createAdapter() {
        binding.rvCharacters.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.rvCharacters.setHasFixedSize(true)
        binding.rvCharacters.adapter = adapter

        adapter.onItemClick = { id ->
            val bundle = bundleOf(ID to id)
            findNavController().navigate(
                R.id.action_allCharactersFragment_to_characterDetailFragment,
                bundle
            )
        }

    }

    companion object {
        const val ID = "ID"
    }
}