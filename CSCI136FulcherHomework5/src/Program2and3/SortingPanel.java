package Program2and3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SortingPanel extends JPanel{
	
	//array of different income options
	String [] incomeLevelCB = {"$100,000.00 +","$70,000.00 - $99,999","$40000.00 - $69,999","$25,000.00 - $39,999","$10,000.00 - $24,999","$0.00 - $9,999"};

	JTextArea resultsArea = new JTextArea(30,50);
	
	//Student[] myStudents = new Student[25];
	ArrayList<Student> myStudents = new ArrayList<Student>();
	
	int index;
	
	JTextField nameSearchBox = new JTextField(10);
	JTextField ageSearchBox = new JTextField(10);
	JTextField aptitudeSearchBox = new JTextField(10);
	
	
	JTextField nameBox = new JTextField(10);
	JTextField ageBox = new JTextField(10);
	JComboBox incomeBox = new JComboBox(incomeLevelCB);
	JTextField aptitudeBox = new JTextField(10);

	public SortingPanel() throws FileNotFoundException{
		
		//sets size of frame
		setPreferredSize(new Dimension(1000, 650));
		
		//reads in file
		readIn();
		
		//adds the resultsarea
		add(resultsArea);
		
		//insertion sort
		AscendingSortButtonLIstener myDecendingButtonListener = new AscendingSortButtonLIstener();
		JButton AscendingSortButton = new JButton("Insertion Sort -  Ascending");
		AscendingSortButton.addActionListener(myDecendingButtonListener);
		
		add(AscendingSortButton);
		
		//selection sort
		DecendingSortButtonLIstener myDecendingSortButtonListener = new DecendingSortButtonLIstener();
		JButton DecendingSortButton = new JButton("Selection Sort - Decending");
		DecendingSortButton.addActionListener(myDecendingSortButtonListener);
		
		add(DecendingSortButton);
		
		//search stuff
		searchButtonListener mySearchListener = new searchButtonListener();
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(mySearchListener);
		
		JLabel searchName = new JLabel("Search - Name: ");
		add(searchName);
		add(nameSearchBox);
		JLabel searchAge = new JLabel("Age: ");
		add(searchAge);
		add(ageSearchBox);
		JComboBox searchCB = new JComboBox(incomeLevelCB);
		add(searchCB);
		JLabel searchApt = new JLabel("Aptitude: ");
		add(searchApt);
		add(aptitudeSearchBox);
		
		add(searchButton);
		
		//adding new student panel stuff
		JPanel insertPanel = new JPanel();
		add(insertPanel);
		
		JLabel nameLabel = new JLabel("Student Name: ");
		insertPanel.add(nameLabel);
		
		nameBox = new JTextField(10);
		insertPanel.add(nameBox);
		
		JLabel ageLabel = new JLabel("Student Age: ");
		insertPanel.add(ageLabel);
		
		ageBox = new JTextField(10);
		insertPanel.add(ageBox);
		
		JLabel incomeLevel = new JLabel("Select parents yearly income Level");
		insertPanel.add(incomeLevel);
		incomeBox = new JComboBox(incomeLevelCB);
		incomeLevelCBListener myIncomeListener = new incomeLevelCBListener();
		incomeBox.addActionListener(myIncomeListener);
		insertPanel.add(incomeBox);
		
		JLabel aptitudeLabel = new JLabel("Student Aptitude: ");
		insertPanel.add(aptitudeLabel);
		
		aptitudeBox = new JTextField(10);
		insertPanel.add(aptitudeBox);
		
		JButton addButton = new JButton("Add");
		addButtonListener myAddButtonListener = new addButtonListener();
		addButton.addActionListener(myAddButtonListener);
		add(addButton);
		
	}
	public class addButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			Student addedStudent = new Student(nameBox.getText(), Integer.parseInt(ageBox.getText()),incomeBox.getSelectedIndex(), 
					Integer.parseInt(aptitudeBox.getText()));

		myStudents.add(addedStudent);
		resultsArea.append(addedStudent.toString());
		}
		
	}
	
	public class searchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Student[] myStudentArray = new Student[myStudents.size()];
			for(int i = 0; i <myStudents.size();i++)
			{
				myStudentArray[i] = myStudents.get(i);
			}
			
			Student searchStudent = new Student(nameSearchBox.getText(),Integer.parseInt(ageSearchBox.getText()),incomeBox.getSelectedIndex(),Integer.parseInt(aptitudeSearchBox.getText()));
			Comparable x = Sorting.binarySearch(myStudentArray ,searchStudent);
			resultsArea.setText("");
			resultsArea.append(searchStudent.toString());
			System.out.println(x);
			//resultsArea.setText(x);
		}
	}
	
	public class AscendingSortButtonLIstener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Sorting.insertionSort(myStudents);
			
			resultsArea.setText("");
			
			resultsArea.append(myStudents.toString());
		} 
		
	}
	
	public class DecendingSortButtonLIstener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			resultsArea.setText("");
			Sorting.selectionSort(myStudents);
			

			resultsArea.append(myStudents.toString());
		} 
		
	}
	
	//income level combo box listener
	public class incomeLevelCBListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	
	public void readIn() throws FileNotFoundException
	{
		File myFile = new File("students.txt");
		Scanner myScanner = new Scanner(myFile);
		
		while (myScanner.hasNext())
		{
			String myLine = myScanner.nextLine();
			
			// create another scanner for the string
			Scanner myLineScan = new Scanner(myLine);

			myLineScan.useDelimiter(",");
			
			while (myLineScan.hasNext()) { 
				
				String name = myLineScan.next();
				int age = myLineScan.nextInt();
				int incomeLevelIndex = myLineScan.nextInt();
				int aptitude = myLineScan.nextInt();
				
				Student myStudent = new Student(name,age,incomeLevelIndex, aptitude);
				//myStudents[index] = myStudent;
				myStudents.add(myStudent);
				//System.out.println(myStudents[index]);
				
				//print in JTextArea
				//resultsArea.append(myStudents[index].toString());
				//resultsArea.append(myStudents.toString());
			}
		
			index++;
		}
		resultsArea.append(myStudents.toString());
	}
}
