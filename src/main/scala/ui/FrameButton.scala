package ui
import scala.swing._

<<<<<<< HEAD
class FrameButton(label: String) extends Button(label) {
  background = Theme.color.HIGHLIGHT
  foreground = Theme.color.MAIN
  border = new javax.swing.border.EmptyBorder(5, 15, 5, 15)
  
  listenTo(this)
  reactions += {
    case event.ButtonClicked(_) =>
      {
        background = Theme.color.HIGHLIGHT
        foreground = Theme.color.MAIN
      }
  }
=======
class FrameButton(label : String) extends Button(label){
    background = Theme.color.HIGHLIGHT
    foreground = Theme.color.MAIN
    border = new javax.swing.border.EmptyBorder(5, 15, 5, 15)
>>>>>>> branch 'master' of https://gitlab.istic.univ-rennes1.fr/arallain/avatar_project.git
}