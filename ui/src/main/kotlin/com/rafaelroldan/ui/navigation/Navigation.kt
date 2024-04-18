package com.rafaelroldan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rafaelroldan.common.ConstantsTesting.TEST_TAG_NAVIGATION_HOST
import com.rafaelroldan.ui.characterdetails.CharacterDetailsScreen
import com.rafaelroldan.ui.characterlist.CharacterListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.testTag(TEST_TAG_NAVIGATION_HOST),
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
