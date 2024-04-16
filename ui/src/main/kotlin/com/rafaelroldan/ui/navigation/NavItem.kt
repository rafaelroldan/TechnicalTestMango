package com.rafaelroldan.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NamedNavArgument> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.name}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }
    data object CharacterList: NavItem(ROUTER_CHARACTER_LIST)
    data object CharacterDetail: NavItem(ROUTER_CHARACTER_DETAILS, listOf(navArgument(ARGUMENT_CHARACTER_DETAILS_ID) { type = NavType.IntType })){
        fun createNavRoute(characterId: Int) = "$baseRoute/$characterId"
    }
}

const val ROUTER_CHARACTER_LIST = "characterListScreen"
const val ROUTER_CHARACTER_DETAILS = "characterDetailScreen"
const val ARGUMENT_CHARACTER_DETAILS_ID = "characterId"