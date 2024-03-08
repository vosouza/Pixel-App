package com.evosouza.pixelapp.ui.fragments.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.evosouza.core.model.PhotoDomain
import com.evosouza.pixelapp.R
import com.evosouza.pixelapp.databinding.FragmentPopularBinding
import com.evosouza.pixelapp.ui.fragments.adapter.photoadapter.PhotoAdapter
import com.evosouza.pixelapp.ui.fragments.popular.viewmodel.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var photoAdapter: PhotoAdapter
    private val viewModel: PopularViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        fetchWallpapers()
    }

    private fun initAdapter(){
        photoAdapter = PhotoAdapter(::detail)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        with(binding.rvPopupar){
            scrollToPosition(0)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun fetchWallpapers(){
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularWallpapers().collectLatest {pagingData ->
                    photoAdapter.submitData(pagingData)
                }
            }
        }
    }
    private fun observeLoadingState(){
        lifecycleScope.launch {
            photoAdapter.loadStateFlow.collectLatest { loadingState ->
                when(loadingState.refresh){
                    is LoadState.Loading -> {
//                        binding imagePulseAnimation.pulseAnimation()
                    }
                    is LoadState.NotLoading -> {
//                        binding.imagePulseAnimation.animationCancel()
//                        binding.imagePulseAnimation.visibility = View.GONE
                    }
                    is LoadState.Error -> {
                        Toast.makeText(requireContext(), "Try again later", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun detail(photoDomain: PhotoDomain){
        val data = arrayOf(photoDomain.srcDomain.original, photoDomain.description)
//        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(data))
    }
}