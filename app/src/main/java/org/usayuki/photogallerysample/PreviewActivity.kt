package org.usayuki.photogallerysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val image = intent.getIntExtra("image", R.mipmap.ic_launcher)
            transaction.add(R.id.container, PreviewFragment.newInstance(image))
            transaction.commit()
        }
    }
}