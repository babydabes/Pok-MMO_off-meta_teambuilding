package com.pokemmo.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pokemmo.feature.damagecalc.DamageCalcScreen
import com.pokemmo.feature.pokedex.PokedexScreen
import com.pokemmo.feature.pokedex.PokemonDetailScreen
import com.pokemmo.feature.taxonomy.TaxonomyScreen
import com.pokemmo.feature.teambuilder.TeamBuilderScreen
import com.pokemmo.feature.teambuilder.TeamListScreen

sealed class Screen(val route: String) {
    data object TeamList : Screen("teams")
    data object TeamBuilder : Screen("team/{teamId}") {
        fun createRoute(id: Long) = "team/$id"
    }
    data object Pokedex : Screen("pokedex")
    data object PokemonDetail : Screen("pokemon/{pokemonId}") {
        fun createRoute(id: Int) = "pokemon/$id"
    }
    data object Taxonomy : Screen("taxonomy")
    data object DamageCalc : Screen("damage-calc")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TeamList.route,
        modifier = modifier,
    ) {
        composable(Screen.TeamList.route) {
            TeamListScreen(
                onTeamClick = { id -> navController.navigate(Screen.TeamBuilder.createRoute(id)) },
                onNewTeam = { id -> navController.navigate(Screen.TeamBuilder.createRoute(id)) },
            )
        }

        composable(
            route = Screen.TeamBuilder.route,
            arguments = listOf(navArgument("teamId") { type = NavType.LongType }),
        ) {
            TeamBuilderScreen(
                teamId = it.arguments!!.getLong("teamId"),
                onNavigateUp = { navController.navigateUp() },
            )
        }

        composable(Screen.Pokedex.route) {
            PokedexScreen(
                onPokemonClick = { id ->
                    navController.navigate(Screen.PokemonDetail.createRoute(id))
                },
            )
        }

        composable(
            route = Screen.PokemonDetail.route,
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType }),
        ) {
            PokemonDetailScreen(
                pokemonId = it.arguments!!.getInt("pokemonId"),
                onNavigateUp = { navController.navigateUp() },
            )
        }

        composable(Screen.Taxonomy.route) {
            TaxonomyScreen(
                onPokemonClick = { id ->
                    navController.navigate(Screen.PokemonDetail.createRoute(id))
                },
            )
        }

        composable(Screen.DamageCalc.route) {
            DamageCalcScreen()
        }
    }
}
