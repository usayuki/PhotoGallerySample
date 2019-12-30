package org.usayuki.photogallerysample

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val GRID_SPAN = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recycler_thumbnail) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, GRID_SPAN)
        recyclerView.adapter = RecyclerViewAdapter(object : RecyclerViewAdapter.ListListener {
            override fun onClickItem(view: View, resource: Int) {
                val intent = Intent(this@MainActivity, PreviewActivity::class.java)
                intent.putExtra("image", resource)
                val options = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, Pair.create(view, "preview"))
                startActivity(intent, options.toBundle())
            }
        })
        recyclerView.setHasFixedSize(true)
    }


}
