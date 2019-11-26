import Tables.PogodaEntity;
import org.hibernate.Session;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vladimir on 08.10.2017.
 */

    //Модель таблицы
class Table_pogoda_Model extends AbstractTableModel {

    private int columnCount=16;
    private ArrayList<String[]> dataArrayList;

    public Table_pogoda_Model()
    {
        dataArrayList=new ArrayList<String[]>();
        //dataArrayList.add(new String[getColumnCount()]);
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
        String [] rows=dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    //М-д добавляющи данные в таблицу
    public void addDate(String[] row)
    {
        String [] rowTable=new String[getColumnCount()];
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
    public void dellRow (String str)
    {
        for (int i=0;i<dataArrayList.size();i++) {
            String[] row = new String[5];
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
        String [] row={
                String.valueOf(pogoda.getIdPogoda()),
                String.valueOf(pogoda.getNaselennayPunktByIdPunkt().getNameNaselenPunkt()),
                String.valueOf(pogoda.getData()),
                String.valueOf(pogoda.gettNoh()),
                String.valueOf(pogoda.gettUtro()),
                String.valueOf(pogoda.gettDay()),
                String.valueOf(pogoda.gettVeher()),
                String.valueOf(pogoda.getDavlenie()),
                String.valueOf(pogoda.getVlagnosty()),
                String.valueOf(pogoda.getAtmosfernaeYvleniyByIdOsadkyNoh().getVidYvleniy()),
                String.valueOf(pogoda.getAtmosfernaeYvleniyByIdOsadkyUtro().getVidYvleniy()),
                String.valueOf(pogoda.getAtmosfernaeYvleniyByIdOsadkyDay().getVidYvleniy()),
                String.valueOf(pogoda.getAtmosfernaeYvleniyByIdOsadkyVeher().getVidYvleniy()),
                String.valueOf(pogoda.getSkorostyVetra()),
                String.valueOf(pogoda.getVeterByNapravlenieVetra().getNapravlenieVetra()),
                String.valueOf(pogoda.getTemperaturaVoda()),

        };
            addDate(row);
    }
  }

}
