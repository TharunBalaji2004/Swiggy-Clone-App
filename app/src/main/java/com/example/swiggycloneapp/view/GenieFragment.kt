package com.example.swiggycloneapp.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.swiggycloneapp.ImageSlideAdapter
import com.example.swiggycloneapp.R
import com.example.swiggycloneapp.databinding.FragmentGenieBinding
import com.example.swiggycloneapp.utils.genieSlideList
import com.example.swiggycloneapp.utils.instamartSlide1
import com.example.swiggycloneapp.utils.instamartSlide2
import kotlin.math.abs


class GenieFragment : Fragment() {

    private lateinit var handler: Handler
    private lateinit var genieSlideAdapter: ImageSlideAdapter
    private lateinit var genieViewPager: ViewPager2
    private lateinit var binding: FragmentGenieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genieViewPager = binding.genieViewpager
        genieSlideAdapter = ImageSlideAdapter(genieSlideList, genieViewPager)

        handler = Handler(Looper.myLooper()!!)
        genieViewPager.adapter = genieSlideAdapter
        genieViewPager.offscreenPageLimit = 3
        genieViewPager.clipToPadding = false
        genieViewPager.clipChildren = false
        genieViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        setUpTransformer()

        genieViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })

    }

    private fun setUpTransformer(){
        val transfomer = CompositePageTransformer()
        transfomer.addTransformer(MarginPageTransformer(90))
        transfomer.addTransformer{ page,position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
            page.scaleX = 0.85f + r * 0.4f
        }
        genieViewPager.setPageTransformer(transfomer)

    }

    private val runnable = Runnable {
        genieViewPager.currentItem = genieViewPager.currentItem + 1
    }
}