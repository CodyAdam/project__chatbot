package ui
import scala.swing._
import scala.swing.event._
import java.awt.Color
import scala.io.StdIn._

class BottomContainer(userSay: String => Unit) extends BoxPanel(Orientation.Horizontal) {
  val textField = new TextField {
    background = Theme.color.MAIN
    foreground = Theme.color.TEXT

  }
  val sendButton = new Button(">") {
    preferredSize = new Dimension(44, 40)
    background = Theme.color.SECONDARY
  }

  listenTo(sendButton, textField.keys, Theme)
  reactions += {
    case ButtonClicked(button) => sendMessage(textField.text)
    case KeyPressed(component, key, _, _) => {
      if (key.equals(Key.Enter))
        sendMessage(textField.text)
    }
    case Theme.ThemeChange =>
      {
        sendButton.background = Theme.color.SECONDARY
        textField.background = Theme.color.MAIN
        textField.foreground = Theme.color.TEXT
      }
  }

  border = new javax.swing.border.LineBorder(Theme.color.SECONDARY, 8, true)
  preferredSize = new Dimension(0, 40)

  contents += textField
  contents += sendButton

  def sendMessage(msg: String): Unit = {
    textField.text = ""
    userSay(msg)
  }
}