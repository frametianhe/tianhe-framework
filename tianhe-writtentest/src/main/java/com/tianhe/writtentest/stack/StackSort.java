package com.tianhe.writtentest.stack;

import java.util.Stack;

/**
 * 关键点是保证临时栈的元素顺序和原始栈的顺序一致，最后在把临时栈的元素弹出来push到原始栈中即可
 * Created by tianhe on 2020/1/15.
 */
public class StackSort {

    /**
     * 原始栈中元素从栈顶到站底 1 2 3 4 5，用一个临时变量，一个临时栈，使栈中的元素顺序倒过来
     *
     * @param args
     */
    public static void main(String[] args) {
        Stack stack = new Stack();
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }
//        排序之前
        System.out.println(stack);
        Object temp = 0;
        Stack tempStack = new Stack();

        if (tempStack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        while (!stack.isEmpty()) {
            int tempStackSize = tempStack.size();
            temp = stack.pop();
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
            tempStack.push(temp);
            for (int i = 0; i < tempStackSize; i++) {
                tempStack.push(stack.pop());
            }
        }

        while (!tempStack.isEmpty()){
            stack.push(tempStack.pop());
        }

//        排序之后
        System.out.println(stack);
    }
}
