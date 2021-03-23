package ui
import scala.swing._
import scala.swing.event
import javax.swing._
import scala.io.StdIn._
import java.awt
import java.awt.MouseInfo
import java.awt.Frame

class TopContainer extends BorderPanel {
  
  val top =  new BoxPanel(Orientation.Vertical) {
    background = Theme.color.MAIN
    border = new javax.swing.border.EmptyBorder(3, 3, 3, 3)
  }
  
  var draggingLeft: Boolean = false
  var draggingTop: Boolean = false
  var draggingRight: Boolean = false
  var draggingBottom: Boolean = false
  
  var start: awt.Point = new awt.Point(0, 0)
  var current: awt.Point = new awt.Point(0, 0)
  val thickness = 5;
  
  val scrollPanel = new ScrollPane(top) {
    border = new javax.swing.border.EmptyBorder(0, 0, 0, 0)
    verticalScrollBar.unitIncrement = 10
    verticalScrollBar.blockIncrement = 30
    listenTo(mouse.clicks, mouse.moves)
    reactions += {
      case e: event.MouseMoved => {
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
          if (UI.peer.getExtendedState() == Frame.MAXIMIZED_BOTH) {
            UI.peer.setExtendedState(Frame.NORMAL)
          }  
          val bound: awt.Rectangle = UI.bounds
          if (draggingRight && bound.width - (start.x - current.x) > UI.minimumSize.width) {
            UI.peer.setSize((bound.width - (start.x - current.x)), bound.height);
            start = current
          }else if (draggingLeft && bound.width + (start.x - current.x) > UI.minimumSize.width){
            UI.peer.setSize(bound.width + (start.x - current.x), bound.height);
            UI.peer.setLocation(current.x ,UI.peer.getLocationOnScreen.getY.toInt);
            start = current
          }else if (draggingBottom && bound.height - (start.y - current.y) > UI.minimumSize.height){
            UI.peer.setSize(bound.width, bound.height - (start.y - current.y));
            start = current
          }else if (draggingTop && bound.height + (start.y - current.y) > UI.minimumSize.height){
            UI.peer.setSize(bound.width, bound.height + (start.y - current.y));
            UI.peer.setLocation(UI.peer.getLocationOnScreen.getX.toInt , current.y);
            start = current
          }
        }
      } 
    }
  }
  layout(scrollPanel) = BorderPanel.Position.Center;
  
  def addMessage(autor: String, msg: String, date: String, isUser: Boolean) {
    top.contents += new Message(autor, msg, date, isUser)
  }
}


  

