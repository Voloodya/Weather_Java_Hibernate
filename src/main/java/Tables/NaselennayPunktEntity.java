package Tables;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "naselennay_punkt", schema = "climate", catalog = "")
public class NaselennayPunktEntity {
    private int idPunkt;
    private String nameNaselenPunkt;
    private CountryEntity countryByIdCountry;
    private ClimatPoyasEntity climatPoyasByIdPoyas;
    private Collection<PogodaEntity> pogodasByIdPunkt;

    public NaselennayPunktEntity(int idPunkt, String nameNaselenPunkt, CountryEntity countryByIdCountry, ClimatPoyasEntity climatPoyasByIdPoyas) {
        this.idPunkt = idPunkt;
        this.nameNaselenPunkt = nameNaselenPunkt;
        this.countryByIdCountry = countryByIdCountry;
        this.climatPoyasByIdPoyas = climatPoyasByIdPoyas;

    }

    public NaselennayPunktEntity() {
    }

    @Id
    @Column(name = "id_punkt", nullable = false)
    public int getIdPunkt() {
        return idPunkt;
    }

    public void setIdPunkt(int idPunkt) {
        this.idPunkt = idPunkt;
    }

    @Basic
    @Column(name = "Name_naselen_punkt", nullable = true, length = 30)
    public String getNameNaselenPunkt() {
        return nameNaselenPunkt;
    }

    public void setNameNaselenPunkt(String nameNaselenPunkt) {
        this.nameNaselenPunkt = nameNaselenPunkt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NaselennayPunktEntity that = (NaselennayPunktEntity) o;

        if (idPunkt != that.idPunkt) return false;
        if (nameNaselenPunkt != null ? !nameNaselenPunkt.equals(that.nameNaselenPunkt) : that.nameNaselenPunkt != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPunkt;
        result = 31 * result + (nameNaselenPunkt != null ? nameNaselenPunkt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_country", referencedColumnName = "id_country", nullable = false)
    public CountryEntity getCountryByIdCountry() {
        return countryByIdCountry;
    }

    public void setCountryByIdCountry(CountryEntity countryByIdCountry) {
        this.countryByIdCountry = countryByIdCountry;
    }

    @ManyToOne
    @JoinColumn(name = "id_poyas", referencedColumnName = "id_poyas", nullable = false)
    public ClimatPoyasEntity getClimatPoyasByIdPoyas() {
        return climatPoyasByIdPoyas;
    }

    public void setClimatPoyasByIdPoyas(ClimatPoyasEntity climatPoyasByIdPoyas) {
        this.climatPoyasByIdPoyas = climatPoyasByIdPoyas;
    }

    @OneToMany(mappedBy = "naselennayPunktByIdPunkt")
    public Collection<PogodaEntity> getPogodasByIdPunkt() {
        return pogodasByIdPunkt;
    }

    public void setPogodasByIdPunkt(Collection<PogodaEntity> pogodasByIdPunkt) {
        this.pogodasByIdPunkt = pogodasByIdPunkt;
    }

    public String toString(){
        return "idPunkt: "+idPunkt+" nameNaselenPunkt: "+nameNaselenPunkt;
    }
}
