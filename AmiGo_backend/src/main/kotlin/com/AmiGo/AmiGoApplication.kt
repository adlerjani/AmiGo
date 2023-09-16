package com.AmiGo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmiGoApplication

fun main(args: Array<String>) {
	runApplication<AmiGoApplication>(*args)
}
