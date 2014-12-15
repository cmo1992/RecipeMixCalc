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
 *IngTorec database Object
 * @author cmo
 */
@Entity
@Table(name = "ingtorec", catalog = "imrdb", schema = "")
@NamedQueries({
   @NamedQuery(name = "Ingtorec.findAll", query = "SELECT i FROM Ingtorec i"),
   @NamedQuery(name = "Ingtorec.findByIrpk", query = "SELECT i FROM Ingtorec i WHERE i.irpk = :irpk"),
   @NamedQuery(name = "Ingtorec.findByRecpk", query = "SELECT i FROM Ingtorec i WHERE i.recpk = :recpk"),
   @NamedQuery(name = "Ingtorec.findByIngpk", query = "SELECT i FROM Ingtorec i WHERE i.ingpk = :ingpk"),
   @NamedQuery(name = "Ingtorec.findByPercentage", query = "SELECT i FROM Ingtorec i WHERE i.percentage = :percentage")})
public class IngTorec implements Serializable {
   @Transient
   private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "irpk")
   private Integer irpk;
   @Basic(optional = false)
   @Column(name = "recpk")
   private int recpk;
   @Basic(optional = false)
   @Column(name = "ingpk")
   private int ingpk;
   @Basic(optional = false)
   @Column(name = "percentage")
   private float percentage;

   public IngTorec() {
   }

   public IngTorec(Integer irpk) {
      this.irpk = irpk;
   }

   public IngTorec(Integer irpk, int recpk, int ingpk, float percentage) {
      this.irpk = irpk;
      this.recpk = recpk;
      this.ingpk = ingpk;
      this.percentage = percentage;
   }

   public Integer getIrpk() {
      return irpk;
   }

   public void setIrpk(Integer irpk) {
      Integer oldIrpk = this.irpk;
      this.irpk = irpk;
      changeSupport.firePropertyChange("irpk", oldIrpk, irpk);
   }

   public int getRecpk() {
      return recpk;
   }

   public void setRecpk(int recpk) {
      int oldRecpk = this.recpk;
      this.recpk = recpk;
      changeSupport.firePropertyChange("recpk", oldRecpk, recpk);
   }

   public int getIngpk() {
      return ingpk;
   }

   public void setIngpk(int ingpk) {
      int oldIngpk = this.ingpk;
      this.ingpk = ingpk;
      changeSupport.firePropertyChange("ingpk", oldIngpk, ingpk);
   }

   public float getPercentage() {
      return percentage;
   }

   public void setPercentage(float percentage) {
      float oldPercentage = this.percentage;
      this.percentage = percentage;
      changeSupport.firePropertyChange("percentage", oldPercentage, percentage);
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (irpk != null ? irpk.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof IngTorec)) {
         return false;
      }
      IngTorec other = (IngTorec) object;
      if ((this.irpk == null && other.irpk != null) || (this.irpk != null && !this.irpk.equals(other.irpk))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "recconv.Ingtorec[ irpk=" + irpk + " ]";
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      changeSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      changeSupport.removePropertyChangeListener(listener);
   }
   
}
