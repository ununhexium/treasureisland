package net.lab0.treasure.shell

import net.lab0.treasure.structure.Game
import org.springframework.context.annotation.Profile
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
@Profile("dev")
class DevPad(val game: Game)
{
  @ShellMethod("Checks the integrity of the game")
  fun checkIntegrity(): String
  {
    val count = game.followAllPaths()
    return "Found $count pages."
  }
}
