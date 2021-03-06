package com.example.pa

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_TABS = 3
class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }
    // return the 3 tab fragments when fragments are created in overridden class
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ProductTab()
            1 -> return SignupTab()
        }
        return AppReviewsTab()
    }
}
