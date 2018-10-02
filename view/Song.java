package view;

public class Song implements Comparable<Song> {

	String title;
	String artist;
	String album;
	String year;
	
	public Song(String title, String artist, String album, String year) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
	    return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getYear() {
		return year;
	}
	
	public String toString()
	{
	      return title + "  by  " + artist;
	}
	
	public String csvString()
	{
	      return title + "," + artist + "," + album + "," + year;
	}
	
	
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public int compareTo(Song u) {
	    if (getTitle() == null || u.getTitle() == null) {
	      return 0;
	    }
	    
	    if (getTitle().compareTo(u.getTitle()) == 0) {
	    	return getArtist().compareTo(u.getArtist());
	    }
	    return getTitle().compareTo(u.getTitle());
	}
	
}
