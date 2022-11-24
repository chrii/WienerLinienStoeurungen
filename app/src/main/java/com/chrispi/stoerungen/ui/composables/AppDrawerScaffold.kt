package com.chrispi.stoerungen.ui.composables

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawerScaffold(
    appBarHeader: String,
    navigation: NavController,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem by remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding),
                        icon = { Icon(item, contentDescription = "an icon") },
                        label = { Text(text = item.name) },
                        selected = item == selectedItem,
                        onClick = {
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(),
                    onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Home"
                    )
                }
                Text(
                    text = appBarHeader,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            content()
        }
    }
}