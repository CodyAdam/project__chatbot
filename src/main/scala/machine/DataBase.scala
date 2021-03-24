package machine

import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.immutable._

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
  private var list_Words: HashMap[String, Set[Language]] = new HashMap();

  /**
   * Initialize the data base with all the alias, places and languages
   * @note cette fonction est lancé au début du programme
   */
  def init(): Unit = {
    languages = LanguageImporter.getLanguages()
    places = XMLImporter.getPlacesFromXml()
    alias = AliasImporter.getAliasFromFile()
    for(lang <- languages){
      addWordHashmap(lang.langue.toLowerCase(), lang);
      for(w <- lang.politesse){
        addWordHashmap(w.toLowerCase(), lang);
      }
      for(w <- lang.recherche){
        addWordHashmap(w.toLowerCase(), lang);
      }
      for(w <- lang.linternauteTrigger){
        addWordHashmap(w.toLowerCase(), lang);
      }
      addWordHashmap(lang.expression.agree.toLowerCase(), lang);
      addWordHashmap(lang.expression.disagree.toLowerCase(), lang);
    }
  }
  
  /**
   * Add a word in hasmap if it doesn't exist and link this word with its languages
   * @param w the word to add
   * @param lang its languages
   */
  def addWordHashmap(w : String, lang : Language) : Unit = {
    var ref : Option[Set[Language]] = list_Words.get(w);
    ref match {
      case None => list_Words.put(w, Set(lang));
      case Some(set) => list_Words(w) = set ++ Set(lang);
    }
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
   * @return all the words link to their languages
   */
  def getWordsLanguage(): HashMap[String, Set[Language]] = list_Words;
  
  /**
   * @return all the languages
   */
  def getLanguages(): List[Language] = languages;

  /**
   * @return the aliases map
   */
  def getAlias(): Map[List[String], List[String]] = alias;
}