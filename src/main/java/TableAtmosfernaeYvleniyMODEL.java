import Tables.AtmosfernaeYvleniyEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vladimir on 13.11.2017.
 */
public class TableAtmosfernaeYvleniyMODEL extends AbstractTableModel {

    private int columnCount;
    private ArrayList<String[]> dataArrayList;

    public TableAtmosfernaeYvleniyMODEL()
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
            case 1:return "Вид атмосферного явления";
            case 2:return "Изображение";
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

    public void addAtmosferYvleniy(List<AtmosfernaeYvleniyEntity> atmosfernaeYvleniyAll){
        Iterator iterator=atmosfernaeYvleniyAll.iterator();
        dataArrayList.clear();
        while (iterator.hasNext()){
            AtmosfernaeYvleniyEntity atmosfernaeYvleniy;
            atmosfernaeYvleniy= (AtmosfernaeYvleniyEntity) iterator.next();
            String [] row={
                    String.valueOf(atmosfernaeYvleniy.getIdOsadky()),
                    String.valueOf(atmosfernaeYvleniy.getVidYvleniy()),
                    String.valueOf(atmosfernaeYvleniy.getPicture()),
            };
            addDate(row);
        }


    }
}
