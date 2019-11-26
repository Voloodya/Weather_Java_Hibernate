import Build.HibernateSessionFactory;
import Service.*;
import Tables.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainClass {


    public static void main (String[] args) throws SQLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Frame_Interface frame=new Frame_Interface();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       // HibernateSessionFactory.shutdown();
    }
}

class Frame_Interface extends JFrame
{

    public Frame_Interface() throws SQLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Погода в Мире");
        ImageIcon icon=new ImageIcon("D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\globus.gif");

        setBackground(Color.lightGray);
        setLayout(new GridBagLayout());
        mainPanel = new JPanel(new GridBagLayout());
        desktop=new JDesktopPane();
        desktop.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                mainPanel.setBounds(0, 0, desktop.getWidth(), desktop.getHeight());
                mainPanel.updateUI();
            }
        });
       // desktop.add(mainPanel, JDesktopPane.DEFAULT_LAYER);

        setContentPane(desktop);
        // Создание строки главного меню
        JMenuBar menuBar = new JMenuBar();
        JMenu menuUsers=new JMenu("Меню");
        JMenuItem menuSave=new JMenuItem("Сохранить в файл");
        menuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Диалоговое окно сохранения файла
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xlsx","*.*");
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(filter);
                if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    for(int i=0;i<model_table.getRowCount();i++) {
                        for (int j=0;j<model_table.getColumnCount();j++){
                            try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {
                                fw.write(String.valueOf(model_table.getValueAt(i,j))+" ");
                            } catch (IOException e2) {
                                System.out.println("Ошибка!");
                            }
                        }
                    }
                }
            }
        });
        JMenuItem menuPrint=new JMenuItem("Печать");
        JMenuItem menuAdmistration=new JMenuItem("Администратор БД");
        menuUsers.add(menuAdmistration);
        menuAdmistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               final JInternalFrame usersData=new JInternalFrame("Учётные данные",true,//Допускается измен размеров
                        true,//допускается максимиз-я
                        true);//допускается свертывание
                usersData.setSize(300,150);
                final JTextField login=new JTextField(100);
                final JPasswordField password=new JPasswordField(100);
                usersData.setLayout(new GridBagLayout());
                usersData.add(login,new GridBagConstraints(0,1,1,1,0.9,0.9,
                        GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(1,5,1,5),0,0));
                usersData.add(password,new GridBagConstraints(0,2,1,1,0.9,0.9,
                        GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(1,5,1,5),0,0));
                JButton test=new JButton("Войти");
                test.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String Login=login.getText();
                        String Password=password.getText();
                        UsersEnity user=null;
                        UsersService usersService=new UsersService();
                        try {
                            user=usersService.getUsersLoginPasw(Login,Password);
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(null, "Неверный логин или пароль");
                            e1.printStackTrace();
                        }
                        if(user!=null){
                            FrameInner frameInner=new FrameInner();
                            frameInner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameInner.setVisible(true);
                            usersData.setVisible(false);
                        } else {JOptionPane.showMessageDialog(null, "Неверный логин или пароль");}
                    }
                });
                usersData.add(test,new GridBagConstraints(0,3,1,1,0.9,0.9,
                        GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(1,5,1,5),0,0));
                desktop.add(usersData);
                usersData.setVisible(true);

            }
        });
        menuUsers.add(menuSave);
        menuUsers.add(menuPrint);
        // Добавление в главное меню выпадающих пунктов меню
        menuBar.add(menuUsers);
        setJMenuBar(menuBar);


         //Главная таблица Pogoda
        model_table=new ModelTablePogoda(); //Создание объекта модели таблицы
        table_pogoda=new JTable(model_table); //Сздание таблицы по модели model_table
        table_pogoda.setRowHeight(60);
        table_pogoda.removeColumn(table_pogoda.getColumnModel().getColumn(0));//Удаление 1-го столбца
        table_pogodaScrollPage=new JScrollPane(table_pogoda); //Добавление в таблицу прокрутки
        table_pogodaScrollPage.setPreferredSize(new Dimension(DEFAULT_WIDTH-5,DEFAULT_HEIGHT-(DEFAULT_HEIGHT/3))); //установка размеров таблицы
        mainPanel.add(table_pogodaScrollPage,new GridBagConstraints(0,0,16,1,1,0.2,
                GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(1,1,1,1),1,1));

        PogodaService pogodaService=new PogodaService();
        pogodaAll=pogodaService.getAllPogoda();
        model_table.addPogoda(pogodaAll);
      JButton searhButtonCity=new JButton("Поиск по городу");
      searhButtonCity.setPreferredSize(new Dimension(70,30));;
      searhButtonCity.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String selectRow= (String) gorodList.getSelectedItem();
              PogodaService pogodaService=new PogodaService();
              boolean statuschekBox=checkBoxData.isSelected();  //Проверка статуса JCheckBox
              if(statuschekBox) {
                  try {
                      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                      Date searhDate=new Date();
                      try {
                          searhDate=format.parse(data.getText());
                      } catch (ParseException e1) {
                          e1.printStackTrace();
                          JOptionPane.showMessageDialog(null, "Неверный формат даты");
                      }
                      pogodaAll = pogodaService.getPogodaCityDate(selectRow,searhDate);
                  } catch (SQLException e1) {
                      e1.printStackTrace();
                  }
              }
              else{
                  try {
                      pogodaAll = pogodaService.getPogodaNameCity(selectRow);
                  } catch (SQLException e1) {
                      e1.printStackTrace();
                  }
              }
              model_table.addPogoda(pogodaAll);
              model_table.fireTableDataChanged();

          }
      });
        JButton searhButtonCountry=new JButton("Поиск по стране");
        searhButtonCountry.setPreferredSize(new Dimension(70,30));
        searhButtonCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountryEntity countryEntity=new CountryEntity();
                CountryServise countryServise=new CountryServise();
                String selectCountry= (String) countryList.getSelectedItem();
                PogodaService pogodaService = new PogodaService();
                boolean statuschekBox = checkBoxCountry.isSelected();
                if(statuschekBox) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date searhDate=new Date();
                    try {
                        searhDate=format.parse(dataCountry.getText());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Неверный формат даты");
                    }
                    try {

                        countryEntity = countryServise.getCountryName(selectCountry);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        pogodaAll = pogodaService.getPogodaCountryData(countryEntity,searhDate);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else{
                    try {

                        countryEntity = countryServise.getCountryName(selectCountry);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaAll = pogodaService.getPogodaCountry(countryEntity);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                model_table.addPogoda(pogodaAll);
                model_table.fireTableDataChanged();

            }
        });
        JButton searhButtonRegion=new JButton("Поиск по региону");
        searhButtonRegion.setPreferredSize(new Dimension(70,30));
        searhButtonRegion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegionEntity regionEntity=new RegionEntity();
                RegionService regionServise=new RegionService();
                PogodaService pogodaService=new PogodaService();
                String selectRegion= (String) regionList.getSelectedItem();
                boolean statuschekBox=checkBoxRegion.isSelected();
                if(statuschekBox){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date searhDate=new Date();
                    try {
                        searhDate=format.parse(dataRegion.getText());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Неверный формат даты");
                    }
                    try {
                        regionEntity=regionServise.getRegionName(selectRegion);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaRegion=pogodaService.getPogodaRegionData(regionEntity,searhDate);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    try {
                        regionEntity = regionServise.getRegionName(selectRegion);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaRegion = pogodaService.getPogodaRegion(regionEntity);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                ModelTablePogodaRegion modelTablePogodaRegion=new ModelTablePogodaRegion();
                JTable tablePogodaRegion = new JTable(modelTablePogodaRegion);
                modelTablePogodaRegion.addPogodaRegion(pogodaRegion);
                modelTablePogodaRegion.fireTableDataChanged();
                tablePogodaRegion.setRowHeight(60);
                tablePogodaRegion.removeColumn(tablePogodaRegion.getColumnModel().getColumn(0));//Удаление столбца с id
                JScrollPane tablePogodaRegionScrollPage=new JScrollPane(tablePogodaRegion);
            createClimatePoyasFrame(tablePogodaRegionScrollPage);
            }
        });

        JButton searhButtonClimatePoyas=new JButton("Поиск по типу климата");
        searhButtonClimatePoyas.setPreferredSize(new Dimension(70,30));
        searhButtonClimatePoyas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClimatPoyasEntity poyasEntity=new ClimatPoyasEntity();
                ClimatPoyasService poyasService=new ClimatPoyasService();
                String selectPoyas= (String) climateList.getSelectedItem();
                PogodaService pogodaService=new PogodaService();
                boolean statuschekBox=checkBoxClimate.isSelected();
                if (statuschekBox){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date searhDate=new Date();
                    try {
                        searhDate=format.parse(dataClimate.getText());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Неверный формат даты");
                    }
                    try {
                        poyasEntity = poyasService.getClimatPoyasName(selectPoyas);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaAll = pogodaService.getPogodaClimateData(poyasEntity,searhDate);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        poyasEntity = poyasService.getClimatPoyasName(selectPoyas);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaAll = pogodaService.getPogodaClimate(poyasEntity);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                ModelTablePogodaPoyas modelTablePogodaPoyas=new ModelTablePogodaPoyas();
                JTable tablePogodaPoyas = new JTable(modelTablePogodaPoyas);
                tablePogodaPoyas.setRowHeight(60);
                tablePogodaPoyas.removeColumn(tablePogodaPoyas.getColumnModel().getColumn(0));//Удаление столбца с id
                JScrollPane tablePogodaPoyasScrollPage=new JScrollPane(tablePogodaPoyas);
                modelTablePogodaPoyas.addPogoda(pogodaAll);
                modelTablePogodaPoyas.fireTableDataChanged();
                createClimatePoyasFrame(tablePogodaPoyasScrollPage);
            }
        });
        JButton searhButtonOther=new JButton("Найти");
        searhButtonOther.setPreferredSize(new Dimension(70,30));
        searhButtonOther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClimatPoyasEntity poyasEntity=new ClimatPoyasEntity();
                ClimatPoyasService poyasService=new ClimatPoyasService();
                PogodaService pogodaService=new PogodaService();
                RegionEntity regionEntity=new RegionEntity();
                RegionService regionServise=new RegionService();
                CountryEntity countryEntity=new CountryEntity();
                CountryServise countryServise=new CountryServise();
                String selectPoyas= (String) climateList.getSelectedItem();
                String selectRegion= (String) regionList.getSelectedItem();
                String selectCountry=(String) countryList.getSelectedItem();
                boolean statuschekBox=checkBoxRegion.isSelected();
                boolean statuschekBox2=checkBoxClimate.isSelected();
                boolean statuschekBoxCountr=checkBoxCountry2.isSelected();
                boolean statuschekBoxReg=checkBoxRegion2.isSelected();
                boolean statuschekBoxClim=checkBoxClimate2.isSelected();
                if(statuschekBoxReg && statuschekBoxClim && (statuschekBox ||statuschekBox2)){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date searhDate=new Date();
                    try {
                        searhDate=format.parse(dataRegion.getText());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Неверный формат даты");
                    }
                    try {
                        poyasEntity = poyasService.getClimatPoyasName(selectPoyas);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        regionEntity=regionServise.getRegionName(selectRegion);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaAll = pogodaService.getPogodaClimateRegionDate(poyasEntity,regionEntity,searhDate);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else if(statuschekBoxReg && statuschekBoxClim && statuschekBoxCountr && (statuschekBox ||statuschekBox2)){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date searhDate=new Date();
                    try {
                        searhDate=format.parse(dataRegion.getText());
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Неверный формат даты");
                    }
                    try {

                        countryEntity = countryServise.getCountryName(selectCountry);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        poyasEntity = poyasService.getClimatPoyasName(selectPoyas);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        regionEntity=regionServise.getRegionName(selectRegion);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaAll = pogodaService.getPogodaCountryClimateRegionDate(countryEntity,poyasEntity,regionEntity,searhDate);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if(statuschekBoxReg && statuschekBoxClim && statuschekBoxCountr){
                    try {

                        countryEntity = countryServise.getCountryName(selectCountry);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        poyasEntity = poyasService.getClimatPoyasName(selectPoyas);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        regionEntity=regionServise.getRegionName(selectRegion);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaAll = pogodaService.getPogodaCountryClimateRegion(countryEntity,poyasEntity,regionEntity);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if (statuschekBoxReg && statuschekBoxClim){
                    try {
                        poyasEntity = poyasService.getClimatPoyasName(selectPoyas);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        regionEntity=regionServise.getRegionName(selectRegion);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        pogodaAll = pogodaService.getPogodaClimateRegion(poyasEntity,regionEntity);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

                ModelTablePogodaPoyas modelTablePogodaPoyas=new ModelTablePogodaPoyas();
                JTable tablePogodaPoyas = new JTable(modelTablePogodaPoyas);
                tablePogodaPoyas.setRowHeight(60);
                tablePogodaPoyas.removeColumn(tablePogodaPoyas.getColumnModel().getColumn(0));//Удаление столбца с id
                JScrollPane tablePogodaPoyasScrollPage=new JScrollPane(tablePogodaPoyas);
                modelTablePogodaPoyas.addPogoda(pogodaAll);
                modelTablePogodaPoyas.fireTableDataChanged();
                createClimatePoyasFrame(tablePogodaPoyasScrollPage);
            }
        });
        JButton antiSearhButton=new JButton("Отобразить всё");
        antiSearhButton.setPreferredSize(new Dimension(70,30));
        antiSearhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PogodaService pogodaService=new PogodaService();
                try {
                    pogodaAll=pogodaService.getAllPogoda();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                model_table.addPogoda(pogodaAll);
                model_table.fireTableDataChanged();

            }
        });
        JButton allInformButton=new JButton("Подрбнее");
        allInformButton.setPreferredSize(new Dimension(70,30));
        AllInformButtonAction allInformButtonAction=new AllInformButtonAction();
        allInformButton.addActionListener(allInformButtonAction);

        mainPanel.add(searhButtonCity,new GridBagConstraints(3,1,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(searhButtonCountry,new GridBagConstraints(3,2,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(antiSearhButton,new GridBagConstraints(5,4,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(searhButtonRegion,new GridBagConstraints(3,3,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(searhButtonClimatePoyas,new GridBagConstraints(3,4,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(searhButtonOther,new GridBagConstraints(4,1,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(allInformButton,new GridBagConstraints(5,1,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(data,new GridBagConstraints(3,1,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        checkBoxData=new JCheckBox("",false);
        mainPanel.add(checkBoxData,new GridBagConstraints(2,1,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(2,2,2,2),0,0));
        mainPanel.add(checkBoxCountry,new GridBagConstraints(2,2,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(2,2,2,2),0,0));
        mainPanel.add(checkBoxRegion,new GridBagConstraints(2,3,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(2,2,2,2),0,0));
        mainPanel.add(checkBoxClimate,new GridBagConstraints(2,4,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        mainPanel.add(checkBoxCountry2,new GridBagConstraints(4,2,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(2,2,2,2),0,0));
        mainPanel.add(checkBoxRegion2,new GridBagConstraints(4,3,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(2,2,2,2),0,0));
        mainPanel.add(checkBoxClimate2,new GridBagConstraints(4,4,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
       // mainPanel.add(icon,new GridBagConstraints(4,4,1,1,1,0.03,
          //      GridBagConstraints.CENTER,GridBagConstraints.CENTER,new Insets(5,5,5,5),0,0));
        data.setSize(40,30);

        mainPanel.add(data,new GridBagConstraints(1,1,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0));
        mainPanel.add(dataCountry,new GridBagConstraints(1,2,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0));
        mainPanel.add(dataRegion,new GridBagConstraints(1,3,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0));
        mainPanel.add(dataClimate,new GridBagConstraints(1,4,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),0,0));
        //Объект JComboBox with climatPoys
       ClimatPoyasService climatPoyasService=new ClimatPoyasService();
        ClimatPoyasEntity climatPoyasEntity=new ClimatPoyasEntity();
        List<ClimatPoyasEntity> climatAll=climatPoyasService.getAllClimatPoyas();
        String[] str_climate =new String[climatAll.size()];
        Iterator iteratorClimate=climatAll.iterator();
        int i=0;
        while(iteratorClimate.hasNext()) {
            climatPoyasEntity=(ClimatPoyasEntity)iteratorClimate.next();
            str_climate[i++]=climatPoyasEntity.getNameClimatePoyas();
        }
        climateList=new JComboBox(str_climate);
        JScrollPane climateScrollPane=new JScrollPane(climateList);
        climateList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClimatPoyasService climatPoyasService=new ClimatPoyasService();

                String selectRow= (String) climateList.getSelectedItem();
                try {
                    ClimatPoyasEntity searchClimate=climatPoyasService.getClimatPoyasName(selectRow);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        climateScrollPane.setSize(50,100);
        mainPanel.add(climateScrollPane,new GridBagConstraints(0,4,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(1,5,5,1),1,1));
        //Объект JComboBox with region
        RegionService regionService=new RegionService();
        RegionEntity regionEntity=new RegionEntity();
        List<RegionEntity> regionAll=regionService.getAllRegion();
        String[] str_region =new String[regionAll.size()];
        Iterator iteratorRegion=regionAll.iterator();
        int j=0;
        while(iteratorRegion.hasNext()) {
            regionEntity=(RegionEntity)iteratorRegion.next();
            str_region[j++]=regionEntity.getNameRegion();
        }
        regionList=new JComboBox(str_region);
        JScrollPane regionScrollPane=new JScrollPane(regionList);
        regionScrollPane.setPreferredSize(new Dimension(70,30));
        mainPanel.add(regionScrollPane,new GridBagConstraints(0,3,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),1,1));
        //Объект JComboBox with country
        CountryServise countryServise=new CountryServise();
        CountryEntity countryEntity=new CountryEntity();
        List<CountryEntity> countryAll=countryServise.getAllCountry();
        String[] str_country =new String[countryAll.size()];
        Iterator iteratorCountry=countryAll.iterator();
        int l=0;
        while(iteratorCountry.hasNext()) {
            countryEntity=(CountryEntity)iteratorCountry.next();
            str_country[l++]=countryEntity.getNameCountry();
        }
        countryList=new JComboBox(str_country);
        JScrollPane countryScrollPane=new JScrollPane(countryList);
        countryScrollPane.setPreferredSize(new Dimension(70,30));
        mainPanel.add(countryScrollPane,new GridBagConstraints(0,2,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),1,1));
        //Объект JComboBox with city
        NaselennayPunktService gorodservice=new NaselennayPunktService();
        NaselennayPunktEntity gorod=new NaselennayPunktEntity();
        List<NaselennayPunktEntity> gorodAll=gorodservice.getAllNaselennayPunkt();
        String[] str_goroda =new String[gorodAll.size()];
        Iterator iterator=gorodAll.iterator();
        int k=0;
        while(iterator.hasNext()) {
            gorod=(NaselennayPunktEntity)iterator.next();
            str_goroda[k++]=gorod.getNameNaselenPunkt();
        }
        gorodList=new JComboBox(str_goroda);
        gorodScrollPane=new JScrollPane(gorodList);
        gorodScrollPane.setPreferredSize(new Dimension(70,30));
        mainPanel.add(gorodScrollPane,new GridBagConstraints(0,1,1,1,1,0.03,
                GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),1,1));
        gorodList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectRow= (String) gorodList.getSelectedItem();
                nameCity.setText(selectRow);
            }
        });

       //Объект JComboBox with AtmosfernaeYvleniy
        AtmosfernaeYvleniyServise atmosferservice=new AtmosfernaeYvleniyServise();
        AtmosfernaeYvleniyEntity atmosrernYvleniy=new AtmosfernaeYvleniyEntity();
        List<AtmosfernaeYvleniyEntity> yvleniyAll=atmosferservice.getAllAtmosfernaeYvleniy();
        String[] str_yvleniy =new String[yvleniyAll.size()];
        Iterator iteratorYvleniy=yvleniyAll.iterator();
        int h=0;
        while(iteratorYvleniy.hasNext()) {
            atmosrernYvleniy=(AtmosfernaeYvleniyEntity)iteratorYvleniy.next();
            str_yvleniy[h++]=atmosrernYvleniy.getVidYvleniy();
        }
        final JComboBox yvleniyList=new JComboBox(str_yvleniy);
        yvleniyScrollPane=new JScrollPane(yvleniyList);
        yvleniyScrollPane.setMinimumSize(new Dimension(180,30));
        //mainPanel.add(yvleniyScrollPane,new GridBagConstraints(4,4,1,1,1,0.1,
        //        GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),1,1));
        yvleniyList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectRow= (String) yvleniyList.getSelectedItem();
                osadkyNoh.setText(selectRow);
                osadkyUtro.setText(selectRow);
                osadkyDay.setText(selectRow);
                osadkyVeher.setText(selectRow);
            }
        });
        //Объект JComboBox with Veter
        VeterService veterservice=new VeterService();
        VeterEntity veter;
        List<VeterEntity> veterAll=veterservice.getAllVeter();
        String[] str_veter =new String[veterAll.size()];
        Iterator iteratorVeter=veterAll.iterator();
        int q=0;
        while(iteratorVeter.hasNext()) {
            veter=(VeterEntity)iteratorVeter.next();
            str_veter[q++]=veter.getNapravlenieVetra();
        }
        final JComboBox veterList=new JComboBox(str_veter);
        veterScrollPane=new JScrollPane(veterList);
        veterScrollPane.setMinimumSize(new Dimension(30,30));
       // mainPanel.add(veterScrollPane,new GridBagConstraints(5,4,1,1,1,0.05,
         //       GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,5,5,5),1,1));
        veterList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectRow= (String) veterList.getSelectedItem();
                napravlenieVetra.setText(selectRow);
            }
        });
        add(mainPanel,new GridBagConstraints(0,0,16,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(1,1,1,1),0,0));
        //Обработчик события копирования в JTable выделенной строки
        ListSelectionModel selectionModel = table_pogoda.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            //Метод вызывающийся у слушателя при выделении строки
            @Override
            public void valueChanged(ListSelectionEvent e) {
                TableModel model=table_pogoda.getModel();
                if (table_pogoda.getSelectedRows().length != 1) //getSelectedRows() - возвращает массив индексов выделенных строк
                {
                    return;
                }
                int viewRowIndex = table_pogoda.getSelectedRows()[0];
                int rowIndex = table_pogoda.convertRowIndexToModel(viewRowIndex);//Приобразование индексов строк в исходные (если есть сортировка)
                if (viewRowIndex != -1) {
                    idPogoda.setText(model.getValueAt(rowIndex,0).toString());
                    nameCity.setText(model.getValueAt(rowIndex,1).toString());
                    data.setText(model.getValueAt(rowIndex,2).toString());
                    tNoh.setText(model.getValueAt(rowIndex,3).toString());
                    tUtro.setText(model.getValueAt(rowIndex,4).toString());
                    tDay.setText(model.getValueAt(rowIndex,5).toString());
                    tVeher.setText(model.getValueAt(rowIndex,6).toString());
                    davlenie.setText(model.getValueAt(rowIndex,7).toString());
                    vlagnoste.setText(model.getValueAt(rowIndex,8).toString());
                    osadkyNoh.setText(model.getValueAt(rowIndex,9).toString());
                    osadkyUtro.setText(model.getValueAt(rowIndex,10).toString());
                    osadkyDay.setText(model.getValueAt(rowIndex,11).toString());
                    osadkyVeher.setText(model.getValueAt(rowIndex,12).toString());
                    skorosteVetra.setText(model.getValueAt(rowIndex,13).toString());
                    napravlenieVetra.setText(model.getValueAt(rowIndex,14).toString());
                    temperaturaVoda.setText(model.getValueAt(rowIndex,15).toString());
                }
            }
        });
    }
    //Обработчик удаления строки из БД и обновления таблицы (то что нужно править)
    public class DelButtonAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

                    PogodaService pogodaService=new PogodaService();

                    if (table_pogoda.getSelectedRows().length != 1) //getSelectedRows() - возвращает массив индексов выделенных строк
                    {
                        return;
                    }
                    int viewRowIndex = table_pogoda.getSelectedRow();
                    int rowIndex = table_pogoda.convertRowIndexToModel(viewRowIndex);//Приобразование индексов строк в исходные (если есть сортировка)
            int idObgect=Integer.valueOf(String.valueOf(model_table.getValueAt(rowIndex,0)));
            if (viewRowIndex != -1) {
                        try {
                            pogodaService.deleteInformationPogoda(idObgect); //Удаление объекта по id
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                try {
                    pogodaAll=pogodaService.getAllPogoda();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                model_table.addPogoda(pogodaAll);
                model_table.fireTableDataChanged();
                    }
                }
    }
    //Обработчик кнопки получения всех данных
    public class AllInformButtonAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            PogodaService pogodaService=new PogodaService();
            PogodaEntity singlCity=new PogodaEntity();

            if (table_pogoda.getSelectedRows().length != 1) //getSelectedRows() - возвращает массив индексов выделенных строк
            {
                return;
            }
            int viewRowIndex = table_pogoda.getSelectedRow();
            int rowIndex = table_pogoda.convertRowIndexToModel(viewRowIndex);//Приобразование индексов строк в исходные (если есть сортировка)
            int idObgect=Integer.valueOf(String.valueOf(model_table.getValueAt(rowIndex,0)));
            if (viewRowIndex != -1) {
                try {
                    singlCity=pogodaService.getPogodaId(idObgect);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
            ModelTableAllInform modelTableAllInform=new ModelTableAllInform();
            JTable tableAllInform = new JTable(modelTableAllInform);
            modelTableAllInform.addSinglPogoda(singlCity);
            modelTableAllInform.fireTableDataChanged();
            tableAllInform.setRowHeight(60);
            tableAllInform.removeColumn(tableAllInform.getColumnModel().getColumn(0));//Удаление столбца с id
            JScrollPane tablePogodaRegionScrollPage=new JScrollPane(tableAllInform);
            createClimatePoyasFrame(tablePogodaRegionScrollPage);
        }
    }
        //Обработчик добавления записи
     public class AddButtonAction  implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            PogodaService pogodaService=new PogodaService();

            if (table_pogoda.getSelectedRows().length != 1) //getSelectedRows() - возвращает массив индексов выделенных строк
            {
                return;
            }

                NaselennayPunktService gorod=new NaselennayPunktService();
                AtmosfernaeYvleniyServise atmosfera=new AtmosfernaeYvleniyServise();
                VeterService veter=new VeterService();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate=new Date();
            try {
                newDate=format.parse(data.getText());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
                PogodaEntity newPogoda = null;
            try {
                    newPogoda=new PogodaEntity(gorod.getNaselennayPunktName(nameCity.getText()),newDate,
                            Integer.valueOf(tNoh.getText()),Integer.valueOf(tUtro.getText()),Integer.valueOf(tDay.getText()),
                            Integer.valueOf(tVeher.getText()),Integer.valueOf(vlagnoste.getText()),Integer.valueOf(davlenie.getText()),
                            Integer.valueOf(skorosteVetra.getText()),Integer.valueOf(temperaturaVoda.getText()),
                            atmosfera.getAtmoserYvleniyName(osadkyNoh.getText()),atmosfera.getAtmoserYvleniyName(osadkyUtro.getText()),
                            atmosfera.getAtmoserYvleniyName(osadkyDay.getText()),atmosfera.getAtmoserYvleniyName(osadkyVeher.getText()),
                            veter.getVeterName(napravlenieVetra.getText()));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    pogodaService.addInformationPogoda(newPogoda);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            try {
                pogodaAll=pogodaService.getAllPogoda();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            model_table.addPogoda(pogodaAll);
            model_table.fireTableDataChanged();
            }
        }
    //Обработчик обновления записи
    public class UpdateButtonAction  implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            PogodaService pogodaService=new PogodaService();

            if (table_pogoda.getSelectedRows().length != 1) //getSelectedRows() - возвращает массив индексов выделенных строк
            {
                return;
            }

            NaselennayPunktService gorod=new NaselennayPunktService();
            AtmosfernaeYvleniyServise atmosfera=new AtmosfernaeYvleniyServise();
            VeterService veter=new VeterService();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate=new Date();
            try {
                newDate=format.parse(data.getText());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            PogodaEntity newPogoda = null;
            try {
                newPogoda=new PogodaEntity(Integer.valueOf(idPogoda.getText()),gorod.getNaselennayPunktName(nameCity.getText()),newDate,
                        Integer.valueOf(tNoh.getText()),Integer.valueOf(tUtro.getText()),Integer.valueOf(tDay.getText()),
                        Integer.valueOf(tVeher.getText()),Integer.valueOf(vlagnoste.getText()),Integer.valueOf(davlenie.getText()),
                        Integer.valueOf(skorosteVetra.getText()),Integer.valueOf(temperaturaVoda.getText()),
                        atmosfera.getAtmoserYvleniyName(osadkyNoh.getText()),atmosfera.getAtmoserYvleniyName(osadkyUtro.getText()),
                        atmosfera.getAtmoserYvleniyName(osadkyDay.getText()),atmosfera.getAtmoserYvleniyName(osadkyVeher.getText()),
                        veter.getVeterName(napravlenieVetra.getText()));
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                pogodaService.updatePogoda(newPogoda);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                pogodaAll=pogodaService.getAllPogoda();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            model_table.addPogoda(pogodaAll);
            model_table.fireTableDataChanged();
        }
    }
    class FrameInner extends JFrame
    {
        public FrameInner(){
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            setTitle("Database of weather");
            setBackground(Color.GRAY);
            setLayout(new GridBagLayout());
            add(table_pogodaScrollPage,new GridBagConstraints(0,0,16,2,1,1,
                    GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(1,1,1,1),1,1));
            JPanel textFieldPanel=new JPanel();
            textFieldPanel.add(idPogoda,0);
            textFieldPanel.add(nameCity,1);
            textFieldPanel.add(data,2);
            textFieldPanel.add(tNoh,3);
            textFieldPanel.add(tUtro,4);
            textFieldPanel.add(tDay,5);
            textFieldPanel.add(tVeher,6);
            textFieldPanel.add(vlagnoste,7);
            textFieldPanel.add(davlenie,8);
            textFieldPanel.add(osadkyNoh,9);
            textFieldPanel.add(osadkyUtro,10);
            textFieldPanel.add(osadkyDay,11);
            textFieldPanel.add(osadkyVeher,12);
            textFieldPanel.add(skorosteVetra,13);
            textFieldPanel.add(napravlenieVetra,14);
            textFieldPanel.add(temperaturaVoda,15);
            add(textFieldPanel,new GridBagConstraints(0,2,16,1,0.9,0.9,
                    GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(1,5,1,5),0,0));
            JButton dellButton = new JButton("Удалить запись");
            JButton updateButton = new JButton("Скорректировать запись");
            JButton addButton = new JButton("Добавить запись");
            DelButtonAction delButtonAction=new DelButtonAction();
            dellButton.addActionListener(delButtonAction);
            AddButtonAction addButtonAction=new AddButtonAction();
            addButton.addActionListener(addButtonAction);
            UpdateButtonAction updateButtonAction=new UpdateButtonAction();
            updateButton.addActionListener(updateButtonAction);
            JPanel buttonPanel=new JPanel();
            buttonPanel.add(dellButton);
            buttonPanel.add(updateButton);
            buttonPanel.add(addButton);
            add(buttonPanel,new GridBagConstraints(0,3,16,1,1,1,
                    GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,1,1,2),0,0));
            JPanel jcomboboxPanel=new JPanel();
            jcomboboxPanel.setLayout(new GridBagLayout());
            add(gorodScrollPane,new GridBagConstraints(0,4,1,1,1,0.05,
                    GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,1,1,2),0,0));
            add(yvleniyScrollPane,new GridBagConstraints(4,4,1,1,1,0.05,
                    GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,1,1,2),0,0));
            add(veterScrollPane,new GridBagConstraints(12,4,1,1,1,0.05,
                    GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,1,1,2),0,0));
        }
    }
    public void createClimatePoyasFrame(JScrollPane tableScrollPage){
        final JInternalFrame climatePoyasFrame=new JInternalFrame("Климат",true,//Допускается измен размеров
                true,//допускается максимиз-я
                true);//допускается свертывание
        climatePoyasFrame.setSize(DEFAULT_WIDTH-30,DEFAULT_HEIGHT/2);
        climatePoyasFrame.setLayout(new GridBagLayout());

        climatePoyasFrame.add(tableScrollPage,new GridBagConstraints(0,0,16,2,1,0.15,
                GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(1,1,1,1),1,1));


        desktop.add(climatePoyasFrame);
        climatePoyasFrame.setVisible(true);
    }
    public void createRegionFrame(){
        final JInternalFrame regionFrame=new JInternalFrame("Климат",true,//Допускается измен размеров
                true,//допускается максимиз-я
                true);//допускается свертывание
        regionFrame.setSize(DEFAULT_WIDTH-30,DEFAULT_HEIGHT-30);
        regionFrame.setLayout(new GridBagLayout());


       /* mainPanel.add(tablePogodaRegionScrollPage,new GridBagConstraints(0,5,14,1,1,0.1,
                GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(1,1,1,1),1,1));
        regionFrame.add(tablePogodaRegionScrollPage,new GridBagConstraints(0,0,14,2,1,0.15,
                GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(1,1,1,1),1,1));
        desktop.add(regionFrame);*/
        regionFrame.setVisible(true);
    }
    private JDesktopPane desktop; //Панель для нового фрейма
    private List<PogodaEntity> pogodaAll;
    private List<PogodaEntity> pogodaRegion;
    private JTable table_pogoda;
    public ModelTablePogoda model_table;
    private static final int DEFAULT_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.07);
    private static final int DEFAULT_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.3);
    private JPanel mainPanel;
    private JTextField idPogoda=new JTextField(3);
    private JTextField nameCity=new JTextField(15);
    private JTextField data=new JTextField(8);
    private JTextField tNoh=new JTextField(3);
    private JTextField tUtro=new JTextField(3);
    private JTextField tDay=new JTextField(3);
    private JTextField tVeher=new JTextField(3);
    private JTextField vlagnoste=new JTextField(4);
    private JTextField davlenie=new JTextField(5);
    private JTextField osadkyNoh=new JTextField(13);
    private JTextField osadkyUtro=new JTextField(13);
    private JTextField osadkyDay=new JTextField(13);
    private JTextField osadkyVeher=new JTextField(13);
    private JTextField skorosteVetra=new JTextField(4);
    private JTextField napravlenieVetra=new JTextField(5);
    private JTextField temperaturaVoda=new JTextField(3);
    private JTextField dataCountry=new JTextField(8);
    private JTextField dataRegion=new JTextField(8);
    private JTextField dataClimate=new JTextField(8);
    private JComboBox countryList;
    private JComboBox gorodList;
    private JComboBox regionList;
    private JComboBox climateList;
    private JCheckBox checkBoxData;
    private JCheckBox checkBoxCountry=new JCheckBox("",false);
    private JCheckBox checkBoxRegion=new JCheckBox("",false);
    private JCheckBox checkBoxClimate=new JCheckBox("",false);
    private JCheckBox checkBoxCountry2=new JCheckBox("",false);
    private JCheckBox checkBoxRegion2=new JCheckBox("",false);
    private JCheckBox checkBoxClimate2=new JCheckBox("",false);
//JScrollPane for JComboBox для отражения на дочернем фрейме
    private JScrollPane gorodScrollPane;
    private JScrollPane yvleniyScrollPane;
    private JScrollPane veterScrollPane;
    //
    private JScrollPane table_pogodaScrollPage;
}

