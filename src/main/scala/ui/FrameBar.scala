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
  background = java.awt.Color.RED
  val height: Int = 25
  minimumSize = new Dimension(0, height)
  maximumSize = new Dimension(999999, height)
  preferredSize = maximumSize

  //Contents
  val themeButton: Component = new FrameButton("♻") {
    reactions += { case ButtonClicked(_) => { Theme.cycleTheme() } }
  }
  val minimizeButton: Component = new FrameButton("_") {
    reactions += { case ButtonClicked(_) => { UI.peer.setExtendedState(Frame.ICONIFIED) } }
  }
  val maximizeButton: Component = new FrameButton("🔳") {
    reactions += {
      case ButtonClicked(_) =>
        if (UI.peer.getExtendedState() == Frame.MAXIMIZED_BOTH) {
          UI.peer.setExtendedState(Frame.NORMAL)
        } else
          UI.peer.setExtendedState(Frame.MAXIMIZED_BOTH)
    }
  }
  val closeButton: Component = new FrameButton("✖") { reactions += { case ButtonClicked(_) => { System.exit(0); } } }
  val title: Component = new Label(UI.title)
  val draggablePanel: Component = new PaddingBox(new DraggablePanel(new Point(5, 5)) {
    maximumSize = new Dimension(99999, height)
    layout(title) = BorderPanel.Position.West
    opaque = false
    background = new Color(0, 0, 0, 0)
  }, 5, 0, 5, 0)

  val group: Component = new BoxPanel(Orientation.Horizontal) {
    contents += themeButton
    contents += minimizeButton
    contents += maximizeButton
    contents += closeButton
  }

  layout(draggablePanel) = BorderPanel.Position.Center
  layout(group) = BorderPanel.Position.East

}
