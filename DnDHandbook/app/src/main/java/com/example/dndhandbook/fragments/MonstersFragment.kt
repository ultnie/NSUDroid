package com.example.dndhandbook.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.dndhandbook.dndapi.RestAPI
import com.example.dndhandbook.dndapi.SearchResponse
import com.example.dndhandbook.R
import com.example.dndhandbook.dndapi.MonsterResultResponse
import kotlin.collections.ArrayList


class MonstersFragment : Fragment() {

    val handler = Handler(Looper.getMainLooper())
    val restAPI = RestAPI()
    var searchText = String()
    lateinit var resultFragment: ResultFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_monsters, container, false)
        val searchView = view.findViewById<SearchView>(R.id.monstersSV)
        val listView = view.findViewById<ListView>(R.id.monstersLV)
        val manager = parentFragmentManager

        var monstersList = ArrayList<SearchResponse.SearchItem>()

        val listAdapter = ArrayAdapter<SearchResponse.SearchItem>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            monstersList
        )

        listView.adapter = listAdapter

        listView.setOnItemClickListener{parent, view, position, id ->
            val mBundle = Bundle()
            val result = ArrayList<MonsterResultResponse>()
            resultFragment = ResultFragment()
            val ft = manager.beginTransaction()
            restAPI.getMonster(monstersList[position].url, result, mBundle, ft, resultFragment, container)
        }

        val runner = Runnable { restAPI.getSearch("monsters", searchText, listAdapter) }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) : Boolean{
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                handler.removeCallbacks(runner)
                if (newText != "") {
                    searchText = newText
                    handler.postDelayed(runner, 250)
                }
                return true
            }
        })

        return view
    }
}