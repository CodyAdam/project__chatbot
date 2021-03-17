package machine
import scala.xml.XML

case class Place (
  name: String,
  address: String,
  zipcode: String,
  city: String,
  phoneNumber : String
)

object XMLImporter extends App{
	val xml = XML.loadFile("doc/vAr.xml")
  
  val test :  Place = Place("Gare", "Rue de la Gare","35577", "Cesson-Sévigné", "06450098")
  print(test.city)
}



