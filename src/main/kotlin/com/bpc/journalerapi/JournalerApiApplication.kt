package com.bpc.journalerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// TODO: solve issue of building project by matching the name of the module in settings.gradle
// TODO: Other source of problems: jvm versions, sdk versions, jdk versions, etc.
@SpringBootApplication
class
JournalerApiApplication

fun main(args: Array<String>) {
	runApplication<JournalerApiApplication>(*args)
}
