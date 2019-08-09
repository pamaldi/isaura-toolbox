package cloud.isaura.toolbox;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import cloud.isaura.toolbox.utils.Utils;
import org.junit.Test;

public class UtilsTest {

	
	@Test
	public void when_sequence_is_not_ordered_then_util_false() {
		int [] seq = {1,3,2};
		Boolean ret = Utils.checkIfSequenceIsOrdered(seq);
		assertEquals(Boolean.FALSE, ret);
	}
	
	@Test
	public void when_sequence_is_ordered_then_util_true() {
		int [] seq = {1,12,27,34,56,78,120};
		Boolean ret = Utils.checkIfSequenceIsOrdered(seq);
		assertEquals(Boolean.TRUE, ret);
	}
	
	@Test
	public void when_base_2_and_number_16_then_log_4() {
		double ret = Utils.log(2, 16);
		assertEquals(4.0, ret,0);
	}
	
	@Test
	public void when_base_2_and_number_5_16_then_logRoundToUpperInteger3() {
		double ret = Utils.logRoundToUpperInteger(2, 5.16);
		assertEquals(3.0, ret,0);
	}
	
	
	@Test
	public void when_buildFromIntegerWithLeadingZero1_thenOK() {
		String ret = Utils.buildFromIntegerWithLeadingZero(3, 1);
		assertEquals("001", ret);
	}
	
	
	@Test
	public void when_buildFromIntegerWithLeadingZero4_thenOK() {
		String ret = Utils.buildFromIntegerWithLeadingZero(4, 4);
		assertEquals("0100", ret);
	}
	
	@Test
	public void when_1010_then_bitset_ok() {
		BitSet bs = Utils.buildBitSetFromString("1010");
		assertEquals(false, bs.get(0));
		assertEquals(true, bs.get(1));
		assertEquals(false, bs.get(2));
		assertEquals(true, bs.get(3));
	}
	
	@Test
	public void when_1010110_then_bitset_ok() {
		BitSet bs = Utils.buildBitSetFromString("1010110");
		assertEquals(false, bs.get(0));
		assertEquals(true, bs.get(1));
		assertEquals(true, bs.get(2));
		assertEquals(false, bs.get(3));
		assertEquals(true, bs.get(4));
		assertEquals(false, bs.get(5));
		assertEquals(true, bs.get(6));
	}
	
	@Test
	public void when_add_1010110_and_1010_to_bitset_then_bitset_ok() {
		BitSet bs = new BitSet();
		Utils.addToBitSetFromString("1010110", bs, 0);
		Utils.addToBitSetFromString("1010", bs, 7);
		assertEquals(false, bs.get(0));
		assertEquals(true, bs.get(1));
		assertEquals(true, bs.get(2));
		assertEquals(false, bs.get(3));
		assertEquals(true, bs.get(4));
		assertEquals(false, bs.get(5));
		assertEquals(true, bs.get(6));
		assertEquals(false, bs.get(7));
		assertEquals(true, bs.get(8));
		assertEquals(false, bs.get(9));
		assertEquals(true, bs.get(10));
	}

	@Test
	public void when_62then_bitset_ok() {
		BitSet bs = Utils.generateBitSetFromInt(62);
		//111110
		assertEquals(false, bs.get(0));
		assertEquals(true, bs.get(1));
		assertEquals(true, bs.get(2));
		assertEquals(true, bs.get(3));
		assertEquals(true, bs.get(4));
		assertEquals(true, bs.get(5));

	}
	
	
}
