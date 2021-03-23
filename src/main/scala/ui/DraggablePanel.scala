package ui
import scala.swing._
import java.awt.Frame
import scala.swing.event
import java.awt
import java.awt.MouseInfo;
import javax.swing.border._
import java.awt.Toolkit;
import scala.swing.event.ActionEvent

class DraggablePanel(offset: Point) extends BorderPanel {
  var start: Point = new Point();

  listenTo(mouse.clicks, mouse.moves)
  reactions += {
    case e: event.MousePressed => start = e.point
    case e: event.MouseDragged => {
      val current: Point = UI.peer.getLocation
      if (MouseInfo.getPointerInfo().getLocation().y < Toolkit.getDefaultToolkit().getScreenSize().getHeight - 50){
        if (UI.peer.getExtendedState() == Frame.MAXIMIZED_BOTH) {
          UI.peer.setExtendedState(Frame.NORMAL)
          UI.peer.setLocation(start.x,start.y);
          UI.peer.revalidate()
          start = new Point(0 + UI.bounds.width/2,0)
          println(bounds.width)
          println(start)
        }else if(false){
          //set Frame.MAXIMIZED_BOTH si e.peer.getLocationOnScreen.x est en haut
        }else{
          UI.peer.setLocation(e.peer.getLocationOnScreen.x - start.x - offset.x, e.peer.getLocationOnScreen.y - start.y - offset.y)
        }
      }
    }
    
    case e: event.MouseReleased => {
      println("MouseInfo.getPointerInfo().getLocation().y = " + MouseInfo.getPointerInfo().getLocation().y)
      println("Toolkit.getDefaultToolkit().getScreenSize().getHeight - 50 = " + (Toolkit.getDefaultToolkit().getScreenSize().getHeight - 50))
      
      if (MouseInfo.getPointerInfo().getLocation().y <= 0 && UI.peer.getExtendedState() == Frame.NORMAL){
        println("passe")
        UI.peer.setExtendedState(Frame.MAXIMIZED_BOTH)
      }
    }
  }
}