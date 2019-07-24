package com.ryan.kotlinsample.study

import android.animation.*
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.TranslateAnimation
import com.ryan.kotlinsample.pt

fun propertyAnimation(view: View): Unit {
    val alphAnim = ObjectAnimator.ofFloat(
        view,
        "alpha",
        1f, 0f
    )
    alphAnim.duration = 5000

    val translateAnim = ObjectAnimator.ofFloat(
        view,
        "translationX",
        0f,
        500f
    )
    translateAnim.duration = 5000

    val animatorSet = AnimatorSet()
    animatorSet.duration = 2000
    animatorSet.play(alphAnim)
    animatorSet.play(translateAnim)
    animatorSet.start()

}


fun propertyAnimation2(view: View): Unit {
    val viewPropertyAnimator: ViewPropertyAnimator = view.animate()
    viewPropertyAnimator.duration = 5000
    viewPropertyAnimator.interpolator = AccelerateDecelerateInterpolator()
    viewPropertyAnimator.x(0f)
    viewPropertyAnimator.y(1000f)
    viewPropertyAnimator.setUpdateListener {

    }
    viewPropertyAnimator.setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }

    })
}


/**
 * View 必须拥有一个position:Point参数
 */
fun propertyAnimation3(view: View): Unit {

    val animator = ObjectAnimator.ofObject(
        view,
        "position",
        PointEvaluator(),
        Point(0, 0), Point(500, 0)
    )
    animator.duration = 5000
    animator.start()
}

fun propertyAnimation4(view: View): Unit {
    val holder1 = PropertyValuesHolder.ofFloat("scaleX", 1f)
    val holder2 = PropertyValuesHolder.ofFloat("translationX", 500f)

    val animator = ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2)
    animator.start()
}

fun propertyAnimation5(view: View): Unit {
    val keyFrame1 = Keyframe.ofFloat(0f, 0f)
    // 时间过去50%,动画完成值100
    val keyFrame2 = Keyframe.ofFloat(0.5f, 100f)
    val keyframe3 = Keyframe.ofFloat(1f, 50f)
    val holder = PropertyValuesHolder.ofKeyframe("translationX", keyFrame1, keyFrame2, keyframe3)

    val animator = ObjectAnimator.ofPropertyValuesHolder(view,holder)
    animator.duration = 5000
    animator.start()
}


fun valueAnimator(view: View, height: Int): Unit {
    val valueAnim = ValueAnimator.ofFloat(1f, 0f)
    valueAnim.duration = 5000
    valueAnim.addUpdateListener {
        val value = it.animatedValue as Float
        val originalParam = view.layoutParams
        originalParam.height = (value * height).toInt()
        view.layoutParams = originalParam

    }
    valueAnim.start()

}


fun drawableAnimation(): Unit {
    val animationDrawable: AnimationDrawable = AnimationDrawable()
}

fun viewAnimation(view: View): Unit {
//        val alphaAnimation = AlphaAnimation(0f, 1f)
//        alphaAnimation.duration = 8000
//        myClock.startAnimation(alphaAnimation)


    val translateAnimation = TranslateAnimation(
        0f,
        500f,
        0f,
        500f
    )
    translateAnimation.duration = 5000
    view.startAnimation(translateAnimation)
}


data class Point(var x: Int, var y: Int)

class PointEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {

        val newPoint = Point(0, 0)
        if (startValue != null && endValue != null) {
            val x = startValue.x + fraction * (endValue.x - startValue.x)
            val y = startValue.y + fraction * (endValue.y - startValue.y)
            newPoint.x = x.toInt()
            newPoint.y = y.toInt()
        }
        pt("evaluate  = ${newPoint.toString()}")
        return newPoint
    }

}