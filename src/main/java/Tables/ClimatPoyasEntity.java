package Tables;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "climat_poyas", schema = "climate", catalog = "")
public class ClimatPoyasEntity {
    private int idPoyas;
    private String nameClimatePoyas;
    private String opisanie;
    private Collection<NaselennayPunktEntity> naselennayPunktsByIdPoyas;

    public ClimatPoyasEntity(int idPoyas, String nameClimatePoyas, String opisanie, Collection<NaselennayPunktEntity> naselennayPunktsByIdPoyas) {
        this.idPoyas = idPoyas;
        this.nameClimatePoyas = nameClimatePoyas;
        this.opisanie = opisanie;
        this.naselennayPunktsByIdPoyas = naselennayPunktsByIdPoyas;
    }

    public ClimatPoyasEntity() {
    }

    @Id
    @Column(name = "id_poyas", nullable = false)
    public int getIdPoyas() {
        return idPoyas;
    }

    public void setIdPoyas(int idPoyas) {
        this.idPoyas = idPoyas;
    }

    @Basic
    @Column(name = "Name_climate_poyas", nullable = false, length = 35)
    public String getNameClimatePoyas() {
        return nameClimatePoyas;
    }

    public void setNameClimatePoyas(String nameClimatePoyas) {
        this.nameClimatePoyas = nameClimatePoyas;
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

        ClimatPoyasEntity that = (ClimatPoyasEntity) o;

        if (idPoyas != that.idPoyas) return false;
        if (nameClimatePoyas != null ? !nameClimatePoyas.equals(that.nameClimatePoyas) : that.nameClimatePoyas != null)
            return false;
        if (opisanie != null ? !opisanie.equals(that.opisanie) : that.opisanie != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPoyas;
        result = 31 * result + (nameClimatePoyas != null ? nameClimatePoyas.hashCode() : 0);
        result = 31 * result + (opisanie != null ? opisanie.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "climatPoyasByIdPoyas")
    public Collection<NaselennayPunktEntity> getNaselennayPunktsByIdPoyas() {
        return naselennayPunktsByIdPoyas;
    }

    public void setNaselennayPunktsByIdPoyas(Collection<NaselennayPunktEntity> naselennayPunktsByIdPoyas) {
        this.naselennayPunktsByIdPoyas = naselennayPunktsByIdPoyas;
    }

    public String toString(){
        return "idPoyas: "+idPoyas+" nameClimatePoyas: "+nameClimatePoyas+" opisanie: "+ opisanie;
    }
}
