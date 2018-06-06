package net.lab0.treasure.structure

import org.springframework.shell.TerminalSizeAware

data class Page(
    val description: String,
    val steps: List<Step>
) : TerminalSizeAware
{

  override fun render(terminalWidth: Int): CharSequence
  {
    val actions = steps.joinToString("\n") {
      it.actionKey + " -> " + it.title
    }

    return """
      |$description
      |
      |$actions
      |
      |
    """.trimMargin()
  }

  val noChoice: Boolean
    get() = steps.size == 1
}