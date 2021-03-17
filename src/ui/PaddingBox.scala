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

  contents += new Spacer(0, paddingTop) { if (show) background = Color.red }
  contents += new BoxPanel(Orientation.Horizontal) {
    contents += new Spacer(paddingLeft, 2) { maximumSize = new Dimension(99999, component.maximumSize.height); if (show) background = Color.blue }
    contents += component
    contents += new Spacer(paddingRight, 2) { maximumSize = new Dimension(99999, component.maximumSize.height); if (show) background = Color.green }
  }
  contents += new Spacer(0, paddingBottom) { if (show) background = Color.yellow }
}