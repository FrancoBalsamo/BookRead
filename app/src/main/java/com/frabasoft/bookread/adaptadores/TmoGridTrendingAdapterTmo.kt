package com.frabasoft.bookread.adaptadores

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.frabasoft.bookread.R
import com.frabasoft.bookread.clases.TmoClase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.ArrayList

class TmoGridTrendingAdapterTmo (
    private var tmoClaseArrayList: ArrayList<TmoClase>): RecyclerView.Adapter<TmoGridTrendingAdapterTmo.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        @SuppressLint("InflateParams") val view =
            LayoutInflater.from(parent.context).inflate(R.layout.grilla_trending_populares, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmoClase = tmoClaseArrayList[position]
        Picasso.get()
            .load(tmoClase.imagenUrl)
            .into(holder.portadaManga, object: Callback {
                override fun onSuccess(){
                    holder.pBGrillaTrending.visibility = View.GONE
                }
                override fun onError(e: Exception){

                }
            })
    }

    override fun getItemCount(): Int {
        return tmoClaseArrayList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var portadaManga: ImageView
        var pBGrillaTrending: ProgressBar
        init {
            portadaManga = view.findViewById(R.id.ivPortadaTrending)
            pBGrillaTrending = view.findViewById(R.id.pBGrillaTrending)
        }
    }

    fun updateData(tmoClaseArrayList: ArrayList<TmoClase>) {
        this.tmoClaseArrayList = tmoClaseArrayList
    }
}