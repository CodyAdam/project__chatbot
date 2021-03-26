package machine
import scala.io.Source

/**
 * Object qui permet de formateur et d'importer le fichier alias.txt dans la mémoire de la base de données
 */
object AliasImporter {
  def getAliasFromFile(): Map[List[String], List[String]] = {
    var alias: Map[List[String], List[String]] = Map[List[String], List[String]]()
    val dataRaw: List[String] = Source.fromFile("doc/alias.txt").getLines.toList;
    //Remove comment lines and empty lines
    var data: List[String] = dataRaw.filter((line: String) => { !line.contains("#") && line != "" })

    for (line <- data) {
      val regexArrow = "( *-> *)|( *$)".r
      val splitedLine: List[String] = regexArrow.split(line).toList
      val regexComma = ";".r
      splitedLine.length match {
        case 1 => alias += regexComma.split(splitedLine(0)).toList -> regexComma.split(splitedLine(0)).toList;
        case 2 => alias += regexComma.split(splitedLine(0)).toList -> regexComma.split(splitedLine(1)).toList;
        case _ => throw new Exception("Wrong formating for the line : \"" + line + "\"", None.orNull)
      }
    }
    return alias
  }
}