package Service;


import Build.SessionFactory;
import InterfaceAll.InterfaceNaselennayPunkt;
import Tables.ClimatPoyasEntity;
import Tables.CountryEntity;
import Tables.NaselennayPunktEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class NaselennayPunktService extends SessionFactory implements InterfaceNaselennayPunkt {
    public void addInformationNaselennayPunkt(NaselennayPunktEntity NaselennayPunkt) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session=getSession();
        session.save(NaselennayPunkt);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<NaselennayPunktEntity> getAllNaselennayPunkt() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();

        String sqlSelect="SELECT * FROM naselennay_punkt";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(NaselennayPunktEntity.class);
        List<NaselennayPunktEntity> NaselennayPunkt=query.list();

        //close session with a transaction
        closeTransactionSession();
        return NaselennayPunkt;
    }

    public NaselennayPunktEntity getNaselennayPunktId(int id) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM NaselennayPunktEntity WHERE idPunkt=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List NaselennayPunkt=query.list();
        NaselennayPunktEntity NaselennayPunktId=(NaselennayPunktEntity)NaselennayPunkt.get(0);
        closeTransactionSession();
        return NaselennayPunktId;
    }
    public NaselennayPunktEntity getNaselennayPunktName (String nameNaselennayPunkt) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM NaselennayPunktEntity WHERE nameNaselenPunkt=:nameNaselennayPunkt";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("nameNaselennayPunkt",nameNaselennayPunkt);
        List NaselennayPunkt=query.list();
        NaselennayPunktEntity NaselennayPunktName=(NaselennayPunktEntity)NaselennayPunkt.get(0);
        closeTransactionSession();
        return NaselennayPunktName;
    }

    public void updateNaselennayPunkt(int idPunkt, String nameNaselenPunkt, CountryEntity countryByIdCountry, ClimatPoyasEntity climatPoyasByIdPoyas) throws SQLException {
        Session session=openTransactionSession();
        String hqlupdate="UPDATE NaselennayPunktEntity set idPunkt=:idPunkt, nameNaselenPunkt=:nameNaselenPunkt," +
                "countryByIdCountry=:countryByIdCountry, climatPoyasByIdPoyas=:climatPoyasByIdPoyas  WHERE idPunkt=:id";

        Query query = session.createQuery(hqlupdate);
        query.setParameter("idPunkt", idPunkt);
        query.setParameter("nameNaselenPunkt", nameNaselenPunkt);
        query.setParameter("countryByIdCountry", countryByIdCountry);
        query.setParameter("climatPoyasByIdPoyas", climatPoyasByIdPoyas);
        query.setParameter("id", climatPoyasByIdPoyas);

        int result = query.executeUpdate();
        System.out.println("Rows affected update: " + result);

        closeTransactionSession();
    }

    public void deleteInformationNaselennayPunkt(int idDel) throws SQLException {
        Session session=openTransactionSession();

        String hqlDelete="DELETE FROM NaselennayPunktEntity WHERE idPunkt= :idDel";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDel",idDel);

        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);

        closeTransactionSession();
    }
}
