package com.aengussong.movieviewie.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.ui.DataViewModel
import com.aengussong.movieviewie.ui.details.pager.DetailsPagerAdapter
import com.aengussong.movieviewie.ui.details.pager.DetailsPagerFragment
import com.aengussong.movieviewie.util.setNavigationResult
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val KEY_LAST_POSITION = "last_position"

class DetailsFragment : Fragment() {

    private val viewModel: DataViewModel by sharedViewModel()

    private val args: DetailsFragmentArgs by navArgs()

    private var lastPosition:Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        handleTransition()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lastPosition = savedInstanceState?.getInt(KEY_LAST_POSITION)
        setUpPager()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_LAST_POSITION, pager.currentItem)
        super.onSaveInstanceState(outState)
    }

    private fun handleTransition() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move).apply {
                duration = 500
            }
        postponeEnterTransition()
    }

    private fun setUpPager() {
        val callback = object : DetailsPagerFragment.TransitionCallback {
            override fun invoke() {
                startPostponedEnterTransition()
            }
        }
        DetailsPagerAdapter(this, callback).also { adapter ->
            pager.adapter = adapter
            viewModel.moviesData.observe(viewLifecycleOwner, Observer {
                adapter.updateData(it)
                pager.setCurrentItem(lastPosition ?: args.position, false)
            })
        }

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setNavigationResult(position)
            }
        })

    }

}
