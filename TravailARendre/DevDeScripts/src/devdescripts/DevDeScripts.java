/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devdescripts;

/**
 *
 * @author zineb
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;

public class DevDeScripts {

    public static void main(String[] args) {
        //crée un driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//faire une connection
        String url = "jdbc:mysql://localhost/devscripts"; // Remplacer "db" par le nom de votre base
        String user = "root";
        String password = "";
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      /*try{
         Statement st = cn.createStatement();
         String createTableSQL = "CREATE TABLE IF NOT EXISTS DevData ("
         + "Developpeurs VARCHAR(32), "
         + "Jour CHAR(11), "
         + "NbScripts INTEGER)";
         st.executeUpdate(createTableSQL);
         System.out.println("Table DevData créée avec succès !");
         } catch (SQLException e) {
         e.printStackTrace();
         }
        try {
         Statement st = cn.createStatement();
         String insertDataSQL = "INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES "
         + "('ALAMI', 'Lundi', 1),"
         + "('WAFI', 'Lundi', 2),"
         + "('SALAMI', 'Mardi', 9),"
         + "('SAFI', 'Mardi', 2),"
         + "('ALAMI', 'Mardi', 2),"
         + "('SEBIHI', 'Mercredi', 2),"
         + "('WAFI', 'Jeudi', 3),"
         + "('ALAOUI', 'Vendredi', 9),"
         + "('WAFI', 'Vendredi', 3),"
         + "('SEBIHI', 'Vendredi', 4)";
         st.executeUpdate(insertDataSQL);
         System.out.println("Données insérées avec succès !");
         } catch (SQLException e) {
         e.printStackTrace();
         } */
        //exercice 2 :
        //a
        System.out.println("la personne ayant réalisé le nombre maximum de script en une journée");
        try {
            Statement st = cn.createStatement();
            String maxScriptsSQL = "SELECT Developpeurs, Jour, MAX(NbScripts) FROM DevData GROUP BY Jour";
            ResultSet rs = st.executeQuery(maxScriptsSQL);
            while (rs.next()) {
                System.out.println(rs.getString("Developpeurs") + " a réalisé le maximum de scripts le " + rs.getString("Jour"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //b
        System.out.println("la liste des personnes triée dans l’ordre décroissant selon leur nombre de scripts");
        try {
            Statement st = cn.createStatement();
            String orderScriptsSQL = "SELECT Developpeurs, SUM(NbScripts) AS TotalScripts FROM DevData "
                    + "GROUP BY Developpeurs ORDER BY TotalScripts DESC";
            ResultSet rs = st.executeQuery(orderScriptsSQL);
            while (rs.next()) {
                System.out.println(rs.getString("Developpeurs") + " a réalisé un total de " + rs.getInt("TotalScripts") + " scripts.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Calculer et afficher le nombre total de scripts réalisés en une semaine");
        try {
            Statement st = cn.createStatement();
            String totalScriptsSQL = "SELECT SUM(NbScripts) AS TotalSemaine FROM DevData";
            ResultSet rs = st.executeQuery(totalScriptsSQL);
            if (rs.next()) {
                System.out.println("Le nombre total de scripts réalisés en une semaine est : " + rs.getInt("TotalSemaine"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Calculer et afficher le nombre total de scripts réalisés en une semaine");
        try {
            Statement st = cn.createStatement();
            String totalScriptsSQL = "SELECT SUM(NbScripts) AS TotalSemaine FROM DevData";
            ResultSet rs = st.executeQuery(totalScriptsSQL);
            if (rs.next()) {
                System.out.println("Le nombre total de scripts réalisés en une semaine est : " + rs.getInt("TotalSemaine"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Calculer le nombre total de scripts réalisés par un développeur donné");
        String devName = "ALAMI";  // Le nom du développeur
        try {
            Statement st = cn.createStatement();
            String totalDevScriptsSQL = "SELECT SUM(NbScripts) AS TotalScripts FROM DevData WHERE Developpeurs = '" + devName + "'";
            ResultSet rs = st.executeQuery(totalDevScriptsSQL);
            if (rs.next()) {
                System.out.println(devName + " a réalisé un total de " + rs.getInt("TotalScripts") + " scripts.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//exo3:Obtenir les méta-données d'une requête
        try {
    Statement st = cn.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM DevData");
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
    System.out.println("Le nombre de colonnes : " + columnCount);
    
    for (int i = 1; i <= columnCount; i++) {
        System.out.println("Nom de la colonne " + i + ": " + rsmd.getColumnName(i) + ", Type : " + rsmd.getColumnTypeName(i));
    }
} catch (SQLException e) {
    e.printStackTrace();
}

        /**
         * @param args the command line arguments
         */
        // TODO code application logic here
    }

}
