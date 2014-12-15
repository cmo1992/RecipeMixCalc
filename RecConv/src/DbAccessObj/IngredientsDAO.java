package DbAccessObj;

import DatabaseObj.Ingredients;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class IngredientsDAO
 * Ingredients Data Access Object
 * @author cmo
 */
public class IngredientsDAO {

   public Statement statement = null;
   public Connection dbConnection = null;
/**
 * Gets the list of all the elements of the ingredients table.
 * @return
 * @throws SQLException 
 */
   public ArrayList<Ingredients> getList() throws SQLException {
      String sql = "SELECT * FROM ingredients";
      ArrayList<Ingredients> myList = SQLCall(sql);
      return myList;
   }
/**
 * Inserts an ingredient into the ingredient table.
 * @param ingredient
 * @return
 * @throws SQLException 
 */
   public boolean insertIng(String ingredient) throws SQLException {
      String sql = "INSERT INTO imrdb.ingredients (ingpk, ingredient) VALUES (NULL, '" + ingredient + "');";
      return SQLCall(sql).isEmpty();
   }
/**
 * Gets the index of the Ingredient name if it is in the database.
 * @param in
 * @return
 * @throws SQLException 
 */
   public ArrayList<Ingredients> getindx(String in) throws SQLException {
      String sql = "SELECT * FROM ingredients WHERE ingredient='" + in + "';";
      ArrayList<Ingredients> myList = SQLCall(sql);
      return myList;
   }
/**
 * Checks to see if the ingredient is in the database.
 * @param in
 * @return
 * @throws SQLException 
 */
   public boolean Checkdb(String in) throws SQLException {
      String sql = "SELECT * FROM ingredients WHERE ingredient='" + in + "';";
      return SQLCall(sql).isEmpty();
   }
/**
 * Gets the ingredient name from the ID number.
 * @param in
 * @return
 * @throws SQLException 
 */
   public String getingr(int in) throws SQLException {
      String sql = "SELECT * FROM ingredients WHERE ingpk='" + in + "';";
      return SQLCall(sql).get(0).getIngredient();
   }
/**
 * The SQL call that generically  made for all the different methods above.
 * @param sqlStatemant
 * @return
 * @throws SQLException 
 */
   private ArrayList<Ingredients> SQLCall(String sqlStatemant) throws SQLException {
        ArrayList<Ingredients> myList = new ArrayList();
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
                    int i = rs.getInt("ingpk");
                    int indx = rs.getInt("ingpk");
                    String ingredientName = rs.getString("ingredient");
                    Ingredients e = new Ingredients(i, ingredientName);
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
