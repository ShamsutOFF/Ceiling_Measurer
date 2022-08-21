package com.example.ceilingmeasurer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View

private const val TAG = "@@@@@ RectangleTest"


class RectangleTest(context: Context?, height: String?, width: String?) : View(context) {

    private val paint: Paint = Paint()
    private val paintForPoint: Paint = Paint()
    private val paintForLines: Paint = Paint()
    private val rectHeight = height
    private val rectWidth = width

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val canvasWidth = width.toFloat()
        val canvasHeight = height.toFloat()
        val canvasCenterX = canvasWidth / 2
        val canvasCenterY = canvasHeight / 2

        //Создаем холст
        paint.apply {
            style = Paint.Style.FILL // стиль Заливка
            color = Color.WHITE // закрашиваем холст белым цветом
        }
        canvas.drawPaint(paint)

        paintForPoint.apply {
            color = Color.RED
            style = Paint.Style.FILL
        }

        paintForLines.apply {
            color = Color.BLACK
            strokeWidth = 5F
        }

        if (!rectHeight.isNullOrBlank() && !rectWidth.isNullOrBlank()) {

            val procX = canvasWidth / rectWidth.toFloat()
            val procY = canvasHeight / rectHeight.toFloat()

            val rectW: Float
            val rectH: Float
            if (procX < procY) {
                rectW = rectWidth.toFloat() * procX * 0.95F
                rectH = rectHeight.toFloat() * procX * 0.95F
            } else {
                rectW = rectWidth.toFloat() * procY * 0.95F
                rectH = rectHeight.toFloat() * procY * 0.95F
            }

            val p1 = MyPoint(
                canvasCenterX - rectW / 2,
                canvasCenterY - rectH / 2
            )
            val p2 = MyPoint(
                canvasCenterX + rectW / 2,
                canvasCenterY - rectH / 2
            )
            val p3 = MyPoint(
                canvasCenterX + rectW / 2,
                canvasCenterY + rectH / 2
            )
            val p4 = MyPoint(
                canvasCenterX - rectW / 2,
                canvasCenterY + rectH / 2
            )

            canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paintForLines)
            canvas.drawLine(p2.x, p2.y, p3.x, p3.y, paintForLines)
            canvas.drawLine(p3.x, p3.y, p4.x, p4.y, paintForLines)
            canvas.drawLine(p4.x, p4.y, p1.x, p1.y, paintForLines)

            canvas.drawCircle(p1.x, p1.y, 5F, paintForPoint)
            canvas.drawCircle(p2.x, p2.y, 5F, paintForPoint)
            canvas.drawCircle(p3.x, p3.y, 5F, paintForPoint)
            canvas.drawCircle(p4.x, p4.y, 5F, paintForPoint)

            canvas.drawCircle(canvasCenterX, canvasCenterY, 10F, paintForPoint)
        }
    }
}

class MyPoint(x: Float, y: Float) {
    val x = x
    val y = y
}
