package com.cartoonapp.animemovies.android.features.tabNavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cartoonapp.animemovies.android.AppTheme
import com.cartoonapp.animemovies.android.R

@Composable
fun MainBottomNavigation(
    activeTab: BottomNavTab,
    onTabClick: (BottomNavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedContentColor = AppTheme.colors.primary
    val unselectedContentColor = AppTheme.colors.onSecondary
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
        BottomNavigationItem(
            selected = activeTab == BottomNavTab.Movies,
            onClick = { onTabClick(BottomNavTab.Movies) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_movie),
                    contentDescription = "Movies",
                )
            },
            label = { Text(text = "Movies") },
            selectedContentColor = selectedContentColor,
            unselectedContentColor = unselectedContentColor
        )
        BottomNavigationItem(
            selected = activeTab == BottomNavTab.Favorites,
            onClick = { onTabClick(BottomNavTab.Favorites) },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite),
                    contentDescription = "Favorites",
                )
            },
            label = { Text(text = "Favorites") },
            selectedContentColor = selectedContentColor,
            unselectedContentColor = unselectedContentColor
        )
    }
}