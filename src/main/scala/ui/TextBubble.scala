package ui

import scala.swing._
import javax.swing.SwingConstants
import java.awt.geom.RoundRectangle2D;

class TextBubble(lang: machine.Language, msg: String, isUser: Boolean, parentWidth: Int) extends Button("<html>" + msg) {

  font = Theme.fontLight.deriveFont(20f)
  opaque = false
  foreground = Theme.color.TEXT
  background = Theme.color.SECONDARY
  border = new javax.swing.border.EmptyBorder(10, 10, 10, 10)
  peer.setFocusPainted(false);
  
  if (isUser) horizontalAlignment = (Alignment.Right)
  else horizontalAlignment = (Alignment.Left)

  val maxWidth = (parentWidth * 1).toInt
  val textWidth = peer.getFontMetrics(font).stringWidth(msg)
  val lineHeight = peer.getFontMetrics(font).getHeight;
  var line = 1
  if (maxWidth != 0)
    line = math.ceil(textWidth / maxWidth).toInt + 1

  preferredSize = new Dimension(0, lineHeight * line);

  listenTo(Theme)
  reactions += {
    case event.ButtonClicked(_) => {
      val tssInput = msg.replaceAll("<br/>", " ").replaceAll("<i>", "").replaceAll("</i>", "")
      machine.TextToSpeech.speak(tssInput, lang)
    }
    case Theme.ThemeChange => {
      foreground = Theme.color.TEXT
      background = Theme.color.SECONDARY
    }
  }

  override def paint(g: Graphics2D): Unit = {
    g.setPaint(Theme.color.SECONDARY);
    val roundedRectangle: RoundRectangle2D = new RoundRectangle2D.Float(
      bounds.x,
      bounds.y,
      peer.getFontMetrics(font).stringWidth(msg) + 20,
      bounds.height,
      bounds.height,
      bounds.height);
    g.fill(roundedRectangle)
    super.paint(g)
  }
}