package ui
import scala.swing._
import javax.swing._
import java.awt.Image
import scala.io.StdIn._


/**
 * @author Cody
 * A simpler class to create Images as a swing Component with customizable size
 * 
 * @param imgPath the path to the image
 * @param width the width of the image
 * @param height the height of the image
 */
class Img(imgPath: String, width: Int, height: Int) extends Label() {
  var imageIcon: ImageIcon = new ImageIcon(this.getClass().getResource(imgPath))
  val resizedIcon: ImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT))
  icon = resizedIcon
}