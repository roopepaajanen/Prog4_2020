import java.util.List;
import java.util.Set;
import java.util.Stack;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private int albumAmount = 0;
	private Stack<Object> undoStack = new Stack<Object>();
	private Stack<Object> redoStack = new Stack<Object>();

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
		root.getSoundClipsFromAlbum().addAll(clips);
		System.out.println(root.getSoundClipsFromAlbum());
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
	public void addNewAlbum() throws NullPointerException{
		// TODO: Add your code here
		try {
			String newAlbumName = view.promptForAlbumName();
			Album parentAlbum = view.getSelectedAlbum();
			Album newAlbum = new Album(newAlbumName, parentAlbum);
			view.onAlbumAdded(newAlbum);
			albumAmount++;
		}
		catch (NullPointerException e){
			view.showMessage("Select an album first!");
		}
	}

	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum() throws NullPointerException{
		//TODO: Add your code here
		try {
			Album deleteAlbum = view.getSelectedAlbum();
			if (albumAmount > 0) {
				view.onAlbumRemoved(deleteAlbum);
				albumAmount--;
			}
			if(deleteAlbum == root){
				view.showMessage("You can't delete the root album :(");
			}
		}
		catch (NullPointerException e){
			view.showMessage("Select an album first!");
		}
	}

	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips() throws NullPointerException{
		// TODO: Add your code here
		try {
			for (SoundClip clip : view.getSelectedSoundClips()){
				view.getSelectedAlbum().addSoundClip(clip);
			}
			view.onClipsUpdated();
		}
		catch (NullPointerException e){
			view.showMessage("Select an album first!");
		}
	}

	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){
		// TODO: Add your code here
		for (SoundClip clip : view.getSelectedSoundClips()) {
			view.getSelectedAlbum().removeSoundClip(clip);
		}
		view.onClipsUpdated();
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

	private int undoRedoPointer = -1;
	private Stack<command> commandStack = new Stack<>();

	public void insertCommand(){
		deleteElementsAfterPointer(undoRedoPointer);
		command command = new insertCharacterCommand();
		command.execute();
		commandStack.push(command);
		undoRedoPointer++;
	}

	private void deleteElementsAfterPointer(int undoRedoPointer) {

		if(commandStack.size()<1) {
			return;
		}

		for(int i = commandStack.size() - 1; i > undoRedoPointer; i--){
			commandStack.remove(i);
		}
	}

	public void undo(){
		command command = commandStack.get(undoRedoPointer);
		command.unExecute();
		undoRedoPointer--;
	}

	public void redo(){
		if (undoRedoPointer == commandStack.size() - 1){
			return;
		}
		undoRedoPointer++;
		command command = commandStack.get(undoRedoPointer);
		command.execute();
	}
}
