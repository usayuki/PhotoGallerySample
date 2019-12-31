package org.usayuki.photogallerysample

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.recycler_view.view.*

class RecyclerViewAdapter(private val images: IntArray, private val listener: ListListener) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    companion object {
        private const val ITEM_COUNT = 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_view, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.image.setImageResource(images[position])
        holder.image.setOnClickListener {
            listener.onClickItem(it, images[position])
        }
    }

    interface ListListener {
        fun onClickItem(view: View, resource: Int)
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image_thumbnail
    }
}