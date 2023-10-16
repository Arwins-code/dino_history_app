package com.arwin.dinoshistory

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.arwin.dinoshistory.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        binding.tvName.text = "Arwinsyah Putra"
        binding.tvEmail.text = "arwinsyahputra22@gmail.com"
        copyText()
    }

    private fun setupToolbar() = with(binding) {
        customToolbar.apply {
            setTitle("Detail")
            toolbar().navigationIcon =
                AppCompatResources.getDrawable(this@AboutActivity, R.drawable.ic_back)
            toolbar().setNavigationOnClickListener { onBackPressed() }
        }
    }

    private fun copyText() = with(binding) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        ivCopyName.setOnClickListener {
            val clipData = ClipData.newPlainText("text label", tvName.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this@AboutActivity, "Text coppied", Toast.LENGTH_SHORT).show()
        }

        ivCopyEmail.setOnClickListener {
            val clipData = ClipData.newPlainText("text label", tvEmail.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this@AboutActivity, "Text coppied", Toast.LENGTH_SHORT).show()
        }

    }
}