package ui
import scala.swing._
import java.awt.Color
import javax.swing._
import scala.io.StdIn._
import scala.swing.event._

class BottomContainer(userSay: String => Unit) extends BoxPanel(Orientation.Horizontal) {
  val textField = new TextField{
    background = Theme.color.MAIN
    foreground = Theme.color.TEXT
  }
  val sendButton = new Button(">") {
    preferredSize = new Dimension(44, 40)
    background = Theme.color.SECONDARY
  }
  
  listenTo(sendButton, textField)
  reactions += {
    case ButtonClicked(sendButton) => sendMessage(textField.text)
    case EditDone(textField)       => sendMessage(textField.text)
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