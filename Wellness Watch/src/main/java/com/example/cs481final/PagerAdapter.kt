package com.example.cs481final

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cs481final.fragments.subfrags.MacrosFrag
import com.example.cs481final.fragments.subfrags.NutrientsFrag

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MacrosFrag()
            1 -> NutrientsFrag()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}