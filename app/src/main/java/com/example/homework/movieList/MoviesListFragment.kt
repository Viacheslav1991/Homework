package com.example.homework.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.adapters.MovieListAdapter
import com.example.homework.adapters.MoviesClicks
import com.example.homework.dataFromAcademy.JsonMovieRepository
import com.example.homework.dataFromAcademy.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class MoviesListFragment : Fragment() {

    private var columnCount = 2
    private var listener: MoviesClicks? = null
    private lateinit var adapter: MovieListAdapter
    private lateinit var coroutineScope: CoroutineScope

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
        coroutineScope = CoroutineScope(context = Dispatchers.IO)
        adapter = MovieListAdapter(listener)
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter
        return view
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }
    private fun updateData() {
        coroutineScope.launch {
            adapter.bindCards((JsonMovieRepository(requireContext()) as MovieRepository).loadMovies())
            launch(Dispatchers.Main) { adapter.notifyDataSetChanged() }
        }
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


