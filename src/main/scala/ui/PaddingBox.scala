package ui

import scala.swing._
import javax.swing._
import scala.io.StdIn._
import java.awt.Color

/**
 * @author Cody
 * A Container used to wrap a Component with padding (spaces around)
 *
 * @param component the Component to add padding to
 * @param paddingTop the padding amound on the top side (in pixel)
 * @param paddingBottom the padding amound on the bottom side (in pixel)
 * @param paddingLeft the padding amound on the left side (in pixel)
 * @param paddingRight the padding amound on the right side (in pixel)
 */
class PaddingBox(component: Component, paddingTop: Int, paddingBottom: Int, paddingLeft: Int, paddingRight: Int, show: Boolean = false) extends BoxPanel(Orientation.Vertical) {
  component.preferredSize = component.maximumSize
  component.minimumSize = new Dimension(0, 0)

  val top = new Spacer(0, paddingTop) {
    background = if (show) Color.red else component.background
  }
  val left = new Spacer(paddingLeft, 2) {
    maximumSize = new Dimension(99999, component.maximumSize.height);
    background = if (show) Color.red else component.background
  }
  val right = new Spacer(paddingRight, 2) {
    maximumSize = new Dimension(99999, component.maximumSize.height);
    background = if (show) Color.red else component.background
  }
  val bot = new Spacer(0, paddingBottom) {
    background = if (show) Color.red else component.background
  }
  
  val middleGroup = new BoxPanel(Orientation.Horizontal) {
    contents += left
    contents += component
    contents += right
  }

  contents += top
  contents += middleGroup
  contents += bot

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        top.background = if (show) Color.red else component.background
        left.background = if (show) Color.red else component.background
        right.background = if (show) Color.red else component.background
        bot.background = if (show) Color.red else component.background
      }
  }
}