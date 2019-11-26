package Service;


import Build.SessionFactory;
import InterfaceAll.InterfaceClimatPoyas;
import Tables.ClimatPoyasEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ClimatPoyasService extends SessionFactory implements InterfaceClimatPoyas {
    public void addInformationClimatPoyas(ClimatPoyasEntity ClimatPoyas) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session=getSession();
        session.save(ClimatPoyas);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<ClimatPoyasEntity> getAllClimatPoyas() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();

        String sqlSelect="SELECT * FROM climat_poyas";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(ClimatPoyasEntity.class);
        List<ClimatPoyasEntity> ClimatPoyas=query.list();

        //close session with a transaction
        closeTransactionSession();
        return ClimatPoyas;
    }

    public ClimatPoyasEntity getClimatPoyasId(int id) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM ClimatPoyasEntity WHERE idPoyas=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List ClimatPoyas=query.list();
        ClimatPoyasEntity ClimatPoyasId=(ClimatPoyasEntity)ClimatPoyas.get(0);
        closeTransactionSession();
        return ClimatPoyasId;
    }
    public ClimatPoyasEntity getClimatPoyasName (String climatPoyasName) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM ClimatPoyasEntity WHERE nameClimatePoyas=:ClimatPoyasName";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("ClimatPoyasName",climatPoyasName);
        List poyas=query.list();
        ClimatPoyasEntity poyasName=(ClimatPoyasEntity)poyas.get(0);
        closeTransactionSession();
        return poyasName;
    }

    public void updateClimatPoyas(int idPoyas,String nameClimatePoyas,String opisanie) throws SQLException {
        Session session=openTransactionSession();
        String hqlupdate="UPDATE ClimatPoyasEntity set idPoyas=:idPoyas, nameClimatePoyas=:nameClimatePoyas," +
                "opisanie=:opisanie  WHERE idPoyas=:id";

        Query query = session.createQuery(hqlupdate);
        query.setParameter("idPoyas", idPoyas);
        query.setParameter("nameClimatePoyas", nameClimatePoyas);
        query.setParameter("opisanie", opisanie);
        query.setParameter("id", idPoyas);

        int result = query.executeUpdate();
        System.out.println("Rows affected update: " + result);

        closeTransactionSession();
    }

    public void deleteInformationClimatPoyas(int idDel) throws SQLException {
        Session session=openTransactionSession();

        String hqlDelete="DELETE FROM ClimatPoyasEntity WHERE idPoyas= :idDel";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDel",idDel);

        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);

        closeTransactionSession();
    }
}
