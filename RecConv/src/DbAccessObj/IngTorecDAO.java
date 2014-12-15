package DbAccessObj;

import DatabaseObj.IngTorec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class IngTorecDAO
 * Ingredient to Recipe Data Access Object
 * @author cmo
 */
public class IngTorecDAO {

    public Statement statement = null;
    public Connection dbConnection = null;
/**
 * Gets the list of all the elements of the ingtorec table.
 * @return
 * @throws SQLException 
 */
    public ArrayList<IngTorec> getList() throws SQLException {
        String sql = "SELECT * FROM ingtorec";
        ArrayList<IngTorec> myList = SQLCall(sql);
        return myList;
    }
/**
 * Inserts a ingtorec into the ingtorec table.
 * @param rc
 * @param ingpk
 * @param percentage
 * @return
 * @throws SQLException 
 */
    public boolean insertIng(int rc, int ingpk, float percentage) throws SQLException {
        String sql = "INSERT INTO `imrdb`.`ingtorec` (`irpk`, `recpk`, `ingpk`, `percentage`) VALUES (NULL, " + rc + ", " + ingpk + ", " + percentage + ");";
        return SQLCall(sql).isEmpty();
    }
/**
 * Checks to see if the ingtorec is in the database.
 * @param rc
 * @param ingpk
 * @param percentage
 * @return
 * @throws SQLException 
 */
    public boolean Checkdb(int rc, int ingpk, float percentage) throws SQLException {
        String sql = "SELECT * FROM imrdb.company WHERE recpk='" + rc + "' and ingpk='" + ingpk + "' and percentage='" + percentage + "';";
        ArrayList<IngTorec> myList = SQLCall(sql);
        return myList.isEmpty();
    }
/**
 * Gets the ingtorec name from the ID number.
 * @param in
 * @return
 * @throws SQLException 
 */
    public ArrayList<IngTorec> getRecipe(int in) throws SQLException {
        String sql = "SELECT * FROM ingtorec WHERE recpk='" + in + "';";
        ArrayList<IngTorec> myList = SQLCall(sql);
        return myList;
    }

//   public void deleteIng(String in) throws SQLException {
//      List<Ingrediance> myList = getindx(in);
//      String insertTableSQL = "DELETE FROM imrdb.ingrediance" + " WHERE Index = " + roomIDNum;
//      }
/**
 * The SQL call that generically  made for all the different methods above.
 * @param sqlStatemant
 * @return
 * @throws SQLException 
 */
    private ArrayList<IngTorec> SQLCall(String sqlStatemant) throws SQLException {
        ArrayList<IngTorec> myList = new ArrayList();
        String printMsg;
        try {
            MyConnection mc = new MyConnection();
            dbConnection = mc.getConnection();
            statement = dbConnection.createStatement();
            System.out.println(sqlStatemant);
            
            if (sqlStatemant.charAt(0) == 'I') {
                statement.executeUpdate(sqlStatemant);
                printMsg = "Recipe is inserted into rectocom table!";
            } else if (sqlStatemant.charAt(0) == 'D') {
                statement.executeUpdate(sqlStatemant);
                printMsg = "Recipe was deleted from rectocom table!";
            } else if (sqlStatemant.charAt(0) == 'S') {
                ResultSet rs = statement.executeQuery(sqlStatemant);
                while (rs.next()) {
                    int irpk = rs.getInt("irpk");
                    int recpk = rs.getInt("recpk");
                    int ingpk = rs.getInt("ingpk");
                    float percentage = rs.getFloat("percentage");
                    IngTorec e = new IngTorec(irpk, recpk, ingpk, percentage);
                    myList.add(e);
                }
                printMsg = "There are " + myList.size() + " elements in the List.";
            } else {
                printMsg = "What just happened";
            }
            System.out.println(printMsg);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return myList;
    }
}
