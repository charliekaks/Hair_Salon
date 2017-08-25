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
        String sql = "SELECT id, stylists Where id=:id";
        Stylist mystylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
        return mystylist;
    }
    }
}