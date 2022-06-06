package com.example.imagetest

import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.dispose
import coil.load
import com.github.chrisbanes.photoview.PhotoView

private const val TAG = "ImageFragment"

class ImageFragment() : Fragment() {

    private lateinit var imageView: PhotoView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)

        Log.d(TAG, "onCreateView: Hello from ImageFragment")

        imageView = view.findViewById(R.id.imageView2)

        val resource = arguments?.getInt(KEY_ID)

        imageView.load(resource)
        val scale = arguments?.getFloat(SCALE_ID)!!
        imageView.scale = scale
        imageView.setOnScaleChangeListener { _, _, _ ->
            Log.d(TAG, "onCreateView: scale ${imageView.scale}")
            if (imageView.scale < 1.0) {
                transaction()
                isZoomed = false
            }
        }



        return view
    }

    private fun transaction() {
        if (isZoomed) {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}