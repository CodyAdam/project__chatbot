package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseDefault {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TESTER LA FONCTIONNALITÉ : F1 du tp avec les phrases de bases avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - Hotel de ville
   * - Mairie
   * - Mairie de Rennes
   * - TNB
   * - Theatre de Bretagne
   * - Theatre National de Bretagne
   * - Gare
   * - Gare SNCF 
   * - Theatre la Paillette
   * - La Paillette
   */
  
  //Pour la Mairie
  
  @Test
  def ask{
	  assertEquals(
			  MachineImpl.ask("Où se trouve l'Hotel de ville ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
  @Test
  def ask2{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Mairie ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
    
  @Test
  def ask3{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Mairie de Rennes ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
    //Pour le TNB
  
  @Test
  def ask10{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le TNB?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  
  @Test
  def ask11{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtrè National de Brétàgnè ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
    
  @Test
  def ask12{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtre de Bretagne ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  // Pour la Gare
  
  @Test
  def ask19{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Gare?"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }
  
  @Test
  def ask20{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Gare SNCF ?"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }


  
    // Pour le Théâtre la Paillette
  
  @Test
  def ask25{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtre la Paillette ?"),
			  List("L'adresse de Théâtre La Paillette est : 2, Rue du Pré de Bris")  
			  )
  }

  
}