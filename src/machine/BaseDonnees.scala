package machine

import scala.io.Source


object BaseDonnees {
  
  val international = Source.fromFile("doc/international.txt").getLines.toList
  
  
  
}