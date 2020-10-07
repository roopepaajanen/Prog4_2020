import java.util.List;
import java.util.Set;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private int albumAmount = 0;
	CommandManager commandManager;

	public MusicOrganizerController() {

		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");

		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);

		//någå fin comment
		commandManager = new CommandManager(view);

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
	public Album addNewAlbum() throws NullPointerException{
		// TODO: Add your code here
		Album newAlbum = null;
		try {
			String newAlbumName = view.promptForAlbumName();
			Album parentAlbum = view.getSelectedAlbum();
			 newAlbum = new Album(newAlbumName, parentAlbum);
			view.onAlbumAdded(newAlbum);
			albumAmount++;
			return newAlbum;
		}
		catch (NullPointerException e){
			view.showMessage("Select an album first!");
		}
		return newAlbum;
	}

	public void newAlbumCommandCommunicator(){
		addAlbumCommand command = new addAlbumCommand();
		commandManager.executeCommand(command);
	}

	private class addAlbumCommand implements Command{
		@Override
		public Container execute(){
			return new Container(addNewAlbum(), null);
		}

		@Override
		public void undo(Container album){
			view.onAlbumRemoved(album.getAlbumATM());
		}

		@Override
		public void redo(Container container) {
			view.onAlbumAdded(container.getAlbumATM());
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

	public void deleteAlbumCommandCommunicator(){
		deleteAlbumCommand command = new deleteAlbumCommand();
		commandManager.executeCommand(command);
	}

	private class deleteAlbumCommand implements Command{
		@Override
		public Container execute(){
			Container deleteAlbum = new Container(view.getSelectedAlbum(),null);
			deleteAlbum();
			return deleteAlbum;
		}

		@Override
		public void undo(Container album){
			view.onAlbumAdded(album.getAlbumATM());
		}

		@Override
		public void redo(Container container) {
			view.onAlbumRemoved(container.getAlbumATM());
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

	public void addSoundClipsCommandCommunicator(){
		addSoundClipsCommand command = new addSoundClipsCommand();
		commandManager.executeCommand(command);
	}

	private class addSoundClipsCommand implements Command{
		@Override
		public Container execute(){
			Container addSoundClip = new Container(view.getSelectedAlbum(),view.getSelectedSoundClips());
			addSoundClips();
			return addSoundClip;
		}

		@Override
		public void undo(Container clips){
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClip: clips.getAlbumATMSoundClips()) {
				temp.removeSoundClip(soundClip);
			}
		}

		@Override
		public void redo(Container clips) {
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClip: clips.getAlbumATMSoundClips()) {
				temp.addSoundClip(soundClip);
			}
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

	public void removeSoundClipsCommandCommunicator(){
		removeSoundClipsCommand command = new removeSoundClipsCommand();
		commandManager.executeCommand(command);
	}

	private class removeSoundClipsCommand implements Command{
		@Override
		public Container execute(){
			Container removeSoundClip = new Container(view.getSelectedAlbum(),view.getSelectedSoundClips());
			removeSoundClips();
			return removeSoundClip;
		}

		@Override
		public void undo(Container clips){
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClips: clips.getAlbumATMSoundClips()) {
				temp.addSoundClip(soundClips);
			}
		}

		@Override
		public void redo(Container clips) {
			Album temp = clips.getAlbumATM();
			for (SoundClip soundClip: clips.getAlbumATMSoundClips()) {
				temp.removeSoundClip(soundClip);
			}
		}
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

	public boolean isRedoAvailable(){
		return !commandManager.getRedoStack().empty();
	}

	public boolean isUndoAvailable(){
		return !commandManager.getUndoStack().empty();
	}

	public void isButtonAvailable(){
		view.sendButtonPossibility(isUndoAvailable(),isRedoAvailable());
	}

	public void undo(){
		commandManager.undo();
	}

	public void redo(){
		commandManager.redo();
	}
}
