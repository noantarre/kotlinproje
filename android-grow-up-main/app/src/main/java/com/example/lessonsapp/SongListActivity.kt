package com.example.lessonsapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class SongListActivity : AppCompatActivity() {

    private lateinit var artistAdapter: ArtistRecyclerAdapter
    private lateinit var songAdapter: SongRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val recyclerViewArtists: RecyclerView = findViewById(R.id.rvArtists)
        val recyclerViewForro: RecyclerView = findViewById(R.id.rvForro)
        val recyclerViewStyles: RecyclerView = findViewById(R.id.rvStyles)
        val recyclerViewNineties: RecyclerView = findViewById(R.id.rvNineties)
        val recyclerViewHits: RecyclerView = findViewById(R.id.rvHits)
        val searchBar: EditText = findViewById(R.id.etSearch)
        val searchIcon: ImageView = findViewById(R.id.ivSearch)

        searchIcon.setOnClickListener {
            if (searchBar.visibility == View.GONE) {
                searchBar.visibility = View.VISIBLE
            } else {
                searchBar.visibility = View.GONE
            }
        }

        searchBar.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                val query = searchBar.text.toString()
                search(query)
                true
            } else {
                false
            }
        })

        val artistList = listOf(
            ArtistModel("The Weeknd", R.drawable.the_weeknd),
            ArtistModel("Linkin Park", R.drawable.linkin_park),
            ArtistModel("Linux", R.drawable.linux)
            // Adicione mais artistas conforme necessário
        )

        artistAdapter = ArtistRecyclerAdapter(this, artistList) {
            showMessage(this, "Clicou no artista: ${it.name}")
        }

        // Configurar LayoutManager e Adapter para a seção "Artistas"
        val layoutManagerArtists = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewArtists.layoutManager = layoutManagerArtists
        recyclerViewArtists.adapter = artistAdapter

        val songList = listOf(
            SongModel("Numb", "Linkin Park", "Meteora", R.drawable.linkin_park),
            SongModel("Blinding Lights", "The Weeknd", "Blinding Lights", R.drawable.the_weeknd),
            SongModel("Blinding Lights", "The Weeknd", "Blinding Lights", R.drawable.the_weeknd),
            SongModel("Numb", "Linkin Park", "Meteora", R.drawable.linkin_park)
        )

        songAdapter = SongRecyclerAdapter(this, songList) {
            showMessage(this, "Clicou na música: ${it.title}")
        }

        // Configurar LayoutManager e Adapter para as outras seções
        val layoutManagerForro = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewForro.layoutManager = layoutManagerForro
        recyclerViewForro.adapter = songAdapter

        val layoutManagerStyles = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewStyles.layoutManager = layoutManagerStyles
        recyclerViewStyles.adapter = songAdapter

        val layoutManagerNineties = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNineties.layoutManager = layoutManagerNineties
        recyclerViewNineties.adapter = songAdapter

        val layoutManagerHits = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHits.layoutManager = layoutManagerHits
        recyclerViewHits.adapter = songAdapter

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_musicas -> {
                    true
                }
                R.id.nav_favoritas -> {
                    true
                }
                R.id.nav_outros -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun search(query: String) {
        val filteredArtists = artistAdapter.getOriginalList().filter { it.name.contains(query, ignoreCase = true) }
        artistAdapter.updateList(filteredArtists)

        val filteredSongs = songAdapter.getOriginalList().filter { it.title.contains(query, ignoreCase = true) }
        songAdapter.updateList(filteredSongs)
    }
}
