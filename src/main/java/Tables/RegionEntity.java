package Tables;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "region", schema = "climate", catalog = "")
public class RegionEntity {
    private int idRegion;
    private String nameRegion;
    private String opisanie;
    private Collection<CountryEntity> countriesByIdRegion;

    public RegionEntity(int idRegion, String nameRegion, String opisanie) {
        this.idRegion = idRegion;
        this.nameRegion = nameRegion;
        this.opisanie = opisanie;

    }

    public RegionEntity() {
    }

    @Id
    @Column(name = "id_region", nullable = false)
    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    @Basic
    @Column(name = "Name_region", nullable = false, length = 35)
    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    @Basic
    @Column(name = "Opisanie", nullable = true, length = 550)
    public String getOpisanie() {
        return opisanie;
    }

    public void setOpisanie(String opisanie) {
        this.opisanie = opisanie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegionEntity that = (RegionEntity) o;

        if (idRegion != that.idRegion) return false;
        if (nameRegion != null ? !nameRegion.equals(that.nameRegion) : that.nameRegion != null) return false;
        if (opisanie != null ? !opisanie.equals(that.opisanie) : that.opisanie != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRegion;
        result = 31 * result + (nameRegion != null ? nameRegion.hashCode() : 0);
        result = 31 * result + (opisanie != null ? opisanie.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "regionByIdRegion")
    public Collection<CountryEntity> getCountriesByIdRegion() {
        return countriesByIdRegion;
    }

    public void setCountriesByIdRegion(Collection<CountryEntity> countriesByIdRegion) {
        this.countriesByIdRegion = countriesByIdRegion;
    }

    public String toString(){
        return "idRegion: "+idRegion+" nameRegion: "+nameRegion+" opisanie: "+ opisanie;
    }
}
