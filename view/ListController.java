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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ListController {

	@FXML         
	ListView<String> listView;    
	
	@FXML
	Text title;

	


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
			  
			      //alert.initModality(Modality.NONE);
			//      alert.initOwner(mainStage);
			 //     alert.setTitle("List Item");
			 //     alert.showAndWait();
			
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
	      
	      newShowItem(mainStage);

	      // set listener for the items
	      listView
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener(
	           (obs, oldVal, newVal) -> 
	               newShowItem(mainStage));
	      
	      

	}
	
	private void newShowItem(Stage mainstage) {
		
		String content = "Title: " + listView.getSelectionModel().getSelectedIndex() + "\nArtist: " + listView.getSelectionModel().getSelectedItem()
				+ "\nAlbum: " + listView.getSelectionModel().getSelectedItem()+ "\nYear: " + listView.getSelectionModel().getSelectedItem();
		title.setText(content);
		
	}
	
	private void showItem(Stage mainStage) {                
	      Alert alert = 
	         new Alert(AlertType.INFORMATION);
	      //alert.initModality(Modality.NONE);
	      alert.initOwner(mainStage);
	      alert.setTitle("List Item");
	      alert.setHeaderText(
	           "Selected list item properties");

	      String content = "Index: " + 
	          listView.getSelectionModel()
	                   .getSelectedIndex() + 
	          "\nValue: " + 
	          listView.getSelectionModel()
	                   .getSelectedItem();

	          alert.setContentText(content);
	          alert.showAndWait();
	          //System.out.println("not blocking");
	   }
	/*
	private void showItemInputDialog(Stage mainStage) {                
	      String item = listView.getSelectionModel().getSelectedItem();
	      int index = listView.getSelectionModel().getSelectedIndex();

	      TextInputDialog dialog = new TextInputDialog(item);
	      dialog.initOwner(mainStage); dialog.setTitle("List Item");
	      dialog.setHeaderText("Selected Item (Index: " + index + ")");
	      dialog.setContentText("Enter name: ");

	      Optional<String> result = dialog.showAndWait();
	      if (result.isPresent()) { obsList.set(index, result.get()); }
	   }
	   
	   */
	
	@FXML protected void handleAddAction(ActionEvent event) {
		String item = "Enter song name";
		int index = 0;
		
		TextInputDialog dialog = new TextInputDialog(item);
	      dialog.setTitle("List Item");
	      dialog.setHeaderText("Selected Item (Index: " + index + ")");
	      dialog.setContentText("Enter name: ");
		 
	      Optional<String> result = dialog.showAndWait();
	      if (result.isPresent()) { obsList.add(index, result.get());}
	      
	     
	      
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
		
		 
    }
	
	@FXML protected void handleEditAction(ActionEvent event) {
		 String item = listView.getSelectionModel().getSelectedItem();
	      int index = listView.getSelectionModel().getSelectedIndex();

	      TextInputDialog dialog = new TextInputDialog(item);
	      dialog.setTitle("List Item");
	      dialog.setHeaderText("Selected Item (Index: " + index + ")");
	      dialog.setContentText("Enter name: ");

	      Optional<String> result = dialog.showAndWait();
	      if (result.isPresent()) { obsList.set(index, result.get()); }
	      
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
   }
	
	@FXML protected void handleDeleteAction(ActionEvent event) {
		 String item = listView.getSelectionModel().getSelectedItem();
	      int index = listView.getSelectionModel().getSelectedIndex();
	      
	      obsList.remove(index); 
	      
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


	
	     
  }



}


