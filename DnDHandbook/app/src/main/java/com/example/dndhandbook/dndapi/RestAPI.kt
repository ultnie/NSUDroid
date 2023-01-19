package com.example.dndhandbook.dndapi

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentTransaction
import com.example.dndhandbook.fragments.ResultFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestAPI {
    private val fifthEdApi: FifthEdApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.dnd5eapi.co")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        fifthEdApi = retrofit.create(FifthEdApi::class.java)
    }

    fun getSearch(
        type: String,
        name: String?,
        listAdapter: ArrayAdapter<SearchResponse.SearchItem>
    ) {
        val call = fifthEdApi.getSearch(type, name)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    listAdapter.clear()
                    response.body()?.let {
                        for (res in it.results) {
                            if (", +1, +2, or +3" !in res.name) {
                                listAdapter.add(res)
                            }
                        }
                    }
                }
                else {
                    Log.e("Response", "Response is not successful")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("Call failure", t.message.toString())
            }
        })
    }

    fun getSpell(
        url: String,
        result: ArrayList<SpellResultResponse>,
        mBundle: Bundle,
        ft: FragmentTransaction,
        resultFragment: ResultFragment,
        container: ViewGroup?
    ) {
        val call = fifthEdApi.getSpellResult(url)
        call.enqueue(object : Callback<SpellResultResponse> {
            override fun onResponse(
                call: Call<SpellResultResponse>,
                response: Response<SpellResultResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { result.add(it) }
                    mBundle.putSerializable("res", result)
                    resultFragment.arguments = mBundle
                    ft.setReorderingAllowed(false)
                    ft.replace(container!!.id, resultFragment)
                    ft.addToBackStack("SpellRes")
                    ft.commit()
                } else {
                    Log.e("Response", "Response is not successful")
                }
            }

            override fun onFailure(call: Call<SpellResultResponse>, t: Throwable) {
                Log.e("Call failure", t.message.toString())
            }
        })
    }

    fun getMonster(
        url: String,
        result: ArrayList<MonsterResultResponse>,
        mBundle: Bundle,
        ft: FragmentTransaction,
        resultFragment: ResultFragment,
        container: ViewGroup?
    ) {
        val call = fifthEdApi.getMonsterResult(url)
        call.enqueue(object : Callback<MonsterResultResponse> {
            override fun onResponse(
                call: Call<MonsterResultResponse>,
                response: Response<MonsterResultResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { result.add(it) }
                    mBundle.putSerializable("res", result)
                    resultFragment.arguments = mBundle
                    ft.setReorderingAllowed(false)
                    ft.replace(container!!.id, resultFragment)
                    ft.addToBackStack("MonRes")
                    ft.commit()
                } else {
                    Log.e("Response", "Response is not successful")
                }
            }

            override fun onFailure(call: Call<MonsterResultResponse>, t: Throwable) {
                Log.e("Call failure", t.message.toString())
            }
        })
    }

    fun getMI(
        url: String,
        result: ArrayList<MIResultResponse>,
        mBundle: Bundle,
        ft: FragmentTransaction,
        resultFragment: ResultFragment,
        container: ViewGroup?
    ) {
        val call = fifthEdApi.getMIResult(url)
        call.enqueue(object : Callback<MIResultResponse> {
            override fun onResponse(
                call: Call<MIResultResponse>,
                response: Response<MIResultResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { result.add(it) }
                    mBundle.putSerializable("res", result)
                    resultFragment.arguments = mBundle
                    ft.setReorderingAllowed(false)
                    ft.replace(container!!.id, resultFragment)
                    ft.addToBackStack("MIRes")
                    ft.commit()
                } else {
                    Log.e("Response", "Response is not successful")
                }
            }

            override fun onFailure(call: Call<MIResultResponse>, t: Throwable) {
                Log.e("Call failure", t.message.toString())
            }
        })
    }
}