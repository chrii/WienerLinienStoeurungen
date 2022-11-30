package com.chrispi.stoerungen.ui.menus.app_drawer_menu

import android.os.Build
import androidx.annotation.RequiresApi
import com.chrispi.stoerungen.models.MenuItem

class AppDrawerMenu(items: List<MenuItem>) {
    private val mutableList: MutableList<MenuItem> = items.toMutableList()

    val menu: List<MenuItem> = mutableList.toList()

    fun addItem(item: MenuItem): Boolean = mutableList.add(item)

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeByRoute(route: String): Boolean = mutableList.removeIf { it.route == route }
}