package com.aengussong.movieviewie.ui.details.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.aengussong.imageloadrr.ImageLoadrr
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.model.Movie
import kotlinx.android.synthetic.main.fragment_details_pager.view.*
import org.koin.android.ext.android.inject
import java.io.Serializable
import kotlin.math.absoluteValue

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

    private val loadrr: ImageLoadrr by inject()

    private val movie by lazy { arguments?.getParcelable<Movie?>(KEY_MOVIE) }

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
        resizePosterOnScroll(view)
        //invoke shared transition
        parentFragment?.startPostponedEnterTransition()

        return view
    }

    private fun resizePosterOnScroll(view: View) {
        val minimalHeightPx = resources.getDimension(R.dimen.image_height_small)
        //last scrollY value from ScrollView
        var lastScroll = 0
        val params = view.details_poster.layoutParams as LinearLayout.LayoutParams
        val initialHeight = params.height

        view.scroll.viewTreeObserver
            .addOnScrollChangedListener {
                val scrollY: Int = view.scroll.scrollY
                //if scroll event was vertical resize image
                if (lastScroll != scrollY) {
                    //determine scrolling up or down
                    val goDown = scrollY > lastScroll
                    //reject small scrolls to prevent twitching
                    val currentScrollStep = scrollY - lastScroll
                    if (currentScrollStep.absoluteValue < 20) {
                        return@addOnScrollChangedListener
                    }

                    //change image size depending on the ScrollView scrolling distance
                    if (scrollY < initialHeight - minimalHeightPx) {
                        if (!goDown) {
                            params.height = initialHeight - scrollY
                            view.details_poster.requestLayout()
                        } else {
                            params.height = initialHeight - scrollY
                            view.details_poster.requestLayout()
                        }
                    }

                    lastScroll = scrollY
                }
            }
    }

    private fun displayMovie(view: View) {
        movie?.let {
            loadrr.loadImageInto(it.image, view.details_poster)
            view.details_name.text = it.name
            view.details_description.text = it.description
        }
    }
}
