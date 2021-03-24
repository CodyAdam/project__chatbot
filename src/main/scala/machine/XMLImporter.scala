package machine
import scala.xml

/**
 * Objet qui importe les donnees des organisation de la ville de Rennes dans notre Base de donnees
 */
object XMLImporter {

  /**
   * @return the list of all the places at Rennes from the xml
   */
  def getPlacesFromXml(): List[Place] = {
    val xmlData = xml.XML.loadFile("doc/vAr.xml")
    var places: List[Place] = List()
    (xmlData \\ "organization").map(
      orga => (orga \\ "address").map(address => {
        if ((address \ "city").text == "Rennes") {
          val addressString: String = if (((address \ "street") \ "number").exists(p => p.text != "")) ((address \ "street") \ "number").text + ", " + ((address \ "street") \ "name").text else ((address \ "street") \ "name").text
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
    return places
  }
}



