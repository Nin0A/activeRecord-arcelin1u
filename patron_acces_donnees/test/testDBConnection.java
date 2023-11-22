import com.example.patron_acces_donnees.DBConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testDBConnection {

    private  Connection connection;

    @BeforeEach
    public void init() throws SQLException {
         this.connection = DBConnection.getConnection();
    }

    /**
     * Vérification que la même connection est renvoyé
     * @throws SQLException
     */
    @Test
    public void test1() throws SQLException {
            Connection connect = DBConnection.getConnection();
            assertTrue(connect==this.connection,"Il y a une erreur car nous avons déjà créer la connection");
    }

    /**
     * Vérification
     * @throws SQLException
     */
    @Test
    public void test2() throws SQLException{
        // creation de la table Personne
        {
            String createString = "CREATE TABLE Personne ( " + "ID INTEGER  AUTO_INCREMENT, "
                    + "NOM varchar(40) NOT NULL, " + "PRENOM varchar(40) NOT NULL, " + "PRIMARY KEY (ID))";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(createString);
            System.out.println("1) creation table Personne\n");
        }

        // ajout de personne avec requete preparee
        {
            String SQLPrep = "INSERT INTO Personne (nom, prenom) VALUES (?,?);";
            PreparedStatement prep;
            // l'option RETURN_GENERATED_KEYS permet de recuperer l'id (car
            // auto-increment)
            prep = connection.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, "Spielberg");
            prep.setString(2, "Steven");
            prep.executeUpdate();
            System.out.println("2) ajout Steven Spielberg\n");
        }
    }

    /**
     * Vérification du setter
     */
    @Test
    public void test3() throws SQLException {
        DBConnection dbc =  DBConnection.setNomDB("aaa");
    }
}
