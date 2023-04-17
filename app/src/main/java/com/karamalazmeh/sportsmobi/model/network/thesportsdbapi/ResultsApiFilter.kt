package com.karamalazmeh.sportsmobi.model.network.thesportsdbapi

enum class ResultsApiFilter(val startDate: String, val endDate: String) {
        SHOW_WEEK(
            "2022-00-00",
            "2022-00-10"
        ),

        SHOW_TODAY(
            "2022-00-00",
            "2022-00-10"
        ),

        SHOW_ALL(
            "0000-00-00",
            "9999-99-99"
        )
}