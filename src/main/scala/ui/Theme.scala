package ui
import java.awt.Color
import scala.swing._

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
    highlight = Color.decode("#8653AF"),
    text = Color.WHITE,
    textTertiary = Color.GRAY,
    textSecondary = Color.decode("#0B0C0B"))

  val lightTheme: ColorPalette = new ColorPalette(
    main = Color.WHITE,
    secondary = Color.GRAY,
    tertiary = Color.BLACK,
    highlight = Color.GREEN,
    text = Color.BLACK,
    textTertiary = Color.GREEN,
    textSecondary = Color.GRAY)

  val seaTheme: ColorPalette = new ColorPalette(
    main = new Color(242,244,247),
    secondary = new Color(229,233,240),
    tertiary = Color.decode("#276678"),
    highlight = Color.decode("#4D94FF"),
    text = Color.decode("#276678"),
    textTertiary = Color.decode("#4D94FF"),
    textSecondary = Color.decode("#313333"))

  val themeList: List[ColorPalette] = List(darkTheme, lightTheme, seaTheme)

  var color: ColorPalette = darkTheme

  case object ThemeChange extends event.Event;
  
  def cycleTheme(): Unit = {
    color = themeList((themeList.indexOf(color) + 1) % themeList.size)
    publish(ThemeChange)
  }

}