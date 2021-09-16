package com.example.homework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework.R
import com.example.homework.data.models.MovieCard

class MovieListAdapter(private val clickListener: MoviesClicks?) :
    RecyclerView.Adapter<MovieViewHolder>() {
    private var movieCards: List<MovieCard> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_card, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieCards[position])
        holder.itemView.setOnClickListener {
            clickListener?.movieClick(movieCards[position])
        }
    }

    override fun getItemCount(): Int {
        return movieCards.size
    }

    fun bindCards(cards: List<MovieCard>) {
        movieCards = cards
    }

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val card: ImageView = itemView.findViewById(R.id.iv_movie_card)
    fun onBind(movieCard: MovieCard) {
        Glide.with(context)
            .load(movieCard.picture)
            .into(card)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface MoviesClicks {
    fun movieClick(movieCard: MovieCard)
}