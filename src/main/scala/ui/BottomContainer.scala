package ui

import scala.swing._
import scala.swing.event._
import java.awt.Color
import scala.io.StdIn._

object BottomContainer extends BoxPanel(Orientation.Horizontal) {
  val sendButton = new ui.RoundButton("assets/kiwi-icon.png")
  val vttButton = new ui.RoundButton("assets/kiwi-icon.png")

  listenTo(sendButton, vttButton, Theme)
  reactions += {
    case ButtonClicked(button) => {
      if (button == sendButton)
        sendMessage(MessageTextField.text)
      else if (button == vttButton)
        machine.SpeechToText.startRecognition()
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