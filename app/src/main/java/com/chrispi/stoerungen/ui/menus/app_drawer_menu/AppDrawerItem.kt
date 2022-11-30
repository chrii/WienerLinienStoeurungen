package com.chrispi.stoerungen.ui.menus.app_drawer_menu

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.chrispi.stoerungen.models.MenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawerItem(
    item: MenuItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding),
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(id = item.iconDescription)
            )
        },
        label = { Text(text = stringResource(item.label)) },
        selected = selected,
        onClick = onClick
    )
}