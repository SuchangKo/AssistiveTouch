package com.suchangko.assistivetouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class ChatHeadsView extends View{

	private String text = null;
    private int backgroundColor = Color.RED;
    private String tempText;
 // 속성이 없는 생성자는 소스상에서 직접 생성할때만 쓰인다. 
	public ChatHeadsView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	    @Override
	    protected void onFinishInflate() {
	        setClickable(true);
	        Log.w("LOG","onFinishInflate()");
	    }
	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        
	        // height 진짜 크기 구하기
	        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	        int heightSize = 0;
	        switch(heightMode) {
	        case MeasureSpec.UNSPECIFIED:    // mode 가 셋팅되지 않은 크기가 넘어올때
	            heightSize = heightMeasureSpec;
	            break;
	        case MeasureSpec.AT_MOST:        // wrap_content (뷰 내부의 크기에 따라 크기가 달라짐)
	            heightSize = 60;
	            break;
	        case MeasureSpec.EXACTLY:        // fill_parent, match_parent (외부에서 이미 크기가 지정되었음)
	            heightSize = MeasureSpec.getSize(heightMeasureSpec);
	            break;
	        }
	        
	        // width 진짜 크기 구하기
	        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	        int widthSize = 0;
	        switch(widthMode) {
	        case MeasureSpec.UNSPECIFIED:    // mode 가 셋팅되지 않은 크기가 넘어올때
	            widthSize = widthMeasureSpec;
	            break;
	        case MeasureSpec.AT_MOST:        // wrap_content (뷰 내부의 크기에 따라 크기가 달라짐)
	            widthSize = 60;
	            break;
	        case MeasureSpec.EXACTLY:        // fill_parent, match_parent (외부에서 이미 크기가 지정되었음)
	            widthSize = MeasureSpec.getSize(widthMeasureSpec);
	            break;
	        }

	        
	        Log.w("LOG","onMeasure("+widthMeasureSpec+","+heightMeasureSpec+")");
	        
	        setMeasuredDimension(widthSize, heightSize);
	    }
	    /*
	     *  onMeasure() 메소드에서 결정된 width 와 height 을 가지고 어플리케이션 전체 화면에서 현재 뷰가 그려지는 bound 를 돌려준다.
	     *  
	     *  이 메소드에서는 일반적으로 이 뷰에 딸린 children 들을 위치시키고 크기를 조정하는 작업을 한다.
	     *  유의할점은 넘어오는 파라메터가 어플리케이션 전체를 기준으로 위치를 돌려준다.
	     *  
	     *  super 메소드에서는 아무것도 하지않기때문에 쓰지 않는다.
	     */
	    @Override
	    protected void onLayout(boolean changed, int left, int top, int right,
	            int bottom) {
	        Log.w("LOG","onLayout("+changed+","+left+","+top+","+right+","+bottom+")");
	    }
	    /*
	     * 이 뷰의 크기가 변경되었을때 호출된다.
	     * 
	     * super 메소드에서는 아무것도 하지않기때문에 쓰지 않는다.
	     */
	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	        
	       Log.w("LOG","onSizeChanged("+w+","+h+","+oldw+","+oldh+")");
	    }
	    /*
	     * 실제로 화면에 그리는 영역으로 View 를 상속하고 이 메소드만 구현해도 제대로 보여지게 된다.
	     * 
	     * 그릴 위치는 0,0 으로 시작해서 getMeasuredWidth(), getMeasuredHeight() 까지 그리면 된다.
	     * 
	     * super 메소드에서는 아무것도 하지않기때문에 쓰지 않는다.
	     */
	    @Override
	    protected void onDraw(Canvas canvas) {
	    	/*
	        final Paint p = new Paint();
	        p.setColor(backgroundColor);
	       
	        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(), p);
	        if (text != null) {
	            p.setColor(Color.BLACK);
	            canvas.drawText(text, 10, 15, p); // 왼쪽 아래를 0,0 으로 보고있음
	        }
	        
	        Log.w("LOG","onDraw("+canvas+")");
	        */
	    }
	    /*
	     * 현재 view 가 focus 상태일때 key 를 누르면 이 메소드가 호출됨.
	     * 즉 이 메소드를 사용하려면 setFocusable(true) 여야함. 
	     * 
	     * 그리고 super 메소드에서는 기본적인 키 작업(예를들면 BACK 키 누르면 종료)을 처리하기 때문에 일반적으로 return 시에 호출하는게 좋다.
	     * 만약 기본적인 작업을 하지않게 하려면 super 함수를 호출하지 않아도 된다.
	     * 
	     * 다른 event 메소드들도 유사하게 동작한다.
	     */
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        //Log.w("LOG","onKeyDown("+keyCode+","+event+")");
	        return super.onKeyDown(keyCode, event); 
	    }
	    
	    /*
	     * 이 view 에 touch 가 일어날때 실행됨.
	     * 
	     * 기본적으로 touch up 이벤트가 일어날때만 잡아내며 
	     * setClickable(true) 로 셋팅하면 up,move,down 모두 잡아냄
	     */
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	       Log.w("LOG","onTouchEvent("+event+")");
	        switch(event.getAction()) {
	        case MotionEvent.ACTION_UP:
	            backgroundColor = Color.RED;
	            text = tempText;
	            break;
	        case MotionEvent.ACTION_DOWN:
	            backgroundColor = Color.YELLOW;
	            tempText = text;
	            text = "Clicked!";
	            break;
	        case MotionEvent.ACTION_MOVE:
	            backgroundColor = Color.BLUE;
	            text = "Moved!";
	            break;
	        }
	        invalidate();
	        return super.onTouchEvent(event);
	    }
	    
	    
	    public String getText() {
	        return text;
	    }
	    public void setText(String text) {
	        this.text = text;
	    }
	}

