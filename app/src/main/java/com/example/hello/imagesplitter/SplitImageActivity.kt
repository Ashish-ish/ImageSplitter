package com.example.hello.imagesplitter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.recyclerview.*


class SplitImageActivity : AppCompatActivity(){

    var adapterRecyclerAdapter:SplitImageAdapter?=null
    var list : List<Bitmap>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview)
        list=ArrayList<Bitmap>()
        val extras = intent.extras
        val byteArray = extras!!.getByteArray("image")

        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        list=splitImage(bmp,49)
        setUpRecyclerView(list!!)

    }

    private fun splitImage(bitmap: Bitmap, numberOfBlocks: Int) : List<Bitmap> {

        val rows: Int
        val cols: Int
        val smallimage_Height: Int
        val smallimage_Width: Int

        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)

        cols = Math.sqrt(numberOfBlocks.toDouble()).toInt()
        rows = cols
        smallimage_Height = bitmap.height / rows
        smallimage_Width = bitmap.width / cols


        var yCo = 0
        val list=ArrayList<Bitmap>()

        for (x in 0 until rows) {
            var xCo = 0
            for (y in 0 until cols) {
                try {
                    val bmp = Bitmap.createBitmap(scaledBitmap, xCo, yCo, smallimage_Width, smallimage_Height)
                    list.add(bmp)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                xCo += smallimage_Width
            }
            yCo += smallimage_Height
        }

        return list
    }
    private fun setUpRecyclerView(list: List<Bitmap>) {
        adapterRecyclerAdapter=SplitImageAdapter(this,list)
        recyclerView!!.adapter = adapterRecyclerAdapter
        recyclerView!!.layoutManager = GridLayoutManager(this, 10, GridLayoutManager.HORIZONTAL, false)

    }
}