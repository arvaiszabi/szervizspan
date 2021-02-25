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
    public static void DBaseCarAdd(String Nev, String Con, String Rendszam, String Gyarto, String Tipus, String Hiba)
    {
        Connection connection = DBaseAccess();
        PreparedStatement instruction = null;
        String sql_str = "INSERT INTO Gepjarmu(Tulajdonos, Elérhetőség, Rendszám, Gyártó, Típus, Hiba, Státusz) VALUES(?,?,?,?,?,?,?)";
        try
        {
            instruction = connection.prepareStatement(sql_str);
            instruction.setString(1, Nev);
            instruction.setString(2, Con);
            instruction.setString(3, Rendszam);
            instruction.setString(4, Gyarto);
            instruction.setString(5, Tipus);
            instruction.setString(6, Hiba);
            instruction.setString(7, "false");
            instruction.execute();
            //System.out.println("Adatok hozzáadva!");
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally // Finally ágak az adatbázis bezárása miatt kellenek!
        {
            try
            {
                instruction.close();
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
        PreparedStatement instruction = null;
        String sql_str = "DELETE FROM Gepjarmu WHERE Rendszam = ? ";
        try
        {
            instruction = connection.prepareStatement(sql_str);
            instruction.setString(1, rendszam);
            instruction.execute();
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                instruction.close();
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
        PreparedStatement instruction = null;
        ResultSet Talalat = null;
        String sql_str = "SELECT * FROM Gepjarmu WHERE Gyarto = ? ";
        try
        {
            instruction = connection.prepareStatement(sql_str);
            instruction.setString(1, criteria);
            Talalat = instruction.executeQuery();
            System.out.println(Talalat.getString(1));
            while(Talalat.next())
            {
                System.out.println("OK1");
                AutoLista.add(new Gepjarmu(Talalat.getString("Tulajdonos"), Talalat.getString("Telefon"),
                        Talalat.getString("Gyarto"), Talalat.getString("Tipus"),
                        Talalat.getString("Rendszam"), Talalat.getString("Hiba"),
                        Talalat.getString("Jav_stat")));
            }
            System.out.println("OK2");
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                Talalat.close();
                instruction.close();
                connection.close();
            }catch (SQLException | NullPointerException e)
            {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(AutoLista.iterator().next().toString() + "Függvényben");
        return AutoLista;
    }
}
