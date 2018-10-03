
package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
//import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class ListController {
	
	//int press = 1;
	
	@FXML         
	ListView<Song> listView;    
	
	@FXML
	Text title;
	
	@FXML
	Button buttonEdit, buttonSaveAdd, buttonCancelAdd, buttonSaveEdit, buttonCancelEdit, buttonAdd, buttonDelete;
	
	@FXML
	TextField titledata, artistdata, albumdata, yeardata;

	

	//private ObservableList<String> obsList;     
	private ObservableList<Song> listSongs;

	public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList  
		
		String csvFile = "src/song_list.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        listSongs = FXCollections.observableArrayList();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {


                String[] details = line.split(cvsSplitBy);
                
                Song temp = new Song(details[0], details[1], details[2], details[3]);
                listSongs.add(temp);
                

                

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.sort(listSongs);
        
       
		
		  listView.setItems(listSongs); 
		
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
	      

	}
	
	
	@FXML public void handleMouseClick(MouseEvent arg0) {
		newShowItem();
	}
	
	
	//Update the text with the currently selected string
	private void newShowItem() {
		

		//System.out.println(listView.getSelectionModel().getSelectedIndex());
		
		if(listSongs.size() == 0) {
			titledata.setText(" ");
			artistdata.setText(" ");
			albumdata.setText(" ");
			yeardata.setText(" ");
		}
		else {
		
			titledata.setText(listSongs.get(listView.getSelectionModel().getSelectedIndex()).getTitle());
			artistdata.setText(listSongs.get(listView.getSelectionModel().getSelectedIndex()).getArtist());
			albumdata.setText(listSongs.get(listView.getSelectionModel().getSelectedIndex()).getAlbum());
			yeardata.setText(listSongs.get(listView.getSelectionModel().getSelectedIndex()).getYear());
		}

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

	    

		
		titledata.setEditable(true);
	    artistdata.setEditable(true);
	    albumdata.setEditable(true);
	    yeardata.setEditable(true);
		
		titledata.setText("");
		artistdata.setText("");
		albumdata.setText("");
		yeardata.setText("");

		for(Song i : listSongs) {
			if (i.getTitle().equalsIgnoreCase(titledata.getText())&&i.getArtist().equalsIgnoreCase(artistdata.getText())){
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Invalid Input");
				alert.setHeaderText("Please enter a song that is not already in the library!");
				Optional<ButtonType> result = alert.showAndWait();
				if (!(result.get() == ButtonType.OK)){
				}
			}
		}
		
		 
    }
	
	@FXML protected void handleEditAction(ActionEvent event) {
		
		 //Code to find item
		
		// Collections.sort(listSongs);
		
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

	    
	    

		
		
		titledata.setEditable(true);
	    artistdata.setEditable(true);
	    albumdata.setEditable(true);
	    yeardata.setEditable(true);


	      //Dialog box for new text
	      
		if(isStringNullOrWhiteSpace(titledata.getText())||isStringNullOrWhiteSpace(artistdata.getText())) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Please enter values for the Title and Artist!");
			Optional<ButtonType> result = alert.showAndWait();
		      if (!(result.get() == ButtonType.OK)){
		      }
		}
		

	    
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
	    	  listSongs.remove(index); 
	      } else {
	          // ... user chose CANCEL or closed the dialog
	      }
	      
	      
	      
	      
	      //Code under here saves the file
	      
	      BufferedWriter writer;
			FileWriter file;
				try {
					file = new FileWriter("src/song_list.csv");
					
					 writer = new BufferedWriter(file);

						int list_size = listSongs.size();
						
						for (int i = 0; i < list_size; i++) {
						    //String str = "test";
							try {
								Song st = listSongs.get(i); 
								//System.out.println("hi");
								if (i == 0) {
									writer.write(st.csvString());
									writer.newLine();
								}
								else {
									writer.append(st.csvString());
									writer.newLine();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
						}
						writer.close();
							
					 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 


				newShowItem();
	     
  }
	
	@FXML protected void handleSaveEditAction(ActionEvent event) {
		boolean valid = true;
		
		
		if(isStringNullOrWhiteSpace(titledata.getText())||isStringNullOrWhiteSpace(artistdata.getText())) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Please enter values for the Title and Artist!");
			Optional<ButtonType> result = alert.showAndWait();
		      if (!(result.get() == ButtonType.OK)){
		      }
		}
		if(!(isStringNullOrWhiteSpace(titledata.getText())||isStringNullOrWhiteSpace(artistdata.getText()))) {
			//String item = titledata.getText();
			String titleText = ""; 
			String artistText = ""; 
			String albumText = ""; 
			String yearText = "";
			if(titledata.getText().equals("")) {
				titleText = " ";
			}
			else if(!titledata.getText().equals("")) {
				titleText = titledata.getText();
			}
			if(artistdata.getText().equals("")) {
				artistText = " ";
			}
			else if(!artistdata.getText().equals("")) {
				artistText = artistdata.getText();
			}
			if(albumdata.getText().equals("")) {
				albumText = " ";
			}
			else if(!albumdata.getText().equals("")) {
				albumText = albumdata.getText();
			}
			if(yeardata.getText().equals("")) {
				yearText = " ";
			}
			else if(!yeardata.getText().equals("")) {
				yearText = yeardata.getText();
			}
			
			
			for(Song i : listSongs) {
				if (i.getTitle().equalsIgnoreCase(titledata.getText())&&i.getArtist().equalsIgnoreCase(artistdata.getText())){
					valid = false;
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Invalid Input");
					alert.setHeaderText("Please enter a song that is not already in the library!");
					Optional<ButtonType> result = alert.showAndWait();
					if (!(result.get() == ButtonType.OK)){
					}
				}
			}
			
			if(valid) {
			Song temp = new Song(titleText, artistText, albumText, yearText);
			 
		     int index = listView.getSelectionModel().getSelectedIndex();
			
		     
		      listSongs.set(index, temp);
			
			 BufferedWriter writer;
				FileWriter file;
					try {
						file = new FileWriter("src/song_list.csv");
						
						 writer = new BufferedWriter(file);

							int list_size = listSongs.size();
							
							for (int i = 0; i < list_size; i++) {

								try {
									Song st = listSongs.get(i); 
									//System.out.println("hi");
									if (i == 0) {
										writer.write(st.csvString());
										writer.write('\n');
									}
									else {
										writer.append(st.csvString());
										writer.write('\n');
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								

								
							}
							writer.close();
								
						 
					} catch (IOException e1) {

						e1.printStackTrace();
					} 
					

			        Collections.sort(listSongs);
					
			        
			        int index1 = listSongs.indexOf(temp);
				    
				    listView.getSelectionModel().select(index1);
				    
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
		}
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
		boolean valid = true;
		
		if(isStringNullOrWhiteSpace(titledata.getText())||isStringNullOrWhiteSpace(artistdata.getText())) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Please enter values for the Title and Artist!");
			Optional<ButtonType> result = alert.showAndWait();
		      if (!(result.get() == ButtonType.OK)){
		      }
		}
		if(!(isStringNullOrWhiteSpace(titledata.getText())||isStringNullOrWhiteSpace(artistdata.getText()))) {
			//String item = titledata.getText();
			String titleText = ""; 
			String artistText = ""; 
			String albumText = ""; 
			String yearText = "";
			if(titledata.getText().equals("")) {
				titleText = " ";
			}
			else if(!titledata.getText().equals("")) {
				titleText = titledata.getText();
			}
			if(artistdata.getText().equals("")) {
				artistText = " ";
			}
			else if(!artistdata.getText().equals("")) {
				artistText = artistdata.getText();
			}
			if(albumdata.getText().equals("")) {
				albumText = " ";
			}
			else if(!albumdata.getText().equals("")) {
				albumText = albumdata.getText();
			}
			if(yeardata.getText().equals("")) {
				yearText = " ";
			}
			else if(!yeardata.getText().equals("")) {
				yearText = yeardata.getText();
			}
			
			for(Song i : listSongs) {
				if (i.getTitle().equalsIgnoreCase(titledata.getText())&&i.getArtist().equalsIgnoreCase(artistdata.getText())){
					valid = false;
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Invalid Input");
					alert.setHeaderText("Please enter a song that is not already in the library!");
					Optional<ButtonType> result = alert.showAndWait();
					if (!(result.get() == ButtonType.OK)){
					}
				}
			}
			
			if(valid) {
				
			
			Song temp = new Song(titleText, artistText, albumText, yearText);
			
			listSongs.add(temp);
			

		      BufferedWriter writer;
			FileWriter file;
				try {
					file = new FileWriter("src/song_list.csv");
					
					 writer = new BufferedWriter(file);
					 //writer.write(' ');
					 	
						
						//int list_index = 0;
						int list_size = listSongs.size();
						
						for (int i = 0; i < list_size; i++) {
						    //String str = "test";
							try {
								Song st = listSongs.get(i); 
								//System.out.println("hi");
								if (i == 0) {
									writer.write(st.csvString());
									writer.write('\n');
								}
								else {
									writer.append(st.csvString());
									writer.write('\n');
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
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
				
			    Collections.sort(listSongs);
			    
			    int index = listSongs.indexOf(temp);
			    
			    listView.getSelectionModel().select(index);
			    
				newShowItem();
				
				
				buttonCancelAdd.setDisable(true);
			    buttonSaveAdd.setDisable(true);
				
				listView.setDisable(false);
				buttonEdit.setDisable(false);
				buttonAdd.setDisable(false);
			    buttonDelete.setDisable(false);
			    
			}

		}
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
	
	public static boolean isStringNullOrWhiteSpace(String value) {
	    if (value == null) {
	        return true;
	    }

	    for (int i = 0; i < value.length(); i++) {
	        if (!Character.isWhitespace(value.charAt(i))) {
	            return false;
	        }
	    }

	    return true;
	}




}