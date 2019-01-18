package com.yuxi.dashpath

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

class DashPathView:View {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context,attributeSet: AttributeSet?,
                defStyle:Int,defAttribute:Int):super(context,attributeSet,defStyle,defAttribute){
        init()
    }
    constructor(context: Context,attributeSet: AttributeSet?,
                defStyle:Int):super(context,attributeSet,defStyle){
        init()
    }
    constructor(context:Context,attributeSet: AttributeSet?):this(context,attributeSet,0)
    constructor(context: Context):this(context,null,0)
    lateinit var mPath:Path
    lateinit var path:Path
    fun init(){
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style=Paint.Style.STROKE
        mPath = Path()
        mPath.moveTo(0f,0f)
        for(i in 0..30){
            mPath.lineTo(i*35f,(Math.random()*100).toFloat())
        }
        mEffects[1]=CornerPathEffect(30f)
        mEffects[2]=DiscretePathEffect(3.0f,5.0f)
        mEffects[3]=DashPathEffect(arrayOf(20f,10f,5f,10f).toFloatArray(),0f)
        path = Path()
        path.addRect(0f,0f,8f,8f,Path.Direction.CW)
        mEffects[4]=PathDashPathEffect(path,12f,0f,PathDashPathEffect.Style.MORPH)
        mEffects[5] = ComposePathEffect(mEffects[3],mEffects[1])


    }
    lateinit var mPaint:Paint
    val mEffects = Array<PathEffect?>(6) {
        null
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for(i in 0..(mEffects.size-1)){
            mPaint.pathEffect = mEffects[i]
            canvas?.drawPath(mPath,mPaint)
            canvas?.translate(0f,200f)
        }
    }

}