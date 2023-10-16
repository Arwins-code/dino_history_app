package com.arwin.dinoshistory

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.arwin.dinoshistory.databinding.ActivityDinoDetailBinding
import com.arwin.dinoshistory.model.Dino
import com.bumptech.glide.Glide

class DinoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupData()
        setupToolbar()
    }

    private fun setupToolbar() = with(binding) {
        customToolbar.apply {
            setTitle("Detail")
            toolbar().navigationIcon =
                AppCompatResources.getDrawable(this@DinoDetailActivity, R.drawable.ic_back)
            toolbar().setNavigationOnClickListener { onBackPressed() }
        }
    }

    private fun setupData() = with(binding) {
        val x = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("DATA", Dino::class.java)
        } else {
            intent.getParcelableExtra("DATA")
        }
        if (x != null) {
            Glide.with(this@DinoDetailActivity)
                .load(x.photo)
                .into(ivDinoImage)
            tvName.text = x.name
            tvContent.text = x.description
        }
    }

}