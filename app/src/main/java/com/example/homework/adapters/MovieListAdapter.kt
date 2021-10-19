package com.example.homework.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homework.R
import com.example.homework.databinding.ItemMovieCardBinding
import com.example.homework.modelFromAcademy.Movie

private const val TAG = "MovieListAdapter"

class MovieListAdapter(private val clickListener: MoviesClicks?) :
    ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {
    private var movies: List<Movie> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val itemBinding =
            ItemMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener?.movieClick(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun bindCards(cards: List<Movie>) {
        movies = cards
    }
}

class MovieViewHolder(private val itemBinding: ItemMovieCardBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.bg)
            .fallback(R.drawable.bg)
    }

    fun onBind(movie: Movie) {
        itemBinding.tvFilmName.text = movie.title
        Log.i(TAG, "image: ${movie.imageUrl}");
        Log.i(TAG, "text: ${movie.title}");
        Log.i(TAG, "Thread: ${Thread.currentThread().name}");
        Glide.with(context)
            .load(movie.imageUrl)
            .apply(imageOption)
            .into(itemBinding.ivMovieCard)
        bindStars(movie.rating, itemBinding)
        itemBinding.tvGenre.text = movie.genres.joinToString { genre -> genre.name }
        itemBinding.tvCardCountReviews.text =
            context.getString(R.string.reviews, movie.reviewCount.toString())
    }

    private fun <B> bindStars(movieRating: Int, binding: B) {
        val rating = movieRating / 2.0
        if (binding is ItemMovieCardBinding) {
            if (rating < 4.5) {
                binding.ivCardStar5.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.textColor
                    )
                )
            }
            if (rating < 3.5) {
                binding.ivCardStar4.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.textColor
                    )
                )
            }
            if (rating < 2.5) {
                binding.ivCardStar3.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.textColor
                    )
                )
            }
            if (rating < 1.5) {
                binding.ivCardStar2.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.textColor
                    )
                )
            }
            if (rating < 0.5) {
                binding.ivCardStar1.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.textColor
                    )
                )
            }
        }

    }

}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface MoviesClicks {
    fun movieClick(movie: Movie)
}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = (oldItem == newItem)
}