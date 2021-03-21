package ui
import scala.swing._
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
  var draggingLeft: Boolean = false
  var draggingTop: Boolean = false
  var draggingRight: Boolean = false
  var draggingBottom: Boolean = false

  var start: awt.Point = new awt.Point(0, 0)
  var current: awt.Point = new awt.Point(0, 0)
  val thickness = 5;
  val topScroll = new ScrollPane(top) {
    border = new javax.swing.border.EmptyBorder(0, 0, 0, 0)
    verticalScrollBar.unitIncrement = 10
    verticalScrollBar.blockIncrement = 30
    listenTo(mouse.clicks, mouse.moves)
    reactions += {
      case e: event.MouseMoved => {
  //      println("passe" + e.point)
  //      println(e.point.x-thickness)
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
          val bound: awt.Rectangle = UI.bounds
          if (draggingRight && bound.width - (start.x - current.x) > UI.minimumSize.width) {
            UI.peer.setSize((bound.width - (start.x - current.x)), bound.height);
          }else if (draggingLeft && bound.width + (start.x - current.x) > UI.minimumSize.width){
            UI.peer.setSize(bound.width + (start.x - current.x), bound.height);
            UI.peer.setLocation(current.x ,UI.peer.getLocationOnScreen.getY.toInt);
          }else if (draggingBottom && bound.height - (start.y - current.y) > UI.minimumSize.height){
            UI.peer.setSize(bound.width, bound.height - (start.y - current.y));
          }else if (draggingTop && bound.height + (start.y - current.y) > UI.minimumSize.height){
            UI.peer.setSize(bound.width, bound.height + (start.y - current.y));
            UI.peer.setLocation(UI.peer.getLocationOnScreen.getX.toInt , current.y);
          }
          start = current
        }
      } 
    }
  }
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
        layout(topScroll) = BorderPanel.Position.Center
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
    topScroll.validate()

    // set scroll bar to the bottom
    val scrollBar: ScrollBar = topScroll.verticalScrollBar
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