package com.bpc.journalerapi.data

import java.util.Date

data class TodoLaterThanRequest(var date: Date) {
    constructor() : this(Date()
    )
}

