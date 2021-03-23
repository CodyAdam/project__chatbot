package ui
import scala.swing._

class FrameButton(label: String, onClick: () => Unit) extends BorderPanel {

  val button = new Button(label) {
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.TEXT
    peer.setFocusPainted(false);
    border = new javax.swing.border.EmptyBorder(20, 20, 20, 20)
    reactions += {
      case event.ButtonClicked(_) => onClick()
    }
  }
  val withPadding = new PaddingBox(button, 0,0,0,0)

  layout(withPadding) = BorderPanel.Position.Center

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        button.background = Theme.color.HIGHLIGHT
        button.foreground = Theme.color.TEXT
      }
  }
}
  