package ui

import scala.swing._
import scala.swing.Container._
import javax.swing._
import java.awt.Color
import java.awt.Font
import scala.swing.event._

/**
 * @author Cody
 * A swing panel used as a menu to either chose the username or start as a guest
 */
class WelcomeFrame(setUsername: String => Unit) extends BorderPanel {
  val welcomeMessage: Component = new Label("Welcome the the smart avatar bot !") {
    peer.setFont(new Font("Sans Serif", Font.PLAIN, 24))
    opaque = true
    background = Theme.color.MAIN;
    foreground = Theme.color.TEXT
  }
  val instruction: Component = new Label("Please enter your name bellow") {
    foreground = Theme.color.TEXT_TERTIARY
  }
  val nameTextField: Component = new TextField {
    border = new javax.swing.border.EmptyBorder(0, 0, 0, 0);
    background = Theme.color.SECONDARY
    foreground = Theme.color.TEXT
  }
  val guestButton: Component = new Button("Or start as guest") {
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.TEXT
  }

  listenTo(guestButton, nameTextField)
  reactions += {
    case ButtonClicked(guestButton) => startWithName("Guest")
    case EditDone(nameTextField)    => startWithName(nameTextField.text)
  }

  val group: Component = new BoxPanel(Orientation.Vertical) {
    background = Theme.color.MAIN
    maximumSize = new Dimension(400, 120)
    contents += new PaddingBox(welcomeMessage, 0, 10, 0, 0)
    contents += instruction
    contents += nameTextField
    contents += guestButton
  }

  val withPadding = new PaddingBox(group, 20, 20, 50, 50) { background = Theme.color.MAIN }

  layout(withPadding) = BorderPanel.Position.Center

  /**
   * Send the username to the main UI Component
   * @param name the username chosen
   */
  def startWithName(name: String) = {
    setUsername(name)
  }

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        withPadding.background = Theme.color.MAIN
        guestButton.background = Theme.color.HIGHLIGHT
        guestButton.foreground = Theme.color.TEXT
        nameTextField.background = Theme.color.SECONDARY
        nameTextField.foreground = Theme.color.TEXT
        instruction.foreground = Theme.color.TEXT_TERTIARY
        group.background = Theme.color.MAIN
      }
  }

}