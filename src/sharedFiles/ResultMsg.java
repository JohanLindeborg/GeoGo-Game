package sharedFiles;

import java.awt.geom.Point2D;

public class ResultMsg extends Message {
	private static final long serialVersionUID = 1L;
	
	private double distancePl1;
	private int scorePl1;
	private double distancePl2;
	private int scorePl2;
	private Point2D pointPl2;
	
	
	public ResultMsg( double distPl1, int scorePl1, double distPl2, int scorePl2, Point2D pointPl2) {
		super("server");
		this.distancePl1 = distPl1;
		this.scorePl1 = scorePl1;
		this.distancePl2 = distPl2;
		this.scorePl2 = scorePl2;
		this.pointPl2 = pointPl2;
	}
	
	
	public double getDistPl1() {
		return distancePl1;
	}
	
	public int getScorePl1() {
		return scorePl1;
	}
	
	public double getDistPl2() {
		return distancePl2;
	}
	
	public int getScorePl2() {
		return scorePl2;
	}
	
	public Point2D getPointPl2() {
		return pointPl2;
	}
	

}
