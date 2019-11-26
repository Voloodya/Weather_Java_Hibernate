import Tables.PogodaEntity;
import javafx.scene.image.Image;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelTablePogoda extends AbstractTableModel {

    private int columnCount=16;
    private ArrayList<Object[]> dataArrayList;

    public ModelTablePogoda()
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
            case 10:return ImageIcon.class;
            case 11:return ImageIcon.class;
            case 12:return ImageIcon.class;
            case 13:return String.class;
            case 14:return ImageIcon.class;
            case 15:return String.class;
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
            case 1:return "Город";
            case 2:return "Дата";
            case 3:return "Температура " +
                    '\r'+ "ночь".toString();
            case 4:return "Температура утро";
            case 5:return "Температура день";
            case 6:return "Температура вечер";
            case 7:return "Давление";
            case 8:return "Влажность";
            case 9:return "Осадки ночь";
            case 10:return "Осадки утро";
            case 11:return "Осадки день";
            case 12:return "Осадки вечер";
            case 13:return "Скорость ветра";
            case 14:return "Направление ветра";
            case 15:return "Температура воды";
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
                    String.valueOf(pogoda.getNaselennayPunktByIdPunkt().getNameNaselenPunkt()),
                    String.valueOf(pogoda.getData()),
                    String.valueOf(pogoda.gettNoh()),
                    String.valueOf(pogoda.gettUtro()),
                    String.valueOf(pogoda.gettDay()),
                    String.valueOf(pogoda.gettVeher()),
                    String.valueOf(pogoda.getDavlenie()),
                    String.valueOf(pogoda.getVlagnosty()),
                    new ImageIcon(pogoda.getAtmosfernaeYvleniyByIdOsadkyNoh().getPicture()),
                    new ImageIcon(pogoda.getAtmosfernaeYvleniyByIdOsadkyUtro().getPicture()),
                    new ImageIcon(pogoda.getAtmosfernaeYvleniyByIdOsadkyDay().getPicture()),
                    new ImageIcon(pogoda.getAtmosfernaeYvleniyByIdOsadkyVeher().getPicture()),
                    String.valueOf(pogoda.getSkorostyVetra()),
                    new ImageIcon(pogoda.getVeterByNapravlenieVetra().getPicture()),
                    String.valueOf(pogoda.getTemperaturaVoda()),

            };
            addDate(row);
        }
    }
}
