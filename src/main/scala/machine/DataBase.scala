package machine

import scala.io.Source

case class Place(
  name:        String,
  description: String,
  website:     String,
  address:     String,
  zipcode:     String,
  city:        String,
  phoneNumber: String)

object DataBase {

  private var places: List[Place] = List[Place]()
  private var alias: Map[List[String], List[String]] = Map[List[String], List[String]]()
  private var languages: List[Language] = List[Language]()

  /**
   * Initialize the data base with all the alias, places and languages
   * @note cette fonction est lancé au début du programme
   */
  def init(): Unit = {
    languages = LanguageImporter.getLanguages()
    places = XMLImporter.getPlacesFromXml()
    alias = AliasImporter.getAliasFromFile()
  }

  /**
   * search in the places database wich keys correspond to the input keyword
   * @param keyword the keyword
   * @return list of all the places found
   */
  def findByKeyword(keyword: String): List[Place] = {
    places.filter((place: Place) => {
      place.name.toLowerCase().contains(keyword.toLowerCase())
    })
  }

  /**
   * search in the places database wich keys correspond to the input keywords
   * @param keywords all the keywords
   * @return list of all the places found
   */
  def findByKeywords(keywords: Set[String]): List[Place] = {
    var result: List[Place] = List[Place]()
    keywords.foreach((keyword: String) => {
      result ++= findByKeyword(keyword)
    })
    return result
  }

  /**
   * @return all the languages
   */
  def getLanguages(): List[Language] = languages;

  /**
   * @return the aliases map
   */
  def getAlias(): Map[List[String], List[String]] = alias;
}