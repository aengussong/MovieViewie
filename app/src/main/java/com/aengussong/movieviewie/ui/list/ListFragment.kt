package com.aengussong.movieviewie.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.aengussong.imageloadrr.ImageLoadrr
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.ui.DataViewModel
import com.aengussong.movieviewie.ui.list.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    private val viewModel: DataViewModel by sharedViewModel()
    private val loadrr: ImageLoadrr by inject()

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
        val moviesAdapter = setUpAdapter()
        with(movies_rv) {
            adapter = moviesAdapter

            //support return shared transition
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

            addItemDecoration(
                DividerItemDecoration(
                    movies_rv.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        viewModel.moviesData.observe(viewLifecycleOwner, Observer {
            moviesAdapter.updateData(it)
        })
    }

    private fun setUpAdapter(): MoviesAdapter {
        val itemClickedCallback = { position: Int, elements: Map<View, String> ->
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(position)

            /** Marks created viewHolders as possible elements for shared transaction. Do not work for
             *  elements, that weren't created in recycler view, e.g. for elements on the end of the list
             *  while we are on the start of the list.
             *  So with current implementation it is impossible to make return shared transaction
             *  animation for items, that weren't visible in RecyclerView.
             * */

            val extras = FragmentNavigatorExtras(
                *elements.toList().toTypedArray()
            )
            findNavController().navigate(action, extras)
        }
        return MoviesAdapter(loadrr, itemClickedCallback)
    }

}
