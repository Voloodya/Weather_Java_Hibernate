package Service;


import Build.SessionFactory;
import InterfaceAll.InterfaceAtmosferYvleniy;
import Tables.AtmosfernaeYvleniyEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class AtmosfernaeYvleniyServise extends SessionFactory implements InterfaceAtmosferYvleniy{
    public void addInformationAtmosfernYvleniy(AtmosfernaeYvleniyEntity AtmoserYvleniy) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session=getSession();
        session.save(AtmoserYvleniy);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<AtmosfernaeYvleniyEntity> getAllAtmosfernaeYvleniy() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();

        String sqlSelect="SELECT * FROM atmosfernae_yvleniy";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(AtmosfernaeYvleniyEntity.class);
        List<AtmosfernaeYvleniyEntity> AtmosfernaeYvleniy=query.list();

        //close session with a transaction
        closeTransactionSession();
        return AtmosfernaeYvleniy;
    }

    public AtmosfernaeYvleniyEntity getAtmoserYvleniyId(int id) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM AtmosfernaeYvleniyEntity WHERE idOsadky=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List AtmosfernaeYvleniy=query.list();
        AtmosfernaeYvleniyEntity AtmosfernaeYvleniyId=(AtmosfernaeYvleniyEntity)AtmosfernaeYvleniy.get(0);
        closeTransactionSession();
        return AtmosfernaeYvleniyId;
    }
    public AtmosfernaeYvleniyEntity getAtmoserYvleniyName (String vidYvleniy) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM AtmosfernaeYvleniyEntity WHERE vidYvleniy=:vidYvleniy";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("vidYvleniy",vidYvleniy);
        List AtmoserYvleniy=query.list();
        AtmosfernaeYvleniyEntity AtmoserYvleniyName=(AtmosfernaeYvleniyEntity)AtmoserYvleniy.get(0);
        closeTransactionSession();
        return AtmoserYvleniyName;
    }

    public void updateAtmosfernaeYvleniy(int idOsadky,String vidYvleniy,String picture) throws SQLException {
        Session session=openTransactionSession();
        String hqlupdate="UPDATE AtmosfernaeYvleniyEntity set idOsadky=:idOsadky, vidYvleniy=:vidYvleniy," +
                "picture=:picture  WHERE idOsadky=:id";

        Query query = session.createQuery(hqlupdate);
        query.setParameter("idOsadky", idOsadky);
        query.setParameter("vidYvleniy", vidYvleniy);
        query.setParameter("picture", picture);
        query.setParameter("id", idOsadky);

        int result = query.executeUpdate();
        System.out.println("Rows affected update: " + result);

        closeTransactionSession();
    }

    public void deleteInformationAtmosfernaeYvleniy(int idDel) throws SQLException {
        Session session=openTransactionSession();

        String hqlDelete="DELETE FROM AtmosfernaeYvleniyEntity WHERE idOsadky= :idDel";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDel",idDel);

        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);

        closeTransactionSession();
    }
}
