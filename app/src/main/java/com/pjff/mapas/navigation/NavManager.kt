package com.pjff.mapas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pjff.mapas.viewModel.SearchViewModel
import com.pjff.mapas.views.HomeView
import com.pjff.mapas.views.MapSearchView

@Composable
fun NavManager(searchVM: SearchViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, searchVM)
        }
        //Vid 276
        composable("MapSearchView/{lat}/{long}/{address}", arguments = listOf(
            navArgument("lat") { type = NavType.FloatType },
            navArgument("long") { type = NavType.FloatType },
            navArgument("address") { type = NavType.StringType },
        )){
            val lat = it.arguments?.getFloat("lat") ?: 0.0
            val long = it.arguments?.getFloat("long") ?: 0.0
            val address = it.arguments?.getString("address") ?: ""
            MapSearchView(lat.toDouble(), long.toDouble(), address)
        }
    }
}