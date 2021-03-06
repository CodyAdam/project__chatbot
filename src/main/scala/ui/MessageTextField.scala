package ui
import scala.swing._
import scala.swing.event._
import java.awt.Color
import javax.swing;
import java.awt;
import java.awt.geom.RoundRectangle2D;
import scala.io.StdIn._

object MessageTextField extends TextField {
  opaque = false
  font = Theme.font.deriveFont(18f);
  foreground = Theme.color.TEXT
  border = new javax.swing.border.EmptyBorder(0, 20, 0, 20)

  listenTo(keys, Theme)
  reactions += {
    case KeyPressed(component, key, _, _) => {
      if (key.equals(Key.Enter))
        sendMessage(MessageTextField.text)
    }
    case Theme.ThemeChange => {
      background = Theme.color.MAIN
      foreground = Theme.color.TEXT
    }
  }

  override def paint(g: Graphics2D): Unit = {
    g.setPaint(Theme.color.SECONDARY);
    val roundedRectangle: RoundRectangle2D = new RoundRectangle2D.Float(
      bounds.x - 10,
      bounds.y - 10,
      bounds.width - 10,
      bounds.height,
      bounds.height,
      bounds.height);
    g.fill(roundedRectangle)
    super.paint(g)
  }

  def sendMessage(msg: String): Unit = {
    text = ""
    UI.userSay(msg)
  }
}