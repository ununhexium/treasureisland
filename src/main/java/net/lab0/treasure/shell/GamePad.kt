package net.lab0.treasure.shell

import net.lab0.treasure.structure.Game
import net.lab0.treasure.structure.Page
import org.springframework.context.annotation.Profile
import org.springframework.shell.Availability
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability

@ShellComponent("Main menu")
class GamePad(private val game: Game)
{

  @ShellMethod("Start the game")
  fun start(): Page
  {
    game.goto("start")
    return game.currentPage
  }

  @ShellMethod("Repeats the current page")
  fun repeat() = game.currentPage

  @ShellMethod("Go to a place")
  fun go(place: String) = game.go(place)

  @ShellMethod("Go to the only available page")
  fun next() = game.next()

  fun nextAvailability() = if (game.hasHistory && game.currentPage.noChoice)
  {
    Availability.available()
  }
  else
  {
    Availability.unavailable("there is more than one possible action")
  }

  @ShellMethodAvailability("start", "checkIntegrity")
  fun inMainMenu() = if (!game.hasHistory)
  {
    Availability.available()
  }
  else
  {
    Availability.unavailable("the game didn't start yet")
  }

  @ShellMethodAvailability("go", "repeat")
  fun inGame(): Availability = if (game.hasHistory)
  {
    Availability.available()
  }
  else
  {
    Availability.unavailable("the game didn't start yet")
  }
}
