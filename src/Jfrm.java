import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;

public class Jfrm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_new;
	private JTextField textField_new;
	private JTextField textField_new_1;
	private JTextField textField_new_2;
	private JTextField textField_new_3;
	private JTextField textField_new_A1_1;
	private JTextField textField_new_A1_2;
	private JTextField textField_new_A1_3;
	private JTable table_new;
	static boolean flag = true;

	public static void main(String[] args) {
		Company C1 = new Company("");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jfrm frame = new Jfrm(C1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Jfrm(Company company) {
		//frame
		setTitle(company.get_name());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 500)/2, (screenSize.height - 600)/2, 500, 600);
		//ContentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);
		//JMenuBar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//JMenu
		JMenu file = new JMenu("Файл");
		menuBar.add(file);
		JMenu edit = new JMenu("Редактировать");
		menuBar.add(edit);
		JMenu report = new JMenu("Отчет");
		menuBar.add(report);
		//JTabbedPane main
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setToolTipText("");
		
		JMenuItem menuItem = new JMenuItem("Новый");
		file.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String name_of_company = JOptionPane.showInputDialog(contentPane, "Введите название компании");
				if(name_of_company != "") {
					flag = false;
					edit.setEnabled(true);
					tabbedPane.setVisible(true);
					Company new_company = new Company(name_of_company);
					dispose();
					Jfrm Jfrm_update = new Jfrm(new_company);
					Jfrm_update.setVisible(true);
				}
			}
		});
		JMenuItem menuItem_1 = new JMenuItem("Открыть");
		file.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
				int A = fileopen.showOpenDialog(null);
				if (A == JFileChooser.APPROVE_OPTION) {
					Company c = new Company();
					try {
						FileInputStream fis = new FileInputStream(fileopen.getSelectedFile());
						ObjectInputStream ois = new ObjectInputStream(fis);
						c = (Company) ois.readObject();
						fis.close();
						ois.close();
						flag = false;
						dispose();
						Jfrm frame_open = new Jfrm(c);
						frame_open.setVisible(true);
					} catch (FileNotFoundException FNFEx) {
						FNFEx.printStackTrace();
					} catch (IOException IOEx) {
						IOEx.printStackTrace();
					} catch (ClassNotFoundException CNFEx) {
						CNFEx.printStackTrace();
					}
				}
			}
		});
		JMenuItem menuItem_2 = new JMenuItem("Сохранить");
		file.add(menuItem_2);
		
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filesave = new JFileChooser(new File("c:\\"));
				int B = filesave.showSaveDialog(null);
				if (B == JFileChooser.APPROVE_OPTION) {
					try {
						FileOutputStream fos = new FileOutputStream(filesave.getSelectedFile());
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(company);
						fos.close();
						oos.close();
					} catch (IOException IOEx) {
						IOEx.printStackTrace();
					}
				}
				dispose();
				Jfrm frame_save = new Jfrm(company);
				frame_save.setVisible(true);
			}
		});
		
		JMenuItem menuItem_3 = new JMenuItem("Сформировать и сохранить отчет");
		report.add(menuItem_3);
		
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file_save = new JFileChooser(new File("c:\\"));
				int C = file_save.showSaveDialog(null);
				if (C == JFileChooser.APPROVE_OPTION) {
					CompanyReport CR = new CompanyReport();
					CR.CreateAndSaveReport2(file_save.getSelectedFile(), company);
					JOptionPane.showMessageDialog(null, "Success!", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JMenuItem menuItem_4 = new JMenuItem("Загрузить отчет");
		report.add(menuItem_4);
		
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
				int D = fileopen.showOpenDialog(null);
				if (D == JFileChooser.APPROVE_OPTION) {
					JFrame jfrm_report = new JFrame();
					jfrm_report.setTitle(company.get_name());
					jfrm_report.setBounds((screenSize.width - 400)/2, (screenSize.height - 500)/2, 400, 500);
					contentPane = new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					jfrm_report.setContentPane(contentPane);
					JTextArea TA_report = new JTextArea();

					CompanyReport CR = new CompanyReport();
					CR.UploadReport2(fileopen.getSelectedFile().getPath());
					
					TA_report.setText(CR.UploadReport2(fileopen.getSelectedFile().getPath()));
					contentPane.add(TA_report);
					jfrm_report.setVisible(true);
					JOptionPane.showMessageDialog(null, "Success!", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		if(flag) {
			edit.setEnabled(false);
			report.setEnabled(false);
			tabbedPane.setVisible(false);
			JPanel panel_intro_1 = new JPanel();
			panel_intro_1.setBackground(new Color(225, 255, 225));
			panel_intro_1.setLayout(new GridLayout());
			JPanel panel_intro_2 = new JPanel();
			panel_intro_2.setBackground(new Color(225, 255, 225));
			panel_intro_2.setLayout(new GridLayout(0,1,0,0));
			contentPane.setBackground(new Color(225, 255, 225));
			contentPane.add(panel_intro_1, BorderLayout.CENTER);
			contentPane.add(panel_intro_2, BorderLayout.NORTH);
			
			JLabel label_intro_1 = new JLabel("Welcome my dear friend!");
			label_intro_1.setForeground(new Color(255, 215, 0));
			label_intro_1.setFont(new Font("Times New Roman", Font.ITALIC, 36));
			label_intro_1.setHorizontalAlignment(SwingConstants.CENTER);
			panel_intro_1.add(label_intro_1);
			
			JLabel label_intro_2 = new JLabel("/////////////////////////////");
			label_intro_2.setForeground(new Color(255, 215, 0));
			label_intro_2.setFont(new Font("Courier New", Font.ITALIC, 18));
			panel_intro_2.add(label_intro_2);
			
			JLabel label_intro_3 = new JLabel("//Created by Gallinger V.A.//");
			label_intro_3.setForeground(new Color(255, 215, 0));
			label_intro_3.setFont(new Font("Courier New", Font.ITALIC, 18));
			panel_intro_2.add(label_intro_3);
			
			JLabel label_intro_4 = new JLabel("/////////////////////////////");
			label_intro_4.setForeground(new Color(255, 215, 0));
			label_intro_4.setFont(new Font("Courier New", Font.ITALIC, 18));
			panel_intro_2.add(label_intro_4);
		}
		
		JPanel panel_A = new JPanel();
		panel_A.setToolTipText("");
		tabbedPane.addTab("Информация о тарифах", null, panel_A, null);
		panel_A.setLayout(new GridLayout(1, 5, 0, 0));
		
		JTabbedPane tabbedPane_A = new JTabbedPane(JTabbedPane.TOP);
		panel_A.add(tabbedPane_A);
		
		JPanel panel_B = new JPanel();
		tabbedPane.addTab("Абоненты", null, panel_B, null);
		panel_B.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane_B = new JTabbedPane(JTabbedPane.TOP);
		panel_B.add(tabbedPane_B);
		
		JPanel panel_C = new JPanel();
		tabbedPane.addTab("Стоимость разговора", null, panel_C, null);
		panel_C.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane_C = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_C.setBackground(Color.WHITE);
		panel_C.add(tabbedPane_C);
		
		//Создание новых тарифов
		for(int i = 0; i < company.get_tarif_name().size(); i++) {
			Tarif new_tarif = company.get_tarif_name().get(i);
			
			if(new_tarif instanceof PosecTarif) {
				//Информация о тарифе
				JPanel panel_new_A1 = new JPanel();
				tabbedPane_A.addTab(new_tarif.get_tarif_name(), null, panel_new_A1, null);
				panel_new_A1.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel label_new_A1_1 = new JLabel("Название тарифа: ");
				label_new_A1_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new_A1.add(label_new_A1_1);
				
				textField_new_A1_1 = new JTextField();
				textField_new_A1_1.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_A1_1.setColumns(10);
				textField_new_A1_1.setText(new_tarif.get_tarif_name());
				panel_new_A1.add(textField_new_A1_1);
				
				JLabel label_new_A1_2 = new JLabel("Кол-во абонентов: ");
				label_new_A1_2.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new_A1.add(label_new_A1_2);
				
				textField_new_A1_2 = new JTextField();
				textField_new_A1_2.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_A1_2.setColumns(10);
				panel_new_A1.add(textField_new_A1_2);
				textField_new_A1_2.setText(String.valueOf(new_tarif.amount_abonent()));
				
				JLabel label_new_A1_3 = new JLabel("Тип тарифа: ");
				label_new_A1_3.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new_A1.add(label_new_A1_3);
				
				textField_new_A1_3 = new JTextField();
				textField_new_A1_3.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_A1_3.setColumns(10);
				panel_new_A1.add(textField_new_A1_3);
				textField_new_A1_3.setText(new_tarif.getClass().getName());
				//Абоненты
				JPanel panel_B1_new = new JPanel();
				tabbedPane_B.addTab(new_tarif.get_tarif_name(), null, panel_B1_new, null);
				panel_B1_new.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				String[] headers = new String[] {"ФИО" ,  "Номер телефона" ,  "Остаток на счете"} ;
				String[][] data = new String[new_tarif.amount_abonent()][3];
				table_new = new JTable(data ,headers);
				panel_B1_new.add(new JScrollPane(table_new));
				DefaultTableCellRenderer r = (DefaultTableCellRenderer)table_new.getDefaultRenderer(String.class);
			     //Выравнивание по горизонтали и вертикали 
			    r.setHorizontalAlignment(JLabel.CENTER);
				//Рассчитать стоимость разговора
				JPanel panel_new_C1 = new JPanel();
				tabbedPane_C.addTab(new_tarif.get_tarif_name(), null, panel_new_C1, null);
				
				JButton Button_new_C1 = new JButton("Рассчитать стоимость разговора");
				panel_new_C1.add(Button_new_C1);
				Button_new_C1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String r1 = JOptionPane.showInputDialog("Введите кол-во секунд разговора: ");
						int R1 = new_tarif.rasschitat_stoimost_razgovora(Integer.parseInt(r1));
						JOptionPane.showMessageDialog(panel_new_C1, "Стоимость разговора: " + R1);
					}
				});
				for(int j = 0; j < new_tarif.amount_abonent(); j++) {
					Abonent abonent_new = new_tarif.get_abonents().get(j);
					data[j][0] = String.valueOf(abonent_new.get_FN());
					data[j][1] = String.valueOf(abonent_new.get_number());
					data[j][2] = String.valueOf(abonent_new.get_account());
					
					
				}
			}
			if(new_tarif instanceof PominTarif) {
				//Информация о тарифе
				JPanel panel_new_A1 = new JPanel();
				tabbedPane_A.addTab(new_tarif.get_tarif_name(), null, panel_new_A1, null);
				panel_new_A1.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel label_new_A1_1 = new JLabel("Название тарифа: ");
				label_new_A1_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new_A1.add(label_new_A1_1);
				
				textField_new_A1_1 = new JTextField();
				textField_new_A1_1.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_A1_1.setColumns(10);
				textField_new_A1_1.setText(new_tarif.get_tarif_name());
				panel_new_A1.add(textField_new_A1_1);
				
				JLabel label_new_A1_2 = new JLabel("Кол-во абонентов: ");
				label_new_A1_2.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new_A1.add(label_new_A1_2);
				
				textField_new_A1_2 = new JTextField();
				textField_new_A1_2.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_A1_2.setColumns(10);
				panel_new_A1.add(textField_new_A1_2);
				textField_new_A1_2.setText(String.valueOf(new_tarif.amount_abonent()));
				
				JLabel label_new_A1_3 = new JLabel("Тип тарифа: ");
				label_new_A1_3.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new_A1.add(label_new_A1_3);
				
				textField_new_A1_3 = new JTextField();
				textField_new_A1_3.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_A1_3.setColumns(10);
				panel_new_A1.add(textField_new_A1_3);
				textField_new_A1_3.setText(new_tarif.getClass().getName());
				//Абоненты
				JPanel panel_B1_new = new JPanel();
				tabbedPane_B.addTab(new_tarif.get_tarif_name(), null, panel_B1_new, null);
				panel_B1_new.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				String[] headers = new String[] { "ФИО" ,  "Номер телефона" ,  "Остаток на счете"} ;
				String[][] data = new String[new_tarif.amount_abonent()][3];
				table_new = new JTable(data ,headers);
				panel_B1_new.add(new JScrollPane(table_new));
				DefaultTableCellRenderer r = (DefaultTableCellRenderer)table_new.getDefaultRenderer(String.class);
			     //Выравнивание по горизонтали и вертикали 
			    r.setHorizontalAlignment(JLabel.CENTER);
				//Рассчитать стоимость разговора
				JPanel panel_new_C1 = new JPanel();
				tabbedPane_C.addTab(new_tarif.get_tarif_name(), null, panel_new_C1, null);
				
				JButton Button_new_C1 = new JButton("Рассчитать стоимость разговора");
				panel_new_C1.add(Button_new_C1);
				Button_new_C1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String r1 = JOptionPane.showInputDialog("Введите кол-во секунд разговора: ");
						int R1 = new_tarif.rasschitat_stoimost_razgovora(Integer.parseInt(r1));
						JOptionPane.showMessageDialog(panel_new_C1, "Стоимость разговора: " + R1);
					}
				});
				for(int j = 0; j < new_tarif.amount_abonent(); j++) {
					Abonent abonent_new = new_tarif.get_abonents().get(j);
					data[j][0] = String.valueOf(abonent_new.get_FN());
					data[j][1] = String.valueOf(abonent_new.get_number());
					data[j][2] = String.valueOf(abonent_new.get_account());
				}
			}
		}
		//////////////////////////Создание объектов////////////////////////////////////////////////////////////////////////
		JMenu menu_1 = new JMenu("Создать...");
		edit.add(menu_1);
		
		JMenuItem MenuItem_1 = new JMenuItem("Тариф");
		menu_1.add(MenuItem_1);
		
		MenuItem_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				JFrame jfrm_new = new JFrame();
				jfrm_new.setVisible(true);
				jfrm_new.setResizable(false);
				jfrm_new.setTitle("Tarif's Creator");
				jfrm_new.setBounds(100, 100, 450, 300);
				contentPane_new = new JPanel();
				contentPane_new.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane_new.setLayout(new BorderLayout(10, 10));
				jfrm_new.setContentPane(contentPane_new);
				JPanel panel_new = new JPanel();
				contentPane_new.add(panel_new);
				panel_new.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel label_new1 = new JLabel("Название тарифа: ");
				label_new1.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new.add(label_new1);
				
				textField_new = new JTextField();
				textField_new.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new.setColumns(10);
				textField_new.setText("");
				panel_new.add(textField_new);
				
				JLabel label_new2 = new JLabel("Тип тарифа: ");
				label_new2.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new.add(label_new2);
				
				JRadioButton Jrb1 = new JRadioButton();
				Jrb1.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel label_new3 = new JLabel("Посекундный ");
				label_new3.setHorizontalAlignment(SwingConstants.CENTER);
				
				JRadioButton Jrb2 = new JRadioButton();
				Jrb2.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel label_new4 = new JLabel("Поминутный ");
				label_new4.setHorizontalAlignment(SwingConstants.CENTER);
				
				ButtonGroup Bgr1 = new ButtonGroup();
				Bgr1.add(Jrb1);
				Bgr1.add(Jrb2);
				
				JPanel radiopanel1 = new JPanel();
				radiopanel1.setLayout(new GridLayout(0,2));
				radiopanel1.add(label_new3);
				radiopanel1.add(label_new4);
				radiopanel1.add(Jrb1);
				radiopanel1.add(Jrb2);
				panel_new.add(radiopanel1);
				
				Button button_new1 = new Button("OK");
				Button button_new2 = new Button("Cancel");
				
				panel_new.add(button_new1);
				panel_new.add(button_new2);
				button_new1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String s = JOptionPane.showInputDialog(panel_new, "Введите стоимость раговора: ");
						if(Jrb1.isSelected()) {
							try {
								PosecTarif NewPST = new PosecTarif(textField_new.getText(), Integer.parseInt(s));
								company.add_tarif(NewPST);
								dispose();
								Jfrm Jfrm_update = new Jfrm(company);
								Jfrm_update.setVisible(true);
							}
							catch(NumberFormatException NFEx) {
								NFEx.getMessage();
								NFEx.getStackTrace();
							}
						}
						else if(Jrb2.isSelected()) {
							try {
								PominTarif NewPMT = new PominTarif(textField_new.getText(), Integer.parseInt(s));
								company.add_tarif(NewPMT);
								dispose();
								Jfrm Jfrm_update = new Jfrm(company);
								Jfrm_update.setVisible(true);
							}
							catch(NumberFormatException NFEx){
								NFEx.getMessage();
								NFEx.printStackTrace();
							}
						}
						jfrm_new.dispose();
						setEnabled(true);
					}
				});
				
				button_new2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						jfrm_new.dispose();
						setEnabled(true);
					}
				});
				jfrm_new.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				jfrm_new.addWindowListener( new WindowAdapter()
				{
				    public void windowClosing(WindowEvent e)
				    {
				        int result = JOptionPane.showConfirmDialog(
				        	jfrm_new,
				            "Вы уверены что хотите закончить операцию?",
				            "Exit Application",
				            JOptionPane.YES_NO_OPTION);
				 
				        if (result == JOptionPane.YES_OPTION) {
				        	setEnabled(true);
				        	jfrm_new.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				        }
				    }
				});
			}
		});
		
		JMenuItem MenuItem_2 = new JMenuItem("Абонента");
		menu_1.add(MenuItem_2);
		
		MenuItem_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				JFrame jfrm_new = new JFrame();
				jfrm_new.setVisible(true);
				jfrm_new.setResizable(false);
				jfrm_new.setTitle("Abonent's Creator");
				jfrm_new.setBounds(100, 100, 450, 300);
				contentPane_new = new JPanel();
				contentPane_new.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane_new.setLayout(new BorderLayout(10, 10));
				jfrm_new.setContentPane(contentPane_new);
				
				JPanel panel_new = new JPanel();
				contentPane_new.add(panel_new);
				panel_new.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel label_new1 = new JLabel("ФИО: ");
				label_new1.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new.add(label_new1);
				
				textField_new_1 = new JTextField();
				textField_new_1.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_1.setColumns(10);
				panel_new.add(textField_new_1);
				
				JLabel label_new2 = new JLabel("Номер телефона: ");
				label_new2.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new.add(label_new2);
				
				textField_new_2 = new JTextField();
				textField_new_2.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_2.setColumns(10);
				panel_new.add(textField_new_2);
				
				JLabel label_new3 = new JLabel("Остаток на счете: ");
				label_new3.setHorizontalAlignment(SwingConstants.CENTER);
				panel_new.add(label_new3);
				
				textField_new_3 = new JTextField();
				textField_new_3.setHorizontalAlignment(SwingConstants.CENTER);
				textField_new_3.setColumns(10);
				panel_new.add(textField_new_3);
				
				Button button_new1 = new Button("OK");
				Button button_new2 = new Button("Cancel");
				
				panel_new.add(button_new1);
				panel_new.add(button_new2);
				button_new1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						ImageIcon icon = new ImageIcon();
						String[] str_list = new String[company.get_tarif_name().size()];
						for(int i = 0; i < str_list.length; i++) {
							str_list[i] = company.get_tarif_name().get(i).get_tarif_name();
						}
						//Выбрали тариф для абонента
						String tarif_is_selected = JOptionPane.showInputDialog(panel_new, "Выберите тариф: ", "", 1, icon, str_list, str_list[0]).toString();
						for(int i = 0; i < company.get_tarif_name().size(); i++) {
							if(company.get_tarif_name().get(i).get_tarif_name().equals(tarif_is_selected)) {
								Abonent abonent_new = new Abonent();
								abonent_new.set_FN(textField_new_1.getText());
								abonent_new.set_number(textField_new_2.getText());
								abonent_new.set_account(Integer.parseInt(textField_new_3.getText()));
								company.get_tarif_name().get(i).add_abonent(abonent_new);
								
								dispose();
								Jfrm Jfrm_update = new Jfrm(company);
								Jfrm_update.setVisible(true);
								break;
							}
						}
						
						jfrm_new.dispose();
						setEnabled(true);
					}
				});
				
				button_new2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						jfrm_new.dispose();
						setEnabled(true);
					}
				});
				jfrm_new.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				jfrm_new.addWindowListener( new WindowAdapter()
				{
				    public void windowClosing(WindowEvent e)
				    {
				        int result = JOptionPane.showConfirmDialog(
				        	jfrm_new,
				            "Вы уверены что хотите закончить операцию?",
				            "Exit Application",
				            JOptionPane.YES_NO_OPTION);
				 
				        if (result == JOptionPane.YES_OPTION) {
				        	setEnabled(true);
				        	jfrm_new.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				        }
				    }
				});
			}
		});
		///////////////////////////////////Изменение св-в//////////////////////////////////////////////////////
		JMenu menu_2 = new JMenu("Изменить св-ва...");
		edit.add(menu_2);
		
		JMenuItem MenuItem_3 = new JMenuItem("Тарифа");
		menu_2.add(MenuItem_3);
		
		MenuItem_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon();
				String[] str_list = new String[company.get_tarif_name().size()];
				for(int i = 0; i < str_list.length; i++) {
					str_list[i] = company.get_tarif_name().get(i).get_tarif_name();
				}
				String tarif_is_selected = JOptionPane.showInputDialog(contentPane, "Выберите тариф, который Вы хотите изменить: ", "", 1, icon, str_list, str_list[0]).toString();
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					if(company.get_tarif_name().get(i).get_tarif_name().equals(tarif_is_selected)) {
						int t = i;
						setEnabled(false);
						JFrame jfrm_new = new JFrame();
						jfrm_new.setVisible(true);
						jfrm_new.setResizable(false);
						jfrm_new.setTitle("Tarif's Changer");
						jfrm_new.setBounds(100, 100, 450, 300);
						contentPane_new = new JPanel();
						contentPane_new.setBorder(new EmptyBorder(5, 5, 5, 5));
						contentPane_new.setLayout(new BorderLayout(10, 10));
						jfrm_new.setContentPane(contentPane_new);
						
						JPanel panel_new = new JPanel();
						contentPane_new.add(panel_new);
						panel_new.setLayout(new GridLayout(0, 2, 0, 0));
						
						JLabel label_new1 = new JLabel("Новое название тарифа: ");
						label_new1.setHorizontalAlignment(SwingConstants.CENTER);
						panel_new.add(label_new1);
						
						textField_new_1 = new JTextField();
						textField_new_1.setHorizontalAlignment(SwingConstants.CENTER);
						textField_new_1.setColumns(10);
						textField_new_1.setText("");
						panel_new.add(textField_new_1);
						
						JLabel label_new2 = new JLabel("Новая стоимость разговора: ");
						label_new2.setHorizontalAlignment(SwingConstants.CENTER);
						panel_new.add(label_new2);
						
						textField_new_2 = new JTextField();
						textField_new_2.setHorizontalAlignment(SwingConstants.CENTER);
						textField_new_2.setColumns(10);
						textField_new_2.setText("");
						panel_new.add(textField_new_2);
						
						Button button_new1 = new Button("OK");
						Button button_new2 = new Button("Cancel");
						
						panel_new.add(button_new1);
						panel_new.add(button_new2);
						button_new1.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								if(company.get_tarif_name().get(t) instanceof PosecTarif) {
									PosecTarif NewPST = new PosecTarif(textField_new_1.getText(), Integer.parseInt(textField_new_2.getText()));
									for(int j = 0; j < company.get_tarif_name().get(t).amount_abonent(); j++) {
										NewPST.add_abonent(company.get_tarif_name().get(t).get_abonents().get(j));
									}
									
									company.get_tarif_name().set(t, NewPST);
									JOptionPane.showMessageDialog(panel_new, "Изменение прошло успешно!", "", JOptionPane.INFORMATION_MESSAGE);
									dispose();
									Jfrm Jfrm_update = new Jfrm(company);
									Jfrm_update.setVisible(true);
								}
								
								if(company.get_tarif_name().get(t) instanceof PominTarif) {
									PominTarif NewPMT = new PominTarif(textField_new_1.getText(), Integer.parseInt(textField_new_2.getText()));
									for(int j = 0; j < company.get_tarif_name().get(t).amount_abonent(); j++) {
										NewPMT.add_abonent(company.get_tarif_name().get(t).get_abonents().get(j));
									}
									
									company.get_tarif_name().set(t, NewPMT);
									JOptionPane.showMessageDialog(panel_new, "Изменение прошло успешно!", "", JOptionPane.INFORMATION_MESSAGE);
									dispose();
									Jfrm Jfrm_update = new Jfrm(company);
									Jfrm_update.setVisible(true);
								}
								jfrm_new.dispose();
								setEnabled(true);
							}
						});
						
						button_new2.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								jfrm_new.dispose();
								setEnabled(true);
							}
						});
					}
				}
			}
		});
		
		JMenuItem MenuItem_4 = new JMenuItem("Абонента");
		menu_2.add(MenuItem_4);
		
		MenuItem_4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon();
				int temp = 0;
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					for(int j = 0; j < company.get_tarif_name().get(i).amount_abonent(); j++) {
						temp++;
					}
				}
				String[] str_list = new String[temp];
				int k = 0;
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					for(int j = 0; j < company.get_tarif_name().get(i).amount_abonent(); j++) {
						str_list[k] = company.get_tarif_name().get(i).get_abonents().get(j).get_FN();
						k++;
					}
				}
				
				String abonent_is_selected = JOptionPane.showInputDialog(contentPane, "Выберите абонента, данные которого Вы хотите изменить: ", "", 1, icon, str_list, str_list[0]).toString();
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					for(int j = 0; j < company.get_tarif_name().get(i).amount_abonent(); j++) {
						if(company.get_tarif_name().get(i).get_abonents().get(j).get_FN().equals(abonent_is_selected)) {
							int t1 = i;
							int t2 = j;
							setEnabled(false);
							JFrame jfrm_new = new JFrame();
							jfrm_new.setVisible(true);
							jfrm_new.setResizable(false);
							jfrm_new.setTitle("Abonent's Changer");
							jfrm_new.setBounds(100, 100, 450, 300);
							contentPane_new = new JPanel();
							contentPane_new.setBorder(new EmptyBorder(5, 5, 5, 5));
							contentPane_new.setLayout(new BorderLayout(10, 10));
							jfrm_new.setContentPane(contentPane_new);
							
							JPanel panel_new = new JPanel();
							contentPane_new.add(panel_new);
							panel_new.setLayout(new GridLayout(0, 2, 0, 0));
							
							JLabel label_new1 = new JLabel("Новое ФИО: ");
							label_new1.setHorizontalAlignment(SwingConstants.CENTER);
							panel_new.add(label_new1);
							
							textField_new_1 = new JTextField();
							textField_new_1.setHorizontalAlignment(SwingConstants.CENTER);
							textField_new_1.setColumns(10);
							textField_new_1.setText("");
							panel_new.add(textField_new_1);
							
							JLabel label_new2 = new JLabel("Новый номер телефона: ");
							label_new2.setHorizontalAlignment(SwingConstants.CENTER);
							panel_new.add(label_new2);
							
							textField_new_2 = new JTextField();
							textField_new_2.setHorizontalAlignment(SwingConstants.CENTER);
							textField_new_2.setColumns(10);
							textField_new_2.setText("");
							panel_new.add(textField_new_2);
							
							JLabel label_new3 = new JLabel("Новый счет: ");
							label_new3.setHorizontalAlignment(SwingConstants.CENTER);
							panel_new.add(label_new3);
							
							textField_new_3 = new JTextField();
							textField_new_3.setHorizontalAlignment(SwingConstants.CENTER);
							textField_new_3.setColumns(10);
							textField_new_3.setText("");
							panel_new.add(textField_new_3);
							
							Button button_new1 = new Button("OK");
							Button button_new2 = new Button("Cancel");
							
							panel_new.add(button_new1);
							panel_new.add(button_new2);
							button_new1.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									Abonent new_abonent = new Abonent();
									new_abonent.set_FN(textField_new_1.getText());
									new_abonent.set_number(textField_new_2.getText());
									new_abonent.set_account(Integer.parseInt(textField_new_3.getText()));
									company.get_tarif_name().get(t1).get_abonents().set(t2, new_abonent);
									JOptionPane.showMessageDialog(panel_new, "Изменение прошло успешно!", "", JOptionPane.INFORMATION_MESSAGE);
									dispose();
									Jfrm Jfrm_update = new Jfrm(company);
									Jfrm_update.setVisible(true);
									
									jfrm_new.dispose();
									setEnabled(true);
								}
							});
							
							button_new2.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									jfrm_new.dispose();
									setEnabled(true);
								}
							});
						}
					}
				}
			}
		});
		JMenu menu_3 = new JMenu("Удалить...");
		edit.add(menu_3);
		
		JMenuItem MenuItem_5 = new JMenuItem("Тариф");
		menu_3.add(MenuItem_5);
		
		MenuItem_5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon();
				String[] str_list = new String[company.get_tarif_name().size()];
				for(int i = 0; i < str_list.length; i++) {
					str_list[i] = company.get_tarif_name().get(i).get_tarif_name();
				}
				String tarif_is_selected = JOptionPane.showInputDialog(contentPane, "Выберите тариф, который Вы хотите удалить: ", "", 1, icon, str_list, str_list[0]).toString();
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					if(company.get_tarif_name().get(i).get_tarif_name().equals(tarif_is_selected)) {
						company.remove_tarif(company.get_tarif_name().get(i));
						JOptionPane.showMessageDialog(contentPane, "Изменение прошло успешно!", "", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						Jfrm Jfrm_update = new Jfrm(company);
						Jfrm_update.setVisible(true);
					}
				}
			}
		});
		
		JMenuItem MenuItem_6 = new JMenuItem("Абонента");
		menu_3.add(MenuItem_6);
		
		MenuItem_6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon();
				int temp = 0;
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					for(int j = 0; j < company.get_tarif_name().get(i).amount_abonent(); j++) {
						temp++;
					}
				}
				String[] str_list = new String[temp];
				int k = 0;
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					for(int j = 0; j < company.get_tarif_name().get(i).amount_abonent(); j++) {
						str_list[k] = company.get_tarif_name().get(i).get_abonents().get(j).get_FN();
						k++;
					}
				}
				
				String abonent_is_selected = JOptionPane.showInputDialog(contentPane, "Выберите абонента, которого Вы хотите удалить: ", "", 1, icon, str_list, str_list[0]).toString();
				for(int i = 0; i < company.get_tarif_name().size(); i++) {
					for(int j = 0; j < company.get_tarif_name().get(i).amount_abonent(); j++) {
						if(company.get_tarif_name().get(i).get_abonents().get(j).get_FN().equals(abonent_is_selected)) {
							company.get_tarif_name().get(i).remove_abonent(company.get_tarif_name().get(i).get_abonents().get(j));
							JOptionPane.showMessageDialog(contentPane, "Изменение прошло успешно!", "", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							Jfrm Jfrm_update = new Jfrm(company);
							Jfrm_update.setVisible(true);
						}
					}
				}
			}
		});
	}
}
