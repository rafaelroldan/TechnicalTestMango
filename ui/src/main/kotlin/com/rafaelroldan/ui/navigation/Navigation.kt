package com.rafaelroldan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rafaelroldan.ui.characterdetails.CharacterDetailsScreen
import com.rafaelroldan.ui.characterlist.CharacterListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.CharacterList.route)
    {
        composable(NavItem.CharacterList) {
            CharacterListScreen { characterId ->
                navController.navigate(NavItem.CharacterDetail.createNavRoute(characterId))
            }
        }
        composable(NavItem.CharacterDetail) { backStackEntry ->
            CharacterDetailsScreen(
                characterId = backStackEntry.findArg(NavArgs.CharacterId)
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    this.composable(
        route = navItem.route,
        arguments = navItem.args,
    ){
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArgs): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}