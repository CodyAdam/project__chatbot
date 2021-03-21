package ui
import java.awt.MouseInfo;
import scala.swing._
import java.awt
import javax.swing.border._

class ResizablePane(component: Component) extends BorderPanel {
  val thickness: Int = 5
  val rightSide = new BorderPanel {
    maximumSize = new Dimension(thickness, 9999)
    preferredSize = maximumSize
    cursor = new awt.Cursor(awt.Cursor.W_RESIZE_CURSOR)
  }
  val leftSide = new BorderPanel {
    maximumSize = new Dimension(thickness, 9999)
    preferredSize = maximumSize
    cursor = new awt.Cursor(awt.Cursor.W_RESIZE_CURSOR)
  }

  var draggingLeft: Boolean = false
  var draggingTop: Boolean = false
  var draggingRight: Boolean = false
  var draggingBottom: Boolean = false

  var start: awt.Point = new awt.Point(0, 0)
  var current: awt.Point = new awt.Point(0, 0)

  listenTo(mouse.clicks, mouse.moves)
  reactions += {
    case e: event.MouseMoved => {
//      println("passe" + e.point)
//      println(e.point.x-thickness)
      val bound: awt.Rectangle = UI.bounds
      if (e.point.x <= thickness || e.point.x >= bound.width - thickness)
        cursor = new awt.Cursor(awt.Cursor.W_RESIZE_CURSOR)
      else if (e.point.y <= thickness || e.point.y >= bound.height - thickness)
        cursor = new awt.Cursor(awt.Cursor.N_RESIZE_CURSOR)
      else cursor = new awt.Cursor(awt.Cursor.DEFAULT_CURSOR)
    }
    
    case e : event.MouseExited =>{
      cursor = new awt.Cursor(awt.Cursor.DEFAULT_CURSOR)
    }
    case e: event.MousePressed => {
      val bound: awt.Rectangle = UI.bounds
      if (e.point.x <= thickness)
        draggingLeft = true
      if (e.point.x >= bound.width - thickness)
        draggingRight = true
      if (e.point.y <= thickness)
        draggingTop = true
      if (e.point.y >= bound.height - thickness)
        draggingBottom = true
      if (draggingLeft || draggingTop || draggingRight || draggingBottom)
        start = MouseInfo.getPointerInfo().getLocation();
    }
    case e: event.MouseReleased => {
      draggingLeft = false
      draggingTop = false
      draggingRight = false
      draggingBottom = false
    }
    case e: event.MouseDragged => {
      if (draggingLeft || draggingTop || draggingRight || draggingBottom) {
        current = MouseInfo.getPointerInfo().getLocation();
        val bound: awt.Rectangle = UI.bounds
        if (draggingRight && bound.width - (start.x - current.x) > UI.minimumSize.width) {
          UI.peer.setSize((bound.width - (start.x - current.x)), bound.height);
        }else if (draggingLeft && bound.width + (start.x - current.x) > UI.minimumSize.width){
          UI.peer.setSize(bound.width + (start.x - current.x), bound.height);
          UI.peer.setLocation(current.x ,UI.peer.getLocationOnScreen.getY.toInt);
        }else if (draggingBottom && bound.height - (start.y - current.y) > UI.minimumSize.height){
          UI.peer.setSize(bound.width, bound.height - (start.y - current.y));
        }else if (draggingTop && bound.height + (start.y - current.y) > UI.minimumSize.height){
          UI.peer.setSize(bound.width, bound.height + (start.y - current.y));
          UI.peer.setLocation(UI.peer.getLocationOnScreen.getX.toInt , current.y);
        }
        start = current
      }
    }
  }

  layout(component) = BorderPanel.Position.Center
}