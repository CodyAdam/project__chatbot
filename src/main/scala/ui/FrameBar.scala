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
object FrameBar extends BorderPanel {
  //Style
  val height: Int = 45
  minimumSize = new Dimension(0, height)
  maximumSize = new Dimension(999999, height)
  preferredSize = maximumSize

  //Contents
  val themeButton: Component = new FrameButton("assets/theme-dark.png", "assets/theme-light.png", () => Theme.cycleTheme())
  val minimizeButton: Component = new FrameButton("assets/minimize-dark.png", "assets/minimize-light.png", () => UI.peer.setExtendedState(Frame.ICONIFIED))
  val closeButton: Component = new FrameButton("assets/close-dark.png", "assets/close-light.png", () => System.exit(0))
  val maximizeButton: Component = new FrameButton("assets/maximize-dark.png", "assets/maximize-light.png", () => {
    if (UI.peer.getExtendedState() == Frame.MAXIMIZED_BOTH) {
      UI.peer.setExtendedState(Frame.NORMAL)
    } else
      UI.peer.setExtendedState(Frame.MAXIMIZED_BOTH)
  })

  val titleIcon: Component = new Img("assets/kiwi-icon.png", 30, 30) {
    border = new javax.swing.border.EmptyBorder(10, 10, 10, 10)
    background = Theme.color.MAIN;
  }
  val title: Component = new Label(UI.title) {
    font = Theme.font.deriveFont(20f)
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
  val dragOffset = new Point(title.bounds.width + 5, title.bounds.height + 5)
  val draggable = new DraggablePanel(dragOffset) {
    background = Theme.color.MAIN;
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

  background = Theme.color.MAIN;
  //border = new javax.swing.border.EmptyBorder(5, 5, 0, 5)
  layout(new PaddingBox(draggable, 5, 0, 5, 0)) = BorderPanel.Position.Center
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
