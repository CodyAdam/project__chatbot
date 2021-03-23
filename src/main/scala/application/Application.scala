package application

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import machine.DataBase
import ui.UI
import machine.TextToSpeech

object Application extends SimpleSwingApplication {
  DataBase.init()
  
  def top: MainFrame = UI
}