package net.lab0.treasure.shell

import net.lab0.treasure.event.NewPageEvent
import net.lab0.treasure.structure.Game
import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.context.event.EventListener
import org.springframework.shell.jline.PromptProvider
import org.springframework.stereotype.Component


@Component
class CustomPromptProvider(val game: Game) : PromptProvider
{
  var lastLocation: String? = null

  override fun getPrompt(): AttributedString {
    return if (lastLocation != null)
    {
      AttributedString(
          "Island @$lastLocation > ",
          AttributedStyle.DEFAULT.foreground(AttributedStyle.WHITE)
      )
    }
    else
    {
      AttributedString(
          "Menu > ",
          AttributedStyle.DEFAULT.foreground(AttributedStyle.RED)
      )
    }
  }

  @EventListener
  fun handle(event: NewPageEvent)
  {
    this.lastLocation = event.location
  }
}

