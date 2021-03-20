package machine
import scala.io.Source

/**
 * Objet qui converti un fichier text contenant des expression des langue et formate le tout dans des objets adapté
 * 
 * pour chaque langue créer un objet Language (définition dans le fichier stateManager.scala)
 */
object LanguageImporter {
  
  private val dataRaw: List[String] = Source.fromFile("doc/alias.txt").getLines.toList;
  
  
  
  def getLanguagesFromFile() : List[Language] =  ??? //TODO 
  
}