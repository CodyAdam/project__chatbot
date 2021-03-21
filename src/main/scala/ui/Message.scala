package ui
import scala.swing._

class Message(autor: String, msg: String, date: String, isUser: Boolean) extends BorderPanel {
  val HEIGHT: Int = 40
  maximumSize = new Dimension(9999, HEIGHT)
  preferredSize = new Dimension(1, HEIGHT)
  background = Theme.color.MAIN

  val autorLabel = new PaddingBox(new Label(autor) {
    foreground = Theme.color.TEXT_SECONDARY
    xLayoutAlignment = if (isUser) 1 else 0
  }, 0, 0, 10, 10)
  val textBubble = new TextBubble(msg, isUser)

  layout(autorLabel) = if (isUser) BorderPanel.Position.East else BorderPanel.Position.West
  layout(textBubble) = BorderPanel.Position.Center
}