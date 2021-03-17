package ui
import scala.swing._
import scala.swing.event
import java.awt
import java.awt.MouseInfo;
import javax.swing.border._
import java.awt.Toolkit;

class DraggablePanel(offset: Point) extends BorderPanel {
  var start: Point = new Point();

  listenTo(mouse.clicks, mouse.moves)
  reactions += {
    case e: event.MousePressed => start = e.point
    case e: event.MouseDragged => {
      val current: Point = UI.peer.getLocation
      if (MouseInfo.getPointerInfo().getLocation().y < Toolkit.getDefaultToolkit().getScreenSize().getHeight - 50){
        UI.peer.setLocation(e.peer.getLocationOnScreen.x - start.x - offset.x, e.peer.getLocationOnScreen.y - start.y - offset.y)
      }
    }
  }
}