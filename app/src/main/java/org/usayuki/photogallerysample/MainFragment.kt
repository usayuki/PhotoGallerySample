package org.usayuki.photogallerysample

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    companion object {
        private const val GRID_SPAN = 3
        private const val IMAGES = "images"
        fun newInstance(images: IntArray) = MainFragment().also {
            it.arguments = bundleOf(
                IMAGES to images
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val images = arguments!!.getIntArray(IMAGES)
        val recyclerView = view.findViewById(R.id.recycler_thumbnail) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), GRID_SPAN)
        recyclerView.adapter = RecyclerViewAdapter(images!!, object : RecyclerViewAdapter.ListListener {
            override fun onClickItem(view: View, resource: Int) {
                val intent = Intent(requireActivity(), PreviewActivity::class.java)
                intent.putExtra("image", resource)
                val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity(), Pair.create(view, "preview"))
//                val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity(), view, "preview")
                startActivity(intent, options.toBundle())
            }
        })
    }
}