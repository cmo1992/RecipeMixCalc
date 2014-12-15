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
 * Rectocom database object
 * @author cmo
 */
@Entity
@Table(name = "rectocom", catalog = "imrdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Rectocom.findAll", query = "SELECT r FROM Rectocom r"),
    @NamedQuery(name = "Rectocom.findByRcpk", query = "SELECT r FROM Rectocom r WHERE r.rcpk = :rcpk"),
    @NamedQuery(name = "Rectocom.findByCompk", query = "SELECT r FROM Rectocom r WHERE r.compk = :compk"),
    @NamedQuery(name = "Rectocom.findByRecpk", query = "SELECT r FROM Rectocom r WHERE r.recpk = :recpk")})
public class Rectocom implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rcpk")
    private Integer rcpk;
    @Basic(optional = false)
    @Column(name = "compk")
    private int compk;
    @Basic(optional = false)
    @Column(name = "recpk")
    private int recpk;

    public Rectocom() {
    }

    public Rectocom(Integer rcpk) {
        this.rcpk = rcpk;
    }

    public Rectocom(Integer rcpk, int compk, int recpk) {
        this.rcpk = rcpk;
        this.compk = compk;
        this.recpk = recpk;
    }

    public Integer getRcpk() {
        return rcpk;
    }

    public void setRcpk(Integer rcpk) {
        Integer oldRcpk = this.rcpk;
        this.rcpk = rcpk;
        changeSupport.firePropertyChange("rcpk", oldRcpk, rcpk);
    }

    public int getCompk() {
        return compk;
    }

    public void setCompk(int compk) {
        int oldCompk = this.compk;
        this.compk = compk;
        changeSupport.firePropertyChange("compk", oldCompk, compk);
    }

    public int getRecpk() {
        return recpk;
    }

    public void setRecpk(int recpk) {
        int oldRecpk = this.recpk;
        this.recpk = recpk;
        changeSupport.firePropertyChange("recpk", oldRecpk, recpk);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rcpk != null ? rcpk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rectocom)) {
            return false;
        }
        Rectocom other = (Rectocom) object;
        if ((this.rcpk == null && other.rcpk != null) || (this.rcpk != null && !this.rcpk.equals(other.rcpk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "imrdb.Rectocom[ rcpk=" + rcpk + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
