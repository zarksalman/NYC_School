package com.nyc.highschool.feature.nychighSchool

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyc.highschool.R
import com.nyc.highschool.databinding.FragmentNycHighschoolListBinding

import com.nyc.highschool.feature.nychighSchool.adapter.MoviesAdapter
import com.nyc.highschool.feature.nychighSchool.listResponse.BaseListResponse
import com.nyc.highschool.feature.nychighSchool.movieListManager.ListApiManager
import com.nyc.highschool.feature.nychighSchool.movieListManager.PopularNycHighSchool
import com.nyc.highschool.feature.nychighSchool.viewmodel.NycHighSchoolListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NychighSchoolListFragment : Fragment(), LifecycleOwner, RecyclerViewClickListener {

  private lateinit var binding: FragmentNycHighschoolListBinding
  private val movieViewModel: NycHighSchoolListViewModel by viewModels()
  private var query: String = ""
  private lateinit var movieListAdapter: MoviesAdapter
  private lateinit var listApiManager: ListApiManager


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nyc_highschool_list, container, false)
//        movieViewModel =
//            ViewModelProvider(this, GlobalViewModelFactory()).get(MovieListViewModel::class.java)
    listApiManager = ListApiManager()
    return binding.root
  }

  private fun loadAPIData() {
    listApiManager.nychighSchoolList(PopularNycHighSchool(movieViewModel))
//        listApiManager.nowPlayingMovies(NowPlayingMovies(movieViewModel))
//        listApiManager.upCommingMovies(UpComingMovies(movieViewModel))

  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setAdapter()
    setSearchBar()
    createObserver()
    loadAPIData()
  }

  private fun setAdapter() {
    movieListAdapter = MoviesAdapter(this)
    binding.rvMovieList.apply {
      adapter = movieListAdapter
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
    }
  }


  private fun setSearchBar() {

    binding.searchLayoutId.searchBar.apply {
      doAfterTextChanged {
        query = it.toString()
        movieListAdapter.getFilterList(query)
      }

      setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          loadAPIData()
          return@setOnEditorActionListener true
        }
        false
      }
    }
    binding.searchLayoutId.searchBar.text.clear()
  }

  private fun createObserver() {
    movieViewModel.mutableMovieList.observe(viewLifecycleOwner, {
      movieListAdapter.apply {
        artistListData = it as ArrayList<BaseListResponse?>
        filterArtistListData = artistListData
        notifyDataSetChanged()
      }
    })

//        movieViewModel.errorMessage.observe(viewLifecycleOwner, {
//            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//        })
//
//        movieViewModel.loading.observe(viewLifecycleOwner, Observer {
////            if (it) {
////                binding.progressDialog.visibility = View.VISIBLE
////            } else {
////                binding.progressDialog.visibility = View.GONE
////            }
//        })


//        movieViewModel.apply {
//            binding.movieListViewModel = this
//            movieViewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {
//
//
//                if (it.results.isNullOrEmpty()) {
//
//
//                    binding.apply {
//                        tvArtistItemsTitle.isVisible
//                        rvMovieList.isVisible
//                    }
//
//                    binding.apply {
//                        tvArtistItemsTitle.isInvisible
//                        rvMovieList.isInvisible
//                    }
//                } else {
//                    movieListAdapter.apply {
//                        artistListData = ArrayList(it.results)
//                        filterArtistListData = artistListData
//                        notifyDataSetChanged()
//                    }
//
//
//                }
//            })
//        }
  }

  override fun onRecyclerViewItemClick(view: View, movie: BaseListResponse?) {

//    findNavController().navigate(
////      MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
////        movie!!
////      )
//    )
  }

}