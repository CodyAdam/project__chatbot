package ui
package ui
import scala.swing._
import scala.swing.event._
import java.awt.Color
import javax.swing;
import java.awt;
import java.awt.geom.Ellipse2D;
import scala.io.StdIn._

class RoundButton(imgPath: String) extends Button() {
  icon = new Img(imgPath, 20,20).icon
  font = Theme.fontBold.deriveFont(18f);
  preferredSize = new Dimension(30, 30)
  background = Theme.color.SECONDARY
  //opaque = false;
  foreground = Theme.color.TEXT
  peer.setFocusPainted(false);
  peer.setContentAreaFilled(false)
  border = new javax.swing.border.EmptyBorder(5, 0, 5, 0)

  listenTo(mouse.moves, mouse.clicks)
  reactions += {
    case e: event.MouseReleased => background = Theme.color.SECONDARY
    case e: event.MousePressed => background = Theme.color.SECONDARY.brighter()
    case e: event.MouseEntered => background = Theme.color.SECONDARY.brighter().brighter()
    case e: event.MouseExited  => background = Theme.color.SECONDARY
  }

  override def paint(g: Graphics2D): Unit = {

    g.setPaint(background);
    g.setStroke(new awt.BasicStroke(3.0f));
    val ellipse2D: Ellipse2D = new Ellipse2D.Float(
      0,
      0,
      30,
      30)
    g.fill(ellipse2D)
    super.paint(g)
  }
}