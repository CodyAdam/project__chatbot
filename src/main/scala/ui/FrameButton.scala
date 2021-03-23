package ui
import scala.swing._

class FrameButton(label: String, onClick: () => Unit) extends BorderPanel {

  val button = new Button(label) {
    font = Theme.font.deriveFont(14f);
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.TEXT
    peer.setFocusPainted(false);
    border = new javax.swing.border.EmptyBorder(20, 20, 20, 20)
    listenTo(mouse.moves)
    reactions += {
      case event.MouseEntered(_,_,_) => background = Theme.color.HIGHLIGHT.brighter()
      case event.MouseExited(_,_,_) => background = Theme.color.HIGHLIGHT
      case event.ButtonClicked(_) => onClick()
    }
  }

  layout(button) = BorderPanel.Position.Center

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        button.background = Theme.color.HIGHLIGHT
        button.foreground = Theme.color.TEXT
      }
  }
}
  