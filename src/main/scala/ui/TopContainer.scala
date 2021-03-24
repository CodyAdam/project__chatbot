package ui
import scala.swing._
import scala.swing.event
import javax.swing._
import scala.io.StdIn._
import java.awt
import java.awt.MouseInfo
import java.awt.Frame

class TopContainer extends BorderPanel {
  val content = new BoxPanel(Orientation.Vertical) {
    background = Theme.color.MAIN
  }

  val scrollPanel = new ScrollPane(content)
  val withPadding = new PaddingBox(scrollPanel, 3, 3, 3, 3);
  layout(withPadding) = BorderPanel.Position.Center;

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        content.background = Theme.color.MAIN
      }
  }
  
  def addMessage(autor: String, msg: String, date: String, isUser: Boolean) {
    content.contents += new Message(autor, msg, date, isUser)
  }
}


  

