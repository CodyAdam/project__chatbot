package ui
import java.awt.Color
import java.awt
import scala.swing._
import java.io._;

object Theme extends Publisher {

  case class ColorPalette(MAIN: Color, SECONDARY: Color, TERTIARY: Color, HIGHLIGHT: Color, TEXT: Color, TEXT_TERTIARY: Color, TEXT_SECONDARY: Color)

  val darkTheme: ColorPalette = new ColorPalette(
    Color.decode("#0B0C0B"),
    Color.decode("#1F201F"),
    Color.decode("#CDD8DD"),
    Color.decode("#55FF4F"),
    Color.WHITE,
    Color.GRAY,
    Color.decode("#0B0C0B"))

  val lightTheme: ColorPalette = new ColorPalette(
    Color.decode("#F4F9F9"),
    Color.decode("#CCF2F4"),
    Color.decode("#00BFFF"),
    Color.decode("#00BFFF"),
    Color.decode("#0B0C0B"),
    Color.decode("#F4F9F9"),
    Color.decode("#F4F9F9"))

  val themeList: List[ColorPalette] = List(darkTheme, lightTheme)
  var color: ColorPalette = darkTheme

  var font: Font = null;
  var fontLight: Font = null;
  var fontBold: Font = null;
  try {
    var url: java.net.URL = getClass().getResource("assets/Roboto-Light.ttf");
    fontLight = awt.Font.createFont(awt.Font.TRUETYPE_FONT, new File(url.toURI()));
    url = getClass().getResource("assets/Roboto-Medium.ttf");
    fontBold = awt.Font.createFont(awt.Font.TRUETYPE_FONT, new File(url.toURI()));
    url = getClass().getResource("assets/Roboto-Regular.ttf");
    font = awt.Font.createFont(awt.Font.TRUETYPE_FONT, new File(url.toURI()));
  } catch {
    case _: FileNotFoundException | _: java.lang.NullPointerException => println("Font not found")
    case e: Throwable => throw e
  }

  case object ThemeChange extends event.Event;
  case object PostThemeChange extends event.Event;

  def cycleTheme(): Unit = {
    color = themeList((themeList.indexOf(color) + 1) % themeList.size)
    publish(ThemeChange)
    publish(PostThemeChange)
  }
}