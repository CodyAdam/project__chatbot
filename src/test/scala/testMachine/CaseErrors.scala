package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseErrors {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : F2 du tp avec erreurs d'orthographes avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - Accents
   * - Mots de même longueur mais change de 1 lettre
   * - Mots avec 1 lettre en moins
   * - Mots avec 1 lettre en plus
   */

// -----------------------Partie Zoé en trop dans les test (peut servir ici)--------------------------------------//
  
  // Mairie
  
  // Avec une erreur de frappe acceptée
  
  @Test
  def ask4{
	  assertEquals(
			  MachineImpl.ask("Où se trove l'Hotl de vile ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
  @Test
  def ask5{
	  assertEquals(
			  MachineImpl.ask("Ou se trouve la Mairié ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
    
  @Test
  def ask6{
	  assertEquals(
			  MachineImpl.ask("Ou se trouve la Marie de Renens ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
  // Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask7{
	  assertEquals(
			  MachineImpl.ask("Où se trouve l'Hot de vile ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask8{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Maie ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
    
  @Test
  def ask9{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Mair,ie de Rennes ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  // TNB
    
  // Avec une erreur de frappe acceptée
  
  @Test
  def ask13{
	  assertEquals(
			  MachineImpl.ask("Où se trove le tn ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  @Test
  def ask14{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtre National Bretagne?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
    
  @Test
  def ask15{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâte Bretane ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  // Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask16{
	  assertEquals(
			  MachineImpl.ask("Où se trove le ttnnb ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask17{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le thaetre national de Bretagne ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
    
  @Test
  def ask18{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Theatre de Bertagne ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  // Gare
  
  // Avec une erreur de frappe acceptée
  
  @Test
  def ask21{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Garre SNCF"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }
  
  @Test
  def ask22{
	  assertEquals(
			  MachineImpl.ask("Ou se trouve la Gares ?"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }
  
  // Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask23{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Ga,re SNFC?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask24{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Gaer ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  // Théâtre la Paillette
  
  // Avec une erreur de frappe acceptée
  
  @Test
  def ask26{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le théatre la pailette ?"),
			  List("L'adresse de Théâtre La Paillette est : 2, Rue du Pré de Bris")  
			  )
  }

  
  // Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask27{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Paillette ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask28{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le theatre la Palliette ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
}