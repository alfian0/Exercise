package com.example.dicodingsubmission1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsubmission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<News>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(3000)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvNews.setHasFixedSize(true)

        list.addAll(getListNews())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListNews(): ArrayList<News> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataImage = resources.getStringArray(R.array.data_image)
        val listNews = ArrayList<News>()
        for (i in dataTitle.indices) {
            val news = News(dataTitle[i], dataDescription[i], dataImage[i])
            listNews.add(news)
        }
        return listNews
    }

    private fun showRecyclerList() {
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListNewsAdapater(list) { news ->
            val moveIntent = Intent(this@MainActivity, NewsDetailActivity::class.java)
            moveIntent.putExtra(NewsDetailActivity.EXTRA_NEWS, news)
            startActivity(moveIntent)
        }
        binding.rvNews.adapter = listHeroAdapter
    }
}