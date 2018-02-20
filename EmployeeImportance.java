/**

题意：规定了一个员工信息的数据结构，包含了雇员的id(唯一)、重要值和他的直系下属的雇员id。
一个员工至多只有一个直系领导，但是可以有多个下属。求出给定一个员工集合及领导的id，求出领导及其所有下属的重要值总和。

用hashMap将id与其对应的数据结构映射起来，找到id对应的员工信息并将其加入到一个队列中。

*/

/**
You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:
Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
Note:
One employee has at most one direct leader and may have several subordinates.
The maximum number of employees won't exceed 2000.
*/

/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        int res = 0;
        Map<Integer, Employee> map = new HashMap<Integer, Employee>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        Queue<Employee> q = new LinkedList<>();
        q.offer(map.get(id));
        while (!q.isEmpty()) {
            Employee current = q.poll();
            res += current.importance;
            for (Integer i : current.subordinates) {
                q.offer(map.get(i));
            }
        }
        return res;
    }
}
