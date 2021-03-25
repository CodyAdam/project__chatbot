package ui
import scala.swing._

class Message(lang: machine.Language, msg: String, date: String, isUser: Boolean) extends BorderPanel {
  val HEIGHT: Int = 50
  maximumSize = new Dimension(9999, HEIGHT)
  preferredSize = new Dimension(1, HEIGHT)

  val autorLabel = new Img("assets/kiwi-pp.png", 40, 40) { background = Theme.color.MAIN }
  val textBubble = new TextBubble(lang ,msg, isUser)

  if (!isUser)
    layout(new PaddingBox(autorLabel, 0, 0, 10, 10)) = BorderPanel.Position.West
  layout(textBubble) = BorderPanel.Position.Center

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        autorLabel.background = Theme.color.MAIN
        background = Theme.color.MAIN
      }
  }
}