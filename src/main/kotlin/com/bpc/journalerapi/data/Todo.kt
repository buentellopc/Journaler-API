package com.bpc.journalerapi.data


data class Todo (
    var id: String = "",
    var title: String,
    var message: String,
    var schedule: Long
)