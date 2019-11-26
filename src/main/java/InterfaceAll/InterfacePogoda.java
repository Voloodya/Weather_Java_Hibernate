package InterfaceAll;

import Tables.AtmosfernaeYvleniyEntity;
import Tables.NaselennayPunktEntity;
import Tables.PogodaEntity;
import Tables.VeterEntity;
import com.mysql.fabric.xmlrpc.base.Data;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * Created by Vladimir on 30.10.2017.
 */
public interface InterfacePogoda {

    public void addInformationPogoda(PogodaEntity pogoda) throws SQLException;
    public List<PogodaEntity> getAllPogoda() throws SQLException;
    public PogodaEntity getPogodaId(int id) throws SQLException;
    public void updatePogoda(int idPogoda,Date date, int tNoh, int tUtro, int tDay, int tVeher,
                             int vlagnosty, int davlenie, AtmosfernaeYvleniyEntity atmosfernaeYvleniyNoh,
                             AtmosfernaeYvleniyEntity atmosfernaeYvleniyUtro, AtmosfernaeYvleniyEntity atmosfernaeYvleniyDay,
                             AtmosfernaeYvleniyEntity atmosfernaeYvleniyVeher, int skorostyVetra, VeterEntity napravlenieVetra, int temperaturaVoda) throws SQLException;
    public void deleteInformationPogoda(int idDel) throws SQLException;


}
