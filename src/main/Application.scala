package main

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import ui.UI

object Application extends SimpleSwingApplication {

  machine.DataBase.init()
  def top : MainFrame = UI 
}