package com.ryan.kotlinsample.study

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.ryan.kotlinsample.pt
import java.util.*

class MyClock : View {

    val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mDefaultSize: Int = 200

    constructor(context: Context?) : super(context) {

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.textAlign = Paint.Align.CENTER

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (widthMode == MeasureSpec.AT_MOST &&
            heightMode == MeasureSpec.AT_MOST
        ) {
            setMeasuredDimension(mDefaultSize, mDefaultSize)
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefaultSize, heightSize)
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mDefaultSize)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        getCurrentTime()
        val clockSize =
            if (measuredWidth > measuredHeight) measuredHeight.toFloat() / 2
            else measuredWidth.toFloat() / 2

        canvas?.drawCircle(
            clockSize, clockSize,
            clockSize - 10,
            mPaint
        )

        drawDial(canvas!!, clockSize)


        drawSeconedNeedle(canvas!!, clockSize)
//        drawHourNeedle()
        invalidate()

    }

    val mSeconedPath = Path()


    private fun drawSeconedNeedle(canvas: Canvas, clockSize: Float) {
        canvas.save()
        canvas.translate(clockSize, clockSize)
        canvas.rotate(mCurrentSeconedDegree)
        canvas.translate(0f, -clockSize + 20)
        canvas.drawLine(0f, 0f, 0f, 10f, mPaint)
        canvas.restore()
    }

    private fun drawDial(canvas: Canvas, clockSize: Float) {
        mPaint.textSize = 20f
        repeat(4) {
            canvas.save()
            canvas.translate(clockSize, clockSize)
            canvas.rotate(90f * (it + 1))
            canvas.translate(0f, -clockSize + 10)

            canvas.rotate(-90f * (it + 1))
//            canvas.drawCircle(0f, 0f, 10f, mPaint)
            canvas.drawText("${(it + 1) * 3}", 0f, 10f, mPaint)
            canvas.restore()
        }

    }

    private var mCurrentSeconedDegree: Float = 0f
    private var mCurrentMinuteDegree: Float = 0f
    private var mCurrentHourDegree: Float = 0f

    private fun getCurrentTime(): Unit {
        val calendar = Calendar.getInstance()
        val seconed = calendar.get(Calendar.SECOND).toFloat()
        val minute = calendar.get(Calendar.MINUTE).toFloat()
        val hour = calendar.get(Calendar.HOUR_OF_DAY).toFloat()

        mCurrentSeconedDegree = seconed / 60f * 360f
        mCurrentMinuteDegree = minute / 60f * 360f
        mCurrentHourDegree = hour / 24f * 360f

    }
}