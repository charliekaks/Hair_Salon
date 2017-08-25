import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest{

    //rule that cleans the tests in the DB before and after tests    
    @Rule
    public DatabaseRule database = new DatabaseRule();

    
    @Test
    public void stylist_instantiatesCorrectly_true(){
        Stylist myStylist = new Stylist("chuck", 12345);
        assertTrue(myStylist instanceof Stylist);
    }

    @Test
    public void getName_instanciatesNameCOrrectly_chuck(){
        Stylist myStylist = new Stylist("chuck", 12345);
        assertEquals("chuck", myStylist.getName());

    }

    @Test
    public void getNumber_instanciatesNumberCOrrectly_12345(){
        Stylist myStylist = new Stylist("chuck", 12345);
        assertEquals(12345, myStylist.getNumber());

    }
    @Test
    public void save_savesToTheDatabase_true(){
        Stylist myStylist = new Stylist("chuck", 12345);
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
    }
    @Test
    public void all_returnsAllInstancesOfStylist_true(){
        Stylist firstStylist = new Stylist("joan", 5678);
        firstStylist.save();
        Stylist secondStylist = new Stylist("Mark", 12567);
        secondStylist.save();
        assertTrue(Stylist.all().get(0).equals(firstStylist));
        assertTrue(Stylist.all().get(1).equals(secondStylist));
    }


}