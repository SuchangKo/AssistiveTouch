package com.suchangko.assistivetouch;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ChatHeadsService extends Service {
    int lastAction=0;
	 
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;  
    private ChatHeadsView mChatHeadsView; 
    private View mAssistiveView;
    private LayoutInflater inflater;
    private float mTouchX, mTouchY;
    private int mViewX, mViewY;
    Button bt1,bt2,bt3,bt4,bt5,bt6;
    Button bt_quit;
    ImageView iv_arrow;
    boolean img_arrowisclicked=false;
     
    private OnTouchListener mViewTouchListener1 = new OnTouchListener() {
        
        @Override public boolean onTouch(View v, MotionEvent event) {
        	int nowAction = event.getAction();
            
            if(event.getAction()==MotionEvent.ACTION_MOVE){
            	int x = (int)(event.getRawX() - mTouchX);  
                int y = (int)(event.getRawY() - mTouchY);  
                 
         
                mParams.x = mViewX + x-33;
                mParams.y = mViewY + y-33;
                 
                mWindowManager.updateViewLayout(mChatHeadsView, mParams);    
                Log.d("LOG","Move");
                lastAction=nowAction;
                return true;
            }else if(event.getAction()==MotionEvent.ACTION_CANCEL){
            	Log.d("LOG","Cancel");
            	lastAction=nowAction;
            	return true;
            }else if(event.getAction()==0){
            	Log.d("LOG","false Action : "+event.getAction());
            	lastAction=nowAction;
            	return false;
            }else{
            	boolean returnbool;
            	if(lastAction==0){
            		returnbool=false;
            	}else{
            		returnbool=true;
            	}
            	Log.d("LOG","true Action : "+event.getAction());
            	lastAction=nowAction;
            	return returnbool;
            }
        }    
    };
    private OnTouchListener mViewTouchListener2 = new OnTouchListener() {
        
        @Override public boolean onTouch(View v, MotionEvent event) {
        	int nowAction = event.getAction();
            
            if(event.getAction()==MotionEvent.ACTION_MOVE){
            	int x = (int)(event.getRawX() - mTouchX);  
                int y = (int)(event.getRawY() - mTouchY);  
                 
         
                mParams.x = mViewX + x-310;
                mParams.y = mViewY + y-53;
                 
                mWindowManager.updateViewLayout(mAssistiveView, mParams);    
                Log.d("LOG","Move");
                lastAction=nowAction;
                return true;
            }else if(event.getAction()==MotionEvent.ACTION_CANCEL){
            	Log.d("LOG","Cancel");
            	lastAction=nowAction;
            	return true;
            }else if(event.getAction()==0){
            	Log.d("LOG","false Action : "+event.getAction());
            	int x = (int)(event.getRawX() - mTouchX);  
                int y = (int)(event.getRawY() - mTouchY);
                Log.d("XY","x:"+x+" y:"+y);
            	lastAction=nowAction;
            	return false;
            }else{
            	boolean returnbool;
            	if(lastAction==0){
            		returnbool=false;
            	}else{
            		returnbool=true;
            	}
            	Log.d("LOG","true Action : "+event.getAction());
            	lastAction=nowAction;
            	if(img_arrowisclicked){
            		mAssistiveView.setOnTouchListener(null);
            		img_arrowisclicked=false;
            	}
            	return returnbool;
            }
        }    
    };
    
	private OnClickListener mViewOnClickListener1 = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mWindowManager.removeView(mChatHeadsView);
				mWindowManager.addView(mAssistiveView, mParams);
				//mAssistiveView.setOnClickListener(mViewOnClickListener2);
			    //mAssistiveView.setOnTouchListener(mViewTouchListener2);
			}
		};
	private OnClickListener mViewOnClickListener2 = new OnClickListener() {
		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "",Toast.LENGTH_LONG).show();
			}
		};
    @Override
    public IBinder onBind(Intent arg0) { return null; }
     
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Assistive Touch가 실행되었습니다.",Toast.LENGTH_LONG).show();
        inflater  = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        mAssistiveView=(LinearLayout)inflater.inflate(R.layout.activity_main,null);
        iv_arrow = (ImageView)mAssistiveView.findViewById(R.id.arrow);
        iv_arrow.setOnTouchListener(mViewTouchListener2);
        iv_arrow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mAssistiveView.setOnTouchListener(mViewTouchListener2);
				img_arrowisclicked=true;
				
			}
		});
        bt1 = (Button)mAssistiveView.findViewById(R.id.button1);
        bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mWindowManager.removeView(mAssistiveView);
			}
		});
        bt2 = (Button)mAssistiveView.findViewById(R.id.button2);
        bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent  intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage("com.kakao.talk");
				startActivity(intent);
			}
		});
        bt3 = (Button)mAssistiveView.findViewById(R.id.button3);
        bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("tel:");
				Intent  intent = new Intent(Intent.ACTION_DIAL);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setData(uri);
				startActivity(intent);
			}
		});
        bt4 = (Button)mAssistiveView.findViewById(R.id.button4);
        bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowToast("4");
			}
		});
        bt5 = (Button)mAssistiveView.findViewById(R.id.button5);
        bt5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowToast("5");
			}
		});
        bt6 = (Button)mAssistiveView.findViewById(R.id.button6);
        bt6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowToast("6");
			}
		});
        bt_quit = (Button)mAssistiveView.findViewById(R.id.button_quit);
        bt_quit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mWindowManager.removeView(mAssistiveView);
				mWindowManager.addView(mChatHeadsView, mParams);
			}
		});
        bt1.setText("종료");
        bt2.setText("카카오");
        bt3.setText("전화걸기");
        mChatHeadsView = new ChatHeadsView(this);
        mChatHeadsView.setBackgroundResource(R.drawable.assist);
        mChatHeadsView.setOnTouchListener(mViewTouchListener1); 
        mChatHeadsView.setOnClickListener(mViewOnClickListener1);
        mParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE, //TouchEvent 받음     
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, //KeyEvent, TouchEvent를 Child(앱)로 넘김
            PixelFormat.TRANSLUCENT );     
        mParams.gravity = Gravity.LEFT  | Gravity.TOP; 
        mParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
        
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mChatHeadsView, mParams);  
       //mChatHeadsView.set
        		
    }
    private void ShowToast(String Text){
    	Toast.makeText(getApplicationContext(), "Toast : "+Text,Toast.LENGTH_SHORT).show();
    }
    private void showAlert(String title, String message) {
    	new AlertDialog.Builder(getApplicationContext())
    	.setMessage("종료하시겠습니까?")
        .setPositiveButton("종료",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mWindowManager.removeView(mAssistiveView);
			}
		})
        .setNegativeButton("취소",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
        .show();
    }
    private void showAlert_quit() {
    	new AlertDialog.Builder(this)
    	.setMessage("종료하시겠습니까?")
        .setPositiveButton("종료",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				mWindowManager.removeView(mAssistiveView);
			}
		})
        .setNegativeButton("취소",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
        .show();
    }
}
