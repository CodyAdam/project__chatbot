package ui

import scala.swing._
import scala.swing.Container._
import java.awt.Color
import java.awt
import scala.swing.event._
import machine.StateManager
import machine.DataBase
/**
 * @author Cody
 * A swing panel used as a menu to either chose the username or start as a guest
 */
object WelcomeFrame extends BorderPanel {

  val bannerDark = new Img("assets/banner-dark.png", (120 * 4.15393).toInt, 120)
  val bannerLight = new Img("assets/banner-light.png", (120 * 4.15393).toInt, 120)
  var banner = new Img("assets/banner-dark.png", (120 * 4.15393).toInt, 120) {
    background = Theme.color.MAIN
  }
  val flagFR: Flag = new Flag("assets/france.png", 50, 50)
  val flagEN: Flag = new Flag("assets/united-kingdom.png", 50, 50)
  val flagAL: Flag = new Flag("assets/germany.png", 50, 50)
  val flagES: Flag = new Flag("assets/spain.png", 50, 50)
  val flagIT: Flag = new Flag("assets/italy.png", 50, 50)

  val flagsContainer = new BoxPanel(Orientation.Horizontal) {
    background = Theme.color.MAIN
    maximumSize = new Dimension(500, 70)
    contents += flagFR
    contents += flagEN
    contents += flagES
    contents += flagAL
    contents += flagIT
  }

  val group: Component = new BoxPanel(Orientation.Vertical) {
    background = Theme.color.MAIN
    maximumSize = new Dimension(500, 250)

    contents += new PaddingBox(banner, 0, 0, 0, 0)
    contents += new PaddingBox(flagsContainer, 0, 0, 0, 0)
  }

  val withPadding = new PaddingBox(group, 6, 6, 20, 20)
  layout(withPadding) = BorderPanel.Position.Center

  listenTo(flagFR.button, flagEN.button, flagAL.button, flagES.button, flagIT.button, Theme)
  reactions += {
    case ButtonClicked(button) => {
      if (button == flagFR)
        StateManager.changeLanguage(DataBase.getLanguages()(0))
      else if (button == flagEN)
        StateManager.changeLanguage(DataBase.getLanguages()(1))
      else if (button == flagES)
        StateManager.changeLanguage(DataBase.getLanguages()(2))
      else if (button == flagAL)
        StateManager.changeLanguage(DataBase.getLanguages()(3))
      else if (button == flagIT)
        StateManager.changeLanguage(DataBase.getLanguages()(4))
      UI.hasLang = true
      UI.init()
    }
    case Theme.ThemeChange =>
      {
        if (Theme.color == Theme.darkTheme) {
          banner.icon = bannerDark.icon
        } else {
          banner.icon = bannerLight.icon
        }
        banner.background = Theme.color.MAIN
        group.background = Theme.color.MAIN
        flagsContainer.background = Theme.color.MAIN
      }
  }
}