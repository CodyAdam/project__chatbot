package ui
import scala.swing._
import scala.swing.event._
import javax.swing._
import scala.io.StdIn._
import scala.swing.event.ButtonClicked
import java.awt.Frame

/**
 * @author Cody
 *
 * A Container class used to make a borderless window with a custom Frame bar.
 * The bar can be used to drag the window or manage the window using the top buttons (ex : Close, minimize, etc.)
 */
class FrameBar extends BorderPanel {
  //Style
  val height: Int = 40
  minimumSize = new Dimension(0, height)
  maximumSize = new Dimension(999999, height)
  preferredSize = maximumSize

  //Contents
  val themeButton: Component = new FrameButton("♻", () => Theme.cycleTheme())
  val minimizeButton: Component = new FrameButton("_", () => UI.peer.setExtendedState(Frame.ICONIFIED))
  val closeButton: Component = new FrameButton("✖", () => System.exit(0))
  val maximizeButton: Component = new FrameButton("🔳", () => {
    if (UI.peer.getExtendedState() == Frame.MAXIMIZED_BOTH) {
      UI.peer.setExtendedState(Frame.NORMAL)
    } else
      UI.peer.setExtendedState(Frame.MAXIMIZED_BOTH)
  })

  val titleIcon: Component = new Img("assets/kiwi-icon.png", 30, 30){ 
    border = new javax.swing.border.EmptyBorder(0, 8, 0, 8) 
    background = Theme.color.MAIN;
  }
  val title: Component = new Label(UI.title) {
    font = Theme.font.deriveFont(16f)
    opaque = true;
    foreground = Theme.color.TEXT;
    background = Theme.color.MAIN;
  }
  val titleWithIcon = new BoxPanel(Orientation.Horizontal) {
    background = Theme.color.MAIN;
    contents += titleIcon
    contents += title
  }
  val spacer = new Spacer() { background = Theme.color.MAIN }
  val dragOffset = new Point(title.bounds.width, title.bounds.height)
  val draggable = new DraggablePanel(dragOffset) {
    maximumSize = new Dimension(99999, height)
    layout(spacer) = BorderPanel.Position.Center
    layout(titleWithIcon) = BorderPanel.Position.West
    opaque = true
  }
  val group: Component = new BoxPanel(Orientation.Horizontal) {
    contents += themeButton
    contents += minimizeButton
    contents += maximizeButton
    contents += closeButton
  }

  layout(draggable) = BorderPanel.Position.Center
  layout(group) = BorderPanel.Position.East

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        titleIcon.background = Theme.color.MAIN;
        title.foreground = Theme.color.TEXT;
        title.background = Theme.color.MAIN;
        titleWithIcon.background = Theme.color.MAIN;
        spacer.background = Theme.color.MAIN
        draggable.background = Theme.color.MAIN
      }
  }

}
