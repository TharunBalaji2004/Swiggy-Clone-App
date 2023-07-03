package com.example.swiggycloneapp.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.swiggycloneapp.ImageSlideAdapter
import com.example.swiggycloneapp.R
import com.example.swiggycloneapp.adapters.HoriImageAdapter
import com.example.swiggycloneapp.adapters.VertiImageAdapter
import com.example.swiggycloneapp.databinding.FragmentFoodBinding
import com.example.swiggycloneapp.utils.exploreFoodsList
import com.example.swiggycloneapp.utils.getQuicklyFoodsList
import com.example.swiggycloneapp.utils.homeSlideImages
import com.example.swiggycloneapp.utils.topRatedFoodsList
import kotlin.math.abs

class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var slideAdapter: ImageSlideAdapter
    private lateinit var topRatedAdapter: HoriImageAdapter
    private lateinit var getQuicklyAdapter: HoriImageAdapter
    private lateinit var exploreAdapter: VertiImageAdapter

    private lateinit var textSwitcher: TextSwitcher
    private val hintStrings = arrayOf("Hyderabad Briyani", "A2B Full Meals", "KFC Party Chicken Bowl", "Grill Nights BBQ", "Domino's Cheese Pizza")
    private var currentHintIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2 = binding.viewpager2
        handler = Handler(Looper.myLooper()!!)
        imageList = homeSlideImages
        slideAdapter = ImageSlideAdapter(imageList, viewPager2)
        viewPager2.adapter = slideAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })

        textSwitcher = binding.textSwitcher
        textSwitcher.setFactory {
            val textView = TextView(context)
            textView.textSize = 16f
            textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.swiggy_font_regular)
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
            textView
        }

        switchText()

        binding.apply {
            rvToprated.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            topRatedAdapter = HoriImageAdapter(topRatedFoodsList)
            rvToprated.adapter = topRatedAdapter

            rvQuickly.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            getQuicklyAdapter = HoriImageAdapter(getQuicklyFoodsList)
            rvQuickly.adapter = getQuicklyAdapter

            rvExplore.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            exploreAdapter = VertiImageAdapter(exploreFoodsList)
            rvExplore.adapter = exploreAdapter

            svFood.viewTreeObserver.addOnScrollChangedListener {
                val linearLayoutHeight = llBody.height
                val scrollY = svFood.scrollY

                if (scrollY >= linearLayoutHeight) {
                    Handler().postDelayed({ rlHeader.visibility = View.GONE }, 200)
                } else {
                    Handler().postDelayed({ rlHeader.visibility = View.VISIBLE }, 200)
                }
            }
        }

    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transfomer = CompositePageTransformer()
        transfomer.addTransformer(MarginPageTransformer(90))
        transfomer.addTransformer{ page,position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
            page.scaleX = 0.85f + r * 0.4f
        }
        viewPager2.setPageTransformer(transfomer)
    }

    private fun switchText() {
        textSwitcher.setText(hintStrings[currentHintIndex])
        currentHintIndex = (currentHintIndex + 1) % hintStrings.size

        textSwitcher.postDelayed({ switchText() }, 1500) // Delay between text switches (2 seconds in this example)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2500)
    }

}