import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private Album rate;
	private Album flag;
	CommandManager commandManager;

	public MusicOrganizerController() {

		// Create the root album for all sound clips
		root = new Album("All Sound Clips");
		// Create the rate album for the very guud sound clips
		rate = new Album("yes veri gud sundlips");
		// Create the flag album for the flagged sound clips
		flag = new Album("Flagged");

		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);

		//A very descriptive comment XD...
		commandManager = new CommandManager();

		// Create the blocking queue
		queue = new SoundClipBlockingQueue();

		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public void loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		// TODO: Add the loaded sound clips to the root album
		List<SoundClip> theClips = new ArrayList<>(clips);
		root.addSoundClips(theClips);

		System.out.println(root.getSoundClipsFromAlbum());
	}

	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}
	/**
	 * Returns the rate album
	 */
	public Album getRateAlbum(){
		return rate;
	}
	/**
	 * Returns the flag album
	 */
	public Album getFlagAlbum(){
		return flag;
	}

	//----------------------------------------------------------------------------------------------------------------
	/**
	 * Adds an album to the Music Organizer
	 */
	public Album addNewAlbum() throws NullPointerException{
		// TODO: Add your code here
		Album newAlbum = null;
		try {
			String newAlbumName = view.promptForAlbumName();
			Album parentAlbum = view.getSelectedAlbum();
			 newAlbum = new Album(newAlbumName, parentAlbum);
			view.onAlbumAdded(newAlbum);
			return newAlbum;
		}
		catch (NullPointerException e){
			view.showMessage("Select an album first!");
		}
		return newAlbum;
	}

	/**
	 * The respective button calls this method to then communicate between their respective Command method and the
	 * method that does the actual functionality
	 */
	public void newAlbumCommandCommunicator(){
		addAlbumCommand command = new addAlbumCommand();
		commandManager.executeCommand(command);
	}

	/**
	 * Implements the Command interface to perform either its normal execution, or to either undo or redo.
	 */
	private class addAlbumCommand implements Command{
		/**	Performs the normal execution */
		@Override
		public Container execute(){
			return new Container(addNewAlbum(), null);
		}

		/**	Performs the undo functionality */
		@Override
		public void undo(Container album){
			view.onAlbumRemoved(album.getAlbumATM());
		}

		/**	Performs the redo functionality */
		@Override
		public void redo(Container container) {
			view.onAlbumAdded(container.getAlbumATM());
		}
	}

	//----------------------------------------------------------------------------------------------------------------
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum() throws NullPointerException{
		//TODO: Add your code here
		try {
			Album deleteAlbum = view.getSelectedAlbum();
			if((deleteAlbum != root) || (deleteAlbum != rate) || (deleteAlbum != flag)) {
				view.onAlbumRemoved(deleteAlbum);
			}

		}
		catch (NullPointerException e){
			view.showMessage("Select an album first!");
		}
	}

	/**
	 * The respective button calls this method to then communicate between their respective Command method and the
	 * method that does the actual functionality
	 */
	public void deleteAlbumCommandCommunicator(){
		deleteAlbumCommand command = new deleteAlbumCommand();
		commandManager.executeCommand(command);
	}

	/**
	 * Implements the Command interface to perform either its normal execution, or to either undo or redo.
	 */
	private class deleteAlbumCommand implements Command{
		/**	Performs the normal execution */
		@Override
		public Container execute(){
			Container deleteAlbum = new Container(view.getSelectedAlbum(),null);
			deleteAlbum();
			return deleteAlbum;
		}

		/**	Performs the undo functionality */
		@Override
		public void undo(Container album){
			view.onAlbumAdded(album.getAlbumATM());
		}

		/**	Performs the redo functionality */
		@Override
		public void redo(Container container) {
			view.onAlbumRemoved(container.getAlbumATM());
		}
	}

	//----------------------------------------------------------------------------------------------------------------
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips() throws NullPointerException{
		// TODO: Add your code here
		try {
			view.getSelectedAlbum().addSoundClips(view.getSelectedSoundClips());
			view.onClipsUpdated();
		}
		catch (NullPointerException e){
			view.showMessage("Select an album first!");
		}
	}

	/**
	 * The respective button calls this method to then communicate between their respective Command method and the
	 * method that does the actual functionality
	 */
	public void addSoundClipsCommandCommunicator(){
		addSoundClipsCommand command = new addSoundClipsCommand();
		commandManager.executeCommand(command);
	}

	/**
	 * Implements the Command interface to perform either its normal execution, or to either undo or redo.
	 */
	private class addSoundClipsCommand implements Command{
		/**	Performs the normal execution */
		@Override
		public Container execute(){
			Container addSoundClip = new Container(view.getSelectedAlbum(),view.getSelectedSoundClips());
			addSoundClips();
			return addSoundClip;
		}

		/**	Performs the undo functionality */
		@Override
		public void undo(Container clips){
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClip: clips.getAlbumATMSoundClips()) {
				temp.removeSoundClip(soundClip);
			}
		}

		/**	Performs the redo functionality */
		@Override
		public void redo(Container clips) {
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClip: clips.getAlbumATMSoundClips()) {
				temp.addSoundClip(soundClip);
			}
		}
	}

	//----------------------------------------------------------------------------------------------------------------
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){
		// TODO: Add your code here
		view.getSelectedAlbum().removeSoundClips(view.getSelectedSoundClips());
		view.onClipsUpdated();
	}

	/**
	 * The respective button calls this method to then communicate between their respective Command method and the
	 * method that does the actual functionality
	 */
	public void removeSoundClipsCommandCommunicator(){
		removeSoundClipsCommand command = new removeSoundClipsCommand();
		commandManager.executeCommand(command);
	}

	/**
	 * Implements the Command interface to perform either its normal execution, or to either undo or redo.
	 */
	private class removeSoundClipsCommand implements Command{
		/**	Performs the normal execution */
		@Override
		public Container execute(){
			Container removeSoundClip = new Container(view.getSelectedAlbum(),view.getSelectedSoundClips());
			removeSoundClips();
			return removeSoundClip;
		}

		/**	Performs the undo functionality */
		@Override
		public void undo(Container clips){
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClips: clips.getAlbumATMSoundClips()) {
				temp.addSoundClip(soundClips);
			}
		}

		/**	Performs the redo functionality */
		@Override
		public void redo(Container clips) {
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClip: clips.getAlbumATMSoundClips()) {
				temp.removeSoundClip(soundClip);
			}
		}
	}

	//----------------------------------------------------------------------------------------------------------------
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

	/**	Checks if the redo stack is empty or not */
	public boolean isRedoAvailable(){
		return !commandManager.getRedoStack().empty();
	}

	/**	Checks if the undo stack is empty or not */
	public boolean isUndoAvailable(){
		return !commandManager.getUndoStack().empty();
	}

	/**	Sends the status from the undo and redo stacks checks forward to MusicOrganizerWindow*/
	public void isButtonAvailable(){
		view.sendButtonAvailability(isUndoAvailable(), isRedoAvailable());
	}

	/**	Invokes the undo functionality in the CommandManager class */
	public void undo(){
		commandManager.undo();
	}

	/**	Invokes the redo functionality in the CommandManager class */
	public void redo(){
		commandManager.redo();
	}
}
