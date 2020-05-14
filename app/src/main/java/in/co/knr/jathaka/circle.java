package in.co.knr.jathaka;
import android.view.View;

import android.view.*;
import android.content.*;
import android.graphics.*;

import android.util.*;

public class circle extends View
{

    MainActivity ma=new MainActivity();
    Paint[] paintRaasi = new Paint[12];
    private int wid=0,hyt=0;
    private RectF rf=new RectF(0,0,wid,hyt);
    public Paint p,pp;
    private void inti(){
        p=new Paint();
        pp=new Paint();
        pp.setColor(Color.CYAN);
        //paintRaasi[0].setAntiAlias(true);
        //paintRaasi[0].setColor(Color.argb(120,255,0,0));
        p.setAntiAlias(true);p.setColor(Color.RED);
        if(ma.wi>ma.hi){wid=ma.wi/2;hyt=ma.hi;}
        else{wid=ma.wi;hyt=ma.hi/2;}

    }

    public circle(Context con)
    {super(con);inti();}


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        Log.d("a","onMeasure(int widthMeasureSpec, int heightMeasureSpec)  "+widthMeasureSpec+"  "+heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }



    @Override
    protected void onDraw(Canvas canvas)
    {
        // TODO: Implement this method
        Log.i("a","onDraw()");
        super.onDraw(canvas);
        RectF rf=new RectF(0,0,300,600);
        canvas.drawArc(rf,270f,330f,true,pp);
        canvas.drawLine(0,300,700,300,p);
        //p.setColor(Color.BLUE);
        canvas.drawArc(rf,30,60,true,p);
        invalidate();
    }

    @Override
    public void draw(Canvas canvas)
    {
        // TODO: Implement this method
        super.draw(canvas);
    }

}
