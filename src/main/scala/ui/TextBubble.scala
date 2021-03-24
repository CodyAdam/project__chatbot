package ui

import scala.swing._
import javax.swing.SwingConstants

class TextBubble(msg: String, isUser: Boolean) extends BorderPanel {
  val message: Component = new Label(msg) {
    opaque = true
    foreground = Theme.color.TEXT
    background = Theme.color.MAIN
  }

  val withPadding = new PaddingBox(message, 5, 5, 5, 5)
  val spacer = new Spacer { background = Theme.color.MAIN }
  
  layout(spacer) = BorderPanel.Position.Center
  layout(withPadding) = if (isUser) BorderPanel.Position.East else BorderPanel.Position.West

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        message.foreground = Theme.color.TEXT
        spacer.background = Theme.color.MAIN
        message.background = Theme.color.MAIN
      }
  }
}