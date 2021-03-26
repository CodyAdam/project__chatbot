package application

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import machine.DataBase
import machine.TextToSpeech
import ui.UI

object Application extends SimpleSwingApplication {
  DataBase.init()
  TextToSpeech.init()
  def top: MainFrame = UI
}