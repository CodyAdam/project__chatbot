package ui
import java.awt.Color
import scala.swing._

object Theme extends Publisher {

  class ColorPalette(main: Color, secondary: Color, tertiary: Color, highlight: Color, text: Color, textHighlight: Color, textSecondary: Color) {
    var MAIN: Color = main
    var SECONDARY: Color = secondary
    var TERTIARY: Color = tertiary
    var HIGHLIGHT: Color = highlight

    var TEXT: Color = text
    var TEXT_HIGHLIGHT: Color = textHighlight
    var TEXT_SECONDARY: Color = textSecondary
  }

  val darkTheme: ColorPalette = new ColorPalette(
    main = Color.BLACK,
    secondary = Color.GRAY,
    tertiary = Color.WHITE,
    highlight = Color.YELLOW,
    text = Color.WHITE,
    textHighlight = Color.YELLOW,
    textSecondary = Color.GRAY)

  val lightTheme: ColorPalette = new ColorPalette(
    main = Color.WHITE,
    secondary = Color.GRAY,
    tertiary = Color.BLACK,
    highlight = Color.GREEN,
    text = Color.BLACK,
    textHighlight = Color.GREEN,
    textSecondary = Color.GRAY)

  val seaTheme: ColorPalette = new ColorPalette(
    main = new Color(242,244,247),
    secondary = new Color(229,233,240),
    tertiary = Color.decode("#276678"),
    highlight = Color.decode("#4D94FF"),
    text = Color.decode("#276678"),
    textHighlight = Color.decode("#4D94FF"),
    textSecondary = Color.decode("#313333"))

  val themeList: List[ColorPalette] = List(darkTheme, lightTheme, seaTheme)

  var color: ColorPalette = seaTheme

  case object ThemeChange extends event.Event;
  
  def cycleTheme(): Unit = {
    color = themeList((themeList.indexOf(color) + 1) % themeList.size)
    publish(ThemeChange)
  }

}