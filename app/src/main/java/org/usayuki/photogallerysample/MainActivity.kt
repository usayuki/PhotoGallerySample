package org.usayuki.photogallerysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val images = intArrayOf(
        R.drawable.usayuki,
        R.drawable.posters,
        R.drawable.kotlin,
        R.drawable.swift,
        R.drawable.usatip
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container, MainFragment.newInstance(images))
            transaction.commit()
        }
    }


}
