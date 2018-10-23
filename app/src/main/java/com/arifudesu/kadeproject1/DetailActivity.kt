package com.arifudesu.kadeproject1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    private var detail: String = ""
    private var nama: String = ""
    private var logo: Int = 0
    private lateinit var detailText: TextView
    private lateinit var logoImage: ImageView
    private lateinit var namaText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /////// ANKO LAYOUT ////////
        verticalLayout {
            padding = dip(16)

            //Text Judul
            namaText = textView{
                textSize = 20f
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                textColor = Color.BLACK

            }.lparams(width = matchParent, height = wrapContent)

            //Gambar Logo
            logoImage = imageView().lparams(width = matchParent, height = 200){
                padding = dip(16)
                topMargin = dip(12)
                bottomMargin = dip(12)
            }

            //Text Detail
            detailText = textView{
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }

        }

        val intent = intent
        detail = intent.getStringExtra("detail")
        logo = intent.getIntExtra("logo", 0)
        nama = intent.getStringExtra("nama")

        namaText.text = nama
        detailText.text = detail
        Glide.with(this).load(logo).into(logoImage)
    }
}
