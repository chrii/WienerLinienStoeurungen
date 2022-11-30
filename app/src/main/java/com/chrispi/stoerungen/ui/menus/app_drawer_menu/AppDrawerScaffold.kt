package com.chrispi.stoerungen.ui.menus.app_drawer_menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawerScaffold(
    appBarHeader: String,
    drawerMenu: AppDrawerMenu,
    navigation: NavController,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedItem by remember { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                drawerMenu.menu.forEachIndexed { index, item ->
                    AppDrawerItem(
                        item = item,
                        selected = index == selectedItem,
                    ) {
                        scope.launch {
                            drawerState.close()
                            navigation.navigate(item.route)
                        }
                    }
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