package Tables;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pogoda", schema = "climate", catalog = "")
public class PogodaEntity {
    private int idPogoda;
    private Date data;
    private Integer tNoh;
    private Integer tUtro;
    private Integer tDay;
    private Integer tVeher;
    private Integer vlagnosty;
    private Integer davlenie;
    private Integer skorostyVetra;
    private Integer temperaturaVoda;
    private NaselennayPunktEntity naselennayPunktByIdPunkt;
    private AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyNoh;
    private AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyUtro;
    private AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyDay;
    private AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyVeher;
    private VeterEntity veterByNapravlenieVetra;

    public PogodaEntity(NaselennayPunktEntity naselennayPunktByIdPunkt,Date data, Integer tNoh, Integer tUtro, Integer tDay,
                        Integer tVeher, Integer vlagnosty, Integer davlenie,
                        Integer skorostyVetra, Integer temperaturaVoda,
                        AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyNoh, AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyUtro,
                        AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyDay, AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyVeher,
                        VeterEntity veterByNapravlenieVetra) {

        this.data = data;
        this.tNoh = tNoh;
        this.tUtro = tUtro;
        this.tDay = tDay;
        this.tVeher = tVeher;
        this.vlagnosty = vlagnosty;
        this.davlenie = davlenie;
        this.skorostyVetra = skorostyVetra;
        this.temperaturaVoda = temperaturaVoda;
        this.naselennayPunktByIdPunkt = naselennayPunktByIdPunkt;
        this.atmosfernaeYvleniyByIdOsadkyNoh = atmosfernaeYvleniyByIdOsadkyNoh;
        this.atmosfernaeYvleniyByIdOsadkyUtro = atmosfernaeYvleniyByIdOsadkyUtro;
        this.atmosfernaeYvleniyByIdOsadkyDay = atmosfernaeYvleniyByIdOsadkyDay;
        this.atmosfernaeYvleniyByIdOsadkyVeher = atmosfernaeYvleniyByIdOsadkyVeher;
        this.veterByNapravlenieVetra = veterByNapravlenieVetra;
    }
    public PogodaEntity(int idPogoda,NaselennayPunktEntity naselennayPunktByIdPunkt,Date data, Integer tNoh, Integer tUtro, Integer tDay,
                        Integer tVeher, Integer vlagnosty, Integer davlenie,
                        Integer skorostyVetra, Integer temperaturaVoda,
                        AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyNoh, AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyUtro,
                        AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyDay, AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyVeher,
                        VeterEntity veterByNapravlenieVetra) {
        this.idPogoda=idPogoda;
        this.data = data;
        this.tNoh = tNoh;
        this.tUtro = tUtro;
        this.tDay = tDay;
        this.tVeher = tVeher;
        this.vlagnosty = vlagnosty;
        this.davlenie = davlenie;
        this.skorostyVetra = skorostyVetra;
        this.temperaturaVoda = temperaturaVoda;
        this.naselennayPunktByIdPunkt = naselennayPunktByIdPunkt;
        this.atmosfernaeYvleniyByIdOsadkyNoh = atmosfernaeYvleniyByIdOsadkyNoh;
        this.atmosfernaeYvleniyByIdOsadkyUtro = atmosfernaeYvleniyByIdOsadkyUtro;
        this.atmosfernaeYvleniyByIdOsadkyDay = atmosfernaeYvleniyByIdOsadkyDay;
        this.atmosfernaeYvleniyByIdOsadkyVeher = atmosfernaeYvleniyByIdOsadkyVeher;
        this.veterByNapravlenieVetra = veterByNapravlenieVetra;
    }

    public PogodaEntity() {
    }

    @Id
    @Column(name = "id_pogoda", nullable = false)
    public int getIdPogoda() {
        return idPogoda;
    }

