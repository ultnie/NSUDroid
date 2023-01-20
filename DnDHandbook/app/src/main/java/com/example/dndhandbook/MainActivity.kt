package com.example.dndhandbook

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.dndhandbook.fragments.MagicItemsFragment
import com.example.dndhandbook.fragments.MonstersFragment
import com.example.dndhandbook.fragments.SpellsFragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    var spellsFragment = SpellsFragment()
    var monsterFragment = MonstersFragment()
    var magicItemsFragment = MagicItemsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: NavigationBarView = findViewById(R.id.nav_view)
        val sideNavView: NavigationView = findViewById(R.id.side_nav_view)

        if (savedInstanceState == null) {
            setCurrentFragment(spellsFragment)
        }

        navView.setOnItemSelectedListener {
            supportFragmentManager.popBackStack()
            itemSelect(it)
        }

        sideNavView.setNavigationItemSelectedListener {
            supportFragmentManager.popBackStack()
            itemSelect(it)
        }
    }


    fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.nav_host_fragment_activity_main, fragment)
        commit()
    }

    fun itemSelect (it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.navigation_spells -> {
                if (spellsFragment.lifecycle.currentState != Lifecycle.State.RESUMED) {
                    setCurrentFragment(spellsFragment)
                }
                return true
            }
            R.id.navigation_monsters -> {
                if (monsterFragment.lifecycle.currentState != Lifecycle.State.RESUMED) {
                    setCurrentFragment(monsterFragment)
                }
                return true
            }
            R.id.navigation_magic_items -> {
                if (magicItemsFragment.lifecycle.currentState != Lifecycle.State.RESUMED) {
                    setCurrentFragment(magicItemsFragment)
                }
                return true
            }
            else -> return false
        }
    }
}