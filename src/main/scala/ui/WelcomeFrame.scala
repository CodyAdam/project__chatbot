package ui

import scala.swing._
import scala.swing.Container._
import javax.swing._
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

  val bannerDark: Component = new Img("assets/banner-dark.png", (120 * 4.15393).toInt, 120) { background = Theme.color.MAIN; }
  val bannerLight: Component = new Img("assets/banner-light.png", (120 * 4.15393).toInt, 120) { background = Theme.color.MAIN; }

  val flagFR: Component = new Flag("assets/france.png", "FR", 50, 30)
  val flagEN: Component = new Flag("assets/england.png", "EN", 50, 30)
  val flagAL: Component = new Flag("assets/allemagne.png", "AL", 50, 30)
  val flagES: Component = new Flag("assets/espagne.png", "ES", 50, 30)
  val flagIT: Component = new Flag("assets/italie.png", "IT", 50, 30)
  listenTo(flagFR, flagEN, flagAL, flagES, flagIT)
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
  }

  val flagsContainer = new BoxPanel(Orientation.Horizontal) {
    background = Theme.color.MAIN
    contents += flagFR
    contents += flagEN
    contents += flagES
    contents += flagAL
    contents += flagIT
  }

  var banner: Component = if (Theme.color == Theme.darkTheme) bannerDark else bannerLight;

  val group: Component = new BoxPanel(Orientation.Vertical) {
    background = Theme.color.MAIN
    maximumSize = new Dimension(600, 300)
    contents += new PaddingBox(banner, 0, 0, 0, 0)
    contents += new PaddingBox(flagsContainer, 0, 0, 0, 0)
  }

  val withPadding = new PaddingBox(group, 20, 20, 20, 20)

  layout(withPadding) = BorderPanel.Position.Center

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        banner = if (Theme.color == Theme.darkTheme) bannerDark else bannerLight;
        flagsContainer.background = Theme.color.MAIN
        bannerLight.background = Theme.color.MAIN
        bannerDark.background = Theme.color.MAIN
        withPadding.background = Theme.color.MAIN
        group.background = Theme.color.MAIN
      }
  }

}