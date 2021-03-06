import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest{
     //rule that cleans the tests in the DB before and after tests    
     @Rule
     public DatabaseRule database = new DatabaseRule();
 
     
     @Test
     public void client_instantiatesCorrectly_true(){
         Client myClient = new Client("chuck", 12345, 1);
         assertTrue(myClient instanceof Client);
     }
 
     @Test
     public void getName_instanciatesNameCOrrectly_chuck(){
         Client myClient = new Client("chuck", 12345, 1);
         assertEquals("chuck", myClient.getName());
 
     }

     @Test

     public void getStylistId_instanciatesStylistIdCorrectly_true(){
        Client myClient = new Client("chuck", 12345, 1);
        assertEquals(1, myClient.getStylistId());
     }
 
     @Test
     public void getPhoneNumber_instanciatesPhoneNumberCorrectly_12345(){
         Client myClient = new Client("chuck", 12345, 1);
         assertEquals(12345, myClient.getPhoneNumber());
 
     }
     @Test
     public void save_savesToTheDatabase_true(){
         Client myClient = new Client("chuck", 12345, 1);
         myClient.save();
         assertTrue(Client.all().get(0).equals(myClient));
     }
     @Test
     public void all_returnsAllInstancesOfClient_true(){
         Client firstClient = new Client("joan", 5678, 1);
         firstClient.save();
         Client secondClient = new Client("Mark", 12567,2);
         secondClient.save();
         assertTrue(Client.all().get(0).equals(firstClient));
         assertTrue(Client.all().get(1).equals(secondClient));
     }
     
     @Test
     public void delete_deletesClient_true() {
       Client myClient = new Client("joan", 5678, 1);
       myClient.save();
       int myClientId = myClient.getId();
       myClient.delete();
       assertEquals(null, Client.find(myClientId));
     }

     @Test
     public void update_updatesClientDescription_true() {
       Client myClient = new Client("joan", 5678, 1);
       myClient.save();
       myClient.update("Mark", 1234);
       assertEquals("Mark", Client.find(myClient.getId()).getName());
     }


 
}