import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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


	public MusicOrganizerButtonPanel(MusicOrganizerController contr, MusicOrganizerWindow view) {
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
	 * <p>
	 * Example:
	 * ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
	 * JButton newAlbumButton = new JButton(newAlbumIcon);
	 * <p>
	 * will put the imageIcon on the button, instead of the text "New Album", as
	 * done below
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

	private JButton createUndoButton() {
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

	private JButton createRedoButton() {
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

	private JButton createFlagButton() {
		ImageIcon flagIcon = new ImageIcon("icons/Actions-flag-icon.png");
		JButton flagButton = new JButton("Flag");
		//JButton flagButton = new JButton(flagIcon);
		flagButton.setToolTipText("Flag your song");
		flagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<SoundClip> flaggedSoundClips = view.getSelectedSoundClips();
				FlaggedAlbum flaggedAlbum = controller.getFlagAlbum();
				try {
					for (SoundClip clip : flaggedSoundClips) {
						if (flaggedAlbum.containsClip(clip)) {
							flaggedAlbum.removeSoundClip(clip);
							clip.setColor("#000080");
						} else {
							flaggedAlbum.addSoundClip(clip);
							clip.setColor("red");
						}
					}
				} catch (Exception ex) {
					view.showMessage("Please select a sound clip first!" + ex);
				}
				view.onClipsUpdated();
			}
		});
		return flagButton;
	}

	private JButton createRateButton() {
		ImageIcon rateIcon = new ImageIcon("icons/favourites_32.png");
		JButton rateButton = new JButton("Rate");
		//JButton rateButton = new JButton(rateIcon);
		rateButton.setToolTipText("Rate your song");
		rateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
				int rate = Integer.parseInt(view.popUpRate());
				RatedAlbum ratedAlbum = controller.getRateAlbum();

				if(rate >=0 && rate <=5) {
					for(SoundClip clip : selectedSoundClips){
						clip.setRating(rate);
					}
					try {
						if (rate == 5 || rate == 4) {
							ratedAlbum.addSoundClips(selectedSoundClips);
						}
						if (rate < 4) {
							ratedAlbum.removeSoundClips(selectedSoundClips);
						}

					} catch (Exception f) {
						view.showMessage("Please enter a rating from 0 to 5!" + f);
					}
				}
				view.onClipsUpdated();
			}
		});
		return rateButton;
	}

	/**
	 * Changes the state of the undo and redo buttons
	 */
	public void enableDisableButton(Boolean undoPossible, Boolean redoPossible) {
		undoButton.setEnabled(undoPossible);
		redoButton.setEnabled(redoPossible);
	}

	/**
	 * Changes the state of the creation and deletion of albums and sound clips buttons
	 */
	public void almostAllButtonAvailability(Boolean nAPossible, Boolean dAPossible, Boolean aSCPossible,
											Boolean rSCPossible) {
		newAlbumButton.setEnabled(nAPossible);
		deleteAlbumButton.setEnabled(dAPossible);
		addSoundClipsButton.setEnabled(aSCPossible);
		removeSoundClipsButton.setEnabled(rSCPossible);
	}
}
