package ui
import scala.swing._

class Message(lang: machine.Language, msg: String, date: String, isUser: Boolean) extends BoxPanel(Orientation.Horizontal) {
  preferredSize = peer.getPreferredSize
  minimumSize = peer.getPreferredSize

  val kiwi = new Img("assets/kiwi-pp.png", 40, 40) { background = Theme.color.MAIN }
  val textBubble = new TextBubble(lang, msg, isUser, 23)

  val d = new Label(
    "<html> long line long line long line longine long line longine long linene long line longine long linene long line longine long line long <br>  line 2 <br> line 3<br> line 4 <br> line 5<br> line last") {
    opaque = true
    font = Theme.fontLight.deriveFont(14f)
    foreground = Theme.color.TEXT
    background = Theme.color.SECONDARY
    preferredSize = peer.getPreferredSize
    minimumSize = peer.getPreferredSize
    //peer.setFocusPainted(false);
  }
  preferredSize = d.size
  if (isUser) {
    d.text = "<html> long line long line long line longine e long line long <br>  line 2 <br> line 5"
  }

  //if (!isUser)
  //  layout(new BorderPanel { layout(kiwi) = BorderPanel.Position.South }) = BorderPanel.Position.West
  //layout(new BoxPanel(Orientation.Horizontal) { contents += d; preferredSize = d.preferredSize; d.horizontalTextPosition = Alignment.Left }) = BorderPanel.Position.Center

  contents += d

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        kiwi.background = Theme.color.MAIN
        background = Theme.color.MAIN
      }
  }
}