package main

import scala.swing.SimpleSwingApplication
import scala.swing.MainFrame
import ui.UI

object Application extends SimpleSwingApplication {

  def top : MainFrame = UI 
}