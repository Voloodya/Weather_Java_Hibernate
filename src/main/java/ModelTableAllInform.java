import Tables.PogodaEntity;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelTableAllInform extends AbstractTableModel {
    private int columnCount=20;
    private ArrayList<Object[]> dataArrayList;

    public ModelTableAllInform()
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
            case 9:return String.class;
            case 10:return String.class;
            case 11:return String.class;
            case 12:return String.class;
            case 13:return ImageIcon.class;
            case 14:return ImageIcon.class;
            case 15:return ImageIcon.class;
            case 16:return ImageIcon.class;
            case 17:return String.class;
            case 18:return ImageIcon.class;
            case 19:return String.class;
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
            case 1:return "Климат";
            case 2:return "Описание климата";
            case 3:return "Регион";
            case 4:return "Описание региона";
            case 5:return "Город";
            case 6:return "Дата";
            case 7:return "Температура " +
                    '\r'+ "ночь".toString();
            case 8:return "Температура утро";
            case 9:return "Температура день";
            case 10:return "Температура вечер";
            case 11:return "Давление";
            case 12:return "Влажность";
            case 13:return "Осадки ночь";
            case 14:return "Осадки утро";
            case 15:return "Осадки день";
            case 16:return "Осадки вечер";
            case 17:return "Скорость ветра";
            case 18:return "Направление ветра";
            case 19:return "Температура воды";
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

    public void addSinglPogoda(PogodaEntity singlPogoda){

            Object [] row={
                    String.valueOf(singlPogoda.getIdPogoda()),
                    String.valueOf(singlPogoda.getNaselennayPunktByIdPunkt().getClimatPoyasByIdPoyas().getNameClimatePoyas()),
                    String.valueOf(singlPogoda.getNaselennayPunktByIdPunkt().getClimatPoyasByIdPoyas().getOpisanie()),
                    String.valueOf(singlPogoda.getNaselennayPunktByIdPunkt().getCountryByIdCountry().getRegionByIdRegion().getNameRegion()),
                    String.valueOf(singlPogoda.getNaselennayPunktByIdPunkt().getCountryByIdCountry().getRegionByIdRegion().getOpisanie()),
                    String.valueOf(singlPogoda.getNaselennayPunktByIdPunkt().getNameNaselenPunkt()),
                    String.valueOf(singlPogoda.getData()),
                    String.valueOf(singlPogoda.gettNoh()),
                    String.valueOf(singlPogoda.gettUtro()),
                    String.valueOf(singlPogoda.gettDay()),
                    String.valueOf(singlPogoda.gettVeher()),
                    String.valueOf(singlPogoda.getDavlenie()),
                    String.valueOf(singlPogoda.getVlagnosty()),
                    new ImageIcon(singlPogoda.getAtmosfernaeYvleniyByIdOsadkyNoh().getPicture()),
                    new ImageIcon(singlPogoda.getAtmosfernaeYvleniyByIdOsadkyUtro().getPicture()),
                    new ImageIcon(singlPogoda.getAtmosfernaeYvleniyByIdOsadkyDay().getPicture()),
                    new ImageIcon(singlPogoda.getAtmosfernaeYvleniyByIdOsadkyVeher().getPicture()),
                    String.valueOf(singlPogoda.getSkorostyVetra()),
                    new ImageIcon(singlPogoda.getVeterByNapravlenieVetra().getPicture()),
                    String.valueOf(singlPogoda.getTemperaturaVoda()),
            };
            addDate(row);
    }
}
