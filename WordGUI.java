package Project4;
//package Project4;

import javax.swing.*;
import java.util.NoSuchElementException.*;

//import lab19.FileMenuHandler;

import java.io.*;

//import project1.TextFileInput;
//import project1.WordGUI;


import java.awt.BorderLayout;
import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
//Nicole Haller CSCI 212-11E
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.File;

import javax.swing.*;


public final class WordGUI extends JFrame {

	  //JFrame variables
	  static JFrame myFrame;
	  static WordGUI gui;
	  static Container cPane;
	  static public TextArea aBox, eBox, iBox, oBox, uBox, otherBox;
	   
	  //stringTokenizer variables
	  static StringTokenizer myTokens; //StringTokenizer is a predefined class in java as part of util package  
	  public static String line;
  
	   
	 //contructor of WordGUI that takes in file name that the user selected 
	 public WordGUI(String chosenFileName) {
		 initialize();
		 readWordsFromFile(chosenFileName);
	 }
	 
	 //contructor of WordGUI that adds one word entered into the GUI 
	 public WordGUI(String inputWord, int a) {
		 // clear all the boxes of the GUI so that it doesnt print the same words again 
		 aBox.setText("");
		 eBox.setText("");
		 iBox.setText("");
		 oBox.setText("");
		 uBox.setText("");
		 //after everything is cleared from first GUI, add word in
		 addWord(inputWord);
	 }
	 
	
	//initializes what the GUI looks like 
	public static void initialize() {	
	    myFrame = new JFrame();
	    //creates textArea for each box within the GUI
		myFrame.setSize(400,200); 
		myFrame.setLocation(100,100); 
		//set to close when click x
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

    //creates treeMap called name which takes two Words: the keys are the word, and the values are null
	//TreeMap is sorted alphabetically by the WordComparator class
	static TreeMap <Word, Word> name= new TreeMap<Word, Word>(new WordComparator());
	
	
	public static void treeIntoGUI(TreeMap<Word, Word> map) {
		//iterates through tree map and places words into correct box
		Set set = name.entrySet();
		Iterator i = set.iterator();
		Map.Entry<Word, Word> me;
		while (i.hasNext()) {
			me= (Map.Entry)i.next(); //makes "me" into next Map entry (key and value)
			//converts the word into a string
			String a= me.getKey().toString();
			//checks first letter of current word and places in correct box
			if (a.charAt(0)=='a' || a.charAt(0)=='A') {
				  aBox.append(a + "\n");
			}
			else if (a.charAt(0)=='e' || a.charAt(0)=='E') {
				  eBox.append(a + "\n");
			}
			else if (a.charAt(0)=='i' || a.charAt(0)=='I') {
				  iBox.append(a + "\n");
			}
			else if (a.charAt(0)=='o' || a.charAt(0)=='O') {
				  oBox.append(a + "\n");
			}
			else if (a.charAt(0)=='u' || a.charAt(0)=='U') {
				  uBox.append(a + "\n");
			}
			else {
				  otherBox.append(a + "\n");
			}
	     }
	  
		  myFrame.setVisible(true);
	}
	
	//takes words from file and sorts them using a treemap, then iterates thru treemap and puts words into GUI of 6 boxes
	public static void readWordsFromFile(String chosenFileName){ 
		   //creates GUI box layout of 2 rows and 3 columns 
		   myFrame.setLayout(new GridLayout(2,3));
		   cPane = myFrame.getContentPane();
		   //sets TextAreas for a, e, i, o, u, other
		   aBox = new TextArea(); 
		   eBox = new TextArea();
		   iBox = new TextArea();
		   oBox = new TextArea();
		   uBox = new TextArea();
		   otherBox = new TextArea();
		   
		   //adds each box to the cPane
		   cPane.add(aBox); 
		   cPane.add(eBox); 
		   cPane.add(iBox); 
		   cPane.add(oBox); 
		   cPane.add(uBox); 
		   cPane.add(otherBox); 
		  
		   //takes in the chosen file name 
		  TextFileInput tfi = new TextFileInput(chosenFileName);
		  
		  String line; //create a string variable to store next line of file
	      line = tfi.readLine(); //read first line of file
	      while (line!=null) { ///while there are still words in the file
	    	  myTokens = new StringTokenizer(line," ,. "); //create a stringTokenizer that will separate the words in the line by spaces, commas, or periods
	    		  while (myTokens.hasMoreTokens()) {
	    			  try {
	    				//make each separate string of the line into word types and add each valid word to tree map 
	    				  Word b= new Word(myTokens.nextToken());
	    				  name.put(b, null);
	    			  }
	    			  catch (IllegalWordException e) {
	    				//catches the word if its not valid and prints "invalid word:" + invalid word
	    				  System.out.println(e.getMessage());
	    			
	    			  }
	    		  }
	    		  line=tfi.readLine(); //read next line
	    		  
	      }
	      treeIntoGUI(name);
	}
	   
	
	   // adds users additional word to the GUI by adding it into tree map and then sorts into GUI alphabetically
	   public static void addWord(String inputWord) {	   
		   Word c;
		   try {
   			  //makes users input into a word type
   			  c= new Word(inputWord);
   			  //if valid, adds word to tree map 
   			  name.put(c, null);
   			}
   			catch (IllegalWordException e) {
				System.out.println(e.getMessage());
   			}
		   treeIntoGUI(name);
	   }
}