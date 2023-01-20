package com.example.dndhandbook.dndapi

class SearchResponse(
    val count: Int,
    val results: List<SearchItem>
): java.io.Serializable {

    class SearchItem(
        val name: String,
        val url: String
    ): java.io.Serializable {
        override fun toString(): String {
            return name
        }
    }
}