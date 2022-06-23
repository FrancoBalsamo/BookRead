package com.frabasoft.bookread.adaptadores

import com.frabasoft.bookread.clases.TmoClase
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.frabasoft.bookread.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.util.ArrayList

class TmoGridPopularesAdapterTmo(
    private var tmoClaseArrayList: ArrayList<TmoClase>
) : RecyclerView.Adapter<TmoGridPopularesAdapterTmo.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        @SuppressLint("InflateParams") val view =
            LayoutInflater.from(parent.context).inflate(R.layout.grilla_populares_home, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmoClase = tmoClaseArrayList[position]
        Picasso.get()
            .load(tmoClase.imagenUrl)
            .into(holder.portadaManga, object: Callback {
                override fun onSuccess(){
                    holder.pBGrillaPopulares.visibility = View.GONE
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
        var pBGrillaPopulares: ProgressBar
        init {
            portadaManga = view.findViewById(R.id.ivPortadaPopulares)
            pBGrillaPopulares = view.findViewById(R.id.pBGrillaPopulares)
        }
    }

    fun updateData(tmoClaseArrayList: ArrayList<TmoClase>) {
        this.tmoClaseArrayList = tmoClaseArrayList
    }
}