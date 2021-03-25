package ui

import scala.swing._
import javax.swing.SwingConstants

class TextBubble(lang: machine.Language, msg: String, isUser: Boolean) extends BorderPanel {
  val messageButton: Component = new Button(msg) {
    font = Theme.fontLight.deriveFont(20f)
    opaque = true
    foreground = Theme.color.TEXT
    background = Theme.color.MAIN
    peer.setFocusPainted(false);
  }

  val withPadding = new PaddingBox(messageButton, 5, 5, 5, 5)
  val spacer = new Spacer { background = Theme.color.MAIN }

  layout(spacer) = BorderPanel.Position.Center
  layout(withPadding) = if (isUser) BorderPanel.Position.East else BorderPanel.Position.West

  listenTo(Theme, messageButton)
  reactions += {
    case event.ButtonClicked(_) => machine.TextToSpeech.speak(msg, lang)
    case Theme.ThemeChange =>
      {
        messageButton.foreground = Theme.color.TEXT
        spacer.background = Theme.color.MAIN
        messageButton.background = Theme.color.MAIN
      }
  }
}