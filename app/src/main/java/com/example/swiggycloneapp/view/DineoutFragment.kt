package com.example.swiggycloneapp.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.swiggycloneapp.ImageSlideAdapter
import com.example.swiggycloneapp.R
import com.example.swiggycloneapp.adapters.DineoutHoriImageAdapter
import com.example.swiggycloneapp.adapters.DineoutVertiImageAdapter
import com.example.swiggycloneapp.adapters.HoriImageAdapter
import com.example.swiggycloneapp.databinding.FragmentDineoutBinding
import com.example.swiggycloneapp.utils.dineoutBestOffersList
import com.example.swiggycloneapp.utils.dineoutMoreList
import com.example.swiggycloneapp.utils.dineoutSlideList
import com.example.swiggycloneapp.utils.genieSlideList
import com.example.swiggycloneapp.utils.topRatedFoodsList
import kotlin.math.abs

class DineoutFragment : Fragment() {

    private lateinit var handler: Handler
    private lateinit var dineoutSlideAdapter: ImageSlideAdapter
    private lateinit var dineoutViewPager: ViewPager2
    private lateinit var binding: FragmentDineoutBinding
    private lateinit var dineOutHoriAdapter: DineoutHoriImageAdapter
    private lateinit var dineOutVertiAdapter: DineoutVertiImageAdapter

    private val hintStrings = arrayOf("Buhari Hotel", "Palmshore", "Royal Le Meridian", "Purva Windermare", "The Orange Palace")
    private var currentHintIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDineoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvBestoffers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            dineOutHoriAdapter = DineoutHoriImageAdapter(dineoutBestOffersList)
            rvBestoffers.adapter = dineOutHoriAdapter

            rvMorearoundyou.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            dineOutVertiAdapter = DineoutVertiImageAdapter(dineoutMoreList)
            rvMorearoundyou.adapter = dineOutVertiAdapter

            dineoutViewPager = dineoutViewpager
            dineoutSlideAdapter = ImageSlideAdapter(dineoutSlideList, dineoutViewPager)

            handler = Handler(Looper.myLooper()!!)
            dineoutViewPager.adapter = dineoutSlideAdapter
            dineoutViewPager.offscreenPageLimit = 3
            dineoutViewPager.clipToPadding = false
            dineoutViewPager.clipChildren = false
            dineoutViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            setUpTransformer()

            dineoutViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(runnable)
                    handler.postDelayed(runnable, 2500)
                }
            })

            svDineout.viewTreeObserver.addOnScrollChangedListener {
                val linearLayoutHeight = llSearchbar.height + llMedian.height
                val scrollY = svDineout.scrollY

                if (scrollY >= linearLayoutHeight) {
                    llSearchbar.visibility = View.VISIBLE
                } else {
                    llSearchbar.visibility = View.GONE
                }
            }

            textSwitcher.setFactory {
                val textView = TextView(context)
                textView.textSize = 16f
                textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.swiggy_font_regular)
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                textView
            }

            switchText()
        }
    }

    private fun setUpTransformer(){
        val transfomer = CompositePageTransformer()
        transfomer.addTransformer(MarginPageTransformer(90))
        transfomer.addTransformer{ page,position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
            page.scaleX = 0.85f + r * 0.4f
        }
        binding.dineoutViewpager.setPageTransformer(transfomer)

    }

    private val runnable = Runnable {
        binding.dineoutViewpager.currentItem = dineoutViewPager.currentItem + 1
    }

    private fun switchText() {
        binding.textSwitcher.setText(hintStrings[currentHintIndex])
        currentHintIndex = (currentHintIndex + 1) % hintStrings.size

        binding.textSwitcher.postDelayed({ switchText() }, 1500) // Delay between text switches (2 seconds in this example)
    }

}