package com.example.homework

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework.adapters.ActorListAdapter
import com.example.homework.databinding.FragmentMoviesDetailsBinding
import com.example.homework.modelFromAcademy.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesDetailsFragment : Fragment() {

    private var listener: MoviesDetailsClicks? = null
    private var backIV: ImageButton? = null
    private lateinit var adapter: ActorListAdapter
    private lateinit var movie: Movie
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var coroutineScope: CoroutineScope

    companion object {
        const val ARG_MOVIE = "movie"
        private const val TAG = "MoviesDetailsFragment"
        fun newInstance(movie: Movie): MoviesDetailsFragment {
            val args = Bundle()
            args.putSerializable(ARG_MOVIE, movie)

            val fragment = MoviesDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.get(ARG_MOVIE) as Movie
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        Glide.with(requireContext())
            .load(movie.detailImageUrl)
            .into(binding.ivBackground)

        coroutineScope = CoroutineScope(context = Dispatchers.IO)

        binding.tvAgeLimit.text = getString(R.string.age_limit, movie.pgAge.toString())
        binding.tvFilmName.text = movie.title
        binding.tvGenre.text = movie.genres.joinToString { genre -> genre.name }
        binding.tvStorylineContent.text = movie.storyLine
        binding.tvCountReviews.text = movie.reviewCount.toString()

        bindStars(movie.rating, binding)

        val recycler = binding.root.findViewById<RecyclerView>(R.id.rv_actors)
        adapter = ActorListAdapter()
        recycler.adapter = adapter
        recycler.layoutManager =
            LinearLayoutManager(activity as Context, LinearLayoutManager.HORIZONTAL, false)

//        adapter.bindActors(movie.actors)
        backIV = binding.root.findViewById<ImageButton>(R.id.ibBack)
            .apply {
                setOnClickListener { listener?.backClick() }
            }

        return binding.root
    }

    private fun <B> bindStars(movieRating: Int, binding: B) {
        val rating = movieRating / 2.0
        if (binding is FragmentMoviesDetailsBinding) {
            if (rating < 4.5) {
                binding.ivStar5.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.textColor
                    )
                )
            }
            if (rating < 3.5) {
                binding.ivStar4.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.textColor
                    )
                )
            }
            if (rating < 2.5) {
                binding.ivStar3.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.textColor
                    )
                )
            }
            if (rating < 1.5) {
                binding.ivStar2.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.textColor
                    )
                )
            }
            if (rating < 0.5) {
                binding.ivStar1.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.textColor
                    )
                )
            }
        }

    }

    override fun onStart() {
        super.onStart()
        updateActors()
    }

    private fun updateActors() {
        coroutineScope.launch {
            adapter.bindActors(movie.actors)
            launch(Dispatchers.Main) { adapter.notifyDataSetChanged() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setClickListener(l: MoviesDetailsClicks?) {
        listener = l
    }

    interface MoviesDetailsClicks {
        fun backClick()
    }
}