package ui
import scala.swing._
import scala.swing.event
import javax.swing._
import scala.io.StdIn._
import java.awt
import java.awt.MouseInfo
import java.awt.Frame

object TopContainer extends BorderPanel {
  val messageContainer = new BoxPanel(Orientation.Vertical) {
    background = Theme.color.MAIN
  }

  val scrollPanel = new ScrollPane(messageContainer) {
    background = Theme.color.MAIN;
    border = new javax.swing.border.EmptyBorder(0, 0, 0, 0)
  }

  val withPadding = new PaddingBox(scrollPanel, 0, 10, 5, 5);
  layout(withPadding) = BorderPanel.Position.Center;

  listenTo(Theme)
  reactions += {
    case Theme.ThemeChange =>
      {
        scrollPanel.background = Theme.color.MAIN
        messageContainer.background = Theme.color.MAIN
      }
  }

  def addMessage(lang: machine.Language, msg: String, date: String, isUser: Boolean) {
    messageContainer.contents += new Message(lang, msg, date, isUser)
  }
}


  

