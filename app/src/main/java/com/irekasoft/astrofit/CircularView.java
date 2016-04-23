package com.irekasoft.astrofit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hijazi on 23/4/16.
 */
public class CircularView extends View
{
  private static final int DEFAULT_CIRCLE_COLOR = Color.RED;

  private int circleColor = DEFAULT_CIRCLE_COLOR;
  private Paint paint;

  public CircularView(Context context)
  {
    super(context);
    init(context, null);
  }

  public CircularView(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs)
  {
    paint = new Paint();
    paint.setAntiAlias(true);
  }

  public void setCircleColor(int circleColor)
  {
    this.circleColor = circleColor;
    invalidate();
  }

  public int getCircleColor()
  {
    return circleColor;
  }

  protected void onDraw(Canvas canvas)
  {
    super.onDraw(canvas);

    int w = getWidth();
    int h = getHeight();

    int pl = getPaddingLeft();
    int pr = getPaddingRight();
    int pt = getPaddingTop();
    int pb = getPaddingBottom();

    int usableWidth = w - (pl + pr);
    int usableHeight = h - (pt + pb);

    int radius = Math.min(usableWidth, usableHeight) / 2;
    int cx = pl + (usableWidth / 2);
    int cy = pt + (usableHeight / 2);

    paint.setColor(circleColor);
    canvas.drawCircle(cx, cy, radius, paint);
  }
}
