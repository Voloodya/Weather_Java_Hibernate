package InterfaceAll;

import Tables.AtmosfernaeYvleniyEntity;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Vladimir on 01.11.2017.
 */
public interface InterfaceAtmosferYvleniy {

    public void addInformationAtmosfernYvleniy(AtmosfernaeYvleniyEntity AtmoserYvleniy) throws SQLException;
    public List<AtmosfernaeYvleniyEntity> getAllAtmosfernaeYvleniy() throws SQLException;
    public AtmosfernaeYvleniyEntity getAtmoserYvleniyId(int id) throws SQLException;
    public void updateAtmosfernaeYvleniy(int idOsadky,String vidYvleniy,String picture) throws SQLException;
    public void deleteInformationAtmosfernaeYvleniy(int idDel) throws SQLException;
}
