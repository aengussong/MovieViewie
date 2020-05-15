package com.aengussong.movieviewie.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.ui.DataViewModel
import com.aengussong.movieviewie.ui.details.pager.DetailsPagerAdapter
import kotlinx.android.synthetic.main.details_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val KEY_POSITION = "position"

class DetailsFragment : Fragment() {

    companion object {
        const val TAG = "details"

        fun newInstance(position: Int) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_POSITION, position)
                }
            }
    }

    private val viewModel: DataViewModel by sharedViewModel()

    private val argsPosition by lazy { arguments?.getInt(KEY_POSITION) ?: 0 }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPager()
    }

    private fun setUpPager() {
        val adapter = DetailsPagerAdapter(this)
        pager.adapter = adapter
        viewModel.moviesData.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
            pager.setCurrentItem(argsPosition, false)
        })
    }

}
