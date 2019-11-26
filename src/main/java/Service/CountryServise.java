package Service;


import Build.SessionFactory;
import InterfaceAll.InterfaceCountry;
import Tables.CountryEntity;
import Tables.RegionEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class CountryServise extends SessionFactory implements InterfaceCountry {
    public void addInformationCountry(CountryEntity Country) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session=getSession();
        session.save(Country);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<CountryEntity> getAllCountry() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();

        String sqlSelect="SELECT * FROM country";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(CountryEntity.class);
        List<CountryEntity> Country=query.list();

        //close session with a transaction
        closeTransactionSession();
        return Country;
    }

    public CountryEntity getCountryId(int id) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM CountryEntity WHERE idCountry=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List Country=query.list();
        CountryEntity CountryId=(CountryEntity)Country.get(0);
        closeTransactionSession();
        return CountryId;
    }

    public CountryEntity getCountryName (String nameCountry) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM CountryEntity WHERE nameCountry=:nameCountry";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("nameCountry",nameCountry);
        List country=query.list();
        CountryEntity countryName=(CountryEntity)country.get(0);
        closeTransactionSession();
        return countryName;
    }

    public void updateCountry(int idCountry, String nameCountry, RegionEntity regionByIdRegion) throws SQLException {
        Session session=openTransactionSession();
        String hqlupdate="UPDATE CountryEntity set idCountry=:idCountry, nameCountry=:nameCountry," +
                "regionByIdRegion=:regionByIdRegion  WHERE idCountry=:id";

        Query query = session.createQuery(hqlupdate);
        query.setParameter("idCountry", idCountry);
        query.setParameter("nameCountry", nameCountry);
        query.setParameter("regionByIdRegion", regionByIdRegion);
        query.setParameter("id", idCountry);

        int result = query.executeUpdate();
        System.out.println("Rows affected update: " + result);

        closeTransactionSession();
    }

    public void deleteInformationCountry(int idDel) throws SQLException {
        Session session=openTransactionSession();

        String hqlDelete="DELETE FROM CountryEntity WHERE idCountry= :idDel";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDel",idDel);

        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);

        closeTransactionSession();
    }
}
