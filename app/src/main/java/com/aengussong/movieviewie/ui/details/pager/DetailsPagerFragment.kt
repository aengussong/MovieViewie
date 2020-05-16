package com.aengussong.movieviewie.ui.details.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.aengussong.imageloadrr.ImageLoadrr
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.model.Movie
import kotlinx.android.synthetic.main.fragment_details_pager.view.*
import org.koin.android.ext.android.inject
import java.io.Serializable

private const val KEY_MOVIE = "movie"
private const val KEY_TRANSITION_CALLBACK = "callback"

class DetailsPagerFragment : Fragment() {

    companion object {
        fun newInstance(movie: Movie, callback: TransitionCallback) =
            DetailsPagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_MOVIE, movie)
                    putSerializable(KEY_TRANSITION_CALLBACK, callback)
                }
            }
    }

    private val loadrr: ImageLoadrr by inject()

    private val movie by lazy { arguments?.getParcelable<Movie?>(KEY_MOVIE) }
    private val callback by lazy { arguments?.getSerializable(KEY_TRANSITION_CALLBACK) as TransitionCallback }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_pager, container, false)
        //set shared transition elements
        ViewCompat.setTransitionName(view.details_poster, movie?.image)
        ViewCompat.setTransitionName(view.details_name, movie?.name)
        //display data
        displayMovie(view)
        //invoke shared transition
        callback.invoke()

        return view
    }

    private fun displayMovie(view: View) {
        movie?.let {
            loadrr.loadImageInto(it.image, view.details_poster)
            view.details_name.text = it.name
            view.details_description.text = it.description
        }
    }

    interface TransitionCallback : Serializable {
        fun invoke()
    }
}
