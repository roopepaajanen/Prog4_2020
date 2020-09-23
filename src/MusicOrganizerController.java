import java.util.List;
import java.util.Set;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private UserInputGUI IO = new UserInputGUI(); //input reader
	private int albumAmount = 0;

	public MusicOrganizerController() {

		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");

		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);

		// Create the blocking queue
		queue = new SoundClipBlockingQueue();

		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		// TODO: Add the loaded sound clips to the root album
		root.albumSongs.addAll(clips);
		System.out.println(root.albumSongs);
		return clips;
	}

	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}

	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){
		// TODO: Add your code here
		String newAlbumName = view.promptForAlbumName();
		Album parentAlbum = view.getSelectedAlbum();
		Album newAlbum = new Album(newAlbumName, parentAlbum);
		System.out.println(newAlbum.getAlbumName());
		view.onAlbumAdded(newAlbum);
		albumAmount++;
		
	}

	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ //TODO Update parameters if needed
		Album deleteAlbum = view.getSelectedAlbum();

		if(albumAmount>0) {
			view.onAlbumRemoved(deleteAlbum);
			albumAmount--;
		}
		else{
			System.out.println("Create first an album");
		}
	}

	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
	}

	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
	}

	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		for(int i=0;i<l.size();i++)
			queue.enqueue(l.get(i));
	}
}
