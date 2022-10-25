package com.example.marvelapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {              //Ponto de entrada, pra gerenciar fluxo
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Não é um fragmento comum, vai receber outros fragmentos para facilitar a navegação
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nave_host_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavMain.setupWithNavController(navController)

        //Aqui mostra os destinos iniciais das telas
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.charactersFragment, R.id.favoritesFragment, R.id.aboutFragment)
        )

        binding.toobarApp.setupWithNavController(navController, appBarConfiguration)

        //Configura o destino para que seta apareça no grafo de navegação
        navController.addOnDestinationChangedListener {_, destination, _ ->
            val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination) {
                binding.toobarApp.setNavigationIcon(R.drawable.ic_back)
            }

        }
    }
}


















