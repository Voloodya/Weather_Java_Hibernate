import Tables.NaselennayPunktEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vladimir on 13.11.2017.
 */
public class TableNaselennayPunktMODEL extends AbstractTableModel {

    private int columnCount;
    private ArrayList<String[]> dataArrayList;

    public TableNaselennayPunktMODEL()
    {
        dataArrayList=new ArrayList<String[]>();

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
            case 2:return "Страна";
            case 3:return "Климатический " +
                    '\r'+ "пояс".toString();
        }
        return "";
    }

    //Удаление строки
    public void dellRow (String str)
    {
        for (int i=0;i<dataArrayList.size();i++) {
            String[] row;
            row=dataArrayList.get(i);
            if(row[0].equals(row)) dataArrayList.remove(i);
        }

    }

    public void addNaselennayPunkt(List<NaselennayPunktEntity> naselennayPunktAll){
        Iterator iterator=naselennayPunktAll.iterator();
        dataArrayList.clear();
        while (iterator.hasNext()){
            NaselennayPunktEntity naselennayPunkt;
            naselennayPunkt= (NaselennayPunktEntity) iterator.next();
            String [] row={
                    String.valueOf(naselennayPunkt.getIdPunkt()),
                    String.valueOf(naselennayPunkt.getNameNaselenPunkt()),
                    String.valueOf(naselennayPunkt.getCountryByIdCountry().getNameCountry()),
                    String.valueOf(naselennayPunkt.getClimatPoyasByIdPoyas().getNameClimatePoyas()),

            };
            addDate(row);
        }


    }
}
