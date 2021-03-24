package ui
import scala.swing._
import javax.swing.ImageIcon
import java.awt
import scala.io.StdIn._

class Flag(imgPath: String, width: Int, height: Int) extends BorderPanel {
  val button = new Button("") {
    background = Theme.color.MAIN
    border = new javax.swing.border.EmptyBorder(0, 0, 0, 0)
    opaque = true
    peer.setFocusPainted(false);
    var imageIcon: ImageIcon = new ImageIcon(this.getClass().getResource(imgPath))
    val resizedIcon: ImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, awt.Image.SCALE_DEFAULT))

    icon = resizedIcon

    listenTo(mouse.moves, Theme)
    reactions += {
      case event.MouseEntered(_, _, _)                    => background = Theme.color.MAIN.brighter()
      case event.MouseExited(_, _, _) | Theme.ThemeChange => background = Theme.color.MAIN
    }
  }

  layout(button) = BorderPanel.Position.Center
}