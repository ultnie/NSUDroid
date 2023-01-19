package com.example.dndhandbook.dndapi

class SearchResponse(
    val count: Int,
    val results: List<SearchItem>
) {

    class SearchItem(
        val name: String,
        val url: String
    ) {
        override fun toString(): String {
            return name
        }
    }
}