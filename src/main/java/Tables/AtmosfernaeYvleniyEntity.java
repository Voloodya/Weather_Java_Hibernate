package Tables;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "atmosfernae_yvleniy", schema = "climate", catalog = "")
public class AtmosfernaeYvleniyEntity {
    private int idOsadky;
    private String vidYvleniy;
    private String picture;
    private Collection<PogodaEntity> pogodasByIdOsadky;
    private Collection<PogodaEntity> pogodasByIdOsadky_0;
    private Collection<PogodaEntity> pogodasByIdOsadky_1;
    private Collection<PogodaEntity> pogodasByIdOsadky_2;

    public AtmosfernaeYvleniyEntity(int idOsadky, String vidYvleniy, String picture) {
        this.idOsadky = idOsadky;
        this.vidYvleniy = vidYvleniy;
        this.picture = picture;

    }

    public AtmosfernaeYvleniyEntity() {
    }

    @Id
    @Column(name = "id_osadky", nullable = false)
    public int getIdOsadky() {
        return idOsadky;
    }

    public void setIdOsadky(int idOsadky) {
        this.idOsadky = idOsadky;
    }

    @Basic
    @Column(name = "Vid_yvleniy", nullable = false, length = 35)
    public String getVidYvleniy() {
        return vidYvleniy;
    }

    public void setVidYvleniy(String vidYvleniy) {
        this.vidYvleniy = vidYvleniy;
    }

    @Basic
    @Column(name = "Picture", nullable = true, length = 200)
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

        AtmosfernaeYvleniyEntity that = (AtmosfernaeYvleniyEntity) o;

        if (idOsadky != that.idOsadky) return false;
        if (vidYvleniy != null ? !vidYvleniy.equals(that.vidYvleniy) : that.vidYvleniy != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOsadky;
        result = 31 * result + (vidYvleniy != null ? vidYvleniy.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "atmosfernaeYvleniyByIdOsadkyNoh")
    public Collection<PogodaEntity> getPogodasByIdOsadky() {
        return pogodasByIdOsadky;
    }

    public void setPogodasByIdOsadky(Collection<PogodaEntity> pogodasByIdOsadky) {
        this.pogodasByIdOsadky = pogodasByIdOsadky;
    }

    @OneToMany(mappedBy = "atmosfernaeYvleniyByIdOsadkyUtro")
    public Collection<PogodaEntity> getPogodasByIdOsadky_0() {
        return pogodasByIdOsadky_0;
    }

    public void setPogodasByIdOsadky_0(Collection<PogodaEntity> pogodasByIdOsadky_0) {
        this.pogodasByIdOsadky_0 = pogodasByIdOsadky_0;
    }

    @OneToMany(mappedBy = "atmosfernaeYvleniyByIdOsadkyDay")
    public Collection<PogodaEntity> getPogodasByIdOsadky_1() {
        return pogodasByIdOsadky_1;
    }

    public void setPogodasByIdOsadky_1(Collection<PogodaEntity> pogodasByIdOsadky_1) {
        this.pogodasByIdOsadky_1 = pogodasByIdOsadky_1;
    }

    @OneToMany(mappedBy = "atmosfernaeYvleniyByIdOsadkyVeher")
    public Collection<PogodaEntity> getPogodasByIdOsadky_2() {
        return pogodasByIdOsadky_2;
    }

    public void setPogodasByIdOsadky_2(Collection<PogodaEntity> pogodasByIdOsadky_2) {
        this.pogodasByIdOsadky_2 = pogodasByIdOsadky_2;
    }

    public String toString(){
        return "idOsadky: "+idOsadky+" vidYvleniy: "+vidYvleniy+" picture: "+ picture;
    }
}
