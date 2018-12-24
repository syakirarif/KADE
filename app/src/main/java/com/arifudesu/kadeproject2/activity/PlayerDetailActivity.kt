package com.arifudesu.kadeproject2.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.RelativeLayout
import com.arifudesu.kadeproject2.R
import com.arifudesu.kadeproject2.model.Player
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_player_detail.*
import com.applikeysolutions.animation.BlurAnimation
import com.applikeysolutions.animation.orionpreview.ScaleAnimation
import com.applikeysolutions.animation.orionpreview.TranslationAnimation
import kotlinx.android.synthetic.main.player_detail_card.*

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var list: Player

    private lateinit var playerId: String

    private lateinit var increaseAnimationImage: ScaleAnimation
    private lateinit var decreaseAnimationImage: ScaleAnimation
    private lateinit var upAnimationImageView: TranslationAnimation
    private lateinit var downAnimationImageView: TranslationAnimation
    private lateinit var blurAnimation: BlurAnimation

    private lateinit var blurredBitmap: Bitmap
    private var originalBitmap: Bitmap? = null
    private var screenHeight: Float = 0.0f
    private var screenWidth: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val intent = intent

        list = intent.getParcelableExtra("data")
        playerId = list.playerId!!

        Log.d("playerId", list.playerId)
        Log.d("playerName", list.playerName)

        val image = findViewById<CircleImageView>(R.id.img_player_card_photo)

        Picasso.get()
            .load(list.playerPhoto?.trim())
            .placeholder(R.drawable.img_blank_photo)
            .into(image)

        Picasso.get()
            .load(list.playerThumb?.trim())
            .into(img_player_thumb)

        //tv_player_title.text = list.playerName
        tv_player_card_position.text = list.playerPosition
        tv_player_card_nationality.text = list.playerNationality
        tv_player_card_desc.text = list.playerDesc
        tv_player_card_name.text = list.playerName

        cv_player_detail.setOnClickListener {
            upAnimationImageView.showAnimation()
            increaseAnimationImage.showAnimation()

            cv_player_detail.isClickable = false
            rl_container.isClickable = true
            addBlur()
        }

        rl_container.setOnClickListener {
            downAnimationImageView.showAnimation()
            decreaseAnimationImage.showAnimation()

            rl_container.isClickable = false
            cv_player_detail.isClickable = true
            removeBlur()
        }

        getScreenSize()
        setPlayerBottomMargin()
        initAnimation()
    }

    fun getScreenSize() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels.toFloat()
        screenWidth = displayMetrics.widthPixels.toFloat()
    }

    fun setPlayerBottomMargin() {
        ll_product_details.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            @SuppressLint("ObsoleteSdkInt")
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    ll_product_details.viewTreeObserver.removeGlobalOnLayoutListener(this)
                } else {
                    ll_product_details.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
                val params = cv_player_detail.layoutParams as RelativeLayout.LayoutParams
                params.bottomMargin = ll_product_details.measuredHeight * -1
                cv_player_detail.layoutParams = params
            }

        })
    }

    override fun onResume() {
        super.onResume()
        initAnimation()
    }

    fun initAnimation(){
        img_player_thumb.post {

            increaseAnimationImage = ScaleAnimation.ScaleAnimationBuilder(img_player_thumb, 1.5f, 1.5f).build()
            decreaseAnimationImage = ScaleAnimation.ScaleAnimationBuilder(img_player_thumb, 1f, 1f).build()
            upAnimationImageView = TranslationAnimation.TranslationAnimationBuilder(cv_player_detail,
                TranslationAnimation.TranslationMode.TranslationY, 0F, -(screenHeight / 2.2f))
                .build()

            downAnimationImageView = TranslationAnimation.TranslationAnimationBuilder(cv_player_detail,
                TranslationAnimation.TranslationMode.TranslationY, -(screenHeight / 3), 0F)
                .build()

            blurAnimation = BlurAnimation.BlurAnimationBuilder(0.4f, 7f).build()
            //Log.d("URLplayerThumb", list.playerThumb?.trim())

            Picasso.get()
                .load(list.playerThumb?.trim())
                .into(object : com.squareup.picasso.Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    originalBitmap = bitmap
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.img_blank_photo)
                }
            })

            if(originalBitmap == null){
                originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.img_blank_photo)
            }

            blurredBitmap = blurAnimation.blur(this, originalBitmap)

        }
    }

    fun addBlur() {
        img_player_thumb.setImageBitmap(BitmapDrawable(resources, blurredBitmap).bitmap)
    }

    fun removeBlur() {
        Picasso.get()
            .load(list.playerThumb?.trim())
            .into(img_player_thumb)
    }
}
