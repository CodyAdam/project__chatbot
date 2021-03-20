package machine

class InfoMot(val types:EnumerationType.Value, val langues:EnumerationLangue.Value) {

  
  
}

object Test extends App {
  
  val test = new InfoMot(EnumerationType.politesse, EnumerationLangue.francais)
  println(test)
  
}