package com.example.codingtest.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.NonNull
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout


class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val message: String?
)


class FlingBehavior : AppBarLayout.Behavior {
    private var isPositive = false
    var isEnabled = true

    constructor() {
        setDragCallback()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setDragCallback()
    }

    private fun setDragCallback() {
        setDragCallback(object : DragCallback() {
            override fun canDrag(@NonNull appBarLayout: AppBarLayout): Boolean {
                return isEnabled
            }
        })
    }


    override fun onNestedFling(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {

        var velocityY = velocityY
        var consumed = consumed
        if (velocityY > 0 && !isPositive || velocityY < 0 && isPositive) {
            velocityY = velocityY * -1
        }
        if (target is RecyclerView && velocityY < 0) {
            val recyclerView = target as RecyclerView
            val firstChild: View = recyclerView.getChildAt(0)
            val childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild)
            consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD
        }
        return super.onNestedFling(
            coordinatorLayout!!,
            child!!, target, velocityX, velocityY, consumed
        )

    }



    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        isPositive = dy > 0
    }


    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        return isEnabled && super.onStartNestedScroll(
            parent,
            child,
            directTargetChild,
            target,
            nestedScrollAxes,
            type
        )
    }

    companion object {
        private const val TOP_CHILD_FLING_THRESHOLD = 3
    }


}