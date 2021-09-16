package com.example.homework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.homework.adapters.MovieListAdapter
import com.example.homework.adapters.MoviesClicks
import com.example.homework.domain.MoviesDataSource

/**
 * A fragment representing a list of Items.
 */
class MoviesListFragment : Fragment() {

    private var columnCount = 2
    private var listener: MoviesClicks? = null
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.rv_movie_cards)
        adapter = MovieListAdapter(listener)
        recycler.adapter = adapter
        /*with(recycler) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
        }*/
        recycler.layoutManager = GridLayoutManager(context, 2)
        adapter.bindCards(MoviesDataSource(activity as Context).getCards())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* avengersIV = view.findViewById<ImageView>(R.id.ivAvengers)
            .apply {
                setOnClickListener { listener?.avengersClick() }
            }*/
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MoviesListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    fun setClickListener(l: MoviesClicks?) {
        listener = l
    }

}


