package Service;


import Build.SessionFactory;
import InterfaceAll.InterfaseVeter;
import Tables.VeterEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class VeterService extends SessionFactory implements InterfaseVeter{
    public void addInformationVeter(VeterEntity Veter) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session=getSession();
        session.save(Veter);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<VeterEntity> getAllVeter() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();

        String sqlSelect="SELECT * FROM veter";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(VeterEntity.class);
        List<VeterEntity> Veter=query.list();

        //close session with a transaction
        closeTransactionSession();
        return Veter;
    }

    public VeterEntity getVeterId(int id) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM VeterEntity WHERE idVeter=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List Veter=query.list();
        VeterEntity VeterId=(VeterEntity)Veter.get(0);
        closeTransactionSession();
        return VeterId;
    }
    public VeterEntity getVeterName (String nameVeter) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM VeterEntity WHERE napravlenieVetra=:nameVeter";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("nameVeter",nameVeter);
        List veter=query.list();
        VeterEntity veterName=(VeterEntity)veter.get(0);
        closeTransactionSession();
        return veterName;
    }

    public void updateVeter(int idVeter,String napravlenieVetra,String picture) throws SQLException {
        Session session=openTransactionSession();
        String hqlupdate="UPDATE VeterEntity set idVeter=:idVeter, napravlenieVetra=:napravlenieVetra," +
                "picture=:picture WHERE idVeter=:id";

        Query query = session.createQuery(hqlupdate);
        query.setParameter("idVeter", idVeter);
        query.setParameter("napravlenieVetra", napravlenieVetra);
        query.setParameter("picture", picture);
        query.setParameter("id", idVeter);

        int result = query.executeUpdate();
        System.out.println("Rows affected update: " + result);

        closeTransactionSession();
    }

    public void deleteInformationVeter(int idDel) throws SQLException {
        Session session=openTransactionSession();

        String hqlDelete="DELETE FROM VeterEntity WHERE idVeter= :idDel";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDel",idDel);

        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);

        closeTransactionSession();
    }
}
