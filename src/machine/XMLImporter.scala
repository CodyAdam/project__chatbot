package machine
import scala.xml

case class Place(
  name:        String,
  description: String,
  website:     String,
  address:     String,
  zipcode:     String,
  city:        String,
  phoneNumber: String)

/**
 * Objet qui importe les donnees des organisation de la ville de Rennes dans notre Base de donnees
 */
object XMLImporter extends App {
  val xmlData = xml.XML.loadFile("doc/vAr.xml")

  var places: List[Place] = List()
  (xmlData \\ "organization").map(
    orga => (orga \\ "address").map(address => {
      if ((address \ "city").text == "Rennes") {
        val addressString: String = if (((address \ "street") \ "number").contains(xml.Text)) ((address \ "street") \ "number").text + ", " + ((address \ "street") \ "name").text else ((address \ "street") \ "name").text
        places = places ++ List(Place(
          (orga \ "name").text,
          (orga \ "description").text,
          (orga \ "web").text,
          addressString,
          (address \ "zipcode").text,
          (address \ "city").text,
          (address \ "phone").text))
      }
    }))

  BaseDonnees.addPlaces(places);
}



