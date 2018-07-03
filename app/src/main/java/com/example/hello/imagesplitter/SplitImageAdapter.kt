package com.example.hello.imagesplitter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.graphics.BitmapFactory
import com.bumptech.glide.Glide
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.TextView


class SplitImageAdapter(var context: Context,var list: List<Bitmap>) : RecyclerView.Adapter<SplitImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.small_image_items, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        bindItems(holder!!, context, position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView : ImageView=itemView.findViewById(R.id.small_image)
    }

    private fun bindItems(holder: ViewHolder, context: Context, position: Int) {
        val drawable = BitmapDrawable(context.getResources(), list[position])
        holder.imageView.setImageDrawable(drawable)
    }
}