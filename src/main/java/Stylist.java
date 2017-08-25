import org.sql2o.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stylist{
    private String stylistName;
    private int phoneNumber;
    private int id;


    //method to overide the equals method
    @Override
    public boolean equals(Object otherStylist){
        if (!(otherStylist instanceof Stylist)) {
            return false;
        }else{
            Stylist newStylist = (Stylist) otherStylist;
            return this.getName().equals(newStylist.getName())&&
            this.getNumber()==newStylist.getNumber();
        }

    }

    public Stylist(String name, int number){
        this.stylistName = name;
        this.phoneNumber = number;
    }

    public String getName(){
        return stylistName;
    }

    public int getNumber(){
        return phoneNumber;
    }

    public int getId(){
        return id;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO stylists (stylistName, phoneNumber) VALUES (:stylistName, :phoneNumber)";
            this.id = (int) con.createQuery(sql, true)
            .addParameter( "stylistName", this.stylistName)
            .addParameter("phoneNumber", this.phoneNumber)
            .executeUpdate()
            .getKey();
        }
    }

    public static List<Stylist>all(){
        String sql = "SELECT * FROM stylists";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

    public static Stylist find(int id){
        try(Connection con = DB.sql2o.open()){
        String sql = "SELECT * FROM stylists WHERE id=:id";
        Stylist mystylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
        return mystylist;
    }
    }

    public void update(String stylistName, int phone){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE clients SET stylistName= :stylistName, phoneNumber=:phoneNumber WHERE id=:id";
            con.createQuery(sql)
            .addParameter( "stylistName", stylistName)
            .addParameter("phonNumber", phoneNumber)
            .addParameter("id",id)
            .executeUpdate();
        }

    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM stylists WHERE id=:id";
            con.createQuery(sql)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

    public List<Client> getClients(){
        try(Connection con = DB.sql2o.open()){
            String sql="SELECT * FROM clients WHERE stylistid=:id";
            return con.createQuery(sql)
            .addParameter("id", this.id)
            .executeAndFetch(Client.class);
        }
    }
}