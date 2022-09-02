package com.nyc.highschool.feature.satscore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nyc.highschool.R
import com.nyc.highschool.databinding.FragmentMovieDetailBinding
import com.nyc.highschool.feature.satscore.viewmodel.SatScoreViewModel
import com.nyc.highschool.feature.nychighSchool.listResponse.BaseListResponse
import com.nyc.highschool.utils.doubleToStringNoDecimal
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

//    private val args: MovieDetailArgs by navArgs()

  private val movieDetailViewModel: SatScoreViewModel by viewModels()
  private lateinit var binding: FragmentMovieDetailBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    // Inflate the layout for this fragment

    binding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_sat_score_detail, container, false)

    return binding.root


  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
//        requireActivity().onBackPressedDispatcher
//            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                   println("")
//
//                }
//            })


    val message = arguments?.getParcelable<BaseListResponse>("movie")?.id
    createObserver()
    message?.let { loadAPIData(it) }

  }

  private fun addBackPress() {
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner)
//        {
//            findNavController().navigate(
//                MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment()
//            )
//        }
  }


  private fun loadAPIData(query: Int) {

    movieDetailViewModel.prepareMovieDetailRepo(query)
  }

  private fun createObserver() {
    movieDetailViewModel.apply {
      binding.movieDetailViewModel = this
      movieDetailViewModel.movieDetailLiveData.observe(viewLifecycleOwner, Observer {
        it.run {
          binding.apply {

            loadImage(posterImage, it.poster_path)
            loadImage(backdropImage, it.backdrop_path)
            tvMovieDuration.text = "${it.runtime} mins"
            tvOverview.text = "Overview : ${it.overview}"
            tvName.text = it.original_title
            tvAverageVote.text = it.vote_average.toString()
            tvTotalVote.text = it.vote_count.toString()
            tvRevenue.text = doubleToStringNoDecimal(it.revenue)
          }
        }


      })
    }
  }

  fun loadImage(view: ImageView, url: String) {
    Picasso.get().load("http://image.tmdb.org/t/p/w500$url").centerCrop().resize(100, 199)
      .placeholder(R.drawable.user_placeholder)
      .error(R.drawable.user_placeholder_error)

      .into(view);
  }

}