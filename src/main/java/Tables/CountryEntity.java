package Tables;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Vladimir on 30.10.2017.
 */
@Entity
@Table(name = "country", schema = "climate", catalog = "")
public class CountryEntity {
    private int idCountry;
    private String nameCountry;
    private RegionEntity regionByIdRegion;
    private Collection<NaselennayPunktEntity> naselennayPunktsByIdCountry;

    public CountryEntity(int idCountry, String nameCountry, RegionEntity regionByIdRegion) {
        this.idCountry = idCountry;
        this.nameCountry = nameCountry;
        this.regionByIdRegion = regionByIdRegion;

    }

    public CountryEntity() {
    }

    @Id
    @Column(name = "id_country", nullable = false)
    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @Column(name = "Name_country", nullable = false, length = 35)
    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (idCountry != that.idCountry) return false;
        if (nameCountry != null ? !nameCountry.equals(that.nameCountry) : that.nameCountry != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCountry;
        result = 31 * result + (nameCountry != null ? nameCountry.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_region", referencedColumnName = "id_region", nullable = false)
    public RegionEntity getRegionByIdRegion() {
        return regionByIdRegion;
    }

    public void setRegionByIdRegion(RegionEntity regionByIdRegion) {
        this.regionByIdRegion = regionByIdRegion;
    }

    @OneToMany(mappedBy = "countryByIdCountry")
    public Collection<NaselennayPunktEntity> getNaselennayPunktsByIdCountry() {
        return naselennayPunktsByIdCountry;
    }

    public void setNaselennayPunktsByIdCountry(Collection<NaselennayPunktEntity> naselennayPunktsByIdCountry) {
        this.naselennayPunktsByIdCountry = naselennayPunktsByIdCountry;
    }

    public String toString(){
        return "idCountry: "+idCountry+" nameCountry: "+nameCountry;
    }
}
