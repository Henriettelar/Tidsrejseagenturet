package Model.DB;

import Model.Customer;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepo {
    private final DBConnection db;

    public DBRepo(DBConnection db){
        this.db = db;
    }

    //Test til at db faktisk virker :|
    public void testConnection() {
        try (Connection c = db.get()) {
            DatabaseMetaData md = c.getMetaData();
            System.out.println("✅ Connection OK: " + md.getURL());
            System.out.println("    Driver: " + md.getDriverName() + " - " + md.getDriverVersion());

        } catch (Exception e) {
            System.out.println("❌ Connection ERROR: " + e.getMessage());
            System.out.println("Tip: Check URL/USER/PASS and MySQL is running.");
        }
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customerId, name, email FROM Customers";
        try (Connection c = db.get()){
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("customerId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                customers.add(new Customer(id, name, email));
            }
        } catch (Exception e) {
            System.err.println("Fejl i hentning af kunder: " + e.getMessage());
            e.printStackTrace();
        }
        return customers;
        }

        public int addCustomer(String name, String email){
            String sql = "INSERT INTO Customers (name, email) VALUES (?, ?)";
            try (Connection c = db.get()){
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, name);
                ps.setString(2, email);

                int affected = ps.executeUpdate();
                if (affected == 0) {
                    System.err.println("Insert fejlede");
                    return -1;
                }
                try (ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next()){
                        return rs.getInt(1);
                    }
                }
            } catch (Exception e) {
                System.err.println("Fejl i tilføjelse af kunde: " + e.getMessage());
                e.printStackTrace();
            }
            return -1;
        }
    }



