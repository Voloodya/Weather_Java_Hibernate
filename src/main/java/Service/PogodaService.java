package Service;

import Build.SessionFactory;
import InterfaceAll.InterfacePogoda;
import Tables.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class PogodaService extends SessionFactory implements InterfacePogoda {

    public void addInformationPogoda(PogodaEntity pogoda) throws SQLException {
        //open session with a transaction
        openTransactionSession();
        Session session=getSession();
        session.save(pogoda);
        System.out.println("Rows affected add: unknown" );
        //close session with a transaction
        closeTransactionSession();
    }

    public List<PogodaEntity> getAllPogoda() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();
        String sqlSelect="SELECT * FROM POGODA";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(PogodaEntity.class);
        List<PogodaEntity> pogodaList=query.list();
        //close session with a transaction
        closeTransactionSession();
        return pogodaList;
    }
    public List<PogodaEntity> getPogodaCountry(CountryEntity country) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.countryByIdCountry=:country";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("country",country);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaCountryData(CountryEntity country,Date data) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.countryByIdCountry=:country AND data=:data";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("country",country);
        query.setParameter("data",data);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaRegion(RegionEntity region) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.countryByIdCountry.regionByIdRegion=:region";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("region",region);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaRegionData(RegionEntity region,Date data) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.countryByIdCountry.regionByIdRegion=:region AND data=:data";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("region",region);
        query.setParameter("data",data);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaClimate(ClimatPoyasEntity poyas) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.climatPoyasByIdPoyas=:poyas";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("poyas",poyas);
        List pogoda=query.list();

        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaClimateData(ClimatPoyasEntity poyas,Date data) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.climatPoyasByIdPoyas=:poyas AND data=:data";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("poyas",poyas);
        query.setParameter("data",data);
        List pogoda=query.list();

        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaClimateRegion(ClimatPoyasEntity poyas,RegionEntity region) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.climatPoyasByIdPoyas=:poyas AND naselennayPunktByIdPunkt.countryByIdCountry.regionByIdRegion=:region";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("poyas",poyas);
        query.setParameter("region",region);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaClimateRegionDate(ClimatPoyasEntity poyas,RegionEntity region,Date date) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.climatPoyasByIdPoyas=:poyas AND naselennayPunktByIdPunkt.countryByIdCountry.regionByIdRegion=:region AND data=:date";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("poyas",poyas);
        query.setParameter("region",region);
        query.setParameter("date",date);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaCountryClimateRegionDate(CountryEntity country,ClimatPoyasEntity poyas,RegionEntity region,Date date) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.countryByIdCountry=:country AND naselennayPunktByIdPunkt.climatPoyasByIdPoyas=:poyas AND naselennayPunktByIdPunkt.countryByIdCountry.regionByIdRegion=:region AND data=:date";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("country",country);
        query.setParameter("poyas",poyas);
        query.setParameter("region",region);
        query.setParameter("date",date);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaCountryClimateRegion(CountryEntity country,ClimatPoyasEntity poyas,RegionEntity region) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.countryByIdCountry=:country AND naselennayPunktByIdPunkt.climatPoyasByIdPoyas=:poyas AND naselennayPunktByIdPunkt.countryByIdCountry.regionByIdRegion=:region";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("country",country);
        query.setParameter("poyas",poyas);
        query.setParameter("region",region);
        List pogoda=query.list();
        closeTransactionSession();
        return pogoda;
    }
    public PogodaEntity getPogodaId(int id) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE idPogoda=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List pogoda=query.list();
        PogodaEntity pogodaId=(PogodaEntity)pogoda.get(0);
        closeTransactionSession();
        return pogodaId;
    }
    public List<PogodaEntity> getPogodaNameCity (String nameCity) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.nameNaselenPunkt=:nameCity";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("nameCity",nameCity);
        List pogoda=query.list();
        //PogodaEntity pogodaName=(PogodaEntity)pogoda.get(0);
        closeTransactionSession();
        return pogoda;
    }
    public List<PogodaEntity> getPogodaCityDate (String nameCity,Date data) throws SQLException {
        Session session=openTransactionSession();
        String hqlSelect="FROM PogodaEntity WHERE naselennayPunktByIdPunkt.nameNaselenPunkt=:nameCity AND data=:data";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("nameCity",nameCity);
        query.setParameter("data",data);
        List pogoda=query.list();

        closeTransactionSession();
        return pogoda;
    }

    public void updatePogoda(int idPogoda,Date date, int tNoh, int tUtro, int tDay, int tVeher,
                             int vlagnosty, int davlenie, AtmosfernaeYvleniyEntity atmosfernaeYvleniyNoh,
                             AtmosfernaeYvleniyEntity atmosfernaeYvleniyUtro, AtmosfernaeYvleniyEntity atmosfernaeYvleniyDay,
                             AtmosfernaeYvleniyEntity atmosfernaeYvleniyVeher, int skorostyVetra, VeterEntity napravlenieVetra, int temperaturaVoda) throws SQLException {
        Session session=openTransactionSession();
        String hqlupdate="UPDATE PogodaEntity set data=:date," +
        " tNoh=:tNoh, tUtro=:tUtro, tDay=:tDay, tVeher=:tVeher, " +
        "vlagnosty=:vlagnosty, davlenie=:davlenie, atmosfernaeYvleniyByIdOsadkyNoh=:atmosfernaeYvleniyNoh," +
        "atmosfernaeYvleniyByIdOsadkyUtro=:atmosfernaeYvleniyUtro, atmosfernaeYvleniyByIdOsadkyDay=:atmosfernaeYvleniyDay, " +
        "atmosfernaeYvleniyByIdOsadkyVeher=:atmosfernaeYvleniyVeher, skorostyVetra=:skorostyVetra, " +
        "veterByNapravlenieVetra=:napravlenieVetra, temperaturaVoda=:temperaturaVoda  WHERE idPogoda=:idPogoda";

        Query query = session.createQuery(hqlupdate);
        query.setParameter("date", date);
        query.setParameter("tNoh", tNoh);
        query.setParameter("tUtro", tUtro);
        query.setParameter("tDay", tDay);
        query.setParameter("tVeher", tVeher);
        query.setParameter("vlagnosty", vlagnosty);
        query.setParameter("davlenie", davlenie);
        query.setParameter("atmosfernaeYvleniyNoh", atmosfernaeYvleniyNoh);
        query.setParameter("atmosfernaeYvleniyUtro", atmosfernaeYvleniyUtro);
        query.setParameter("atmosfernaeYvleniyDay", atmosfernaeYvleniyDay);
        query.setParameter("atmosfernaeYvleniyVeher", atmosfernaeYvleniyVeher);
        query.setParameter("skorostyVetra", skorostyVetra);
        query.setParameter("napravlenieVetra", napravlenieVetra);
        query.setParameter("temperaturaVoda", temperaturaVoda);
        query.setParameter("idPogoda", idPogoda);
        int result = query.executeUpdate();
        System.out.println("Rows affected update: " + result);
        closeTransactionSession();
    }

    public  void updatePogoda(PogodaEntity pogoda) throws SQLException
    {
        Session session=openTransactionSession();
        session.saveOrUpdate(pogoda);
        //close session with a transaction
        closeTransactionSession();
    }
    public void deleteInformationPogoda(int idDel) throws SQLException {
        Session session=openTransactionSession();
        String hqlDelete="DELETE FROM PogodaEntity WHERE idPogoda = :idDell";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDell",idDel);
        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);
        closeTransactionSession();
    }
}
