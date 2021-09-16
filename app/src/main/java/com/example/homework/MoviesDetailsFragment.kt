package com.example.homework

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.adapters.ActorListAdapter
import com.example.homework.adapters.MovieListAdapter
import com.example.homework.domain.MoviesDataSource

class MoviesDetailsFragment : Fragment() {
    private var listener: MoviesDetailsClicks? = null
    private var backIV: ImageButton? = null
    private lateinit var adapter: ActorListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.rv_actors)
        adapter = ActorListAdapter()
        recycler.adapter = adapter
        /*with(recycler) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
        }*/
        recycler.layoutManager = LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL, false)
        adapter.bindActors(MoviesDataSource(activity as Context).getActors())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backIV = view.findViewById<ImageButton>(R.id.ibBack)
            .apply {
                setOnClickListener { listener?.backClick() }
            }
    }

    fun setClickListener(l: MoviesDetailsClicks?) {
        listener = l
    }

    interface MoviesDetailsClicks {
        fun backClick()
    }
}