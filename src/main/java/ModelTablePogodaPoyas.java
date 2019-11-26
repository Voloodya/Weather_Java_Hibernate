import Tables.PogodaEntity;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelTablePogodaPoyas extends AbstractTableModel {
    private int columnCount=11;
    private ArrayList<Object[]> dataArrayList;

    public ModelTablePogodaPoyas()
    {
        dataArrayList=new ArrayList<Object[]>();
    }

    //Возвращает количество строк в таблице
    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    //Возвращает количество колонок в таблице
    @Override
    public int getColumnCount() {
        return columnCount;
    }
    //Возвращает значение определнной ячейки таблицы, по номеру стр и номеру колонки
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object [] rows=dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }
    // Тип хранимых в столбцах данных
    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:return String.class;
            case 1:return String.class;
            case 2:return String.class;
            case 3:return String.class;
            case 4:return String.class;
            case 5:return String.class;
            case 6:return String.class;
            case 7:return String.class;
            case 8:return String.class;
            case 9:return ImageIcon.class;
            case 10:return String.class;
            default: return Object.class;
        }
    }

    //М-д добавляющи данные в таблицу
    public void addDate(Object[] row)
    {
        Object [] rowTable=new String[getColumnCount()];
        rowTable=row;
        dataArrayList.add(rowTable);
    }
    //Установка наименований столбцов
    @Override
    public String getColumnName(int indexColumn)
    {
        switch (indexColumn){
            case 0:return "#id";
            case 1:return "Климатический пояс";
            case 2:return "Регион";
            case 3:return "Страна";
            case 4:return "Населённый " +
                    '\r'+ "пункт".toString();
            case 5:return "Дата";
            case 6:return "Среднесуточная температура";
            case 7:return "Влажность";
            case 8:return "Скорость ветра";
            case 9:return "Направление ветра";
            case 10:return "Температура воды";
        }
        return "";
    }

    //Удаление строки
    public void dellRow (Object str)
    {
        for (int i=0;i<dataArrayList.size();i++) {
            Object[] row = new Object[16];
            row=dataArrayList.get(i);
            if(row[0].equals(row)) dataArrayList.remove(i);
        }

    }

    public void addPogoda(List<PogodaEntity> pogodaAll){
        Iterator iterator=pogodaAll.iterator();
        dataArrayList.clear();
        while (iterator.hasNext()){
            PogodaEntity pogoda;
            pogoda= (PogodaEntity) iterator.next();
            Object [] row={
                    String.valueOf(pogoda.getIdPogoda()),
                    String.valueOf(pogoda.getNaselennayPunktByIdPunkt().getClimatPoyasByIdPoyas().getNameClimatePoyas()),
                    String.valueOf(pogoda.getNaselennayPunktByIdPunkt().getCountryByIdCountry().getRegionByIdRegion().getNameRegion()),
                    String.valueOf(pogoda.getNaselennayPunktByIdPunkt().getCountryByIdCountry().getNameCountry()),
                    String.valueOf(pogoda.getNaselennayPunktByIdPunkt().getNameNaselenPunkt()),
                    String.valueOf(pogoda.getData()),
                    String.valueOf((pogoda.gettNoh()+pogoda.gettUtro()+pogoda.gettDay()+pogoda.gettVeher())/4),
                    String.valueOf(pogoda.getVlagnosty()),
                    String.valueOf(pogoda.getSkorostyVetra()),
                    new ImageIcon(pogoda.getVeterByNapravlenieVetra().getPicture()),
                    String.valueOf(pogoda.getTemperaturaVoda()),

            };
            addDate(row);
        }
    }
}
