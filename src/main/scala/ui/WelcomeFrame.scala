package ui

import scala.swing._
import scala.swing.Container._
import javax.swing._
import java.awt.Color
import java.awt
import scala.swing.event._

/**
 * @author Cody
 * A swing panel used as a menu to either chose the username or start as a guest
 */
class WelcomeFrame(setUsername: String => Unit) extends BorderPanel {

  val icon: Component = new Img("assets/banner-dark.png", (120 * 4.15393).toInt, 120) { background = Theme.color.MAIN; }
  val iconWithPadding = new PaddingBox(icon, 0, 0, 0, 0, true) // center icon

  val flagFR: Component = new Button("FR") {
    font = Theme.font.deriveFont(14f)
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.TEXT
  }
  val flagEN: Component = new Button("EN") {
    font = Theme.font.deriveFont(14f)
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.TEXT
  }
  val flagAL: Component = new Button("AL") {
    font = Theme.font.deriveFont(14f)
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.TEXT
  }
  listenTo(flagFR, flagEN, flagAL)
  reactions += {
    case ButtonClicked(_) => startWithName("Guest")
  }

  val flagsContainer = new BoxPanel(Orientation.Horizontal) {
    maximumSize = new Dimension(600, 100)
    background = Theme.color.MAIN
    contents += flagFR
    contents += flagEN
    contents += flagAL
  }

  val group: Component = new BoxPanel(Orientation.Vertical) {
    background = Theme.color.MAIN
    maximumSize = new Dimension(600, 300)
    contents += iconWithPadding
    contents += flagsContainer
  }

  val withPadding = new PaddingBox(group, 20, 20, 20, 20, true) { background = Theme.color.MAIN }

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
        icon.background = Theme.color.MAIN
        withPadding.background = Theme.color.MAIN
        group.background = Theme.color.MAIN
      }
  }

}