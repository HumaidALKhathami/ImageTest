package com.example.imagetest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2


class ViewPagerFragment : Fragment() {


    private lateinit var viewPager2: ViewPager2

    private val resourcesList = listOf(
        R.drawable.bird,
        R.drawable.mountains,
        R.drawable.arches,
        R.drawable.green,
        R.drawable.city,
        R.drawable.cows,
        R.drawable.desert,
        R.drawable.green2,
        R.drawable.green3,
        R.drawable.lake,
        R.drawable.light_house,
        R.drawable.sea
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager , container , false)

        viewPager2 = view.findViewById(R.id.view_pager)

        viewPager2.adapter = ViewPagerAdapter(resourcesList, requireActivity())


        return view
    }

    inner class ViewPagerAdapter(private val resourcesList: List<Int>, fa: FragmentActivity) :
        FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = resourcesList.size

        override fun createFragment(position: Int): Fragment = Item(resourcesList[position])
    }
}