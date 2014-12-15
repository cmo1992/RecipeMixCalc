package DbAccessObj;

import DatabaseObj.Recipes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class RecipesDAO
 * Recipes Data Access Object
 * @author cmo
 */
public class RecipesDAO {

    public Statement statement = null;
    public Connection dbConnection = null;
/**
 *  Gets the list of all the elements of the recipe table.
 * @return
 * @throws SQLException 
 */
    public ArrayList<Recipes> getList() throws SQLException {
        String sql = "SELECT * FROM recipes";
        ArrayList<Recipes> myList = SQLCall(sql);
        return myList;
    }
/**
 * Inserts a recipe name into the recipe table.
 * @param recipe
 * @return
 * @throws SQLException 
 */
    public boolean insertRec(String recipe) throws SQLException {
        String sql = "INSERT INTO imrdb.recipes (recpk, recipe) VALUES (NULL, '" + recipe + "');";
        ArrayList<Recipes> myList = SQLCall(sql);
        return myList.isEmpty();
    }
/**
 * Gets the index of the recipe name if it is in the database.
 * @param in
 * @return
 * @throws SQLException 
 */
    public int getindx(String in) throws SQLException {
        String sql = "SELECT * FROM recipes WHERE recipe='" + in + "';";
        ArrayList<Recipes> myList = SQLCall(sql);
        return myList.get(0).getRecpk();
    }
/**
 * Checks to see if the recipe is in the database.
 * @param in
 * @return
 * @throws SQLException 
 */
    public boolean Checkdb(String in) throws SQLException {
        String sql = "SELECT * FROM recipes WHERE recipe='" + in + "';";
        ArrayList<Recipes> myList = SQLCall(sql);
        return myList.isEmpty();
    }
/**
 * Gets the recipe name from the ID number.
 * @param in
 * @return
 * @throws SQLException 
 */
    public String getrec(int in) throws SQLException {
        String sql = "SELECT * FROM recipes WHERE recpk='" + in + "';";
        ArrayList<Recipes> myList = SQLCall(sql);
        return myList.get(0).getRecipe();
    }
/**
 * The SQL call that generically  made for all the different methods above.
 * @param sqlStatemant
 * @return
 * @throws SQLException 
 */
    private ArrayList<Recipes> SQLCall(String sqlStatemant) throws SQLException {
        ArrayList<Recipes> myList = new ArrayList();
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
                    int indx = rs.getInt("recpk");
                    String recipeName = rs.getString("recipe");
                    Recipes e = new Recipes(indx, recipeName);
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
