/**
Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

 Notice

If landing and flying happens at the same time, we consider landing should happen at first.

Have you met this question in a real interview? Yes
Example
For interval list

[
  [1,10],
  [2,3],
  [5,8],
  [4,7]
]
Return 3
*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Point {
    int flag, time;
    Point(int f, int t) {
        this.flag = f;
        this.time = t;
    }
    public static Comparator<Point> PointComparator = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) return p1.flag - p2.flag;
            else return p1.time - p2.time;
        }
    };
}

public class Solution {
    /*
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
        int res = 0, count = 0;
        if (airplanes == null || airplanes.size() == 0) return res;
        
        List<Point> points = new ArrayList<Point>(); 
        for (Interval i : airplanes) {
            points.add(new Point(1, i.start));
            points.add(new Point(0, i.end));
        }
        Collections.sort(points, Point.PointComparator);
        for (Point p : points) {
            if (p.flag == 1) count++;
            else count--;
            res = Math.max(res, count);
        }
        return res;
    }
}
