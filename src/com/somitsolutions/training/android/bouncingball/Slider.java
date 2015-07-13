package com.somitsolutions.training.android.bouncingball;

public class Slider {

	private float leftEdgeX;
	private float width;
	private boolean isLeftToRightMovement = true;
	private float dx;
	private float bottom;
	
	public Slider(float leftEdgeX, float width, float bottom, boolean isLeftToRightMovement, float dx){
		this.leftEdgeX = leftEdgeX;
		this.width = width;
		this.setLeftToRightMovement(isLeftToRightMovement);
		this.bottom = bottom;
		this.dx = dx;
	}
	public float getLeftEdgeX() {
		return leftEdgeX;
	}
	public void setLeftEdgeX(float leftEdgeX) {
		this.leftEdgeX = leftEdgeX;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public boolean isLeftToRightMovement() {
		return isLeftToRightMovement;
	}
	public void setLeftToRightMovement(boolean isLeftToRightMovement) {
		this.isLeftToRightMovement = isLeftToRightMovement;
	}
	public float getDx() {
		return dx;
	}
	public void setDx(float dx) {
		this.dx = dx;
	}
	public float getBottom() {
		return bottom;
	}
	public void setBottom(float bottom) {
		this.bottom = bottom;
	}
	
}
