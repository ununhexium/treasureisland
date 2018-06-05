package net.lab0.treasure.cli

import net.lab0.treasure.structure.Game
import net.lab0.treasure.structure.Page
import org.springframework.shell.Availability
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent("Main menu")
class Menu(private val game: Game)
{
  fun startAvailability() = if (!game.hasHistory)
  {
    Availability.available()
  }
  else
  {
    Availability.unavailable("the game already started")
  }

  @ShellMethod("Start the game")
  fun start(): Page
  {
    game.goto("start")
    return game.currentPage
  }

  @ShellMethod("Repeats the current page")
  fun repeat() = game.currentPage

  fun doAvailability() = if (game.hasHistory)
  {
    Availability.available()
  }
  else
  {
    Availability.unavailable("the game didn't start yet")
  }

  @ShellMethod("Go to a place")
  fun go(place: String) = game.go(place)

  @ShellMethod("Go to the only available page")
  fun next() = game.next()

  fun nextAvailability() = if (game.currentPage.noChoice)
  {
    Availability.available()
  }
  else
  {
    Availability.unavailable("there is more than one possible action")
  }
}
