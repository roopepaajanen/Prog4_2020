import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MusicOrganizerButtonPanel extends JPanel {

	private MusicOrganizerController controller;
	private MusicOrganizerWindow view;
	
	private JButton newAlbumButton;
	private JButton deleteAlbumButton;
	private JButton addSoundClipsButton;
	private JButton removeSoundClipsButton;	
	private JButton playButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton flagButton;
	private JButton rateButton;


	
	public MusicOrganizerButtonPanel(MusicOrganizerController contr, MusicOrganizerWindow view){
		super(new BorderLayout());

		controller = contr;
		
		this.view = view;
		
		JToolBar toolbar = new JToolBar();
		
		newAlbumButton = createNewAlbumButton();
		toolbar.add(newAlbumButton);

		deleteAlbumButton = createDeleteAlbumButton();
		toolbar.add(deleteAlbumButton);

		addSoundClipsButton = createAddSoundClipsButton();
		toolbar.add(addSoundClipsButton);

		removeSoundClipsButton = createRemoveSoundClipsButton();
		toolbar.add(removeSoundClipsButton);

		playButton = createPlayButton();
		toolbar.add(playButton);

		undoButton = createUndoButton();
		toolbar.add(undoButton);
		undoButton.setEnabled(false);

		redoButton = createRedoButton();
		toolbar.add(redoButton);
		redoButton.setEnabled(false);

		flagButton = createFlagButton();
		toolbar.add(flagButton);

		rateButton = createRateButton();
		toolbar.add(rateButton);

		this.add(toolbar);

	}
	
	/**
	 * Note: You can replace the text strings in the instantiations of the JButtons
	 * below with ImageIcons if you prefer to have buttons with icons instead of
	 * buttons with text strings
	 * 
	 *  Example:
	 *  ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
	 *  JButton newAlbumButton = new JButton(newAlbumIcon);
	 *  
	 *  will put the imageIcon on the button, instead of the text "New Album", as 
	 *  done below
	 * 
	 */
	
	private JButton createNewAlbumButton() {
		ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
		JButton newAlbumButton = new JButton("New Album");
		//JButton newAlbumButton = new JButton(newAlbumIcon);
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.newAlbumCommandCommunicator();
				controller.isButtonAvailable();
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		ImageIcon deleteAlbumIcon = new ImageIcon("icons/folder_delete_32.png");
		JButton deleteAlbumButton = new JButton("Remove Album");
		//JButton deleteAlbumButton = new JButton(deleteAlbumIcon);
		deleteAlbumButton.setToolTipText("Delete Selected Album");
		deleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteAlbumCommandCommunicator();
				controller.isButtonAvailable();
			}
		});
		return deleteAlbumButton;
	}

	private JButton createAddSoundClipsButton() {
		ImageIcon addSoundClipsIcon = new ImageIcon("icons/document_add_32.png");
		JButton addSoundClipButton = new JButton("Add Sound Clips");
		//JButton addSoundClipButton = new JButton(addSoundClipsIcon);
		addSoundClipButton.setToolTipText("Add Selected Sound Clips To Selected Album");
		addSoundClipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addSoundClipsCommandCommunicator();
				controller.isButtonAvailable();
			}
		});
		return addSoundClipButton;
	}
	
	private JButton createRemoveSoundClipsButton() {
		ImageIcon removeSoundClipsIcon = new ImageIcon("icons/document_delete_32.png");
		JButton removeSoundClipsButton = new JButton("Remove Sound Clips");
		//JButton removeSoundClipsButton = new JButton(removeSoundClipsIcon);
		removeSoundClipsButton.setToolTipText("Remove Selected Sound Clips From Selected Album");
		removeSoundClipsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeSoundClipsCommandCommunicator();
				controller.isButtonAvailable();
			}
		});
		return removeSoundClipsButton;
	}
	
	private JButton createPlayButton() {
		ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton playButton = new JButton("Play");
		//JButton playButton = new JButton(playIcon);
		playButton.setToolTipText("Play Selected Sound Clip");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.playSoundClips();
				controller.isButtonAvailable();
			}
		});
		return playButton;
	}

	private JButton createUndoButton(){
		ImageIcon undoIcon = new ImageIcon("icons/Actions-blue-arrow-undo-icon.png");
		JButton undoButton = new JButton("Undo");
		//JButton undoButton = new JButton(undoIcon);
		undoButton.setToolTipText("Undo Previous Action");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
				controller.isButtonAvailable();
			}
		});
		return undoButton;
	}

	private JButton createRedoButton(){
		ImageIcon redoIcon = new ImageIcon("icons/Actions-blue-arrow-redo-icon.png");
		JButton redoButton = new JButton("Redo");
		//JButton redoButton = new JButton(redoIcon);
		redoButton.setToolTipText("Redo Previous Action");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
				controller.isButtonAvailable();
			}
		});
		return redoButton;
	}

	private JButton createFlagButton(){
		ImageIcon flagIcon = new ImageIcon("icons/Actions-flag-icon.png");
		JButton flagButton = new JButton("Flag");
		//JButton flagButton = new JButton(flagIcon);
		flagButton.setToolTipText("Flag your song");
		flagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Do something idk
			}
		});
		return flagButton;
	}

	private JButton createRateButton(){
		ImageIcon rateIcon = new ImageIcon("icons/favourites_32.png");
		JButton rateButton = new JButton("Rate");
		//JButton rateButton = new JButton(rateIcon);
		rateButton.setToolTipText("Rate your song");
		rateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rate = view.popUpRate();
				if(rate>3){

				}
			}
		});
		return rateButton;
	}

	/**	Changes the state of the undo and redo buttons */
	public void enableDisableButton(Boolean undoPossible, Boolean redoPossible){
		undoButton.setEnabled(undoPossible);
		redoButton.setEnabled(redoPossible);
	}

}
