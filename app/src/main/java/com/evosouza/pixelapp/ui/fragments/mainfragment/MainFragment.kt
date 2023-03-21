package com.evosouza.pixelapp.ui.fragments.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.evosouza.pixelapp.R
import com.evosouza.pixelapp.databinding.FragmentMainBinding
import com.evosouza.pixelapp.databinding.FragmentPopularBinding
import com.evosouza.pixelapp.ui.fragments.categories.CategoriesFragment
import com.evosouza.pixelapp.ui.fragments.collections.CollectionsFragment
import com.evosouza.pixelapp.ui.fragments.pageadapter.ViewPagerAdapter
import com.evosouza.pixelapp.ui.fragments.popular.PopularFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val tabTitle = listOf("Popular", "Collections", "Categories")
    private val fragments = listOf(PopularFragment(), CollectionsFragment(), CategoriesFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initTabLayout()
        initToolbar()
    }

    private fun initTabLayout(){
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun initToolbar(){
        binding.toolbar.title = "Wallpapers"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager(){
        val pageAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)
        binding.viewPager.adapter = pageAdapter
    }
}