    public void setIdPogoda(int idPogoda) {
        this.idPogoda = idPogoda;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "Data_", nullable = false)
    public  java.util.Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "t_noh", nullable = true)
    public Integer gettNoh() {
        return tNoh;
    }

    public void settNoh(Integer tNoh) {
        this.tNoh = tNoh;
    }

    @Basic
    @Column(name = "t_utro", nullable = true)
    public Integer gettUtro() {
        return tUtro;
    }

    public void settUtro(Integer tUtro) {
        this.tUtro = tUtro;
    }

    @Basic
    @Column(name = "t_day", nullable = true)
    public Integer gettDay() {
        return tDay;
    }

    public void settDay(Integer tDay) {
        this.tDay = tDay;
    }

    @Basic
    @Column(name = "t_veher", nullable = true)
    public Integer gettVeher() {
        return tVeher;
    }

    public void settVeher(Integer tVeher) {
        this.tVeher = tVeher;
    }

    @Basic
    @Column(name = "Vlagnosty", nullable = true)
    public Integer getVlagnosty() {
        return vlagnosty;
    }

    public void setVlagnosty(Integer vlagnosty) {
        this.vlagnosty = vlagnosty;
    }

    @Basic
    @Column(name = "Davlenie", nullable = true)
    public Integer getDavlenie() {
        return davlenie;
    }

    public void setDavlenie(Integer davlenie) {
        this.davlenie = davlenie;
    }

    @Basic
    @Column(name = "Skorosty_vetra", nullable = true)
    public Integer getSkorostyVetra() {
        return skorostyVetra;
    }

    public void setSkorostyVetra(Integer skorostyVetra) {
        this.skorostyVetra = skorostyVetra;
    }

    @Basic
    @Column(name = "Temperatura_voda", nullable = true)
    public Integer getTemperaturaVoda() {
        return temperaturaVoda;
    }

    public void setTemperaturaVoda(Integer temperaturaVoda) {
        this.temperaturaVoda = temperaturaVoda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PogodaEntity that = (PogodaEntity) o;

        if (idPogoda != that.idPogoda) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (tNoh != null ? !tNoh.equals(that.tNoh) : that.tNoh != null) return false;
        if (tUtro != null ? !tUtro.equals(that.tUtro) : that.tUtro != null) return false;
        if (tDay != null ? !tDay.equals(that.tDay) : that.tDay != null) return false;
        if (tVeher != null ? !tVeher.equals(that.tVeher) : that.tVeher != null) return false;
        if (vlagnosty != null ? !vlagnosty.equals(that.vlagnosty) : that.vlagnosty != null) return false;
        if (davlenie != null ? !davlenie.equals(that.davlenie) : that.davlenie != null) return false;
        if (skorostyVetra != null ? !skorostyVetra.equals(that.skorostyVetra) : that.skorostyVetra != null)
            return false;
        if (temperaturaVoda != null ? !temperaturaVoda.equals(that.temperaturaVoda) : that.temperaturaVoda != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPogoda;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (tNoh != null ? tNoh.hashCode() : 0);
        result = 31 * result + (tUtro != null ? tUtro.hashCode() : 0);
        result = 31 * result + (tDay != null ? tDay.hashCode() : 0);
        result = 31 * result + (tVeher != null ? tVeher.hashCode() : 0);
        result = 31 * result + (vlagnosty != null ? vlagnosty.hashCode() : 0);
        result = 31 * result + (davlenie != null ? davlenie.hashCode() : 0);
        result = 31 * result + (skorostyVetra != null ? skorostyVetra.hashCode() : 0);
        result = 31 * result + (temperaturaVoda != null ? temperaturaVoda.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_punkt", referencedColumnName = "id_punkt", nullable = false)
    public NaselennayPunktEntity getNaselennayPunktByIdPunkt() {
        return naselennayPunktByIdPunkt;
    }

    public void setNaselennayPunktByIdPunkt(NaselennayPunktEntity naselennayPunktByIdPunkt) {
        this.naselennayPunktByIdPunkt = naselennayPunktByIdPunkt;
    }

    @ManyToOne
    @JoinColumn(name = "id_osadky_noh", referencedColumnName = "id_osadky")
    public AtmosfernaeYvleniyEntity getAtmosfernaeYvleniyByIdOsadkyNoh() {
        return atmosfernaeYvleniyByIdOsadkyNoh;
    }

    public void setAtmosfernaeYvleniyByIdOsadkyNoh(AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyNoh) {
        this.atmosfernaeYvleniyByIdOsadkyNoh = atmosfernaeYvleniyByIdOsadkyNoh;
    }

    @ManyToOne
    @JoinColumn(name = "id_osadky_utro", referencedColumnName = "id_osadky")
    public AtmosfernaeYvleniyEntity getAtmosfernaeYvleniyByIdOsadkyUtro() {
        return atmosfernaeYvleniyByIdOsadkyUtro;
    }

    public void setAtmosfernaeYvleniyByIdOsadkyUtro(AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyUtro) {
        this.atmosfernaeYvleniyByIdOsadkyUtro = atmosfernaeYvleniyByIdOsadkyUtro;
    }

    @ManyToOne
    @JoinColumn(name = "id_osadky_day", referencedColumnName = "id_osadky")
    public AtmosfernaeYvleniyEntity getAtmosfernaeYvleniyByIdOsadkyDay() {
        return atmosfernaeYvleniyByIdOsadkyDay;
    }

    public void setAtmosfernaeYvleniyByIdOsadkyDay(AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyDay) {
        this.atmosfernaeYvleniyByIdOsadkyDay = atmosfernaeYvleniyByIdOsadkyDay;
    }

    @ManyToOne
    @JoinColumn(name = "id_osadky_veher", referencedColumnName = "id_osadky")
    public AtmosfernaeYvleniyEntity getAtmosfernaeYvleniyByIdOsadkyVeher() {
        return atmosfernaeYvleniyByIdOsadkyVeher;
    }

    public void setAtmosfernaeYvleniyByIdOsadkyVeher(AtmosfernaeYvleniyEntity atmosfernaeYvleniyByIdOsadkyVeher) {
        this.atmosfernaeYvleniyByIdOsadkyVeher = atmosfernaeYvleniyByIdOsadkyVeher;
    }

    @ManyToOne
    @JoinColumn(name = "Napravlenie_vetra", referencedColumnName = "id_veter")
    public VeterEntity getVeterByNapravlenieVetra() {
        return veterByNapravlenieVetra;
    }

    public void setVeterByNapravlenieVetra(VeterEntity veterByNapravlenieVetra) {
        this.veterByNapravlenieVetra = veterByNapravlenieVetra;
    }

    public String toString(){
        return "idPogoda: "+idPogoda+" naselennayPunktByIdPunkt: "+naselennayPunktByIdPunkt.toString()+" Date: "+ data+ " tNoh: " + tNoh
        + " tUtro: " + tUtro+ " tDay: " + tDay+ " tVeher: " + tNoh+
        " vlagnosty: "+ vlagnosty+" davlenie: "+ davlenie+" skorostyVetra: " +skorostyVetra+
        " Veter: " +veterByNapravlenieVetra.toString()+" atmosfernaeYvleniy: " +atmosfernaeYvleniyByIdOsadkyNoh.toString()+
        " atmosfernaeYvleniy: "+ atmosfernaeYvleniyByIdOsadkyUtro.toString() +" atmosfernaeYvleniy: "+atmosfernaeYvleniyByIdOsadkyDay.toString()+
        " atmosfernaeYvleniy: "+ atmosfernaeYvleniyByIdOsadkyVeher.toString()+" temperaturaVoda: "+temperaturaVoda;
    }
}
