package ui
import scala.swing._
import scala.swing.event._
import java.awt.Color
import scala.io.StdIn._

object BottomContainer extends BoxPanel(Orientation.Horizontal) {
  val textField = new TextField {
    background = Theme.color.MAIN
    foreground = Theme.color.TEXT
  }
  val sendButton = new Button(">") {
    preferredSize = new Dimension(44, 40)
    background = Theme.color.SECONDARY
  }
  val vttButton = new Button("startRecognition") {
    preferredSize = new Dimension(150, 40)
    background = Theme.color.SECONDARY
  }

  listenTo(sendButton, vttButton, textField.keys, Theme)
  reactions += {
    case ButtonClicked(button) => {
      if (button == sendButton)
        sendMessage(textField.text)
      else if (button == vttButton)
        machine.SpeechToText.startRecognition()
    }
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
  contents += vttButton

  def sendMessage(msg: String): Unit = {
    textField.text = ""
    UI.userSay(msg)
  }
}