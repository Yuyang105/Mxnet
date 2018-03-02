/**

合并重合时间。

起手start = -1，count = 0。
当我们遇到时间起点，count++，如果此时start = -1， 更新start值。
当我们遇到时间结点，count--，如果此时count = 0，往res插入现在的interval，将start变回-1。

*/


/**
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) return res;
        
        List<Point> point = new ArrayList<Point>();
        for (Interval i : intervals) {
            point.add(new Point(1, i.start));
            point.add(new Point(0, i.end));
        }
        Collections.sort(point, Point.PC);
        
        int start = -1, count = 0;
        for (Point p : point) {
            if (p.flag == 1) {
                count++;
                if (start == -1) start = p.time;
            }
            else count--;
            if (count == 0) {
                res.add(new Interval(start, p.time));
                start = -1;
            }
        }
        return res;
    }
}

class Point {
    int flag, time;
    public Point(int f, int t) {
        this.flag = f;
        this.time = t;
    }
    public static Comparator<Point> PC = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) return p2.flag - p1.flag;
            return p1.time - p2.time;
        }
    };
}
