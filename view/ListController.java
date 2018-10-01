package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
//import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class ListController {
	
	//int press = 1;
	
	@FXML         
	ListView<String> listView;    
	
	@FXML
	Text title;
	
	@FXML
	Button buttonEdit, buttonSaveAdd, buttonCancelAdd, buttonSaveEdit, buttonCancelEdit, buttonAdd, buttonDelete;
	
	@FXML
	TextField titledata, artistdata, albumdata, yeardata;

	

	private ObservableList<String> obsList;              

	public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList  
		obsList = FXCollections.observableArrayList(); 
		
		File file = new File("src/song_list.txt"); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			// Alert alert = 
			//         new Alert(AlertType.INFORMATION);
			String st; 
			
			int list_index = 0;
			
			try {
				while ((st = br.readLine()) != null) {
				    //String str = "test";
					obsList.add(list_index, st);
					//System.out.println(st);
					list_index++;
					//System.out.println(list_index);
					
				}
				
			} catch (IOException ea) {
				// TODO Auto-generated catch block
				ea.printStackTrace();
			} 
			  
			
		}
		catch(FileNotFoundException e) {
			try {			
				file.createNewFile();
			}
			catch(IOException io) {
				
			}
		}
		

		listView.setItems(obsList); 
		
		// select the first item
	      listView.getSelectionModel().select(0);
	      
	      listView.setDisable(false);
	      buttonCancelEdit.setDisable(true);
	      buttonSaveEdit.setDisable(true);
	      
	      buttonCancelAdd.setVisible(true);
	      buttonSaveAdd.setVisible(true);
	      buttonCancelAdd.setDisable(true);
	      buttonSaveAdd.setDisable(true);
	      
	      titledata.setEditable(false);
	      artistdata.setEditable(false);
	      albumdata.setEditable(false);
	      yeardata.setEditable(false);
	      
	      newShowItem();

	      // set listener for the items
	      listView
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener(
	           (obs, oldVal, newVal) -> 
	               newShowItem());
	      
	      

	}
	
	
	//Update the text with the currently selected string
	private void newShowItem() {
		
		//String content = "Title: " + listView.getSelectionModel().getSelectedIndex() + "\nArtist: " + listView.getSelectionModel().getSelectedItem()
		//		+ "\nAlbum: " + listView.getSelectionModel().getSelectedItem()+ "\nYear: " + listView.getSelectionModel().getSelectedItem();
		//title.setText(content);
		
		//titledata.setText(listView.getSelectionModel().getSelectedItem());
		titledata.setText(listView.getSelectionModel().getSelectedItem());
		artistdata.setText(listView.getSelectionModel().getSelectedItem());
		albumdata.setText(listView.getSelectionModel().getSelectedItem());
		yeardata.setText(listView.getSelectionModel().getSelectedItem());
	}



	
	@FXML protected void handleAddAction(ActionEvent event) {
		
		//Code to go to first place (Should be changed to sorted place afterwords
		listView.setDisable(true);
		buttonEdit.setDisable(true);
	
		buttonCancelAdd.setDisable(false);
	    buttonSaveAdd.setDisable(false);
	    buttonCancelEdit.setDisable(true);
	    buttonSaveEdit.setDisable(true);
	    
	    buttonCancelEdit.setVisible(false);
	    buttonSaveEdit.setVisible(false);	    
	    buttonCancelAdd.setVisible(true);
	    buttonSaveAdd.setVisible(true);

	    
	    buttonAdd.setDisable(true);
	    buttonDelete.setDisable(true);
//	    buttonSave.setVisible(false);
	    
	    
		
		//String item = "Enter song name";
		//int index = 0;
		
		//titledata.addActionListener(this);
		
		
		titledata.setEditable(true);
	    artistdata.setEditable(true);
	    albumdata.setEditable(true);
	    yeardata.setEditable(true);
		
		titledata.setText("Enter song name");
		/*
		press = 1;
		while (press == 1) {
			
		try {
			wait();x`
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
		buttonCancelAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				press = 2;
				// TODO Auto-generated method stub
				
			}
			
		});
		*/
		
		//buttonCancelEdit.setOnAction(buttonHandler);
		
		
		//Dialog box to get new namex
		/*
		TextInputDialog dialog = new TextInputDialog(item);
	      dialog.setTitle("List Item");
	      dialog.setHeaderText("Selected Item (Index: " + index + ")");
	      dialog.setContentText("Enter name: ");
		 
	      Optional<String> result = dialog.showAndWait();
	      if (result.isPresent()) { obsList.add(index, result.get());}
	      
	      */
	 
		
		 
    }
	
	@FXML protected void handleEditAction(ActionEvent event) {
		
		 //Code to find item
		
		listView.setDisable(true);
		buttonEdit.setDisable(true);
	
		buttonCancelAdd.setDisable(true);
	    buttonSaveAdd.setDisable(true);
	    buttonCancelEdit.setDisable(false);
	    buttonSaveEdit.setDisable(false);
	    
	    buttonCancelEdit.setVisible(true);
	    buttonSaveEdit.setVisible(true);	    
	    buttonCancelAdd.setVisible(false);
	    buttonSaveAdd.setVisible(false);

	    
	    buttonAdd.setDisable(true);
	    buttonDelete.setDisable(true);
//	    buttonSave.setVisible(false);
	    
	    
		
		//String item = "Enter song name";
		//int index = 0;
		
		//titledata.addActionListener(this);
		
		
		titledata.setEditable(true);
	    artistdata.setEditable(true);
	    albumdata.setEditable(true);
	    yeardata.setEditable(true);


	      //Dialog box for new text
	      
	      
	      
	      //Code to save text
	     
   }
	
	@FXML protected void handleDeleteAction(ActionEvent event) {
		
		  //Finds current index and deletes

	      int index = listView.getSelectionModel().getSelectedIndex();
	      
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Delete Confirmation");
	      alert.setHeaderText("You are trying to delete a song");
	      alert.setContentText("Are you sure you want to do this?");

	      Optional<ButtonType> result = alert.showAndWait();
	      if (result.get() == ButtonType.OK){
	    	  obsList.remove(index); 
	      } else {
	          // ... user chose CANCEL or closed the dialog
	      }
	      
	      
	      
	      
	      //Code under here saves the file
	      
	      BufferedWriter writer;
			FileWriter file;
				try {
					file = new FileWriter("src/song_list.txt");
					
					 writer = new BufferedWriter(file);
					 //writer.write(' ');
					 	
						
						//int list_index = 0;
						int list_size = obsList.size();
						
						for (int i = 0; i < list_size; i++) {
						    //String str = "test";
							try {
								String st = obsList.get(i); 
								//System.out.println("hi");
								if (i == 0) {
									writer.write(st);
									writer.write('\n');
								}
								else {
									writer.append(st);
									writer.write('\n');
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//System.out.println(st);
							//list_index++;
							//System.out.println(list_index);
							
						}
						writer.close();
							
					 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 


				newShowItem();
	     
  }
	
	@FXML protected void handleSaveEditAction(ActionEvent event) {
		
		 String item = titledata.getText();
	     int index = listView.getSelectionModel().getSelectedIndex();
		
	     
	      obsList.set(index, item);
		
		 BufferedWriter writer;
			FileWriter file;
				try {
					file = new FileWriter("src/song_list.txt");
					
					 writer = new BufferedWriter(file);
					 //writer.write(' ');
					 	
						
						//int list_index = 0;
						int list_size = obsList.size();
						
						for (int i = 0; i < list_size; i++) {
						    //String str = "test";
							try {
								String st = obsList.get(i); 
								//System.out.println("hi");
								if (i == 0) {
									writer.write(st);
									writer.write('\n');
								}
								else {
									writer.append(st);
									writer.write('\n');
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							//System.out.println(st);
							//list_index++;
							//System.out.println(list_index);
							
						}
						writer.close();
							
					 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				newShowItem();
		
		
		titledata.setEditable(false);
		artistdata.setEditable(false);
		albumdata.setEditable(false);
		yeardata.setEditable(false);
		
		
		buttonCancelEdit.setDisable(true);
	    buttonSaveEdit.setDisable(true);
		
		listView.setDisable(false);
		buttonEdit.setDisable(false);
		buttonAdd.setDisable(false);
	    buttonDelete.setDisable(false);
	}


	@FXML protected void handleCancelEditAction(ActionEvent event) {
		titledata.setEditable(false);
		artistdata.setEditable(false);
		albumdata.setEditable(false);
		yeardata.setEditable(false);
		
		newShowItem();
		
		buttonCancelEdit.setDisable(true);
	    buttonSaveEdit.setDisable(true);
		
		listView.setDisable(false);
		buttonEdit.setDisable(false);
		buttonAdd.setDisable(false);
	    buttonDelete.setDisable(false);
		
		
	}
	
	
	
	@FXML protected void handleSaveAddAction(ActionEvent event) {
		
		int index = 0;
		
		String item = titledata.getText();
		
		obsList.add(index, item);
		
		   //Code to save file
	      BufferedWriter writer;
		FileWriter file;
			try {
				file = new FileWriter("src/song_list.txt");
				
				 writer = new BufferedWriter(file);
				 //writer.write(' ');
				 	
					
					//int list_index = 0;
					int list_size = obsList.size();
					
					for (int i = 0; i < list_size; i++) {
					    //String str = "test";
						try {
							String st = obsList.get(i); 
							//System.out.println("hi");
							if (i == 0) {
								writer.write(st);
								writer.write('\n');
							}
							else {
								writer.append(st);
								writer.write('\n');
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//System.out.println(st);
						//list_index++;
						//System.out.println(list_index);
						
					}
					writer.close();
						
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			titledata.setEditable(false);
		    artistdata.setEditable(false);
		    albumdata.setEditable(false);
		    yeardata.setEditable(false);
			
			newShowItem();
			
			
			buttonCancelAdd.setDisable(true);
		    buttonSaveAdd.setDisable(true);
			
			listView.setDisable(false);
			buttonEdit.setDisable(false);
			buttonAdd.setDisable(false);
		    buttonDelete.setDisable(false);
		
	}
	
	@FXML protected void handleCancelAddAction(ActionEvent event) {
		
			titledata.setEditable(false);
			artistdata.setEditable(false);
			albumdata.setEditable(false);
			yeardata.setEditable(false);
			
			newShowItem();
			
			buttonCancelAdd.setDisable(true);
		    buttonSaveAdd.setDisable(true);
			
			listView.setDisable(false);
			buttonEdit.setDisable(false);
			buttonAdd.setDisable(false);
		    buttonDelete.setDisable(false);
		
	}




}


