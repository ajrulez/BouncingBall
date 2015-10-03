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
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.concurrent.CountDownLatch;

public class BouncingBallActivity extends Activity implements OnClickListener{
	private static BouncingBallActivity bouncingBallActivity;
	boolean isReplayClicked = false;
	static final int MAX_LEVEL = 4;
	static final int MAX_NUMBER_OF_HIT = 5;
	static Slider slider;
	static Ball ball;

	int surfaceHeight;
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
	
	RelativeLayout rootLayout;

	static CountDownLatch waitTillTheWholeSurfaceIsCreated = new CountDownLatch(1);
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_main);
        p = (Panel)findViewById(R.id.surfaceView1);
        rootLayout = (RelativeLayout) findViewById(R.id.rootView);
        
       
        ViewTreeObserver vtoSurface = p.getViewTreeObserver();
        vtoSurface.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {

				surfaceHeight = p.getHeight();
		        bottom_Of_Surface = surfaceHeight;
		        //We will have to delay the creation of the Ball & the Slider
		        //till the surface is completely loaded and displayed.
		        CreateSliderAndBall();
				waitTillTheWholeSurfaceIsCreated.countDown();
			}
		});
        
       
        leftButton = (Button) findViewById(R.id.button1);
        rightButton = (Button) findViewById(R.id.button2);
        replayButton = (Button) findViewById(R.id.button3);
        
        leftButton.setOnClickListener(this);
        
        rightButton.setOnClickListener(this);
        
        replayButton.setOnClickListener(this);
        
        //find out the width & height of the screen
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        
        int width = displaymetrics.widthPixels;
        
        extremeRight = width-10;
        
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
			
			slider.setLeftToRightMovement(true);
			slider.setDx(Math.abs(slider.getDx()));
		}
		
		if(v.equals(replayButton)){
			isReplayClicked = true;
			Replay();
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
		
		CreateSliderAndBall();
		
		slider.setDx(sliderdX + 2);
		slider.setWidth(sliderWidth - 25);
		ball.setdX((float)(ballDx + .5));
		ball.setdY((float)(ballDy + .5));
			
	   
		p.surfaceCreated(p.getHolder());
		
	}
	
	private void Replay(){

		p._thread.setRunning(false);
		if(ball != null){
			ball = null;
		}
		if(slider != null){
			slider = null;
		}
		
		CreateSliderAndBall();
        p.surfaceCreated(p.getHolder());
        
		number_of_hit_per_level = 0;
		total_number_of_hit = 0;
		level = 1;
	}
	
	private void CreateSliderAndBall(){
		
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        	
			if(rootLayout.getTag().equals("big_screen")){
				slider = new Slider(0, 200, bottom_Of_Surface - 20 , true, 15);
	            ball = new Ball((extremeRight - extremeLeft)/2, 5, 15, 6,6);
			}
			
			if(rootLayout.getTag().equals("small_screen")){
				slider = new Slider(0, 200, bottom_Of_Surface - 20 , true, 9);
	            ball = new Ball((extremeRight - extremeLeft)/2, 5, 15, 4,4);
        	}
        	
        }
        
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
        	if(rootLayout.getTag().equals("big_screen")){
        		slider = new Slider(0, 200, bottom_Of_Surface - 20, true, 10);
                ball = new Ball((extremeRight - extremeLeft)/2, 30, 15, 6,6);
        	}
        	
        	if(rootLayout.getTag().equals("small_screen")){
        		slider = new Slider(0, 200, bottom_Of_Surface - 20, true, 6);
                ball = new Ball((extremeRight - extremeLeft)/2, 30, 15, 4,4);
        	}
        	
        }
       
	}
}
   