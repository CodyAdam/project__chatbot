package ui
import scala.swing._
import javax.swing._
import java.awt
import scala.io.StdIn._

class Flag(imgPath : String , width: Int, height: Int)  extends Button(){
  var imageIcon: ImageIcon = new ImageIcon(this.getClass().getResource(imgPath))
  border = new javax.swing.border.EmptyBorder(0, 0, 0, 0)
  val resizedIcon: ImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, awt.Image.SCALE_DEFAULT))
  icon = resizedIcon
  
  
}