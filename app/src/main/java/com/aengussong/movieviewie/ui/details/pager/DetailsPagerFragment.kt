package com.aengussong.movieviewie.ui.details.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aengussong.imageloadrr.ImageLoadrr
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.model.Movie
import kotlinx.android.synthetic.main.fragment_details_pager.*

private const val KEY_MOVIE = "movie"

class DetailsPagerFragment : Fragment() {

    companion object {

        fun newInstance(movie: Movie) =
            DetailsPagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_MOVIE, movie)
                }
            }
    }

    private val movie by lazy { arguments?.getParcelable(KEY_MOVIE) as Movie? }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayMovie()
    }

    private fun displayMovie() {
        movie?.let {
            ImageLoadrr().loadImageInto(it.image, details_poster)
            details_name.text = it.name
            details_description.text = it.description
        }
    }
}
