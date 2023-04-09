package com.kotlinreactiveapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinReactiveAppApplication

fun main(args: Array<String>) {
	runApplication<KotlinReactiveAppApplication>(*args)
}
