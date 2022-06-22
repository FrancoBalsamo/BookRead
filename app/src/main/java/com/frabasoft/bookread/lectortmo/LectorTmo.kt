package com.frabasoft.bookread.lectortmo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
    private var grillaPopulares: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector_tmo)

        val btnPopulares = findViewById<Button>(R.id.btnPopulares)
        val btnPSeinen = findViewById<Button>(R.id.btnPSeinen)
        val btnPJosei = findViewById<Button>(R.id.btnPJosei)

        grillaPopulares = findViewById(R.id.rvPopulares)
        grillaPopulares?.apply {
            layoutManager = GridLayoutManager(this@LectorTmo, 3)
        }

        loadPopulares()

        btnPopulares.setOnClickListener{
            btnPopulares.setBackgroundResource(R.color.tmoAzul)
            btnPopulares.setTextColor(Color.WHITE)

            loadPopulares()

            btnPSeinen.setBackgroundResource(R.color.tmoGris)
            btnPSeinen.setTextColor(R.color.tmoAzul)

            btnPJosei.setBackgroundResource(R.color.tmoGris)
            btnPJosei.setTextColor(R.color.tmoAzul)
        }

        btnPSeinen.setOnClickListener {
            loadPopularesChicos()

            btnPopulares.setBackgroundResource(R.color.tmoGris)
            btnPopulares.setTextColor(R.color.tmoAzul)

            btnPSeinen.setBackgroundResource(R.color.tmoAzul)
            btnPSeinen.setTextColor(Color.WHITE)

            btnPJosei.setBackgroundResource(R.color.tmoGris)
            btnPJosei.setTextColor(R.color.tmoAzul)
        }

        btnPJosei.setOnClickListener{
            loadPopularesChicas()

            btnPopulares.setBackgroundResource(R.color.tmoGris)
            btnPopulares.setTextColor(R.color.tmoAzul)

            btnPSeinen.setBackgroundResource(R.color.tmoGris)
            btnPSeinen.setTextColor(R.color.tmoAzul)

            btnPJosei.setBackgroundResource(R.color.tmoAzul)
            btnPJosei.setTextColor(Color.WHITE)
        }
    }

    private fun loadPopulares(){
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
                    Log.d("elemPop", "loadPopulares: $elementoPopular")
                    for(elementos in elementoPopular){
                        val imgId = elementos
                            .select("style")
                            .first()
                            .html()
                        Log.d("imgId", "loadPopulares: $imgId")

                        val urlImg = imgId.substring(imgId.indexOf("('") + 2, imgId.indexOf("')"))
                        Log.d("urlImg", "loadPopulares: $urlImg")

                        arrayTmo.add(TmoClase(urlImg, "", "", ""))
                    }
                }catch (e: Exception){
                    Log.d("async", "loadPopulares: " + e.message)
                }
            }, onPostExecute = {
                Log.d("ArrayTmo", "loadPopulares: ${arrayTmo.toArray().size}")
                try {
                    grillaPopulares?.adapter = TmoGridAdapterTmo(arrayTmo)
                }catch (ex: Exception){
                    Log.d("LoadGrilla", "loadPopulares: ${ex.message}")
                }
            }
        )
    }

    private fun loadPopularesChicos(){
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
                            .select("div#pills-populars-boys")
                            .select("div.row")
                            .select("div.element")
                            .select("div.thumbnail")
                    Log.d("elemPop", "loadPopularesChicos: $elementoPopular")
                    for(elementos in elementoPopular){
                        val imgId = elementos
                            .select("style")
                            .first()
                            .html()
                        Log.d("imgId", "loadPopularesChicos: $imgId")

                        val urlImg = imgId.substring(imgId.indexOf("('") + 2, imgId.indexOf("')"))
                        Log.d("urlImg", "loadPopularesChicos: $urlImg")

                        arrayTmo.add(TmoClase(urlImg, "", "", ""))
                    }
                }catch (e: Exception){
                    Log.d("async", "loadPopularesChicos: " + e.message)
                }
            }, onPostExecute = {
                Log.d("ArrayTmo", "loadPopularesChicos: ${arrayTmo.toArray().size}")
                try {
                    grillaPopulares?.adapter = TmoGridAdapterTmo(arrayTmo)
                }catch (ex: Exception){
                    Log.d("LoadGrilla", "loadPopularesChicos: ${ex.message}")
                }
            }
        )
    }

    private fun loadPopularesChicas(){
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
                            .select("div#pills-populars-girls")
                            .select("div.row")
                            .select("div.element")
                            .select("div.thumbnail")
                    Log.d("elemPop", "loadPopulares: $elementoPopular")
                    for(elementos in elementoPopular){
                        val imgId = elementos
                            .select("style")
                            .first()
                            .html()
                        Log.d("imgId", "loadPopulares: $imgId")

                        val urlImg = imgId.substring(imgId.indexOf("('") + 2, imgId.indexOf("')"))
                        Log.d("urlImg", "loadPopulares: $urlImg")

                        arrayTmo.add(TmoClase(urlImg, "", "", ""))
                    }
                }catch (e: Exception){
                    Log.d("async", "loadPopulares: " + e.message)
                }
            }, onPostExecute = {
                Log.d("ArrayTmo", "loadPopulares: ${arrayTmo.toArray().size}")
                try {
                    grillaPopulares?.adapter = TmoGridAdapterTmo(arrayTmo)
                }catch (ex: Exception){
                    Log.d("LoadGrilla", "loadPopulares: ${ex.message}")
                }
            }
        )
    }
}