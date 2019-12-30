package org.usayuki.photogallerysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.github.chrisbanes.photoview.OnScaleChangedListener
import com.github.chrisbanes.photoview.OnSingleFlingListener
import com.github.chrisbanes.photoview.PhotoView

class PreviewActivity : AppCompatActivity() {

    companion object {
        private const val MIN_SCALE = 1f
        private const val MAX_SCALE = 2f
        private const val NAVIGATE_CLOSE_SCALE = 0.5f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val image = intent.getIntExtra("image", R.mipmap.ic_launcher)
        val photoView = findViewById(R.id.photo_view) as PhotoView
        photoView.setImageResource(image)
        photoView.minimumScale = MIN_SCALE
        photoView.maximumScale = MAX_SCALE

        // ピンチアウトで0.5倍まで縮小したら画面を閉じる
        photoView.setOnScaleChangeListener(object : OnScaleChangedListener {
            override fun onScaleChange(scaleFactor: Float, focusX: Float, focusY: Float) {
                if (photoView.scale < NAVIGATE_CLOSE_SCALE) {
                    finishAfterTransition()
                }
            }
        })

        // 等倍のときにスワイプしたら画面を閉じる
        photoView.setOnSingleFlingListener(object : OnSingleFlingListener {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (photoView.scale == MIN_SCALE) {
                    finishAfterTransition()
                    return true
                }
                return false
            }
        })
    }
}