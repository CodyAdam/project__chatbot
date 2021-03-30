package ui

import scala.swing._
import javax.swing.SwingConstants
import java.awt.geom;
import org.apache.commons.lang3.StringUtils

class TextBubble(lang: machine.Language, msg: String, isUser: Boolean, parentWidth: Int) extends Button("<html>" + msg) {

  font = Theme.fontLight.deriveFont(20f)
  opaque = false
  foreground = if (isUser) Theme.color.TEXT_SECONDARY else Theme.color.TEXT
  background = if (isUser) Theme.color.TERTIARY else Theme.color.SECONDARY
  border = new javax.swing.border.EmptyBorder(0, 40, 0, 40)
  peer.setFocusPainted(false);

  if (isUser) horizontalAlignment = (Alignment.Right)
  else horizontalAlignment = (Alignment.Left)

  val lineHeight = 26;
  var line = getLinesNumber()._1
  preferredSize = new Dimension(0, lineHeight * line);
  maximumSize = new Dimension(999999, lineHeight * line);
  minimumSize = new Dimension(0, lineHeight * line);

  listenTo(Theme)
  reactions += {
    case event.ButtonClicked(_) => {
      val tssInput = msg.replaceAll("<br/>", " ").replaceAll("<i>", "").replaceAll("</i>", "")
      machine.TextToSpeech.speak(tssInput, lang)
    }
    case Theme.ThemeChange => {
      foreground = if (isUser) Theme.color.TEXT_SECONDARY else Theme.color.TEXT
      background = if (isUser) Theme.color.TERTIARY else Theme.color.SECONDARY
    }
  }

  def getLinesNumber(): (Int, Int) = {
    val lines = "<br/>".r.split(msg)
    var lineCount = 1
    var maxWidth = 0
    for (line <- lines) {
      val textWidth = peer.getFontMetrics(font).stringWidth(line)
      if (textWidth > maxWidth)
        maxWidth = textWidth
      val lineHeight = 40;
      lineCount += math.ceil(textWidth / parentWidth.toInt).toInt + 1
    }
    return (lineCount, maxWidth)
  }

  override def paint(g: Graphics2D): Unit = {

    var line = getLinesNumber()._1
    preferredSize = new Dimension(0, lineHeight * line);
    maximumSize = new Dimension(999999, lineHeight * line);
    minimumSize = new Dimension(0, lineHeight * line);
    
    if (isUser) {
      val x = bounds.width - getLinesNumber()._2 - 60
      val y = 4
      val w = scala.math.min(getLinesNumber()._2 + 40, bounds.width)
      val h = bounds.height- 4;
      g.setPaint(background);
      g.fill(new geom.RoundRectangle2D.Float(x, y, w, h, 40, 40))
      g.fill(new geom.Rectangle2D.Float(x + w / 2, y + h / 2, w / 2, h / 2))
    } else {
      val x = 20 
      val y = 4
      val w = scala.math.min(getLinesNumber()._2 + 60, bounds.width - 20)
      val h = bounds.height -4;
      g.setPaint(background);
      g.fill(new geom.RoundRectangle2D.Float(x, y, w, h, 40, 40))
      g.fill(new geom.Rectangle2D.Float(x, y + h / 2, w / 2, h / 2))
    }
    super.paint(g)
  }
}