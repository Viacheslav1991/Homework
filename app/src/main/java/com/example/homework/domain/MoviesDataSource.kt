package com.example.homework.domain

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import com.example.homework.R
import com.example.homework.data.models.Actor
import com.example.homework.data.models.MovieCard

class MoviesDataSource(private val context: Context) {
    fun getCards(): List<MovieCard> {
        return listOf(
            MovieCard(BitmapFactory.decodeResource(context.resources, R.drawable.movie_card_1)),
            MovieCard(BitmapFactory.decodeResource(context.resources, R.drawable.movie_card_2)),
            MovieCard(BitmapFactory.decodeResource(context.resources, R.drawable.movie_card_3)),
            MovieCard(BitmapFactory.decodeResource(context.resources, R.drawable.movie_card_4))
        )
    }

    fun getActors(): List<Actor> {
        return listOf(
            Actor(
                BitmapFactory.decodeResource(context.resources, R.drawable.actor_0),
                context.getString(R.string.robert_downey_jr)
            ),

            Actor(
                BitmapFactory.decodeResource(context.resources, R.drawable.actor_1),
                context.getString(R.string.chris_evans)
            ),

            Actor(
                BitmapFactory.decodeResource(context.resources, R.drawable.actor_2),
                context.getString(R.string.mark_ruffalo)
            ),

            Actor(
                BitmapFactory.decodeResource(context.resources, R.drawable.actor_3),
                context.getString(R.string.chris_hemsworth)
            ),
            Actor(
                BitmapFactory.decodeResource(context.resources, R.drawable.actor_3),
                context.getString(R.string.chris_hemsworth)
            ),
            Actor(
                BitmapFactory.decodeResource(context.resources, R.drawable.actor_3),
                context.getString(R.string.chris_hemsworth)
            ),
            Actor(
                BitmapFactory.decodeResource(context.resources, R.drawable.actor_3),
                context.getString(R.string.chris_hemsworth)
            ),
        )

    }
}