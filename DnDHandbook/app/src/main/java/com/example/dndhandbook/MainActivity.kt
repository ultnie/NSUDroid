package com.example.dndhandbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dndhandbook.fragments.MagicItemsFragment
import com.example.dndhandbook.fragments.MonstersFragment
import com.example.dndhandbook.fragments.SpellsFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spellsFragment = SpellsFragment()
        val monsterFragment = MonstersFragment()
        val magicItemsFragment = MagicItemsFragment()
        var spellsFragmentAdded = true
        var monstersFragmentAdded = false
        var magicItemsFragmentAdded = false

        setCurrentFragment(spellsFragment)

        val navView: NavigationBarView = findViewById(R.id.nav_view)

        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_spells -> {
                    if(!spellsFragmentAdded) {
                        setCurrentFragment(spellsFragment)
                        spellsFragmentAdded = true
                        monstersFragmentAdded = false
                        magicItemsFragmentAdded = false
                    }
                    true
                }
                R.id.navigation_monsters -> {
                    if (!monstersFragmentAdded) {
                        setCurrentFragment(monsterFragment)
                        spellsFragmentAdded = false
                        monstersFragmentAdded = true
                        magicItemsFragmentAdded = false
                    }
                    true
                }
                R.id.navigation_magic_items -> {
                    if(!magicItemsFragmentAdded) {
                        setCurrentFragment(magicItemsFragment)
                        spellsFragmentAdded = false
                        monstersFragmentAdded = false
                        magicItemsFragmentAdded = true
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=supportFragmentManager.beginTransaction().apply {
        replace(R.id.nav_host_fragment_activity_main, fragment)
        commit()
    }
}