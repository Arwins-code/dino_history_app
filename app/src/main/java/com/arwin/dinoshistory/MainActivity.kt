package com.arwin.dinoshistory

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arwin.dinoshistory.DummyData.getListDinos
import com.arwin.dinoshistory.databinding.ActivityMainBinding
import com.arwin.dinoshistory.model.Dino
import kotlin.system.exitProcess

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Dino>()
    private var doubleBackToExitPressed = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        binding.rvDino.setHasFixedSize(true)
        list.addAll(getListDinos())
        showRecyclerList()
    }

    private fun setupToolbar() = with(binding) {
        customToolbar.apply {
            setTitle(getString(R.string.app_name))
            toolbar().navigationIcon =
                AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_about)
            toolbar().setNavigationOnClickListener {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
            val endIcon =
                AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_list_option)
            setEndIcon(endIcon) {
                val popupMenu = PopupMenu(this@MainActivity, customToolbar, Gravity.END)
                popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_list -> {
                            rvDino.layoutManager = LinearLayoutManager(this@MainActivity)
                            true
                        }

                        R.id.action_grid -> {
                            rvDino.layoutManager = GridLayoutManager(this@MainActivity, 2)
                            true
                        }

                        else -> super.onOptionsItemSelected(item)
                    }
                }
                popupMenu.show()
            }
        }
    }

    private fun showRecyclerList() = with(binding) {
        rvDino.layoutManager = LinearLayoutManager(this@MainActivity)
        val listDinoAdapter = ListDinosAdapter(list)
        rvDino.adapter = listDinoAdapter

        listDinoAdapter.setOnItemClickCallback(object : ListDinosAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intent = Intent(this, DinoDetailActivity::class.java)
        val dinoData = intent.putExtra("DATA", dino)
        startActivity(dinoData)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackToExitPressed == 2) {
            finishAffinity()
            exitProcess(0)
        } else {
            doubleBackToExitPressed++
            Toast.makeText(this, "Please press Back again to exit", Toast.LENGTH_SHORT).show()
        }

        Handler().postDelayed({
            doubleBackToExitPressed = 1
        }, 2000)

    }

}