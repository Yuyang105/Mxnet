/**

扫描线，把时间看成一条线，也就是timeline，我们加入points。

此时，我们就需要新建一个point类，一个是时间点，一个是记录它是个开始点/结束点。

然后一路扫过去~

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
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (x, y) -> x.start - y.start);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1].end > intervals[i].start) {
                return false;
            }
        }
        return true;
    }
    
    public boolean canAttendMeetings1(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        
        int count = 0;
        
        List<Point> points = new ArrayList<Point>();
        for (Interval i : intervals) {
            points.add(new Point(1, i.start));
            points.add(new Point(0, i.end));
        }
        
        Collections.sort(points, Point.PointComparator);
        
        for (Point p : points) {
            if (p.flag == 1) count++;
            else count--;
            if (count > 1) return false;
        }
        return true;
    }
}

class Point {
    int flag, time;
    public Point(int flag, int time) {
        this.flag = flag;
        this.time = time;
    }
    public static Comparator<Point> PointComparator = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time)
                return p1.flag - p2.flag;
            return p1.time - p2.time;
        }
    };
}
