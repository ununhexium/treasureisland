package net.lab0.treasure.cli

import net.lab0.treasure.Game
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent("Main menu")
class Menu(private val game: Game)
{
  @ShellMethod("Start the game")
  fun start(): String
  {
    game.goto("start")
    return game.currentPage.description
  }
}