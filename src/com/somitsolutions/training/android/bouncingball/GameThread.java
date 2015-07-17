package com.somitsolutions.training.android.bouncingball;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
    
    public class GameThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private Panel _panel;
        private boolean _run = false;

        public GameThread(SurfaceHolder surfaceHolder, Panel panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }

        public void setRunning(boolean run) {
            _run = run;
        }

        public SurfaceHolder getSurfaceHolder() {
            return _surfaceHolder;
        }

        @SuppressLint("WrongCall")
		@Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        _panel.onDraw(c);
                        Thread.sleep(50);
                    }
                }
                 catch(InterruptedException e){
                        	
                 }
                     
                finally {
                }
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    	}
                	}
            	}
        	}