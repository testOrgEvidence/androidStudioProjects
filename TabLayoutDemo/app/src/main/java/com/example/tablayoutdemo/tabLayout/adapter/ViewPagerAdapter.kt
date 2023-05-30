package com.example.tablayoutdemo.tabLayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayoutdemo.fragments.FirstFragment
import com.example.tablayoutdemo.fragments.SecFragment
import com.example.tablayoutdemo.fragments.ThirdFragment

class ViewPagerAdapter(fragmentManager:FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return  3
    }

    override fun createFragment(position: Int): Fragment {
        return  when(position){
            0->{
                FirstFragment()
            }
            1->{
                SecFragment()
            }
            2->{
                ThirdFragment()
            }
            else->{Fragment()}
        }
    }
}