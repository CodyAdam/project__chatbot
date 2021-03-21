package ui
import scala.swing._
import javax.swing._
import scala.io.StdIn._

class TopContainer extends BoxPanel(Orientation.Vertical) {
  background = Theme.color.MAIN
  border = new javax.swing.border.EmptyBorder(3, 3, 3, 3)
  def addMessage(autor: String, msg: String, date: String, isUser: Boolean) {
    contents += new Message(autor, msg, date, isUser)
  }
}

