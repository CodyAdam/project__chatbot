package ui
import java.awt.Color
import java.awt
import scala.swing._
import java.io._;

object Theme extends Publisher {

  class ColorPalette(main: Color, secondary: Color, tertiary: Color, highlight: Color, text: Color, textTertiary: Color, textSecondary: Color) {
    var MAIN: Color = main
    var SECONDARY: Color = secondary
    var TERTIARY: Color = tertiary
    var HIGHLIGHT: Color = highlight

    var TEXT: Color = text
    var TEXT_TERTIARY: Color = textTertiary
    var TEXT_SECONDARY: Color = textSecondary
  }

  val darkTheme: ColorPalette = new ColorPalette(
    main = Color.decode("#0B0C0B"),
    secondary = Color.decode("#1F201F"),
    tertiary = Color.decode("#CDD8DD"),
    highlight = Color.decode("#55FF4F"),
    text = Color.WHITE,
    textTertiary = Color.GRAY,
    textSecondary = Color.decode("#0B0C0B"))

  val lightTheme: ColorPalette = new ColorPalette(
    main = Color.WHITE,
    secondary = Color.GRAY,
    tertiary = Color.BLACK,
    highlight = Color.decode("#55FF4F"),
    text = Color.BLACK,
    textTertiary = Color.GREEN,
    textSecondary = Color.GRAY)

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