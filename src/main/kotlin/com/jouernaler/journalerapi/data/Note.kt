package com.jouernaler.journalerapi.data

import java.rmi.registry.LocateRegistry

data class Note (
    var id: String = "",
    var title: String,
    var message: String,
    var location: String = ""
)