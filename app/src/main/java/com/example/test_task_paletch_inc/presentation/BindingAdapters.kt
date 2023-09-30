package com.example.test_task_paletch_inc.presentation

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.data.network.NYTimesApiStatus
import com.example.test_task_paletch_inc.data.network.NYTimesApiStatus.*

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let{
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView,
               status: NYTimesApiStatus?) {
    when (status) {
        LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DONE -> {
            statusImageView.visibility = View.GONE
        }

        null -> TODO()
    }
}