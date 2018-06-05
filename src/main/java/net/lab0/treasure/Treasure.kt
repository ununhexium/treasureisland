package net.lab0.treasure

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Treasure

fun main(args: Array<String>)
{
  SpringApplication.run(Treasure::class.java, *args)
}