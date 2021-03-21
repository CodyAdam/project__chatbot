package ui
import scala.swing._

/**
 * @author Cody
 * Class used as a Component to create space between other Components
 * @param width the desired minimum width taken by the Component
 * @param height the desired minimum height taken by the Component
 */
class Spacer(width: Int = 0, height: Int = 0) extends BorderPanel {
  maximumSize = new Dimension(99999, 99999)
  minimumSize = new Dimension(width, height)
  preferredSize = minimumSize

}