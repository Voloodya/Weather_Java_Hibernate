package InterfaceAll;

import Tables.CountryEntity;
import Tables.RegionEntity;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceCountry {

    public void addInformationCountry(CountryEntity Country) throws SQLException;
    public List<CountryEntity> getAllCountry() throws SQLException;
    public CountryEntity getCountryId(int id) throws SQLException;
    public void updateCountry(int idCountry, String nameCountry, RegionEntity regionByIdRegion) throws SQLException;
    public void deleteInformationCountry(int idDel) throws SQLException;
}
