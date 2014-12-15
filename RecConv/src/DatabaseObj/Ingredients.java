package DatabaseObj;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Ingredients database object
 * @author cmo
 */
@Entity
@Table(name = "ingredients", catalog = "imrdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ingredients.findAll", query = "SELECT i FROM Ingredients i"),
    @NamedQuery(name = "Ingredients.findByIngpk", query = "SELECT i FROM Ingredients i WHERE i.ingpk = :ingpk"),
    @NamedQuery(name = "Ingredients.findByIngredient", query = "SELECT i FROM Ingredients i WHERE i.ingredient = :ingredient")})
public class Ingredients implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingpk")
    private Integer ingpk;
    @Basic(optional = false)
    @Column(name = "ingredient")
    private String ingredient;

    public Ingredients() {
    }

    public Ingredients(Integer ingpk) {
        this.ingpk = ingpk;
    }

    public Ingredients(Integer ingpk, String ingredient) {
        this.ingpk = ingpk;
        this.ingredient = ingredient;
    }

    public Integer getIngpk() {
        return ingpk;
    }

    public void setIngpk(Integer ingpk) {
        Integer oldIngpk = this.ingpk;
        this.ingpk = ingpk;
        changeSupport.firePropertyChange("ingpk", oldIngpk, ingpk);
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        String oldIngredient = this.ingredient;
        this.ingredient = ingredient;
        changeSupport.firePropertyChange("ingredient", oldIngredient, ingredient);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingpk != null ? ingpk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredients)) {
            return false;
        }
        Ingredients other = (Ingredients) object;
        if ((this.ingpk == null && other.ingpk != null) || (this.ingpk != null && !this.ingpk.equals(other.ingpk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "imrdb.Ingredients[ ingpk=" + ingpk + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
