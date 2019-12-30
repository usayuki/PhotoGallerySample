package org.usayuki.photogallerysample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.chrisbanes.photoview.OnScaleChangedListener
import com.github.chrisbanes.photoview.OnSingleFlingListener
import com.github.chrisbanes.photoview.PhotoView

class PreviewFragment : Fragment() {

    companion object {
        private const val MIN_SCALE = 1f
        private const val MAX_SCALE = 2f
        private const val NAVIGATE_CLOSE_SCALE = 0.5f
        private const val IMAGE = "image"
        fun newInstance(image: Int) = PreviewFragment().also {
            it.arguments = bundleOf(
                IMAGE to image
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val image = arguments!!.getInt(IMAGE)
        val photoView = view.findViewById(R.id.photo_view) as PhotoView
        photoView.setImageResource(image)
        photoView.minimumScale = MIN_SCALE
        photoView.maximumScale = MAX_SCALE

        // ピンチアウトで0.5倍まで縮小したら画面を閉じる
        photoView.setOnScaleChangeListener(object : OnScaleChangedListener {
            override fun onScaleChange(scaleFactor: Float, focusX: Float, focusY: Float) {
                if (photoView.scale < NAVIGATE_CLOSE_SCALE) {
                    requireActivity().finishAfterTransition()
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
                    requireActivity().finishAfterTransition()
                    return true
                }
                return false
            }
        })
    }
}