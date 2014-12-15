package DbAccessObj;

import DatabaseObj.Rectocom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class RectocomDAO
 * Recipe to Company Data Access Object
 * @author cmo
 */
public class RectocomDAO {

    public Statement statement = null;
    public Connection dbConnection = null;
/**
 * Gets the list of all the elements of the rectocom table.
 * @return
 * @throws SQLException 
 */
    public ArrayList<Rectocom> getUnique() throws SQLException {
        String sql = "SELECT * FROM rectocom";
        return SQLCall(sql);
    }
/**
 * Inserts an rectocom into the rectocom table.
 * @param recpk
 * @param compk
 * @return
 * @throws SQLException 
 */
    public boolean insertRec(int recpk, int compk) throws SQLException {
        String sql = "INSERT INTO imrdb.rectocom (rcpk, recpk, compk) VALUES (NULL, '" + recpk + "', '" + compk + "');";
        return SQLCall(sql).isEmpty();
    }
/**
 * Gets the index from the recipe and company ID numbers if it is in the database.
 * @param inRec
 * @param inCom
 * @return
 * @throws SQLException 
 */
    public int getindx(int inRec, int inCom) throws SQLException {
        String sql = "SELECT * FROM rectocom WHERE recpk=" + inRec + " AND compk=" + inCom + ";";
        ArrayList<Rectocom> myList = SQLCall(sql);
        for ( int i = 0; i < myList.size(); i++)
            System.out.println(i + " == " + myList.get(i).getRecpk());
        return myList.get(0).getRcpk();
    }
/**
 * Checks to see if the rectocom is in the database.
 * @param inRec
 * @param inCom
 * @return
 * @throws SQLException 
 */
    public boolean Checkdb(int inRec, int inCom) throws SQLException {
        String sql = "SELECT * FROM rectocom WHERE recpk=" + inRec + " AND compk=" + inCom + ";";
        ArrayList<Rectocom> myList = SQLCall(sql);
        return myList.isEmpty();
    }
/**
 * Gets all the recipes for a company's ID number.
 * @param inCompk
 * @return
 * @throws SQLException 
 */
    public ArrayList<Rectocom> getRec(int inCompk) throws SQLException {
        String sql = "SELECT * FROM rectocom WHERE compk='" + inCompk + "';";
        ArrayList<Rectocom> myList = SQLCall(sql);
        return myList;
    }
 /**
  * The SQL call that generically  made for all the different methods above.
  * @param sqlStatemant
  * @return
  * @throws SQLException 
  */
    private ArrayList<Rectocom> SQLCall(String sqlStatemant) throws SQLException {
        ArrayList<Rectocom> myList = new ArrayList();
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
                    int indx = rs.getInt("rcpk");
                    int recpk = rs.getInt("recpk");
                    int compk = rs.getInt("compk");
                    Rectocom e = new Rectocom(indx, compk, recpk);
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
