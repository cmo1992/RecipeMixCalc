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
 * Recipes database object
 * @author cmo
 */
@Entity
@Table(name = "recipes", catalog = "imrdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Recipes.findAll", query = "SELECT r FROM Recipes r"),
    @NamedQuery(name = "Recipes.findByRecpk", query = "SELECT r FROM Recipes r WHERE r.recpk = :recpk"),
    @NamedQuery(name = "Recipes.findByRecipe", query = "SELECT r FROM Recipes r WHERE r.recipe = :recipe")})
public class Recipes implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recpk")
    private Integer recpk;
    @Basic(optional = false)
    @Column(name = "recipe")
    private String recipe;

    public Recipes() {
    }

    public Recipes(Integer recpk) {
        this.recpk = recpk;
    }

    public Recipes(Integer recpk, String recipe) {
        this.recpk = recpk;
        this.recipe = recipe;
    }

    public Integer getRecpk() {
        return recpk;
    }

    public void setRecpk(Integer recpk) {
        Integer oldRecpk = this.recpk;
        this.recpk = recpk;
        changeSupport.firePropertyChange("recpk", oldRecpk, recpk);
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        String oldRecipe = this.recipe;
        this.recipe = recipe;
        changeSupport.firePropertyChange("recipe", oldRecipe, recipe);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recpk != null ? recpk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipes)) {
            return false;
        }
        Recipes other = (Recipes) object;
        if ((this.recpk == null && other.recpk != null) || (this.recpk != null && !this.recpk.equals(other.recpk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "imrdb.Recipes[ recpk=" + recpk + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
