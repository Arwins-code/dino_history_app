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

    private fun getListDinos(): ArrayList<Dino> {
        val data = ArrayList<Dino>()

        data.add(
            Dino(
                "Ankylosaurus",
                "Ankylosaurus is a genus of armored dinosaur. Its fossils have been found in geological formations dating to the very end of the Cretaceous Period, about 68–66 million years ago, in western North America, making it among the last of the non-avian dinosaurs.",
                "https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcRaY8FgzwGiD0vInmBhGnUabyHDgTprmU-WaUrJcIQqLpwngKbxO-JSR4BmjOOKIe0g"
            )
        )
        data.add(
            Dino(
                "Allosaurus",
                "Allosaurus is an extinct genus of large carnosaurian theropod dinosaur that lived 155 to 145 million years ago during the Late Jurassic period. The name \"Allosaurus\" means \"different lizard\", alluding to its unique concave vertebrae. It is derived from the Greek words ἄλλος and σαῦρος.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/WLA_hmns_Allosaurus.jpg/1200px-WLA_hmns_Allosaurus.jpg"
            )
        )
        data.add(
            Dino(
                "Triceratops",
                "Triceratops is a genus of chasmosaurine ceratopsian dinosaur that lived during the late Maastrichtian age of the Late Cretaceous period, about 68 to 66 million years ago in what is now western North America.",
                "https://t1.gstatic.com/licensed-image?q=tbn:ANd9GcT2498hupIRczIdOmivLxSCksHbrmBvOwh28DCCz1mqXi0pfUvGsNUijoZyOwyqmu19"
            )
        )
        data.add(
            Dino(
                "Carnotaurus",
                "Carnotaurus is a genus of theropod dinosaur that lived in South America during the Late Cretaceous period, probably sometime between 71 and 69 million years ago. The only species is Carnotaurus sastrei.",
                "https://t3.gstatic.com/licensed-image?q=tbn:ANd9GcSDQZSbr1q1uko267KEBU-yDx8zvXyxxLVQpaGwnuduOMvk4Msyh9hpl9_KgOSPyh-8"
            )
        )
        data.add(
            Dino(
                "Stegosaurus",
                "Stegosaurus is a genus of herbivorous, four-legged, armored dinosaur from the Late Jurassic, characterized by the distinctive kite-shaped upright plates along their backs and spikes on their tails.",
                "https://upload.wikimedia.org/wikipedia/commons/b/bc/Journal.pone.0138352.g001A.jpg"
            )
        )
        data.add(
            Dino(
                "Diplodocus",
                "Diplodocus was a genus of diplodocid sauropod dinosaurs, whose fossils were first discovered in 1877 by S. W. Williston.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Diplodocus_model.jpg/800px-Diplodocus_model.jpg"
            )
        )
        data.add(
            Dino(
                "Tyrannosaurus",
                "Tyrannosaurus is a genus of large theropod dinosaur. The species Tyrannosaurus rex, often called T. rex or colloquially T-Rex, is one of the best represented theropods. It lived throughout what is now western North America, on what was then an island continent known as Laramidia.",
                "https://static.wikia.nocookie.net/jurassicpark/images/4/42/Jurassic_Park_Tyrannosaurus_Rex.png/revision/latest?cb=20220425020331"
            )
        )
        data.add(
            Dino(
                "Dilophosaurus",
                "Dilophosaurus is a genus of theropod dinosaurs that lived in what is now North America during the Early Jurassic, about 186 million years ago. Three skeletons were discovered in northern Arizona in 1940, and the two best preserved were collected in 1942",
                "https://static.wikia.nocookie.net/jurassicpark/images/e/ef/Dilophosaurus_Render.png/revision/latest?cb=20220425014454"
            )
        )
        data.add(
            Dino(
                "Giganotosaurus",
                "Giganotosaurus is a genus of theropod dinosaur that lived in what is now Argentina, during the early Cenomanian age of the Late Cretaceous period, approximately 99.6 to 95 million years ago. The holotype specimen was discovered in the Candeleros Formation of Patagonia in 1993 and is almost 70% complete.",
                "https://t0.gstatic.com/licensed-image?q=tbn:ANd9GcSdDSV_IMDfzv9C-oBpNBBHF70E6uoIEbIN7Wo_ty6pBZEicxupCd_-uQ7DhjhZhBnS"
            )
        )
        data.add(
            Dino(
                "Kentrosaurus",
                "Kentrosaurus is a genus of stegosaurid dinosaur from the Late Jurassic in Lindi Region of Tanzania. The type species is K. aethiopicus, named and described by German palaeontologist Edwin Hennig in 1915.",
                "https://static.wikia.nocookie.net/jurassicpark/images/9/91/Kentrosaurus_from_Camp_Creatceous.png/revision/latest?cb=20211213040758"
            )
        )
        data.add(
            Dino(
                "Archaeopteryx",
                "Archaeopteryx, sometimes referred to by its German name, \"Urvogel\", is a genus of avian dinosaurs. The name derives from the ancient Greek ἀρχαῖος, meaning ancient, and πτέρυξ, meaning feather or wing.",
                "https://res.cloudinary.com/dk-find-out/image/upload/q_80,w_1920,f_auto/Archaeopteryx_hmie72.png"
            )
        )
        data.add(
            Dino(
                "Iguanodon",
                "Iguanodon, named in 1825, is a genus of iguanodontian dinosaur. While many species found worldwide have been classified in the genus Iguanodon, dating from the Late Jurassic to Early Cretaceous, taxonomic ...",
                "https://cdn.britannica.com/64/8064-050-930EB395/Iguanodon-scientists-legs-tail-ground.jpg"
            )
        )

        return data
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