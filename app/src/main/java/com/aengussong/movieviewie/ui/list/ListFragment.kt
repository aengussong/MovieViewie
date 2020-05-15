package com.aengussong.movieviewie.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.aengussong.imageloadrr.ImageLoadrr
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.ui.DataViewModel
import com.aengussong.movieviewie.ui.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel: DataViewModel by sharedViewModel()

    private lateinit var itemListener: OnItemSelected

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelected) {
            itemListener = context
        } else {
            throw ClassCastException("$context must implement OnItemSelected")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val adapter = MoviesAdapter(ImageLoadrr(), itemListener::onItemSelected)
        movies_rv.adapter = adapter
        val itemDecoration = DividerItemDecoration(
            movies_rv.context,
            DividerItemDecoration.VERTICAL
        )
        movies_rv.addItemDecoration(itemDecoration)

        viewModel.moviesData.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })
    }

    interface OnItemSelected {
        fun onItemSelected(position: Int)
    }

}
