package com.aengussong.movieviewie.ui.details.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aengussong.movieviewie.model.Movie

class DetailsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var items = listOf<Movie>()

    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment =
        DetailsPagerFragment.newInstance(items[position])

    fun updateData(list: List<Movie>) {
        items = list
        notifyDataSetChanged()
    }
}