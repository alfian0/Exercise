package com.example.dicodingsubmission1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dicodingsubmission1.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NEWS = "extra_news"
    }

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val news = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<News>(EXTRA_NEWS, News::class.java)
        } else {
            intent.getParcelableExtra<News>(EXTRA_NEWS)
        }

        binding.tvItemTitle.text = news?.title
        binding.tvItemDescription.text = news?.description
        Glide.with(this)
            .load(news?.image)
            .into(binding.imgItemPhoto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra("Share this", "Something to share")
                startActivity(Intent.createChooser(intent, "Share with"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}