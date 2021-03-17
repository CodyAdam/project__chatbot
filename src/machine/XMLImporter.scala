package machine
import scala.xml.XML

case class Place (
  name: String,
  address: String,
  zipcode: String,
  phoneNumber : String
)

object XMLImporter extends App{
	val xml = XML.loadFile("doc/vAr.xml")
  
  val test :  Place = Place("dhawhudw", "342","dhawhudw", "342")
  print(test.name)
}

