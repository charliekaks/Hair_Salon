import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client{
    private String clientName;
    private int phoneNumber;
    private int id;
    private int stylistId;

    public Client(String name, int number, int stylistId){
        this.clientName= name;
        this.phoneNumber = number;
        this.stylistId = stylistId;
    }

    @Override

    public boolean equals(Object otherClient){
        if (!(otherClient instanceof Client)) {
            return false;
        }else{
            Client newClient = (Client) otherClient;
            return this.getName().equals(newClient.getName())&&
            this.getId() == newClient.getId()&&
            this.getStylistId() == newClient.getStylistId()&&
            this.getPhoneNumber() == newClient.getPhoneNumber();

        }
    }

    public String getName(){
        return clientName;
    }

    public int getPhoneNumber(){
        return phoneNumber;
    }

    public int getId(){
        return id;
    }

    public int getStylistId(){
        return stylistId;
    }

    public static List<Client> all(){
        String sql = "SELECT * FROM clients";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }
    
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO clients(clientName, phoneNumber, stylistId) VALUES(:clientName,:phoneNumber,:stylistId)";
            this.id = (int) con.createQuery(sql, true)
            .addParameter( "clientName", this.clientName)
            .addParameter("phoneNumber", this.phoneNumber)
            .addParameter("stylistId", this.stylistId)
            .executeUpdate()
            .getKey();
        }
    }

    public static Client find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM clients WHERE id=:id";
            Client client = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Client.class);
            return client;

        }

    }

    public void update(String clientName, int clientNumber){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE clients SET clientName= :clientName, phoneNumber=:phoneNumber WHERE id=:id";
            con.createQuery(sql)
            .addParameter( "clientName", clientName)
            .addParameter("phoneNumber", phoneNumber)
            .addParameter("id",id)
            .executeUpdate();
        }

    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM clients WHERE id=:id";
            con.createQuery(sql)
            .addParameter("id", id)
            .executeUpdate();
        }
    }

}