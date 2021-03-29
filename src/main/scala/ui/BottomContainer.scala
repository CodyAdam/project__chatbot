package ui
import scala.swing._
import scala.swing.event._
import java.awt.Color
import scala.io.StdIn._

object BottomContainer extends BoxPanel(Orientation.Horizontal) {
  val sendButton = new Button(">") {
    preferredSize = new Dimension(44, 50)
    background = Theme.color.SECONDARY
  }
  val vttButton = new Button("startRecognition") {
    preferredSize = new Dimension(150, 50)
    background = Theme.color.SECONDARY
  }

  listenTo(sendButton, vttButton, Theme)
  reactions += {
    case ButtonClicked(button) => {
      if (button == sendButton)
        sendMessage(MessageTextField.text)
      else if (button == vttButton)
        machine.SpeechToText.startRecognition()
    }
    case Theme.ThemeChange =>
      {
        sendButton.background = Theme.color.SECONDARY
      }
  }

  border = new javax.swing.border.EmptyBorder(10, 10, 10, 10)
  background = Theme.color.MAIN
  preferredSize = new Dimension(0, 50)

  contents += MessageTextField
  contents += sendButton
  contents += vttButton

  def sendMessage(msg: String): Unit = {
    MessageTextField.text = ""
    UI.userSay(msg)
  }
}