package cloud.isaura.toolbox;

import static org.junit.Assert.assertEquals;

import cloud.isaura.toolbox.data_structure.EliasFanoEncoder;
import org.junit.Test;



public class EliasFanoEncoderTest {

	
	@Test(expected = IllegalArgumentException.class)
	public void when_sequence_is_null_then_encoding_error() {
		EliasFanoEncoder encoder = new EliasFanoEncoder();
		encoder.prepare();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void when_sequence_is_empty_then_encoding_error() {
		EliasFanoEncoder encoder = new EliasFanoEncoder();
		int[] sequence = {};
		encoder.setSequence(sequence);
		encoder.prepare();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void when_sequence_has_length_1_then_encoding_error() {
		EliasFanoEncoder encoder = new EliasFanoEncoder();
		int[] sequence = {12};
		encoder.setSequence(sequence);
		encoder.prepare();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void when_sequence_not_ordered_then_encoding_error() {
		EliasFanoEncoder encoder = new EliasFanoEncoder();
		int[] sequence = {12,13,1};
		encoder.setSequence(sequence);
		encoder.prepare();
	}
	
	@Test
	public void when_sequence_ordered_then_prepare_ok() {
		EliasFanoEncoder encoder = new EliasFanoEncoder();
		int[] sequence = {3, 4, 7, 13, 14, 15, 21, 25, 36, 38, 54, 62};
		encoder.setSequence(sequence);
		encoder.prepare();
		assertEquals(12, encoder.getN());
		assertEquals(62, encoder.getU());
		assertEquals(3, encoder.getL());
		assertEquals(3, encoder.getH());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void when_not_prepare_then_encoding_error() {
		EliasFanoEncoder encoder = new EliasFanoEncoder();
		int[] sequence = {12,13,1};
		encoder.setSequence(sequence);
		encoder.encode();
	}
	
	@Test
	public void when_encoding_sequence_then_ok() {
		EliasFanoEncoder encoder = new EliasFanoEncoder();
		int[] sequence = {3, 4, 7, 13, 14, 15, 21, 25, 36, 38, 54, 62};
		encoder.setSequence(sequence);
		encoder.prepare();
		encoder.encode();
		assertEquals("011100111101110111101001100110110110", encoder.getLowBitString());
		
	}
}
