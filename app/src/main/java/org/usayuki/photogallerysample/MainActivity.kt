package org.usayuki.photogallerysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val images = intArrayOf(
        R.mipmap.usayuki,
        R.mipmap.posters,
        R.mipmap.kotlin,
        R.mipmap.swift,
        R.mipmap.usatip
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
