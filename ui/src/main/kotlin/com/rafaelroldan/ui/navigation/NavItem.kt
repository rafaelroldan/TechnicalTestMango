package com.rafaelroldan.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArgs> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
    data object CharacterList: NavItem("characterListScreen")
    data object CharacterDetail: NavItem("characterDetailScreen", listOf(NavArgs.CharacterId)){
        fun createNavRoute(characterId: Int) = "$baseRoute/$characterId"
    }
}

enum class NavArgs(val key: String, val navType: NavType<*>) {
    CharacterId("characterId", NavType.IntType)
}