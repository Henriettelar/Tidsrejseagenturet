package Model.DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class DBRepo {
    private final DBConnection db;

    public DBRepo(DBConnection db){
        this.db = db;
    }

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

}
