package DbAccessObj;

import DatabaseObj.Company;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class CompanyDAO.
 * Company Data Access Object.
 * @author cmo
 */
public class CompanyDAO {

    public Statement statement = null;
    public Connection dbConnection = null;
/**
 * Gets the list of all the elements of the company table.
 * @return ArrayList<Company>
 * @throws SQLException 
 */
    public ArrayList<Company> getList() throws SQLException {
        String sql = "SELECT * FROM company ORDER BY compk ASC;";
        ArrayList<Company> myList = SQLCall(sql);
        return myList;
    }
/**
 * Inserts a company into the company table.
 * @param com
 * @return
 * @throws SQLException 
 */
    public boolean insertCom(String com) throws SQLException {
        String sql = "INSERT INTO imrdb.company (compk, comname) VALUES (NULL, '" + com + "');";
        ArrayList<Company> myList = SQLCall(sql);
        return myList.isEmpty();
    }
/**
 * Gets the index of the company name if it is in the database.
 * @param in
 * @return
 * @throws SQLException 
 */
    public int getindx(String in) throws SQLException {
        String sql = "SELECT * FROM imrdb.company WHERE comname='" + in + "';";
        ArrayList<Company> myList = SQLCall(sql);
        return myList.get(0).getCompk();
    }
/**
 * Checks to see if the company is in the database.
 * @param in
 * @return
 * @throws SQLException 
 */
    public boolean Checkdb(String in) throws SQLException {
        String sql = "SELECT * FROM imrdb.company WHERE comname='" + in + "';";
        ArrayList<Company> myList = SQLCall(sql);
        return myList.isEmpty();
    }
/**
 * Gets the company name from the ID number.
 * @param in
 * @return
 * @throws SQLException 
 */
    public String getCompany(int in) throws SQLException {
        String sql = "SELECT * FROM company WHERE compk='" + in + "';";
        return SQLCall(sql).get(0).getComname();
    }
/**
 * Deletes the ingredient from the database.
 * @param in
 * @throws SQLException 
 */
    public void deleteIng(String in) throws SQLException {
        String sql = "DELETE FROM imrdb.ingrediance" + " WHERE Index = " + getindx(in);
        SQLCall(sql);
    }
/**
 * The SQL call that generically  made for all the different methods above.
 * @param sqlStatemant
 * @return
 * @throws SQLException 
 */
    private ArrayList<Company> SQLCall(String sqlStatemant) throws SQLException {
        ArrayList<Company> myList = new ArrayList();
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
                    int indx = rs.getInt("compk");
                    String company = rs.getString("comname");
                    Company e = new Company(indx, company);
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
