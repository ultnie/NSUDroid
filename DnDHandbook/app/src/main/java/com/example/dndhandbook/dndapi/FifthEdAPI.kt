package com.example.dndhandbook.dndapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  FifthEdApi {
    @GET("/api/{type}/")
    fun getSearch(@Path("type") type: String, @Query("name") name: String?) : Call<SearchResponse>

    @GET("{url}")
    fun getSpellResult(@Path(value = "url", encoded = true) url: String) : Call<SpellResultResponse>

    @GET("{url}")
    fun getMonsterResult(@Path(value = "url", encoded = true) url: String) : Call<MonsterResultResponse>

    @GET("{url}")
    fun getMIResult(@Path(value = "url", encoded = true) url: String) : Call<MIResultResponse>
}