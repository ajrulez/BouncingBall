package com.somitsolutions.training.android.bouncingball;

public class Ball {

	private float ballX;//X co-ordinate of the center
	private float ballY;//Y co-ordinate of the center
	private float radius;
	private float dX;
	private float dY;
	
	public Ball(float X, float Y, float radius, float dX, float dY){
		this.ballX = X;
		this.ballY = Y;
		this.radius = radius;
		this.dX = dX;
		this.dY = dY;
		
	}
	public float getBallX() {
		return ballX;
	}
	public void setBallX(float ballX) {
		this.ballX = ballX;
	}
	public float getBallY() {
		return ballY;
	}
	public void setBallY(float ballY) {
		this.ballY = ballY;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public float getdX() {
		return dX;
	}
	public void setdX(float dX) {
		this.dX = dX;
	}
	public float getdY() {
		return dY;
	}
	public void setdY(float dY) {
		this.dY = dY;
	}
}
