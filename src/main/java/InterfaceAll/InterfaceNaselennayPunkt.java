package InterfaceAll;

import Tables.ClimatPoyasEntity;
import Tables.CountryEntity;
import Tables.NaselennayPunktEntity;
import java.sql.SQLException;
import java.util.List;


public interface InterfaceNaselennayPunkt {

    public void addInformationNaselennayPunkt(NaselennayPunktEntity NaselennayPunkt) throws SQLException;
    public List<NaselennayPunktEntity> getAllNaselennayPunkt() throws SQLException;
    public NaselennayPunktEntity getNaselennayPunktId(int id) throws SQLException;
    public void updateNaselennayPunkt(int idPunkt, String nameNaselenPunkt, CountryEntity countryByIdCountry, ClimatPoyasEntity climatPoyasByIdPoyas) throws SQLException;
    public void deleteInformationNaselennayPunkt(int idDel) throws SQLException;
}
