package com.example.sumission1.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.sumission1.data.response.ListEventsItem
import com.example.sumission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_EVENT, ListEventsItem::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_EVENT)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        viewModel.event.observe(this) { event ->
            supportActionBar?.title = event.name
            Glide.with(this)
                .load(event.mediaCover)
                .into(binding.imgItemPhoto)
            binding.tvItemTitle.text = event?.name
            binding.tvEventOrganizer.text = event?.ownerName
            binding.tvEventDate.text = event?.beginTime
            binding.tvEventQuota.text = ((event?.quota ?: 0)-(event?.registrants ?: 0)).toString()
            binding.tvItemDescription.text = HtmlCompat.fromHtml(event.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        binding.btRegister.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(event?.link))
            startActivity(intent)
        }

        viewModel.getEvent(event?.id ?: 0)

        supportActionBar?.title = event?.name
        Glide.with(this)
            .load(event?.mediaCover)
            .into(binding.imgItemPhoto)
        binding.tvItemTitle.text = event?.name
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_EVENT = "extra_event"
    }
}