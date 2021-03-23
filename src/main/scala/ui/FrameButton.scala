package ui
import scala.swing._

class FrameButton(pathDark: String, pathLight: String, onClick: () => Unit) extends BorderPanel {

  val imgDark = new Img(pathDark, 10, 10);
  val imgLight = new Img(pathLight, 10, 10);

  val button = new Button("") {
    preferredSize = new Dimension(60, 10)

    icon = if (Theme.color == Theme.darkTheme) imgDark.imageIcon else imgLight.imageIcon;
    font = Theme.font.deriveFont(14f);
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.TEXT
    peer.setFocusPainted(false);
    border = new javax.swing.border.EmptyBorder(20, 20, 20, 20)
    listenTo(mouse.moves)
    reactions += {
      case event.MouseEntered(_, _, _) => background = Theme.color.HIGHLIGHT.brighter()
      case event.MouseExited(_, _, _)  => background = Theme.color.HIGHLIGHT
      case event.ButtonClicked(_)      => onClick()
    }
  }

  layout(button) = BorderPanel.Position.Center

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        button.icon = if (Theme.color == Theme.darkTheme) imgDark.imageIcon else imgLight.imageIcon;
        button.background = Theme.color.HIGHLIGHT
        button.foreground = Theme.color.TEXT
      }
  }
}
  