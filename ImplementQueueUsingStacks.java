/**
两种做法：

第一种：push O(1) pop O(n)
    push的时候，直接压入s1，pop的时候，全部先压入s2，再取s2的头
    
第二种：push O(n) pop O(1)
    push的时候，如果s1不空，就把s1里面的都倒入s2，然后往s2压入应该压入的元素x，再倒回s1.


*/
class MyQueue {
    Stack <Integer> s1;
    Stack <Integer> s2;
    
    public MyQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }
    
    // time: O(n)
    public void push(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s2.push(x);
        while(!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
    
    // time: O(1)
    public int pop() {
        return s1.pop();
    }
    
    // time: O(1)
    public int peek() {
        return s1.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}


class MyQueue1 {

    Stack<Integer> s1;
    Stack<Integer> s2;
    
    public MyQueue1() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }
    
    // time: O(1)
    public void push(int x) {
        s1.push(x);
    }
    
    // time: O(n)
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        return s2.pop();
    }
    
    // time: O(n)
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        return s2.peek();
    }
    
    // time: O(1)
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
