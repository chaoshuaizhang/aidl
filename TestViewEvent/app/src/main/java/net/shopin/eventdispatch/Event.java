//package net.shopin.eventdispatch;
//
//import android.view.MotionEvent;
//import android.view.View;
//
//import java.util.ArrayList;
//
///**
// * Created by zcs on 2019/2/12.
// */
//
//public class Event {
//
//
//    /**
//     * Activity
//     * @param ev
//     * @return
//     */
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            onUserInteraction();
//        }
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
//        return onTouchEvent(ev);
//    }
//
//    /**
//     * PhoneWindow
//     * @param event
//     * @return
//     */
//    public boolean superDispatchTouchEvent(MotionEvent event) {
//        return mDecor.superDispatchTouchEvent(event);
//    }
//
//    /**
//     * DecorView
//     * @param event
//     * @return
//     */
//    public boolean superDispatchTouchEvent(MotionEvent event) {
//        return super.dispatchTouchEvent(event);
//    }
//
//    /**
//     * ViewGroup
//     * @param ev
//     * @return
//     */
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean handled = false;
//        if (onFilterTouchEventForSecurity(ev)) {
//            final int action = ev.getAction();
//            final int actionMasked = action & MotionEvent.ACTION_MASK;
//            // Handle an initial down.
//            if (actionMasked == MotionEvent.ACTION_DOWN) {
//                //在新的ACTION_DOWN到来时，清除先前的状态，重置点击状态
//                cancelAndClearTouchTargets(ev);
//                resetTouchState();
//            }
//            final boolean intercepted;
//            //在以下两种情况下才会去 判断是否需要拦截
//            //mFirstTouchTarget不为空的情况：当ViewGroup的子元素成功处理时，mFirstTouchTarget不为空
//            //即：viewGroup处理时，mFirstTouchTarget为空
//            if (actionMasked == MotionEvent.ACTION_DOWN || mFirstTouchTarget != null) {
//                final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
//                if (!disallowIntercept) {//允许拦截
//                    intercepted = onInterceptTouchEvent(ev);
//                    ev.setAction(action); // restore action in case it was changed
//                } else {//不允许拦截
//                    intercepted = false;
//                }
//            } else {
//                //当事件不是ACTION_DOWN || mFirstTouchTarget=null时--->说明ViewGroup已经拦截了，
//                //所以onInterceptTouchEvent就不会再调用
//                intercepted = true;
//            }
//            final boolean canceled = resetCancelNextUpFlag(this) || actionMasked == MotionEvent.ACTION_CANCEL;
//            final boolean split = (mGroupFlags & FLAG_SPLIT_MOTION_EVENTS) != 0;
//            TouchTarget newTouchTarget = null;
//            boolean alreadyDispatchedToNewTouchTarget = false;
//            if (!canceled && !intercepted) {//当ViewGroup没有拦截时（可想而知：它不拦截肯定是交给了子View去处理了）
//                if (actionMasked == MotionEvent.ACTION_DOWN
//                        || (split && actionMasked == MotionEvent.ACTION_POINTER_DOWN)
//                        || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
//                    final int actionIndex = ev.getActionIndex(); // always 0 for down
//                    final int idBitsToAssign = split ? 1 << ev.getPointerId(actionIndex)
//                            : TouchTarget.ALL_POINTER_IDS;
//                    removePointersFromTouchTargets(idBitsToAssign);
//                    final int childrenCount = mChildrenCount;
//                    if (newTouchTarget == null && childrenCount != 0) {
//                        final float x = ev.getX(actionIndex);
//                        final float y = ev.getY(actionIndex);
//                        final ArrayList<View> preorderedList = buildTouchDispatchChildList();
//                        final boolean customOrder = preorderedList == null && isChildrenDrawingOrderEnabled();
//                        //获取当前View的子View
//                        final View[] children = mChildren;
//                        for (int i = childrenCount - 1; i >= 0; i--) {
//                            //根据child的个数和数组下标获取child真正的层级序号
//                            final int childIndex = getAndVerifyPreorderedIndex(childrenCount, i, customOrder);
//                            //根据上述的层级序号，一级一级取出子View
//                            final View child = getAndVerifyPreorderedView(preorderedList, children, childIndex);
//                            //判断child是否可以接收到point事件 || 子view是否在转换为其坐标空间时包含point
//                            if (!canViewReceivePointerEvents(child) || !isTransformedTouchPointInView(x, y, child, null)) {
//                                ev.setTargetAccessibilityFocus(false);
//                                continue;
//                            }
//                            //根据子view 找到对应的TouchTarget
//                            newTouchTarget = getTouchTarget(child);
//                            if (newTouchTarget != null) {
//                                // Child is already receiving touch within its bounds.
//                                // Give it the new pointer in addition to the ones it is handling.
//                                newTouchTarget.pointerIdBits |= idBitsToAssign;
//                                break;
//                            }
//                            resetCancelNextUpFlag(child);
//                            //转换触摸事件（根据有没有子View进行转发）
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
//                                //在addTouchTarget中给mFirstTouchTarget赋值，此时mFirstTouchTarget==newTouchTarget
//                                //注意：从上边的分析可以看出，mFirstTouchTarget是否为空直接关系到ViewGroup是否拦截事件
//                                newTouchTarget = addTouchTarget(child, idBitsToAssign);
//                                alreadyDispatchedToNewTouchTarget = true;
//                                break;
//                            }
//                            // The accessibility focus didn't handle the event, so clear
//                            // the flag and do a normal dispatch to all children.
//                            ev.setTargetAccessibilityFocus(false);
//                        }
//                        if (preorderedList != null) preorderedList.clear();
//                    }
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
//            // Dispatch to touch targets.
//            if (mFirstTouchTarget == null) {
//                // No touch targets so treat this as an ordinary view.
//                handled = dispatchTransformedTouchEvent(ev, canceled, null,TouchTarget.ALL_POINTER_IDS);
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
//                        final boolean cancelChild = resetCancelNextUpFlag(target.child) || intercepted;
//                        if (dispatchTransformedTouchEvent(ev, cancelChild, target.child, target.pointerIdBits)) {
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
//    /*
//    * 把ViewGroup的dispatchTouchEvent方法分解分析
//    * */
//
//    //在以下两种情况下才会去判断是否需要拦截（是否执行onInterceptTouchEvent方法）
//    if (actionMasked == MotionEvent.ACTION_DOWN || mFirstTouchTarget != null) {
//        final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
//        if (!disallowIntercept) {//允许拦截
//            intercepted = onInterceptTouchEvent(ev);
//            ev.setAction(action); // restore action in case it was changed
//        } else {//不允许
//            intercepted = false;
//        }
//    } else {
//        //当事件不是ACTION_DOWN || mFirstTouchTarget=null时--->说明ViewGroup已经拦截了，
//        intercepted = true;
//    }
//
//    >mFirstTouchTarget不为空的情况：当ViewGroup的子元素成功处理时，mFirstTouchTarget不为空，
//    即：viewGroup处理时，mFirstTouchTarget为空
//    >>那么可得出结论：只要ViewGroup选择去拦截事件，那么后续的事件都会交给它处理，而不会再执行onInterceptTouchEvent
//
//
//    //在新的ACTION_DOWN事件触发时：
//    if (actionMasked == MotionEvent.ACTION_DOWN) {
//        //清除先前的状态，重置点击状态
//        cancelAndClearTouchTargets(ev);
//        resetTouchState();
//    }
//
//    /**
//     * Resets all touch state in preparation for a new cycle.
//     */
//    private void resetTouchState() {
//        clearTouchTargets();
//        resetCancelNextUpFlag(this);
//        //设置mGroupFlags，设置为ViewGroup可以任
//        //何事件（因为事件分发要重新开始了），此处的位运算不是很懂，
//        //但感觉是把mGroupFlags恢复成最初的值
//        mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
//        mNestedScrollAxes = SCROLL_AXIS_NONE;
//    }
//
//
//    /**
//     * 给mFirstTouchTarget赋值（从这里可以看出，
//     * touchtarget是一个链表结构，相当于是最顶层View和最底层的View的一个连接关系）
//     * @param child
//     * @param pointerIdBits
//     * @return
//     */
//    private TouchTarget addTouchTarget(@NonNull View child, int pointerIdBits) {
//        final TouchTarget target = TouchTarget.obtain(child, pointerIdBits);
//        target.next = mFirstTouchTarget;
//        mFirstTouchTarget = target;
//        return target;
//    }
//
//
//
//
//}
