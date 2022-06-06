package com.example.imagetest

import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.github.chrisbanes.photoview.PhotoView


const val KEY_ID = "resID"
const val SCALE_ID = "scaleID"
private const val TAG = "Item"

var isZoomed = false

class Item(private val resource: Int) : Fragment() {
    private lateinit var imageView: PhotoView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isZoomed = false
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        Log.d(TAG, "onCreateView: Hello from Item fragment")

        imageView = view.findViewById(R.id.imageView)

        imageView.load(resource)

        imageView.setOnScaleChangeListener { _, _, _ ->

            if (imageView.scale > 1.0 && !isZoomed) {
                val scale = imageView.scale


                transaction(scale)



                isZoomed = true
//                imageView.setZoomable(isZoomed)

//                Log.d(TAG, "onCreateView: zoomed in!!")
            }

        }
        return view
    }

    override fun onResume() {
        super.onResume()
//        imageView.isVisible = !isZoomed
    }

    private fun transaction(scale: Float) {
        if (!isZoomed) {
            Log.d(TAG, "transaction: ")

//imageView.isVisible = false
            val args = Bundle()
            args.putInt(KEY_ID, resource)
            args.putFloat(SCALE_ID, scale)

            val fragment = ImageFragment()
            fragment.arguments = args

            activity?.let {
                it.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .addToBackStack(null)
                    .commit()

            }
        }
    }
}