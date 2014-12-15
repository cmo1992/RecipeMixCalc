package AppInterface;

import DatabaseObj.IngTorec;
import DbAccessObj.IngredientsDAO;
import java.sql.SQLException;

/**
 * Class RecIngStrct.
 * This class is used in calculating the Ingredients weight when in the 
 * FrmCalculation.
 * @author cmo
 */
public class RecIngStrct {

    int item;
    String ingredient;
    float percentage;
    float weight;
    int noBags;
    float remainingWeight;
/**
 * Constructor.
 * This creates the RecIngStrct object with all the 
 * @param itemq
 * @param ingredientq
 * @param percentageq
 * @param weightq
 * @param noBagsq
 * @param remainingWeightq 
 */
    public RecIngStrct(int itemq, String ingredientq, float percentageq,
            float weightq, int noBagsq, float remainingWeightq) {
        item = itemq;
        ingredient = ingredientq;
        percentage = percentageq;
        weight = weightq;
        noBags = noBagsq;
        remainingWeight = remainingWeightq;
    }
/**
 * @Method: RecIngStrct
 * @Details: Takes a IngTorec and the total weight of the mix. It takes the item
 * number and percentage from the IngTorec object. It then gets the ingredients 
 * name from the table and finally call the CalculateAmount.
 * @param in
 * @param recipeTotalWeight
 * @throws SQLException 
 */
    public RecIngStrct(IngTorec in, int recipeTotalWeight) throws SQLException {
        IngredientsDAO iDAO = new IngredientsDAO();
        item = in.getIngpk();
        percentage = in.getPercentage();
        ingredient = iDAO.getingr(item);
        calculateAmount(recipeTotalWeight);
        
    }
    /**
     * @Method: calculateAmount
     * @Detail: This calculates the amount of the ingredient is needed to make
     * the mix
     * @param recipeTotalWeight 
     */
    public void calculateAmount(float recipeTotalWeight) {
        weight = percentage * recipeTotalWeight;
        noBags = (int) weight / 50;
        remainingWeight = weight % 50;
    }
    /**
     * @Method: toString
     * @Detail: This method prints out the ingredient and all the details in a 
     * nicely formatted layout.
     * @return String
     */
    @Override
    public String toString() {
        String toSg = new String();
        toSg += "     ";
        toSg += ingredient;
        for( int i = toSg.length(); i < 25; i++)
            toSg += " ";
        
        if(percentage < .1)
            toSg += " ";
        toSg += String.format("%2.2f", percentage * 100) + "%";
        for( int i = toSg.length(); i < 40; i++)
            toSg += " ";
        
        if(weight < 10)
            toSg += "   ";
        else if(weight < 100)
            toSg += "  ";
        else if(weight < 1000)
            toSg += " ";
        toSg += String.format("%.2f", weight);
        for( int i = toSg.length(); i < 55; i++)
            toSg += " ";
        
        if(noBags < 10)
            toSg += "  ";
        else if(noBags < 100)
            toSg += " ";
        toSg += noBags + "";
        for( int i = toSg.length(); i < 70; i++)
            toSg += " ";
        
        if(remainingWeight < 10)
            toSg += "   ";
        else if(remainingWeight < 100)
            toSg += "  ";
        
        toSg += String.format("%.2f", remainingWeight);
        for( int i = toSg.length(); i < 75; i++)
            toSg += " ";
        
        return toSg;
    }
}
