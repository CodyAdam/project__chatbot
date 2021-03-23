package ui
import scala.swing._
import java.awt.Frame
import java.awt.Image
import scala.io.StdIn._
import java.awt.MouseInfo;
import java.awt
import javax.swing.border._
/**
 * The Main UI class used as the MainFrame
 */
object UI extends MainFrame {

  title = "Smart Nagivation Avatar"
  minimumSize = new Dimension(500, 300)
  preferredSize = new Dimension(640, 480)
  background = Theme.color.MAIN
  peer.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  peer.setUndecorated(true); // set the borderless window
  peer.setLocationRelativeTo(null)

  var username: String = ""
  val frameBar = new FrameBar

  val top = new TopContainer
  val bottom = new BottomContainer(userSay)
  init()

  /**
   * Initialise the layout content
   */
  def init(): Unit = {
    if (username.equals("")) {
      contents = new ResizablePane(new BorderPanel {
        layout(frameBar) = BorderPanel.Position.North
        layout(new WelcomeFrame(setUsername)) = BorderPanel.Position.Center
      })
    } else {
      contents = new ResizablePane(new BorderPanel {
        layout(frameBar) = BorderPanel.Position.North
        layout(top) = BorderPanel.Position.Center
        layout(bottom) = BorderPanel.Position.South
      })
      avatarSay("Ask me anything")
    }
  }

  /**
   * set the username and update window
   * @param name the username to be used
   */
  def setUsername(name: String): Unit = {
    init()
    username = name
  }

  /**
   * Used to update messages display when a new message is received
   */
  def onNewMessage(): Unit = {
    // re-draw the ScrollPane since canvas doesn't update automatically
    top.validate()

    // set scroll bar to the bottom
    val scrollBar: ScrollBar = top.scrollPanel.verticalScrollBar
    scrollBar.value = scrollBar.maximum
  }

  /**
   * Send a message as the user
   *
   * @param msg the message to be sent
   */
  def userSay(msg: String): Unit = {
    if (!msg.equals("")) {
      top.addMessage(username, msg, getCurrentTime(), true)
      onNewMessage()
      for (respond: String <- machine.MachineImpl.ask(msg))
        avatarSay(respond)
    }
  }

  /**
   * Send a message as the avatar
   *
   * @param msg the message to be sent
   */
  def avatarSay(msg: String): Unit = {
    top.addMessage("Avatar", msg, getCurrentTime(), false)
    onNewMessage()
  }

  /**
   * @return the timestamp when the function is called formated as "Le dd/MM/yyyy à HH:mm:ss"
   */
  def getCurrentTime(): String = {
    val formatter: java.text.SimpleDateFormat = new java.text.SimpleDateFormat("'Le' dd/MM/yyyy 'à' HH:mm:ss")
    val date: java.util.Date = new java.util.Date(System.currentTimeMillis());
    formatter.format(date);
  }
}