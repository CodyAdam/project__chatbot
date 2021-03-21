package main

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import ui.UI
//TODO implement the TTS functionnality

object Application extends SimpleSwingApplication {

  machine.DataBase.init()
  def top: MainFrame = UI
}