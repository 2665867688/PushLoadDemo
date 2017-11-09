package com.yue.pushload.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yue.pushload.R;

import java.util.ArrayList;

/**
 * @ClassName:SimpleTest04Activity
 * @auther:shimy
 * @date:2017/11/2 0002 上午 10:12
 * @description: view的事件分发机制
 */
public class SimpleTest04Activity extends AppCompatActivity {

    private TextView text;
    private Button button;
    private ScrollView scrollView;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test04);
        initView();
    }

    private void initView(){
        FrameLayout frameLayout;
        findViewById(R.id.btn_simple04_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleTest04Activity.this, "btn 哈哈哈", Toast.LENGTH_SHORT).show();
            }
        });
        
        findViewById(R.id.layout_simple04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleTest04Activity.this, "layout哈哈哈", Toast.LENGTH_SHORT).show();
            }
        });
        
        findViewById(R.id.test_simple04_02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "哈哈哈哈哈哈", Toast.LENGTH_SHORT).show();
            }
        });
    }


    class MyView extends ViewGroup {

        public MyView(Context context) {
            super(context);
            initView();
        }

        public MyView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            initView();
        }


        private void initView(){

        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            return super.dispatchTouchEvent(event);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return super.onInterceptTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return super.onTouchEvent(event);
        }


        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {

        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        // 1. 用于测试目，直接忽略
//        if (mInputEventConsistencyVerifier != null) {
//            mInputEventConsistencyVerifier.onTouchEvent(ev, 1);
//        }
//
//        // If the event targets the accessibility focused view and this is it, start
//        // normal event dispatch. Maybe a descendant is what will handle the click.
//        //如果事件的目标是可访问性焦点视图，那就开始正常的事件调度。直接忽略
//        if (ev.isTargetAccessibilityFocus() && isAccessibilityFocusedViewOrHost()) {
//            ev.setTargetAccessibilityFocus(false);
//        }
//
//        boolean handled = false;
//        // 2. 未被其他窗口遮盖
//        if (onFilterTouchEventForSecurity(ev)) {
//            final int action = ev.getAction();
//            final int actionMasked = action & MotionEvent.ACTION_MASK;
//
//            // Handle an initial down.
//            if (actionMasked == MotionEvent.ACTION_DOWN) {
//                // Throw away all previous state when starting a new touch gesture.
//                // The framework may have dropped the up or cancel event for the previous gesture
//                // due to an app switch, ANR, or some other state change.
//                // 3. 清理触摸操作的所有痕迹，抛弃以前的所有状态，所有状态恢复初始值
//                cancelAndClearTouchTargets(ev);
//                resetTouchState();
//            }
//
//            // Check for interception.
//            //这个变量检测是否拦截此事件
//            final boolean intercepted;
//            //当第一次进来时 处理事件时action_down mFirstTouchTarget 肯定是空，
//            // 如果决定拦截action_down事件，到up的所有事件都将被拦截，因为mFirstTouchTarget==null
//            if (actionMasked == MotionEvent.ACTION_DOWN
//                    || mFirstTouchTarget != null) {
//
//                /**
//                 * 当时action_down事件时 FLAG_DISALLOW_INTERCEPT置为原来的状态，mFirstTouchTarget==null
//                 * 此时disallowIntercept为false,此时viewgroup执行onInterceptTouchEvent判断是否拦截(如是否落在子view区域内等)
//                 * 如果onInterceptTouchEvent返回为true，则表示此viewgroup拦截action_down事件，下面的if (!canceled && !intercepted) {}
//                 * 将不会通过，而此判断内就是分发给子view的逻辑，也就是说将不会分发action_down事件到子view，此时将导致mFirstTouchTarget == null，
//                 * 下面一直到action_up的一系列事件将都有此viewgroup处理，(看此处判断就知道了intercepted会一直为false,
//                 * if (!canceled && !intercepted) {}又不会执行，mFirstTouchTarget 一直为null),
//                 *
//                 * 继续下面代码的分析action_down
//                 * mFirstTouchTarget==null的话将导致下面的代码执行，
//                 * Dispatch to touch targets.
//                 *  if (mFirstTouchTarget == null) {
//                 *  // No touch targets so treat this as an ordinary view.
//                 *  handled = dispatchTransformedTouchEvent(ev, canceled, null,
//                 *   TouchTarget.ALL_POINTER_IDS);}else{····}
//                 *   dispatchTransformedTouchEvent第三个参数childview为null会导致什么，
//                 *   会执行super.dispatchTouchEvent方法的执行也就是View类的dispatchTouchEvent,
//                 *   View类的dispatchTouchEvent会执行onTouchEvent,如果设置了onTouchListener,将会执行onTouchListener的onTouch方法，
//                 *   如果设置了onClickLister的话将会在onTouchEvent内执行onClickListener的onClick方法，
//                 *   如果此viewgroup的onTouchevent返回false(自定义的情况下可能会导致这种情况的出现，一般都会返回true)，
//                 *   则handled为false,即dispatchTransformedTouchEvent返回了false,也就是说我拦截了此事件，但没有去消耗，
//                 *   这时本viewgroup dispatchTouchEvent将返回handled==false,
//                 *   这将导致父ViewGroup的dispatchTransformedTouchEvent 分发失败 返回false,
//                 *   此时父ViewGroup的如下判断内的代码不会执行
//                 *           if (dispatchTransformedTouchEvent(ev, false, child, idBitsToAssign)) {
//                 *              // Child wants to receive touch within its bounds.
//                 *              ····
//                 *             }
//                 *             父viewgroup继续循环，找寻何时的子view去分发，到最后都没有找到合适的子view
//                 *             newTouchTarget = addTouchTarget(child, idBitsToAssign);判断内的这句代码将不会执行
//                 *             mFirstTouchTarget将不会被赋值mFirstTouchTarget==null,，父viewgroup 继续执行如下代码
//                 *             handled = dispatchTransformedTouchEvent(ev, canceled, null,
//                 *             如此循环，直到父view得到处理，最后会到activity,
//                 *             如果父viewgroup找到了合适的子view处理 也就是说子view的dispatchTouchEvent返回true,则
//                 *             dispatchTransformedTouchEvent为true，
//                 *                          if (dispatchTransformedTouchEvent(ev, false, child, idBitsToAssign)) {
//                 *                           // Child wants to receive touch within its bounds.
//                 *                           ·····
//                 *                           }
//                 *                          则mFirstTouchTarget被赋值，结束循环
//                 *
//                 *                          则下面代码执行
//                 *                                     // Dispatch to touch targets.
//                 *                if (mFirstTouchTarget == null) {
//                 *                  // No touch targets so treat this as an ordinary view.
//                 *                    handled = dispatchTransformedTouchEvent(ev, canceled, null,
//                 *                    TouchTarget.ALL_POINTER_IDS);
//                 *                } else {
//                 *                   这儿执行
//                 *                   hanled==true
//                 *                   此时父viewgroup的dispatchTransformedTouchEvent得到的值也为true,父viewgroup结束循环，整个事件分发     过程
//                 *                   结束
//                 *               }
//                 *
//                 *
//                 *
//                 *
//                 */
//                final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
//                if (!disallowIntercept) {
//                    intercepted = onInterceptTouchEvent(ev);
//                    ev.setAction(action); // restore action in case it was changed
//                } else {
//                    intercepted = false;
//                }
//            } else {
//                // There are no touch targets and this action is not an initial down
//                // so this view group continues to intercept touches.
//                intercepted = true;
//            }
//
//            // If intercepted, start normal event dispatch. Also if there is already
//            // a view that is handling the gesture, do normal event dispatch.
//            if (intercepted || mFirstTouchTarget != null) {
//                ev.setTargetAccessibilityFocus(false);
//            }
//
//            // Check for cancelation.
//            final boolean canceled = resetCancelNextUpFlag(this)
//                    || actionMasked == MotionEvent.ACTION_CANCEL;
//
//            // Update list of touch targets for pointer down, if needed.
//            final boolean split = (mGroupFlags & FLAG_SPLIT_MOTION_EVENTS) != 0;
//            TouchTarget newTouchTarget = null;
//            boolean alreadyDispatchedToNewTouchTarget = false;
//            if (!canceled && !intercepted) {
//
//                // If the event is targeting accessiiblity focus we give it to the
//                // view that has accessibility focus and if it does not handle it
//                // we clear the flag and dispatch the event to all children as usual.
//                // We are looking up the accessibility focused host to avoid keeping
//                // state since these events are very rare.
//                View childWithAccessibilityFocus = ev.isTargetAccessibilityFocus()
//                        ? findChildWithAccessibilityFocus() : null;
//
//                if (actionMasked == MotionEvent.ACTION_DOWN
//                        || (split && actionMasked == MotionEvent.ACTION_POINTER_DOWN)
//                        || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
//                    final int actionIndex = ev.getActionIndex(); // always 0 for down
//                    final int idBitsToAssign = split ? 1 << ev.getPointerId(actionIndex)
//                            : TouchTarget.ALL_POINTER_IDS;
//
//                    // Clean up earlier touch targets for this pointer id in case they
//                    // have become out of sync.
//                    removePointersFromTouchTargets(idBitsToAssign);
//
//                    final int childrenCount = mChildrenCount;
//                    if (newTouchTarget == null && childrenCount != 0) {
//                        final float x = ev.getX(actionIndex);
//                        final float y = ev.getY(actionIndex);
//                        // Find a child that can receive the event.
//                        // Scan children from front to back.
//                        final ArrayList<View> preorderedList = buildTouchDispatchChildList();
//                        final boolean customOrder = preorderedList == null
//                                && isChildrenDrawingOrderEnabled();
//                        final View[] children = mChildren;
//                        for (int i = childrenCount - 1; i >= 0; i--) {
//                            final int childIndex = getAndVerifyPreorderedIndex(
//                                    childrenCount, i, customOrder);
//                            final View child = getAndVerifyPreorderedView(
//                                    preorderedList, children, childIndex);
//
//                            // If there is a view that has accessibility focus we want it
//                            // to get the event first and if not handled we will perform a
//                            // normal dispatch. We may do a double iteration but this is
//                            // safer given the timeframe.
//                            if (childWithAccessibilityFocus != null) {
//                                if (childWithAccessibilityFocus != child) {
//                                    continue;
//                                }
//                                childWithAccessibilityFocus = null;
//                                i = childrenCount - 1;
//                            }
//
//                            if (!canViewReceivePointerEvents(child)
//                                    || !isTransformedTouchPointInView(x, y, child, null)) {
//                                ev.setTargetAccessibilityFocus(false);
//                                continue;
//                            }
//
//                            newTouchTarget = getTouchTarget(child);
//                            if (newTouchTarget != null) {
//                                // Child is already receiving touch within its bounds.
//                                // Give it the new pointer in addition to the ones it is handling.
//                                newTouchTarget.pointerIdBits |= idBitsToAssign;
//                                break;
//                            }
//
//                            resetCancelNextUpFlag(child);
//                            if (dispatchTransformedTouchEvent(ev, false, child, idBitsToAssign)) {
//                                // Child wants to receive touch within its bounds.
//                                mLastTouchDownTime = ev.getDownTime();
//                                if (preorderedList != null) {
//                                    // childIndex points into presorted list, find original index
//                                    for (int j = 0; j < childrenCount; j++) {
//                                        if (children[childIndex] == mChildren[j]) {
//                                            mLastTouchDownIndex = j;
//                                            break;
//                                        }
//                                    }
//                                } else {
//                                    mLastTouchDownIndex = childIndex;
//                                }
//                                mLastTouchDownX = ev.getX();
//                                mLastTouchDownY = ev.getY();
//                                newTouchTarget = addTouchTarget(child, idBitsToAssign);
//                                alreadyDispatchedToNewTouchTarget = true;
//                                break;
//                            }
//
//                            // The accessibility focus didn't handle the event, so clear
//                            // the flag and do a normal dispatch to all children.
//                            ev.setTargetAccessibilityFocus(false);
//                        }
//                        if (preorderedList != null) preorderedList.clear();
//                    }
//
//                    if (newTouchTarget == null && mFirstTouchTarget != null) {
//                        // Did not find a child to receive the event.
//                        // Assign the pointer to the least recently added target.
//                        newTouchTarget = mFirstTouchTarget;
//                        while (newTouchTarget.next != null) {
//                            newTouchTarget = newTouchTarget.next;
//                        }
//                        newTouchTarget.pointerIdBits |= idBitsToAssign;
//                    }
//                }
//            }
//
//            // Dispatch to touch targets.
//            if (mFirstTouchTarget == null) {
//                // No touch targets so treat this as an ordinary view.
//                handled = dispatchTransformedTouchEvent(ev, canceled, null,
//                        TouchTarget.ALL_POINTER_IDS);
//            } else {
//                // Dispatch to touch targets, excluding the new touch target if we already
//                // dispatched to it.  Cancel touch targets if necessary.
//                TouchTarget predecessor = null;
//                TouchTarget target = mFirstTouchTarget;
//                while (target != null) {
//                    final TouchTarget next = target.next;
//                    if (alreadyDispatchedToNewTouchTarget && target == newTouchTarget) {
//                        handled = true;
//                    } else {
//                        final boolean cancelChild = resetCancelNextUpFlag(target.child)
//                                || intercepted;
//                        if (dispatchTransformedTouchEvent(ev, cancelChild,
//                                target.child, target.pointerIdBits)) {
//                            handled = true;
//                        }
//                        if (cancelChild) {
//                            if (predecessor == null) {
//                                mFirstTouchTarget = next;
//                            } else {
//                                predecessor.next = next;
//                            }
//                            target.recycle();
//                            target = next;
//                            continue;
//                        }
//                    }
//                    predecessor = target;
//                    target = next;
//                }
//            }
//
//            // Update list of touch targets for pointer up or cancel, if needed.
//            if (canceled
//                    || actionMasked == MotionEvent.ACTION_UP
//                    || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
//                resetTouchState();
//            } else if (split && actionMasked == MotionEvent.ACTION_POINTER_UP) {
//                final int actionIndex = ev.getActionIndex();
//                final int idBitsToRemove = 1 << ev.getPointerId(actionIndex);
//                removePointersFromTouchTargets(idBitsToRemove);
//            }
//        }
//
//        if (!handled && mInputEventConsistencyVerifier != null) {
//            mInputEventConsistencyVerifier.onUnhandledEvent(ev, 1);
//        }
//        return handled;
//    }
//
//
//
//    /**
//     * Transforms a motion event into the coordinate space of a particular child view,
//     * filters out irrelevant pointer ids, and overrides its action if necessary.
//     * If child is null, assumes the MotionEvent will be sent to this ViewGroup instead.
//     */
//    private boolean dispatchTransformedTouchEvent(MotionEvent event, boolean cancel,
//                                                  View child, int desiredPointerIdBits) {
//        final boolean handled;
//
//        // Canceling motions is a special case.  We don't need to perform any transformations
//        // or filtering.  The important part is the action, not the contents.
//        final int oldAction = event.getAction();
//        if (cancel || oldAction == MotionEvent.ACTION_CANCEL) {
//            event.setAction(MotionEvent.ACTION_CANCEL);
//            if (child == null) {
//                handled = super.dispatchTouchEvent(event);
//            } else {
//                handled = child.dispatchTouchEvent(event);
//            }
//            event.setAction(oldAction);
//            return handled;
//        }
//
//        // Calculate the number of pointers to deliver.
//        final int oldPointerIdBits = event.getPointerIdBits();
//        final int newPointerIdBits = oldPointerIdBits & desiredPointerIdBits;
//
//        // If for some reason we ended up in an inconsistent state where it looks like we
//        // might produce a motion event with no pointers in it, then drop the event.
//        if (newPointerIdBits == 0) {
//            return false;
//        }
//
//        // If the number of pointers is the same and we don't need to perform any fancy
//        // irreversible transformations, then we can reuse the motion event for this
//        // dispatch as long as we are careful to revert any changes we make.
//        // Otherwise we need to make a copy.
//        final MotionEvent transformedEvent;
//        if (newPointerIdBits == oldPointerIdBits) {
//            if (child == null || child.hasIdentityMatrix()) {
//                if (child == null) {
//                    handled = super.dispatchTouchEvent(event);
//                } else {
//                    final float offsetX = mScrollX - child.mLeft;
//                    final float offsetY = mScrollY - child.mTop;
//                    event.offsetLocation(offsetX, offsetY);
//
//                    handled = child.dispatchTouchEvent(event);
//
//                    event.offsetLocation(-offsetX, -offsetY);
//                }
//                return handled;
//            }
//            transformedEvent = MotionEvent.obtain(event);
//        } else {
//            transformedEvent = event.split(newPointerIdBits);
//        }
//
//        // Perform any necessary transformations and dispatch.
//        if (child == null) {
//            handled = super.dispatchTouchEvent(transformedEvent);
//        } else {
//            final float offsetX = mScrollX - child.mLeft;
//            final float offsetY = mScrollY - child.mTop;
//            transformedEvent.offsetLocation(offsetX, offsetY);
//            if (! child.hasIdentityMatrix()) {
//                transformedEvent.transform(child.getInverseMatrix());
//            }
//
//            handled = child.dispatchTouchEvent(transformedEvent);
//        }
//
//        // Done.
//        transformedEvent.recycle();
//        return handled;
//    }
}
