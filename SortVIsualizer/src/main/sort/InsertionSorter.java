package main.sort;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class InsertionSorter extends Sorter {

	private double noteConversionConstant = 127f / 256f;
	private Synthesizer midiSynth;
	private MidiChannel mChannel;

	private int intToNote(int size) {
		return (int) (size * noteConversionConstant);
	}

	public InsertionSorter(int[] array) {
		super(array);
	}

	@Override
	public void update() {
		try {
			midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();
			// get and load default instrument and channel lists
			Instrument[] instr = midiSynth.getDefaultSoundbank()
					.getInstruments();
			mChannel = midiSynth.getChannels()[0];

			midiSynth.loadInstrument(instr[0]);// load an instrument
		}
		catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			playSound(key, 50);
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				int temp = array[j];
				array[j] = array[j + 1];
				array[j + 1] = temp;
				j--;
			}
		}
		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			playSound(key,5);

		}
	}

	private void playSound(int size, int delay) {
		try {

			mChannel.noteOn(intToNote(size), 100);
			;// On channel 0, play note number 60 with velocity 100
			Thread.sleep(delay); // wait time in milliseconds to control duration

			mChannel.noteOff(intToNote(size));// turn of the note
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
