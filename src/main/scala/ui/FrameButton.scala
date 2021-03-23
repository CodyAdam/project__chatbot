package ui
import scala.swing._

class FrameButton(label: String) extends Button(label) {
  background = Theme.color.HIGHLIGHT
  foreground = Theme.color.MAIN
  border = new javax.swing.border.EmptyBorder(5, 15, 5, 15)
  
  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        background = Theme.color.HIGHLIGHT
        foreground = Theme.color.MAIN
      }
  }
}