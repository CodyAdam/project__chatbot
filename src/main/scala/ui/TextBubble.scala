package ui

import scala.swing._
import javax.swing.SwingConstants

class TextBubble(lang: machine.Language, msg: String, isUser: Boolean, parentWidth: Int) extends Button("<html>" + msg) {

  
  font = Theme.fontLight.deriveFont(20f)
  opaque = true
  foreground = Theme.color.TEXT
  background = Theme.color.SECONDARY
  border = new javax.swing.border.EmptyBorder(0, 0, 0, 0)
  if (isUser) horizontalAlignment = (Alignment.Right)
  else horizontalAlignment = (Alignment.Left)

  val maxWidth = (parentWidth * 1).toInt
  val textWidth = peer.getFontMetrics(font).stringWidth(msg)
  val lineHeight = peer.getFontMetrics(font).getHeight;
  var line = 1
  if (maxWidth != 0)
    line = math.ceil(textWidth / maxWidth).toInt + 1

  //maximumSize = new Dimension(maxWidth, 999999)
  preferredSize = new Dimension(0, lineHeight * line);

}