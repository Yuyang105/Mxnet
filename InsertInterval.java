/**
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        
        List<Point> point = new ArrayList<Point>();
        for (Interval i : intervals) {
            point.add(new Point(1, i.start));
            point.add(new Point(0, i.end));
        }
        point.add(new Point(1, newInterval.start));
        point.add(new Point(0, newInterval.end));
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
