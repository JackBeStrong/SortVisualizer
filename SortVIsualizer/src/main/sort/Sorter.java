package main.sort;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public abstract class Sorter {

	public int[] array;

	private double noteConversionConstant = 127f / 256f;
	public Synthesizer midiSynth;
	public MidiChannel mChannel;

	public int intToNote(int size) {
		return (int) (size * noteConversionConstant);
	}

	public Sorter(int[] array) {
		this.array = array;
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
			e.printStackTrace();
		}
	}

	public abstract void update();

	public void playSound(int size, int delay) {
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
