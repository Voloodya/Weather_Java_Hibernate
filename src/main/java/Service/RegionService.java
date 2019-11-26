package Service;


import Build.SessionFactory;
import InterfaceAll.InterfaceRegion;
import Tables.RegionEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class RegionService extends SessionFactory implements InterfaceRegion{
    public void addInformationRegion(RegionEntity Region) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session=getSession();
        session.save(Region);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<RegionEntity> getAllRegion() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();

        String sqlSelect="SELECT * FROM region";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(RegionEntity.class);
        List<RegionEntity> Region=query.list();

        //close session with a transaction
        closeTransactionSession();
        return Region;
    }

    public RegionEntity getRegionId(int id) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM RegionEntity WHERE idRegion=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List Region=query.list();
        RegionEntity RegionId=(RegionEntity)Region.get(0);
        closeTransactionSession();
        return RegionId;
    }
    public RegionEntity getRegionName (String nameRegion) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM RegionEntity WHERE nameRegion=:nameRegion";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("nameRegion",nameRegion);
        List region=query.list();
        RegionEntity regionName=(RegionEntity)region.get(0);
        closeTransactionSession();
        return regionName;
    }

    public void updateRegion(int idRegion, String nameRegion,String opisanie) throws SQLException {
        Session session=openTransactionSession();
        String hqlupdate="UPDATE RegionEntity set idRegion=:idRegion, nameRegion=:nameRegion," +
                "opisanie=:opisanie WHERE idRegion=:id";

        Query query = session.createQuery(hqlupdate);
        query.setParameter("idRegion", idRegion);
        query.setParameter("nameRegion", nameRegion);
        query.setParameter("opisanie", opisanie);
        query.setParameter("id", idRegion);

        int result = query.executeUpdate();
        System.out.println("Rows affected update: " + result);

        closeTransactionSession();
    }

    public void deleteInformationRegion(int idDel) throws SQLException {
        Session session=openTransactionSession();

        String hqlDelete="DELETE FROM RegionEntity WHERE idRegion= :idDel";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDel",idDel);

        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);

        closeTransactionSession();
    }
}
