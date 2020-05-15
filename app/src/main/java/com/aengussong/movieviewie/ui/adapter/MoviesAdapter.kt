package com.aengussong.movieviewie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aengussong.imageloadrr.ImageLoadrr
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
    private val loader: ImageLoadrr,
    private val onItemClicked: (Int) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var items = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    fun updateData(list: List<Movie>) {
        items = list
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, position:Int) {
            with(itemView) {
                itemView.setOnClickListener { onItemClicked(position) }
                loader.loadImageInto(movie.image, movie_poster)
                movie_name.text = movie.name
                movie_date.text = movie.time
            }
        }
    }
}