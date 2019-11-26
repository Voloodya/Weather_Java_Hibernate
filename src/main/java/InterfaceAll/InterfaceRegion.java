package InterfaceAll;

import Tables.RegionEntity;
import java.sql.SQLException;
import java.util.List;


public interface InterfaceRegion {

    public void addInformationRegion (RegionEntity Region) throws SQLException;
    public List<RegionEntity> getAllRegion() throws SQLException;
    public RegionEntity getRegionId(int id) throws SQLException;
    public void updateRegion(int idRegion, String nameRegion,String opisanie) throws SQLException;
    public void deleteInformationRegion(int idDel) throws SQLException;
}
