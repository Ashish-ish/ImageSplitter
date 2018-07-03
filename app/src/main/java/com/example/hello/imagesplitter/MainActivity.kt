package com.example.hello.imagesplitter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image_splitter.setOnClickListener(View.OnClickListener {

            onClickListener()
        })
        val bitmap:Bitmap?=null
        Glide.with(this)
                    .load("https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg")
                .error(R.drawable.abc_ic_arrow_drop_right_black_24dp)
                .into(image)


    }

    private fun onClickListener() {
            Glide.with(this)
                .load("https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg")
                .asBitmap()
                    .into(object : SimpleTarget<Bitmap>(24500,24500){
                        override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                            var intent = Intent(this@MainActivity, SplitImageActivity::class.java)
                            val stream = ByteArrayOutputStream()
                            resource!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
                            val byteArray = stream.toByteArray()
                            intent.putExtra("image", byteArray)
                            startActivity(intent)
                        }

                    })
    }
}
