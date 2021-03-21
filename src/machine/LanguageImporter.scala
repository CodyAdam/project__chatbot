package machine
import scala.io.Source

/**
 * Objet qui converti un fichier text contenant des expression des langue et formate le tout dans des objets adapté
 *
 * pour chaque langue créer un objet Language (définition dans le fichier stateManager.scala)
 */
object LanguageImporter {

  /**
   * @return languages in this order as list : français, anglais, espagnol, allemand, italien
   */
  def getLanguages(): List[Language] = {
      val francais = new Language(
    "Français",
    List("bonjour", "salut", "bonsoir"),
    List("recherche", "cherche", "ou", "est", "donc", "trouve", "trouver"),
    List("restaurant", "creperie", "pizzeria"),
    new Expression(
      "oui",
      "non",
      "L'adresse de XXX est",
      "Je ne comprends pas votre demande",
      "Parlez-vous français?",
      "D'accord, quelle est votre demande?",
      "J'ai XXX réponses possibles",
      "Quel est votre choix?"))
  val anglais = new Language(// TODO replace with correct language
    "Français",
    List("bonjour", "salut", "bonsoir"),
    List("recherche", "cherche", "ou", "est", "donc", "trouve", "trouver"),
    List("restaurant", "creperie", "pizzeria"),
    new Expression(
      "oui",
      "non",
      "L'adresse de XXX est",
      "Je ne comprends pas votre demande",
      "Parlez-vous français?",
      "D'accord, quelle est votre demande?",
      "J'ai XXX réponses possibles",
      "Quel est votre choix?"))
  val espagnol = new Language(// TODO replace with correct language
    "Français",
    List("bonjour", "salut", "bonsoir"),
    List("recherche", "cherche", "ou", "est", "donc", "trouve", "trouver"),
    List("restaurant", "creperie", "pizzeria"),
    new Expression(
      "oui",
      "non",
      "L'adresse de XXX est",
      "Je ne comprends pas votre demande",
      "Parlez-vous français?",
      "D'accord, quelle est votre demande?",
      "J'ai XXX réponses possibles",
      "Quel est votre choix?"))
  val allemand = new Language(// TODO replace with correct language
    "Français",
    List("bonjour", "salut", "bonsoir"),
    List("recherche", "cherche", "ou", "est", "donc", "trouve", "trouver"),
    List("restaurant", "creperie", "pizzeria"),
    new Expression(
      "oui",
      "non",
      "L'adresse de XXX est",
      "Je ne comprends pas votre demande",
      "Parlez-vous français?",
      "D'accord, quelle est votre demande?",
      "J'ai XXX réponses possibles",
      "Quel est votre choix?"))
  val italien = new Language( // TODO replace with correct language
    "Français",
    List("bonjour", "salut", "bonsoir"),
    List("recherche", "cherche", "ou", "est", "donc", "trouve", "trouver"),
    List("restaurant", "creperie", "pizzeria"),
    new Expression(
      "oui",
      "non",
      "L'adresse de XXX est",
      "Je ne comprends pas votre demande",
      "Parlez-vous français?",
      "D'accord, quelle est votre demande?",
      "J'ai XXX réponses possibles",
      "Quel est votre choix?"))
    List(francais, anglais, espagnol, allemand, italien) 
  }
}