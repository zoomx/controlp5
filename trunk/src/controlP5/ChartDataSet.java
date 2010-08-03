package controlP5;

import java.util.ArrayList;
import java.util.ListIterator;

public class ChartDataSet extends ArrayList<ChartData> {
	
	CColor _myColors;
	
	
	public ChartDataSet() {
		_myColors = new CColor();
	}
	
	public CColor getColor() {
		return _myColors;
	}
	
	public float[] values() {
		float[] v = new float[size()];
		int n = 0;
		ListIterator<ChartData> litr = listIterator();
    while (litr.hasNext()) {
      v[n++] = litr.next().getValue();
    }
    return v;
	}
	
}
