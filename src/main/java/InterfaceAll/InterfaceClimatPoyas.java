package InterfaceAll;

import Tables.ClimatPoyasEntity;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceClimatPoyas {

    public void addInformationClimatPoyas(ClimatPoyasEntity ClimatPoyas) throws SQLException;
    public List<ClimatPoyasEntity> getAllClimatPoyas() throws SQLException;
    public ClimatPoyasEntity getClimatPoyasId(int id) throws SQLException;
    public void updateClimatPoyas(int idPoyas,String nameClimatePoyas,String opisanie) throws SQLException;
    public void deleteInformationClimatPoyas(int idDel) throws SQLException;
}
