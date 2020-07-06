package com.sean.spotifyapp.screens.search.views

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.sean.spotifyapp.models.Artist

class SearchResultAdapter:
    RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder = SearchResultViewHolder(SearchResultView(parent.context))

    class SearchResultViewHolder(val searchResultView: SearchResultView) :
        RecyclerView.ViewHolder(searchResultView)

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.searchResultView.setArtist(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    private val diffCallback = object : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean = oldItem == newItem
    }

    private val differ =
        AsyncListDiffer(BlogRecyclerChangeCallback(this), AsyncDifferConfig.Builder(diffCallback).build())

    internal inner class BlogRecyclerChangeCallback(
        private val adapter: SearchResultAdapter
    ) : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }
    }

    fun submitList(artistList: List<Artist>?, isQueryExhausted: Boolean) {
        val newList = artistList?.toMutableList()
//        if (isQueryExhausted)
//            newList?.add(NO_MORE_RESULTS_BLOG_MARKER)
        differ.submitList(newList)
    }


}