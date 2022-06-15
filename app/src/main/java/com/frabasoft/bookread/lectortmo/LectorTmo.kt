package com.frabasoft.bookread.lectortmo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frabasoft.bookread.R
import com.frabasoft.bookread.adaptadores.TmoGridAdapterTmo
import com.frabasoft.bookread.clases.AsyncTaskViewModel
import com.frabasoft.bookread.clases.TmoClase
import org.jsoup.Jsoup

class LectorTmo : AppCompatActivity() {
    private val arrayTmo = ArrayList<TmoClase>()
    private val async : AsyncTaskViewModel by lazy {
        ViewModelProvider(this)[AsyncTaskViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector_tmo)

        val grillaPopulares = findViewById<RecyclerView>(R.id.rvPopulares)
        grillaPopulares.apply {
            layoutManager = GridLayoutManager(this@LectorTmo, 3)
        }

        async.execute(
            onPreExecute = {
            }, doInBackground = {
                arrayTmo.clear()
                val url = "https://lectortmo.com/"
                try{
                    val doc = Jsoup.connect(url)
                        .userAgent("Mozilla")
                        .header("Accept", "text/html")
                        .header("Accept-Encoding", "gzip,deflate")
                        .header(
                            "Accept-Language",
                            "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2"
                        )
                        .header("Connection", "keep-alive")
                        .ignoreContentType(true)
                        .get()

                    val elementoPopular =
                        doc.select("div.row")
                            .select("div.col")
                            .select("div.tab-content")
                            .select("div#pills-populars")
                            .select("div.row")
                            .select("div.element")
                            .select("div.thumbnail")
                    Log.d("elemPop", "onCreate: $elementoPopular")
                    for(elementos in elementoPopular){
                        val imgId = elementos
                            .select("style")
                            .first()
                            .html()
                        Log.d("imgId", "onCreate: $imgId")

                        val urlImg = imgId.substring(imgId.indexOf("('") + 2, imgId.indexOf("')"))
                        Log.d("urlImg", "onCreate: $urlImg")

                        arrayTmo.add(TmoClase(urlImg, "", "", ""))
                    }
                }catch (e: Exception){
                    Log.d("async", "onCreate: " + e.message)
                }
            }, onPostExecute = {
                Log.d("ArrayTmo", "onCreate: ${arrayTmo.toArray().size}")
                try {
                    grillaPopulares.adapter = TmoGridAdapterTmo(arrayTmo)
                }catch (ex: Exception){
                    Log.d("LoadGrilla", "onCreate: ${ex.message}")
                }
            }
        )
    }
}