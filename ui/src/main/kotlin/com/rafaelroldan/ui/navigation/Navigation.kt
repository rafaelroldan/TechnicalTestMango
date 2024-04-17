package com.rafaelroldan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rafaelroldan.ui.characterdetails.CharacterDetailsScreen
import com.rafaelroldan.ui.characterlist.CharacterListScreen

//TODO - Test NavHost
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.CharacterList.route
    ) {
        composable(
            route = NavItem.CharacterList.route
        ) {
            CharacterListScreen { characterId ->
                navController.navigate(NavItem.CharacterDetail.createNavRoute(characterId))
            }
        }
        composable(
            route = NavItem.CharacterDetail.route,
            arguments = NavItem.CharacterDetail.navArgs
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt(ARGUMENT_CHARACTER_DETAILS_ID)
            requireNotNull(characterId)
            CharacterDetailsScreen(
                characterId = characterId
            ){
                navController.navigateUp()
            }
        }
    }
}
