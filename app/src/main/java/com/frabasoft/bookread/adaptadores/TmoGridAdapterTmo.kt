package com.frabasoft.bookread.adaptadores

import com.frabasoft.bookread.clases.TmoClase
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.frabasoft.bookread.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

class TmoGridAdapterTmo(
    private var tmoClaseArrayList: ArrayList<TmoClase>
) : RecyclerView.Adapter<TmoGridAdapterTmo.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        @SuppressLint("InflateParams") val view =
            LayoutInflater.from(parent.context).inflate(R.layout.grilla_populares, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmoClase = tmoClaseArrayList[position]
        Picasso.get().load(tmoClase.imagenUrl).into(holder.portadaManga)
    }

    override fun getItemCount(): Int {
        return tmoClaseArrayList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var portadaManga: ImageView

        init {
            portadaManga = view.findViewById(R.id.ivPortadaPopulares)
        }
    }

    fun updateData(tmoClaseArrayList: ArrayList<TmoClase>) {
        this.tmoClaseArrayList = tmoClaseArrayList
    }
}