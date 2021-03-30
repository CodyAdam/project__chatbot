package ui

import scala.swing._
import scala.swing.event._
import java.awt.Color
import javax.swing.ImageIcon
import javax.swing;
import java.awt;
import java.awt.geom.Ellipse2D;
import scala.io.StdIn._

class RoundButton(imgPathDark: String, imgPathLight: String) extends Button() {
  val imageIconLight: ImageIcon = new ImageIcon(this.getClass().getResource(imgPathLight))
  val imageIconDark: ImageIcon = new ImageIcon(this.getClass().getResource(imgPathDark))
  icon = if (Theme.color == Theme.darkTheme) imageIconDark else imageIconLight;
  font = Theme.fontBold.deriveFont(18f);
  preferredSize = new Dimension(30, 30)
  background = Theme.color.SECONDARY
  //opaque = false;
  foreground = Theme.color.TEXT
  peer.setFocusPainted(false);
  peer.setContentAreaFilled(false)
  border = new javax.swing.border.EmptyBorder(8, 10, 7, 10)

  listenTo(mouse.moves, mouse.clicks, Theme)
  reactions += {
    case e: event.MouseReleased => background = Theme.color.SECONDARY
    case e: event.MousePressed  => background = Theme.color.SECONDARY.brighter()
    case e: event.MouseEntered  => background = Theme.color.SECONDARY.brighter().brighter()
    case e: event.MouseExited   => background = Theme.color.SECONDARY
    case Theme.ThemeChange      => background = Theme.color.SECONDARY; icon = if (Theme.color == Theme.darkTheme) imageIconDark else imageIconLight;
  }

  override def paint(g: Graphics2D): Unit = {

    g.setPaint(background);
    g.setStroke(new awt.BasicStroke(3.0f));
    val ellipse2D: Ellipse2D = new Ellipse2D.Float(
      0, 0,
      30, 30)
    g.fill(ellipse2D)
    super.paint(g)
  }
}