package net.lab0.treasure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.common.io.Resources
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class Game
{
  private val mapper by lazy {
    val mapper = ObjectMapper()
    mapper.registerKotlinModule()
  }

  fun goto(id: String)
  {
    val url = Resources.getResource("place/$id.json")
    val json = Resources.toString(url, StandardCharsets.UTF_8)
    pages += mapper.readValue<Page>(json)
  }

  private val pages = mutableListOf<Page>()

  val currentPage
    get() = pages.last()
}