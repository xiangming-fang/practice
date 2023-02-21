package com.xm.jy.test.jdk.datastruct;

import java.util.PriorityQueue;

/**
 * @ClassName: PriorityQueueTest
 * @Description: 类的作用描述
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/29 14:02
 */
public class PriorityQueueTest {
    public static void main(String[] args) {

        final PriorityQueue<Student> queue=new PriorityQueue<>();

        Student p1=new Student(95,"相哥");
        Student p2=new Student(89,"明明");
        Student p3=new Student(89,"郭哥");
        Student p4=new Student(67,"秦哥");
        Student p5=new Student(92,"林就远");
        Student p6=new Student(96,"万羊鸟");
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);//add 和offer效果一样。
        queue.offer(p4);//add 方法实现，其实就是调用了offer
        queue.offer(p5);
        queue.add(p6);
        queue.remove(p6);

        System.out.println(queue.peek());
        System.out.println(queue.poll());

        for (Student Student : queue) {
            System.out.println(Student.toString());
        }
        System.out.println(queue.contains(p6));
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println(queue);
        System.out.println("---------按顺序出队选座------------");
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println(queue.size());
    }

}
class Student implements Comparable<Student>{
    private int score;
    private String name;

    public Student(int age,String name){
        this.score=age;
        this.name=name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "姓名："+name+"-"+score+"分";
    }

    @Override
    public int compareTo(Student o) {
        Student current=(Student)o;
        if(current.getScore()>this.score){
            return 1;
        }else if(current.getScore()==this.score){
            return 0;
        }
        return -1;
    }
}
