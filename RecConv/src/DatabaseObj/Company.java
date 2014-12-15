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
 * Company database object
 * @author cmo
 */
@Entity
@Table(name = "company", catalog = "imrdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompk", query = "SELECT c FROM Company c WHERE c.compk = :compk"),
    @NamedQuery(name = "Company.findByComname", query = "SELECT c FROM Company c WHERE c.comname = :comname")})
public class Company implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compk")
    private Integer compk;
    @Basic(optional = false)
    @Column(name = "comname")
    private String comname;

    public Company() {
    }

    public Company(Integer compk) {
        this.compk = compk;
    }

    public Company(Integer compk, String comname) {
        this.compk = compk;
        this.comname = comname;
    }

    public Integer getCompk() {
        return compk;
    }

    public void setCompk(Integer compk) {
        Integer oldCompk = this.compk;
        this.compk = compk;
        changeSupport.firePropertyChange("compk", oldCompk, compk);
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        String oldComname = this.comname;
        this.comname = comname;
        changeSupport.firePropertyChange("comname", oldComname, comname);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compk != null ? compk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.compk == null && other.compk != null) || (this.compk != null && !this.compk.equals(other.compk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "imrdb.Company[ compk=" + compk + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
