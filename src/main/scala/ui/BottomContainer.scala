package ui

import scala.swing._
import scala.swing.event._
import java.awt.Color
import scala.io.StdIn._

object BottomContainer extends BoxPanel(Orientation.Horizontal) {
  val sendButton = new RoundButton("assets/send-dark.png","assets/send-light.png")
  val vttButton = new RoundButton("assets/mic-dark.png", "assets/mic-light.png")

  listenTo(sendButton, vttButton, Theme)
  reactions += {
    case ButtonClicked(button) => {
      if (button == sendButton)
        sendMessage(MessageTextField.text)
      else if (button == vttButton)
        machine.SpeechToText.startRecognition()
    }
    case Theme.ThemeChange => {
      background = Theme.color.MAIN
      spacer.background = Theme.color.MAIN
    }
  }

  border = new javax.swing.border.EmptyBorder(10, 10, 10, 10)
  background = Theme.color.MAIN
  preferredSize = new Dimension(0, 50)
  val spacer = new Spacer(10, 10) {
    background = Theme.color.MAIN
  }
  contents += MessageTextField
  contents += sendButton
  contents += spacer
  contents += vttButton

  def sendMessage(msg: String): Unit = {
    MessageTextField.text = ""
    UI.userSay(msg)
  }
}