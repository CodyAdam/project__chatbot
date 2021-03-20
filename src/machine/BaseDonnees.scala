package machine

import scala.io.Source

object BaseDonnees {

  private var places: List[Place] = List(
    Place("Mairie de Rennes", "La mairie de Rennes, aussi nommée hôtel de ville de Rennes, désigne à la fois le bâtiment et l'administration et les élus municipaux qui l'occupe",
      "metropole.rennes.fr", "Place de la Mairie", "35031", "Rennes", "02 23 62 10 10"),
    Place("Théâtre La Paillette", "La Paillette est une Maison des Jeunes et de la Culture. C’est un lieu de création et de pratiques artistiques. On y va ou on s’y retrouve pour pratiquer une activité, assister à des spectacles, participer à des aventures artistiques, s’assoir dans le coin lecture ou tout simplement se rencontrer.",
      "la-paillette.net", "2, Rue du Pré de Bris", "35000", "Rennes", "02 99 59 88 86"),
    Place("Théâtre National de Bretagne", "Le théâtre national de Bretagne (TNB) est une institution culturelle créé à Rennes en 1990 par la réunion du centre dramatique de l'Ouest et de la maison de la Culture de Rennes",
      "t-n-b.fr", "1, Rue Saint-Hélier", "35040", "Rennes", "02 99 31 12 31"),
    Place("Gare SNCF", "La gare de Rennes est une gare ferroviaire française de la ligne de Paris-​Montparnasse à Brest, située au sud du centre-ville de Rennes",
      "gares-sncf.com/fr/gare/frrns/rennes", "19, Place de la Gare", "35005", "Rennes", "36 35"))

  addPlaces(XMLImporter.getPlacesFromXml())

  private val international = Source.fromFile("doc/international.txt").getLines.toList

  private val alias: Map[List[String], List[String]] = AliasImporter.getAliasFromFile()

  /**
   * Ajoute des lieux à la liste actuel de lieux
   * @param la liste de Place qui va etre ajouté dans la base de donnees
   */
  def addPlaces(places: List[Place]): Unit = this.places ++= places;

  def getAlias(): Map[List[String], List[String]] = alias;
}