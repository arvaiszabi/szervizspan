package model;

import java.sql.*;
import java.util.ArrayList;

public class DBaseManager {

    private static Connection DBaseAccess() //Kapcsolódás az adatbázishoz.
    {
        Connection connection = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/auto_base.db");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return connection;
    }
    //Hozzáadás az adatbázishoz!
    public static void DBaseCarAdd(Gepjarmu jarmu)
    {
        Connection connection = DBaseAccess();
        PreparedStatement insert = null;
        String sql_str = "INSERT INTO Gepjarmu(Tulajdonos, Elérhetőség, Rendszám, Gyártó, Típus, Hiba, Státusz) VALUES(?,?,?,?,?,?,?)";
        try
        {
            insert = connection.prepareStatement(sql_str);
            insert.setString(1, jarmu.getNev());
            insert.setString(2, jarmu.getKontakt());
            insert.setString(3, jarmu.getRendszam());
            insert.setString(4, jarmu.getGyarto());
            insert.setString(5, jarmu.getTipus());
            insert.setString(6, jarmu.getHiba());
            insert.setString(7, jarmu.getKesz());
            insert.execute();
            //System.out.println("Adatok hozzáadva!");
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally // Finally ágak az adatbázis bezárása miatt kellenek!
        {
            try
            {
                insert.close();
                connection.close();
            } catch (SQLException | NullPointerException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
    //Törlés az adatbázisból!
    public static void DBaseDelete(String rendszam) //Adatbázisból rendszám alapján törlök, az az egyedi azonosító!
    {
        Connection connection = DBaseAccess();
        PreparedStatement delete = null;
        String sql_str = "DELETE FROM Gepjarmu WHERE Rendszám = ? ";
        try
        {
            delete = connection.prepareStatement(sql_str);
            delete.setString(1, rendszam);
            delete.execute();
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                delete.close();
                connection.close();
            } catch (SQLException | NullPointerException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
    //Keresés az adatbázisban!
    public static ArrayList<Gepjarmu> DBaseSearch(String criteria)
    {
        ArrayList<Gepjarmu> AutoLista = new ArrayList<>();
        Connection connection = DBaseAccess();
        PreparedStatement select = null;
        ResultSet Talalat = null;
        String sql_str = "SELECT * FROM Gepjarmu WHERE ? IN (Tulajdonos, Elérhetőség, Rendszám, Gyártó, Típus, Hiba)";
        if(criteria.equals("LISTALL"))
            sql_str = "SELECT * FROM Gepjarmu";
        try
        {
            select = connection.prepareStatement(sql_str);
            if(!criteria.equals("LISTALL"))
            select.setString(1, criteria);
            Talalat = select.executeQuery();
            while(Talalat.next())
            {
                AutoLista.add(new Gepjarmu(Talalat.getString("Tulajdonos"), Talalat.getString("Elérhetőség"),
                        Talalat.getString("Gyártó"), Talalat.getString("Típus"), Talalat.getString("Rendszám"),
                        Talalat.getString("Hiba"), Talalat.getString("Státusz").equals("Elkészült!")?true:false));
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                Talalat.close();
                select.close();
                connection.close();
            }catch (SQLException | NullPointerException e)
            {
                System.err.println(e.getMessage());
            }
        }
        return AutoLista;
    }
    public static void CarDBUpdate(Gepjarmu jarmu)
    {
        Connection connection = DBaseAccess();
        PreparedStatement update = null;
        String sql_str = "UPDATE Gepjarmu SET Tulajdonos = ?, Elérhetőség = ?, Gyártó = ?, Típus = ?, Hiba = ?, Státusz = ? WHERE Rendszám = ?";
        try
        {
            update = connection.prepareStatement(sql_str);
            update.setString(1, jarmu.getNev());
            update.setString(2, jarmu.getKontakt());
            update.setString(3, jarmu.getGyarto());
            update.setString(4, jarmu.getTipus());
            update.setString(5, jarmu.getHiba());
            update.setString(6, jarmu.getKesz());
            update.setString(7, jarmu.getRendszam());
            update.execute();
            //System.out.println("Adatok hozzáadva!");
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally // Finally ágak az adatbázis bezárása miatt kellenek!
        {
            try
            {
                update.close();
                connection.close();
            } catch (SQLException | NullPointerException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
}
