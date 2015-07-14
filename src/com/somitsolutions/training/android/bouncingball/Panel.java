package com.somitsolutions.training.android.bouncingball;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

    
    @SuppressLint("DrawAllocation")
	public class Panel extends SurfaceView implements SurfaceHolder.Callback{
 
    	public GameThread _thread;
    	
    	public Panel(Context context) {
    		super(context);
    		
    		getHolder().addCallback(this);
    	}
    	
    	public Panel(Context context, AttributeSet attrs) {
            super(context, attrs);
            getHolder().addCallback(this);
            // TODO Auto-generated constructor stub
        }

        public Panel(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            getHolder().addCallback(this);
            // TODO Auto-generated constructor stub
        }

    	public void surfaceChanged(SurfaceHolder holder, int format, int width,
    			int height) {
    		// TODO Auto-generated method stub
    		
    	}

    	public void surfaceCreated(SurfaceHolder holder) {
    		// TODO Auto-generated method stub
    		 _thread = new GameThread(getHolder(), this);
    		 _thread.setRunning(true);
             _thread.start();
    	}

    	public void surfaceDestroyed(SurfaceHolder holder) {
    		// TODO Auto-generated method stub
    		
    		 _thread.setRunning(false);
    		
    	}
    	
    	@Override
        public void onDraw(Canvas canvas) {
    		
    		if(!(canvas == null)){
    			
    			
    			
    			canvas.drawColor(Color.BLACK);	
    			
	    		//... Slider Left To Right
	    		if(BouncingBallActivity.slider.isLeftToRightMovement() == true && (BouncingBallActivity.slider.getLeftEdgeX()+ BouncingBallActivity.slider.getWidth()) < BouncingBallActivity.extremeRight){
	    			
	    			BouncingBallActivity.slider.setLeftEdgeX(BouncingBallActivity.slider.getLeftEdgeX() + BouncingBallActivity.slider.getDx());
	                
	                canvas.drawRect(BouncingBallActivity.slider.getLeftEdgeX(), BouncingBallActivity.slider.getBottom()-10, BouncingBallActivity.slider.getLeftEdgeX() + BouncingBallActivity.slider.getWidth(),BouncingBallActivity.slider.getBottom(), BouncingBallActivity.mPaint);
	    		
	    		}
	    		
	    		//at the extreme right end of the canvas
	    		if(BouncingBallActivity.slider.isLeftToRightMovement() == true && (BouncingBallActivity.slider.getLeftEdgeX()+ BouncingBallActivity.slider.getWidth()) >= BouncingBallActivity.extremeRight){
	    			BouncingBallActivity.slider.setLeftToRightMovement(false);
	    			BouncingBallActivity.slider.setDx(-(BouncingBallActivity.slider.getDx()));
	    		}
	    		
	    		////Slider right to left
	    		if(BouncingBallActivity.slider.isLeftToRightMovement() == false && BouncingBallActivity.slider.getLeftEdgeX() > BouncingBallActivity.extremeLeft){
		    		
	    			
	    			BouncingBallActivity.slider.setLeftEdgeX(BouncingBallActivity.slider.getLeftEdgeX() + BouncingBallActivity.slider.getDx());
	    			 
	                canvas.drawRect(BouncingBallActivity.slider.getLeftEdgeX(), BouncingBallActivity.slider.getBottom()-10, BouncingBallActivity.slider.getLeftEdgeX() + BouncingBallActivity.slider.getWidth(),BouncingBallActivity.slider.getBottom(), BouncingBallActivity.mPaint);
	                 
	    		}
	    		
	    		//at the extreme left
	    		if(BouncingBallActivity.slider.isLeftToRightMovement() == false && BouncingBallActivity.slider.getLeftEdgeX() <= BouncingBallActivity.extremeLeft){
	    			BouncingBallActivity.slider.setLeftToRightMovement(true);
	    			BouncingBallActivity.slider.setDx(Math.abs(BouncingBallActivity.slider.getDx()));
	    		}
	    		
	    		//Ball
	    		
	    		BouncingBallActivity.ball.setBallX(BouncingBallActivity.ball.getBallX() + BouncingBallActivity.ball.getdX());
	    		BouncingBallActivity.ball.setBallY(BouncingBallActivity.ball.getBallY() + BouncingBallActivity.ball.getdY());
	    		canvas.drawCircle(BouncingBallActivity.ball.getBallX(), BouncingBallActivity.ball.getBallY(), BouncingBallActivity.ball.getRadius(), BouncingBallActivity.mPaint);
	    		
	    		//Reflection from right wall
	    		if((BouncingBallActivity.ball.getBallX() + BouncingBallActivity.ball.getRadius()) > BouncingBallActivity.extremeRight){
	    			BouncingBallActivity.ball.setdX(-(BouncingBallActivity.ball.getdX()));
	    		}
	    		
	    		//Reflection from left wall
	    		if((BouncingBallActivity.ball.getBallX() - BouncingBallActivity.ball.getRadius()) < BouncingBallActivity.extremeLeft){
	    			BouncingBallActivity.ball.setdX(Math.abs(BouncingBallActivity.ball.getdX()));
	    		}
	    		
	    		//Reflection from slider
	    		if(((BouncingBallActivity.ball.getBallY()+ BouncingBallActivity.ball.getRadius()) >= (BouncingBallActivity.slider.getBottom() - 10)) && ((BouncingBallActivity.ball.getBallX() - BouncingBallActivity.ball.getRadius())>=BouncingBallActivity.slider.getLeftEdgeX()) && ((BouncingBallActivity.ball.getBallX() + BouncingBallActivity.ball.getRadius())<=(BouncingBallActivity.slider.getLeftEdgeX()+BouncingBallActivity.slider.getWidth()))){
	    			BouncingBallActivity.ball.setdY(-Math.abs((BouncingBallActivity.ball.getdY())));
	    			BouncingBallActivity.number_of_hit_per_level++;
	    			BouncingBallActivity.total_number_of_hit++;
	    			
	    			Log.d("Number_of_Hit", Integer.toString(BouncingBallActivity.number_of_hit_per_level));
	    			if(BouncingBallActivity.number_of_hit_per_level == BouncingBallActivity.MAX_NUMBER_OF_HIT && BouncingBallActivity.level < BouncingBallActivity.MAX_LEVEL){
	    				/*BouncingBallActivity.getActivity().callBackHandler.post(new Runnable(){
    						@Override
    						public void run() {
    							// TODO Auto-generated method stub
    							//Toast.makeText(BouncingBallActivity.getActivity().getApplicationContext(), "Congratulations!!!You have won the game...", Toast.LENGTH_LONG).show();
    							BouncingBallActivity.getActivity().increaseLevel();
    							//return;
    						}
    	    				
    	    			});*/
	    				BouncingBallActivity.getActivity().increaseLevel();
	    			}
	    				
	    				if(BouncingBallActivity.level == BouncingBallActivity.MAX_LEVEL && BouncingBallActivity.number_of_hit_per_level == BouncingBallActivity.MAX_NUMBER_OF_HIT){
	    					//posting a toast message to the UI
	    					_thread.setRunning(false);
	    	    			BouncingBallActivity.getActivity().callBackHandler.post(new Runnable(){
	    						@Override
	    						public void run() {
	    							// TODO Auto-generated method stub
	    							Toast.makeText(BouncingBallActivity.getActivity().getApplicationContext(), "Congratulations!!!You have won the game. Total points earned = " + Integer.toString(BouncingBallActivity.total_number_of_hit *10), Toast.LENGTH_LONG).show();
	    						}
	    	    				
	    	    			});
	    	    			BouncingBallActivity.number_of_hit_per_level = 0;
	    	    			BouncingBallActivity.level = 1;
	    				}
	    			return;
	    		}
	    		
	    		//Reflection from top
	    		if(BouncingBallActivity.ball.getBallY() < BouncingBallActivity.top){
	    			BouncingBallActivity.ball.setdY(Math.abs(BouncingBallActivity.ball.getdY()));
	    		}
	    		
	    		//the game is lost
	    		if((BouncingBallActivity.ball.getBallY() > (BouncingBallActivity.slider.getBottom() - 10)) && (((BouncingBallActivity.ball.getBallX() + BouncingBallActivity.ball.getRadius()) < BouncingBallActivity.slider.getLeftEdgeX()) || ((BouncingBallActivity.ball.getBallX() - BouncingBallActivity.ball.getRadius()) > (BouncingBallActivity.slider.getLeftEdgeX()+BouncingBallActivity.slider.getWidth())))){
	    			//stopping the thread
	    			_thread.setRunning(false);
	    			
	    			//posting a toast message to the UI
	    			BouncingBallActivity.getActivity().callBackHandler.post(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(BouncingBallActivity.getActivity().getApplicationContext(), "You have lost the game. Total points earned = " + Integer.toString(BouncingBallActivity.total_number_of_hit *10) , Toast.LENGTH_LONG).show();
							
						}
	    				
	    			});
	    			//BouncingBallActivity.ball = null;
	    			//BouncingBallActivity.slider = null;
	    			
	    			return;
	    		} 		
	    }
    }//onDraw
    	
}//Class ends