/**
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

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
    public int minMeetingRooms(Interval[] intervals) {
        int count = 0, res = 0;
        if (intervals == null || intervals.length == 0) return res;
        
        List<Point> point = new ArrayList<Point>();
        for (Interval i : intervals) {
            point.add(new Point(1, i.start));
            point.add(new Point(0, i.end));
        }
        
        Collections.sort(point, Point.PointComparator);
        
        for (Point p : point) {
            if (p.flag == 1) count++;
            else count --;
            res = Math.max(res, count);
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
    public static Comparator<Point> PointComparator = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) return p1.flag - p2.flag;
            return p1.time - p2.time;
        }
    };
}
