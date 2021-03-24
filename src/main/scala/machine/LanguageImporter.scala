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
      "fr-FR",
      List("Blague", "Plaisanterie"),
      List("Bonjour", "Salut", "Bonsoir"),
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
    val anglais = new Language(
      "English",
      "en-US",
      List("Joke"),
      List("Hi", "Hello", "Morning", "Evening", "Afternoon", "Hey"),
      List("seek", "seeking", "search", "searching", "look", "looking", "where", "find"),
      List("restaurant", "creperie", "pizzeria"),
      new Expression(
        "yes",
        "no",
        "The address of XXX is",
        "I do not understand",
        "Do you speak english?",
        "OK, what is your query?",
        "I found XXX answers",
        "What is your choice?"))
    val espagnol = new Language(
      "Español",
      "es-ES",	
      List("broma", "chiste", "chascarrillo", "	chanza"),
      List("Hola", "Buenos", "Dias"),
      List("donde", "esta", "busco", "buscando"),
      List("restaurante", "creperie", "pizzeria"),
      new Expression(
        "si",
        "no",
        "La dirección de XXX es",
        "No comprendo",
        "Hablas español?",
        "Está bien, cuál es tu petición?",
        "Tengo XXX opciones",
        "Cuál es su elección?"))
    val allemand = new Language(
      "Deustch",
      "de-DE",
      List("Streich", "Witz", "Scherz"),
      List("Hallo", "Guten", "Morgen", "Tag", "Abend"),
      List("wo", "ist", "such", "suchen"),
      List("restaurant", "creperie", "pizzeria"),
      new Expression(
        "ja",
        "nein",
        "Die adresse von XXX ist",
        "Ich verstehe nicht",
        "Sprechen Sie Deutsch?",
        "Okay, was ist Ihr Wunsch?",
        "Ich habe XXX Antworten",
        "Was ist Ihre Wahl?"))
    val italien = new Language(
      "Italiano",
      "it-IT",
      List("barzelletta", "storiella", "battuta", "	burlarsi"),
      List("Buongiorno","Ciao","Salve","Buon","Pomeriggio","Buonasera","Incantato"), //TODO à changer
      List("dove","trova","cerco","cercando"),//TODO à changer
      List("ristorante", "creperie", "pizzeria"),
      new Expression(
        "si",
        "no",
        "Indirizzo di XXX è",
        "No capisco",
        "Parli italiano?",
        "Va bene, qual è la tua richiesta?",
        "Ho XXX risposte",
        "Qual è la vostra scelta?"))
    List(francais, anglais, espagnol, allemand, italien)
  }
}