package main

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import ui.UI

object Application extends SimpleSwingApplication {

  machine.BaseDonnees.init()
  def top : MainFrame = UI 
}