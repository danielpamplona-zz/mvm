
public class Graphics {
	
	private short[] mem;
	
	private GraphicMode currentMode;
	
	public Graphics() {
		mem = new short[256]; //graphic card memory amount 256KB
		currentMode = GraphicMode.READ_MODE; // set mode to default write mode
	}
	
	/**
	 * returns current graphic card state
	 * @return
	 */
	public GraphicMode getCurrentMode() {
		return currentMode;
	}
	
	public void setCurrentMode(GraphicMode currentMode) {
		this.currentMode = currentMode;
	}
	
	/**
	 * Returns existing data in certain address
	 * @param from
	 * @param to
	 * @return
	 */
	public short[] readFromAddress(int from, int to) {
		short[] ret = new short[to-from];
		int count = 0;
		for (int i = from; i < to; i++) {
			ret[count] = mem[i];
			count++;
		}
		
		return ret;
	}
	
	/**
	 * May rewrite existing data
	 * @param address
	 * @param data
	 */
	public void setDataStartinOnAddress(int address, short[] data) {
		for (short d : data) {
			mem[address] = d;
			address++;
		}
	}
	
	
	
	
}
