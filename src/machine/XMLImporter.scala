package machine
import scala.xml.XML

case class Place (
  name: String,
  address: String,
  zipcode: String,
  phoneNumber : String
)


object DataBase {
  val places = List(Place("dhawhudw", "342","dhawhudw", "342"),
    Place("dhawhudw", "342","dhawhudw", "342"), 
    Place("dhawhudw", "342","dhawhudw", "342"))
    
  
}


object XMLImporter extends App{
	val xml = XML.loadFile("doc/vAr.xml")
  val a = (xml \\ "request" \\ "item" \ "condition" \ "@temp") text
  val test :  Place = Place("dhawhudw", "342","dhawhudw", "342")
  print(a)
}

