package ui

import scala.swing._
import javax.swing.SwingConstants

class TextBubble(msg: String, isUser: Boolean) extends BorderPanel {
  background = Theme.color.MAIN
  val message: Component = new Label(msg) {
    opaque = true
    foreground = if (isUser) Theme.color.MAIN else Theme.color.TEXT_SECONDARY
    background = if (isUser) Theme.color.HIGHLIGHT else Theme.color.SECONDARY
  }

  val withBorder: Component = new BorderPanel {
    border = new javax.swing.border.LineBorder(message.background, 5, true)
    layout(message) = BorderPanel.Position.Center
  }

  val withPadding: Component = new BorderPanel {
    border = new javax.swing.border.EmptyBorder(5, 5, 5, 5)
    layout(withBorder) = BorderPanel.Position.Center
  }

  layout(new Spacer) = BorderPanel.Position.Center
  layout(withPadding) = if (isUser) BorderPanel.Position.East else BorderPanel.Position.West

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        message.foreground = if (isUser) Theme.color.MAIN else Theme.color.TEXT_SECONDARY
        message.background = if (isUser) Theme.color.HIGHLIGHT else Theme.color.SECONDARY
        background = Theme.color.MAIN
      }
  }
}