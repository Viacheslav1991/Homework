package com.example.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework.adapters.MoviesClicks
import com.example.homework.modelFromAcademy.Movie
import com.example.homework.movieList.MoviesListFragment

class MainActivity : AppCompatActivity(), MoviesClicks, MoviesDetailsFragment.MoviesDetailsClicks {
    private var moviesListFragment: MoviesListFragment? = null
    private var moviesDetailsFragment: MoviesDetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            moviesListFragment = MoviesListFragment.newInstance(2)
            moviesListFragment?.apply { setClickListener(this@MainActivity) }
            moviesListFragment?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, this, LIST_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit()
            }
        } else {
            moviesListFragment =
                (supportFragmentManager.findFragmentByTag(LIST_FRAGMENT_TAG) as? MoviesListFragment)!!
            moviesListFragment?.apply { setClickListener(this@MainActivity) }

            moviesDetailsFragment =
                (supportFragmentManager.findFragmentByTag(DETAILS_FRAGMENT_TAG) as? MoviesDetailsFragment)
            moviesDetailsFragment?.apply { setClickListener(this@MainActivity) }
        }
    }

    /*override fun avengersClick() {
        supportFragmentManager.beginTransaction()
            .apply {
                add(
                    R.id.fragment_container,
                    MoviesDetailsFragment().apply { setClickListener(this@MainActivity) })
                addToBackStack(null)
                commit()
            }
    }*/

    override fun backClick() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val DETAILS_FRAGMENT_TAG = "DetailsFragment"
        const val LIST_FRAGMENT_TAG = "ListFragment"

    }

    override fun movieClick(movie: Movie) {
        moviesDetailsFragment = MoviesDetailsFragment.newInstance(movie)
        moviesDetailsFragment?.apply { setClickListener(this@MainActivity) }
        supportFragmentManager.beginTransaction()
            .apply {
                replace(
                    R.id.fragment_container,
                    moviesDetailsFragment!!,
                    DETAILS_FRAGMENT_TAG
                )
                addToBackStack(null)
                commit()
            }
    }

}