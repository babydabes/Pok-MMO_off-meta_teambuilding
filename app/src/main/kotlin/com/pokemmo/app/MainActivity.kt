package com.pokemmo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pokemmo.app.navigation.AppNavHost
import com.pokemmo.app.navigation.Screen
import com.pokemmo.core.ui.theme.PokeMMOTeamBuilderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeMMOTeamBuilderTheme {
                MainScaffold()
            }
        }
    }
}

private data class NavItem(
    val label: String,
    val icon: @Composable () -> Unit,
    val route: String,
)

@Composable
private fun MainScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navItems = listOf(
        NavItem("Teams", { Icon(Icons.Default.Groups, "Teams") }, Screen.TeamList.route),
        NavItem("Pokédex", { Icon(Icons.Default.MenuBook, "Pokédex") }, Screen.Pokedex.route),
        NavItem("Taxonomy", { Icon(Icons.Default.Category, "Taxonomy") }, Screen.Taxonomy.route),
        NavItem("Calc", { Icon(Icons.Default.Calculate, "Calc") }, Screen.DamageCalc.route),
    )

    // Only show bottom bar on top-level screens
    val showBottomBar = currentDestination?.route in navItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    navItems.forEach { item ->
                        NavigationBarItem(
                            icon = item.icon,
                            label = { Text(item.label) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        )
                    }
                }
            }
        },
    ) { padding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(padding),
        )
    }
}
