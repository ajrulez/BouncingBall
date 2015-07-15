package com.somitsolutions.training.android.bouncingball;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BouncingBallActivity extends Activity implements OnClickListener{
	private static BouncingBallActivity bouncingBallActivity;
	//static int points = 0;
	static final int MAX_LEVEL = 4;
	static final int MAX_NUMBER_OF_HIT = 5;
	static Slider slider;
	static Ball ball;
	//static boolean leftToRightMovement = true;
	
	static float ballX = 0;
	static float ballY = 290;
	static float extremeRight;
	static float top = 10;
	static float bottom_Of_Surface;
	static float extremeLeft = 10;
	static int number_of_hit_per_level = 0;
	static int total_number_of_hit = 0;
	static int level = 1;
	static Paint mPaint;
	public Panel p;
	
	private Paint mRefreshPaint;
	
	Button leftButton,rightButton, replayButton;
	
	Handler callBackHandler;
	
	//SurfaceHolder surfaceHolder;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //p = new Panel(this);
        setContentView(R.layout.activity_main);
        p = (Panel)findViewById(R.id.surfaceView1);
        
        leftButton = (Button) findViewById(R.id.button1);
        rightButton = (Button) findViewById(R.id.button2);
        replayButton = (Button) findViewById(R.id.button3);
        
        leftButton.setOnClickListener(this);
        
        rightButton.setOnClickListener(this);
        
        replayButton.setOnClickListener(this);
        
        //find out the width & height of the screen
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        
        extremeRight = width-10;
        
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        	
        	slider = new Slider(0, 200, 220, true, 9);
            ball = new Ball((extremeRight - extremeLeft)/2, 5, 15, 4,4);
        }
        
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
        	slider = new Slider(0, 200, 358, true, 6);
            ball = new Ball((extremeRight - extremeLeft)/2, 30, 15, 4,4);
        }
        
        bouncingBallActivity = this;
       
        mPaint = new Paint();
		mPaint.setColor(Color.YELLOW);
		
		mRefreshPaint = new Paint();
		mRefreshPaint.setColor(Color.BLACK);
	
        callBackHandler = new Handler();
    }
    
    public static BouncingBallActivity getActivity(){
    	return bouncingBallActivity;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.equals(leftButton)){
			//leftToRightMovement = false;
			slider.setLeftToRightMovement(false);
			slider.setDx(-(Math.abs(slider.getDx())));
		}
		
		if(v.equals(rightButton)){
			//leftToRightMovement = true;
			slider.setLeftToRightMovement(true);
			slider.setDx(Math.abs(slider.getDx()));
		}
		
		if(v.equals(replayButton)){
			p._thread.setRunning(false);
			if(ball != null){
				ball = null;
			}
			if(slider != null){
				slider = null;
			}
			
			 if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
		        	
				 slider = new Slider(0, 200, 220, true, 9);
		         ball = new Ball((extremeRight - extremeLeft)/2, 5, 15, 4,4);
		        }
		        
		        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
		        	slider = new Slider(0, 200, 358, true, 6);
			        ball = new Ball((extremeRight - extremeLeft)/2, 30, 15, 4,4);
		        }
			
			
	        p.surfaceCreated(p.getHolder());
	        
			number_of_hit_per_level = 0;
			total_number_of_hit = 0;
			level = 1;
		}
	}
	
	
	public void increaseLevel(){
		p._thread.setRunning(false);
		
		level++;
		
		Log.d("Level : ", Integer.toString(level));
		
		number_of_hit_per_level = 0;
		
		float ballDx = Math.abs(ball.getdX());
		Log.d("Ball DX: ", Float.toString(ballDx));
		float ballDy = Math.abs(ball.getdY());
		float sliderWidth = slider.getWidth();
		float sliderdX = Math.abs(slider.getDx());
		
		if(ball != null){
			ball = null;
		}
		if(slider != null){
			slider = null;
		}
		
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        	
			 slider = new Slider(0, sliderWidth - 25, 220, true, sliderdX + 2);
	         ball = new Ball((extremeRight - extremeLeft)/2, 5, 15, (float)(ballDx + .5), (float)(ballDy + .5));
	        }
		
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
			ball = new Ball((extremeRight - extremeLeft)/2, 30, 15, (float)(ballDx + .5), (float)(ballDy + .5));
			slider = new Slider((extremeRight - extremeLeft)/2, sliderWidth - 25, 358, true, sliderdX + 2);
		}
		p.surfaceCreated(p.getHolder());
		
	}
}
   