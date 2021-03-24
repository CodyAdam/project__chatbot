package ui
import scala.swing._
import java.awt.Frame
import java.awt.Image
import java.awt.MouseInfo;
import javax.swing.border._
import java.awt

/**
 * The Main UI class used as the MainFrame
 */
object UI extends MainFrame {
  title = "Kiwipedia"
  minimumSize = new Dimension(500, 300)
  preferredSize = new Dimension(640, 480)
  background = awt.Color.RED;

  peer.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  peer.setUndecorated(true); // set the borderless window
  peer.setLocationRelativeTo(null)

  background = awt.Color.BLACK

  var hasLang: Boolean = false

  init()

  /**
   * Initialise the layout content
   */
  def init(): Unit = {
    if (!hasLang) {
      contents = new ResizablePane(new BorderPanel {
        background = Theme.color.MAIN
        layout(FrameBar) = BorderPanel.Position.North
        layout(WelcomeFrame) = BorderPanel.Position.Center
      }) {
        background = awt.Color.RED;
      }
    } else {
      contents = new ResizablePane(new BorderPanel {
        layout(FrameBar) = BorderPanel.Position.North
        layout(TopContainer) = BorderPanel.Position.Center
        layout(BottomContainer) = BorderPanel.Position.South
      }) {
        background = awt.Color.RED;
      }
      avatarSay(machine.StateManager.currentLanguage.politesse(0))
    }
  }

  /**
   * Used to update messages display when a new message is received
   */
  def onNewMessage(): Unit = {
    // re-draw the ScrollPane since canvas doesn't update automatically
    TopContainer.validate()

    // set scroll bar to the bottom
    val scrollBar: ScrollBar = TopContainer.scrollPanel.verticalScrollBar
    scrollBar.value = scrollBar.maximum
  }

  /**
   * Send a message as the user
   *
   * @param msg the message to be sent
   */
  def userSay(msg: String): Unit = {
    if (!msg.equals("")) {
      TopContainer.addMessage("", msg, getCurrentTime(), true)
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
    TopContainer.addMessage("Avatar", msg, getCurrentTime(), false)
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