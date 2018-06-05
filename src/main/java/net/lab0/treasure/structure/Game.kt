package net.lab0.treasure.structure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.common.io.Resources
import net.lab0.treasure.exception.BadAction
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
    history += mapper.readValue<Page>(json)
  }

  fun go(action: String): Page
  {
    val step = currentPage.steps.firstOrNull { it.actionKey == action }
        ?: throw BadAction("$action is not possible. You can try: \n" + listPossibleActions())

    goto(step.goto)
    return currentPage
  }

  private fun listPossibleActions() =
      currentPage.steps.joinToString { it.actionKey + " -> " + it.title }

  fun next(): Page
  {
    if (currentPage.noChoice)
    {
      goto(currentPage.steps.first().goto)
    }
    else
    {
      throw BadAction("You have to choose the next step: \n" + listPossibleActions())
    }

    return currentPage
  }

  private val history = mutableListOf<Page>()

  val currentPage
    get() = history.last()

  val hasHistory
    get() = history.isNotEmpty()
}