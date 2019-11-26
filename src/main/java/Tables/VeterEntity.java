package Tables;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Vladimir on 30.10.2017.
 */
@Entity
@Table(name = "veter", schema = "climate", catalog = "")
public class VeterEntity {
    private int idVeter;
    private String napravlenieVetra;
    private String picture;
    private Collection<PogodaEntity> pogodasByIdVeter;

    public VeterEntity(int idVeter, String napravlenieVetra, String picture, Collection<PogodaEntity> pogodasByIdVeter) {
        this.idVeter = idVeter;
        this.napravlenieVetra = napravlenieVetra;
        this.picture = picture;
        this.pogodasByIdVeter = pogodasByIdVeter;
    }

    public VeterEntity() {
    }

    @Id
    @Column(name = "id_veter", nullable = false)
    public int getIdVeter() {
        return idVeter;
    }

    public void setIdVeter(int idVeter) {
        this.idVeter = idVeter;
    }

    @Basic
    @Column(name = "Napravlenie_vetra", nullable = false, length = 21)
    public String getNapravlenieVetra() {
        return napravlenieVetra;
    }

    public void setNapravlenieVetra(String napravlenieVetra) {
        this.napravlenieVetra = napravlenieVetra;
    }

    @Basic
    @Column(name = "Picture", nullable = true, length = 150)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeterEntity that = (VeterEntity) o;

        if (idVeter != that.idVeter) return false;
        if (napravlenieVetra != null ? !napravlenieVetra.equals(that.napravlenieVetra) : that.napravlenieVetra != null)
            return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVeter;
        result = 31 * result + (napravlenieVetra != null ? napravlenieVetra.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "veterByNapravlenieVetra")
    public Collection<PogodaEntity> getPogodasByIdVeter() {
        return pogodasByIdVeter;
    }

    public void setPogodasByIdVeter(Collection<PogodaEntity> pogodasByIdVeter) {
        this.pogodasByIdVeter = pogodasByIdVeter;
    }

    public String toString(){
        return "idVeter: "+idVeter+" napravlenieVetra: "+napravlenieVetra+" picture: "+ picture;
    }
}
