package com.nyc.highschool.feature.nychighSchool.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nyc.highschool.R
import com.nyc.highschool.databinding.MovieListRecyclerviewItemBinding
import com.nyc.highschool.feature.nychighSchool.RecyclerViewClickListener
import com.nyc.highschool.feature.nychighSchool.listResponse.BaseListResponse

class MoviesAdapter(private val listener: RecyclerViewClickListener) :
  RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

  var artistListData = ArrayList<BaseListResponse?>()
  var filterArtistListData = ArrayList<BaseListResponse?>()


  override fun getItemCount() = filterArtistListData.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    MoviesViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.nyc_school_list_recyclerview_item,
        parent,
        false
      )
    )

  override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    holder.recyclerviewMovieBinding.movie = filterArtistListData[position]
    holder.recyclerviewMovieBinding.root.setOnClickListener {
      listener.onRecyclerViewItemClick(
        holder.recyclerviewMovieBinding.root, filterArtistListData[position]
      )
    }
  }


  inner class MoviesViewHolder(
    val recyclerviewMovieBinding: MovieListRecyclerviewItemBinding
  ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)


  fun getFilterList(query: String): ArrayList<BaseListResponse?> {
    if (query.isEmpty()) {
      filterArtistListData = artistListData
    }

    filterArtistListData = ArrayList(artistListData.filter {
      it?.title?.toLowerCase()?.contains(query.toLowerCase()) == true
    })
    notifyDataSetChanged()
    return filterArtistListData
  }

}