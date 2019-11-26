package InterfaceAll;

import Tables.VeterEntity;
import java.sql.SQLException;
import java.util.List;

public interface InterfaseVeter {

    public void addInformationVeter (VeterEntity Veter) throws SQLException;
    public List<VeterEntity> getAllVeter() throws SQLException;
    public VeterEntity getVeterId(int id) throws SQLException;
    public void updateVeter(int idVeter,String napravlenieVetra,String picture) throws SQLException;
    public void deleteInformationVeter(int idDel) throws SQLException;
}
