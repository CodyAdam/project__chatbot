package machine

import scala.io.Source


object BaseDonnees {

  
  val international = Source.fromFile("doc/international.txt").getLines.toList
  
  
  

  val places = List(Place("dhawhudw", "342","dhawhudw", "342", "Rennes"),
    Place("dhawhudw", "342","dhawhudw", "342", "Rennes"), 
    Place("dhawhudw", "342","dhawhudw", "342", "Rennes")) 

}