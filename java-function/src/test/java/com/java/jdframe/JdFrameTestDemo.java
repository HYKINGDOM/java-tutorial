package com.java.jdframe;

import com.alibaba.fastjson2.JSON;
import com.java.domain.Student;
import com.java.domain.UserInfo;
import com.java.domain.WebPvDto;
import io.github.burukeyou.dataframe.iframe.JDFrame;
import io.github.burukeyou.dataframe.iframe.SDFrame;
import io.github.burukeyou.dataframe.iframe.item.FI2;
import io.github.burukeyou.dataframe.iframe.item.FI3;
import io.github.burukeyou.dataframe.iframe.item.FI4;
import io.github.burukeyou.dataframe.iframe.support.MaxMin;
import io.github.burukeyou.dataframe.iframe.window.Sorter;
import io.github.burukeyou.dataframe.iframe.window.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JdFrameTestDemo {

    private static List<Student> studentList = new ArrayList<>();


    private static List<WebPvDto> dataList = new ArrayList<>();

    @BeforeEach
    public void init() {

        studentList.add(new Student(1, "a", "一中", "一年级", 11, new BigDecimal(1)));
        studentList.add(new Student(2, "a", "一中", "一年级", 11, new BigDecimal(1)));
        studentList.add(new Student(3, "b", "一中", "三年级", 12, new BigDecimal(2)));
        studentList.add(new Student(4, "c", "二中", "一年级", 13, new BigDecimal(3)));
        studentList.add(new Student(5, "d", "二中", "一年级", 14, new BigDecimal(4)));
        studentList.add(new Student(6, "e", "三中", "二年级", 14, new BigDecimal(5)));
        studentList.add(new Student(7, "e", "三中", "二年级", 15, new BigDecimal(5)));


        dataList.add(new WebPvDto("a",0,1));
        dataList.add(new WebPvDto("a",1,5));
        dataList.add(new WebPvDto("a",2,7));
        dataList.add(new WebPvDto("a",3,3));
        dataList.add(new WebPvDto("a",4,2));
        dataList.add(new WebPvDto("a",5,4));
        dataList.add(new WebPvDto("a",6,4));
        dataList.add(new WebPvDto("b",7,1));
        dataList.add(new WebPvDto("b",8,4));
        dataList.add(new WebPvDto("b",7,6));
        dataList.add(new WebPvDto("b",8,2));
    }

    @Test
    public void test_Function() {
        SDFrame<FI2<String, BigDecimal>> sdf2 =
            SDFrame.read(studentList).whereNotNull(Student::getAge).whereBetween(Student::getAge, 9, 16)
                .groupBySum(Student::getSchool, Student::getScore).sortDesc(FI2::getC2).cutFirst(2);

        sdf2.show();

        List<FI2<String, BigDecimal>> lists = sdf2.toLists();

        System.out.println("columns: " + sdf2.columns());
        System.out.println("count: " + sdf2.count());
        System.out.println("head: " + sdf2.head());
        FI2<String, BigDecimal> head = sdf2.head();
        System.out.println("window: " + sdf2.window());

        System.out.println("JSON: " + JSON.toJSONString(lists));
    }

    @Test
    public void test_Function_01() {
        SDFrame<Student> sdf2 = SDFrame.read(studentList).whereBetween(Student::getAge, 3, 6) // 过滤年龄在[3，6]岁的
            .whereBetweenR(Student::getAge, 3, 6) // 过滤年龄在(3，6]岁的, 不含3岁
            .whereBetweenL(Student::getAge, 3, 6)      // 过滤年龄在[3，6)岁的, 不含6岁
            .whereNotNull(Student::getName) // 过滤名字不为空的数据， 兼容了空字符串''的判断
            .whereGt(Student::getAge, 3)    // 过滤年龄大于3岁
            .whereGe(Student::getAge, 3)   // 过滤年龄大于等于3岁
            .whereLt(Student::getAge, 3)  // 过滤年龄小于3岁的
            .whereIn(Student::getAge, Arrays.asList(3, 7, 8)) // 过滤年龄为3岁 或者7岁 或者 8岁的数据
            .whereNotIn(Student::getAge, Arrays.asList(3, 7, 8)) // 过滤年龄不为为3岁 或者7岁 或者 8岁的数据
            .whereEq(Student::getAge, 3) // 过滤年龄等于3岁的数据
            .whereNotEq(Student::getAge, 3) // 过滤年龄不等于3岁的数据
            .whereLike(Student::getName, "jay") // 模糊查询，等价于 like "%jay%"
            .whereLikeLeft(Student::getName, "jay") // 模糊查询，等价于 like "jay%"
            .whereLikeRight(Student::getName, "jay"); // 模糊查询，等价于 like "%jay"

        sdf2.show();

        List<Student> lists = sdf2.toLists();

        System.out.println("columns: " + sdf2.columns());
        System.out.println("count: " + sdf2.count());
        System.out.println("head: " + sdf2.head());
        System.out.println("window: " + sdf2.window());
    }

    @Test
    public void test_Function_02() {
        JDFrame<Student> frame = JDFrame.read(studentList);
        Student s1 = frame.max(Student::getAge);// 获取年龄最大的学生
        Integer s2 = frame.maxValue(Student::getAge);      // 获取学生里最大的年龄
        Student s3 = frame.min(Student::getAge);// 获取年龄最小的学生
        Integer s4 = frame.minValue(Student::getAge);      // 获取学生里最小的年龄
        BigDecimal s5 = frame.avg(Student::getAge); // 获取所有学生的年龄的平均值
        BigDecimal s6 = frame.sum(Student::getAge); // 获取所有学生的年龄合计
        MaxMin<Student> s7 = frame.maxMin(Student::getAge); // 同时获取年龄最大和最小的学生
        MaxMin<Integer> s8 = frame.maxMinValue(Student::getAge); // 同时获取学生里最大和最小的年龄

        frame.show();

        System.out.println("columns: " + frame.columns());
        System.out.println("count: " + frame.count());
        System.out.println("head: " + frame.head());
        System.out.println("window: " + frame.window());
    }

    @Test
    public void test_Function_03() {
        List<Student> std = null;
        std = SDFrame.read(studentList).distinct().toLists(); // 根据对象hashCode去重
        System.out.println("columns: " + JSON.toJSONString(std));
        std = SDFrame.read(studentList).distinct(Student::getSchool).toLists(); // 根据学校名去重
        System.out.println("columns: " + JSON.toJSONString(std));
        std = SDFrame.read(studentList).distinct(e -> e.getSchool() + e.getLevel()).toLists(); // 根据学校名拼接级别去重复
        System.out.println("columns: " + JSON.toJSONString(std));
        std = SDFrame.read(studentList).distinct(Student::getSchool).distinct(Student::getLevel)
            .toLists(); // 先根据学校名去除重复再根据级别去除重复
        System.out.println("columns: " + JSON.toJSONString(std));

    }

    @Test
    public void test_Function_04() {

        JDFrame<Student> frame = JDFrame.read(studentList);
        // 等价于 select school,sum(age) ... group by school
        List<FI2<String, BigDecimal>> a = frame.groupBySum(Student::getSchool, Student::getAge).toLists();
        // 等价于 select school,max(age) ... group by school
        List<FI2<String, Integer>> a2 = frame.groupByMaxValue(Student::getSchool, Student::getAge).toLists();
        //  与 groupByMaxValue 含义一致，只是返回的是最大的值对象
        List<FI2<String, Student>> a3 = frame.groupByMax(Student::getSchool, Student::getAge).toLists();
        // 等价于 select school,min(age) ... group by school
        List<FI2<String, Integer>> a4 = frame.groupByMinValue(Student::getSchool, Student::getAge).toLists();
        // 等价于 select school,count(*) ... group by school
        List<FI2<String, Long>> a5 = frame.groupByCount(Student::getSchool).toLists();
        // 等价于 select school,avg(age) ... group by school
        List<FI2<String, BigDecimal>> a6 = frame.groupByAvg(Student::getSchool, Student::getAge).toLists();

        // 等价于 select school,sum(age),count(age) group by school
        List<FI3<String, BigDecimal, Long>> a7 = frame.groupBySumCount(Student::getSchool, Student::getAge).toLists();

        // (二级分组)等价于 select school,level,sum(age),count(age) group by school,level
        List<FI3<String, String, BigDecimal>> a8 =
            frame.groupBySum(Student::getSchool, Student::getLevel, Student::getAge).toLists();

        // （三级分组）等价于 select school,level,name,sum(age),count(age) group by school,level,name
        List<FI4<String, String, String, BigDecimal>> a9 =
            frame.groupBySum(Student::getSchool, Student::getLevel, Student::getName, Student::getAge).toLists();
    }

    @Test
    public void test_Function_05() {
        // 等价于 order by age desc
        SDFrame.read(studentList).sortDesc(Student::getAge);
        //  (多级排序) 等价于 order by age desc, level asc.
        SDFrame.read(studentList).sortAsc(Sorter.sortDescBy(Student::getAge).sortAsc(Student::getLevel));
        // 等价于 order by age asc
        SDFrame.read(studentList).sortAsc(Student::getAge);
        // 使用Comparator 排序
        SDFrame.read(studentList).sortAsc(Comparator.comparing(e -> e.getLevel() + e.getId()));
    }

    @Test
    public void test_Function_06() {
        System.out.println("======== 矩阵1 =======");

        SDFrame<Student> sdf = SDFrame.read(studentList);

        sdf.show(20);

        // 获取学生年龄在9到16岁的学学校合计分数最高的前10名
        SDFrame<FI2<String, BigDecimal>> sdf2 =
            SDFrame.read(studentList).whereNotNull(Student::getAge).whereBetween(Student::getAge, 9, 16)
                .groupBySum(Student::getSchool, Student::getScore).sortDesc(FI2::getC2).cutFirst(10);

        System.out.println("======== 矩阵2 =======");
        sdf2.show();

        SDFrame<UserInfo> frame = sdf.join(sdf2, (a, b) -> a.getSchool().equals(b.getC1()), (a, b) -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setSchool(a.getSchool());
            userInfo.setId(b.getC2().intValue());
            userInfo.setName(String.valueOf(a.getId()));
            return userInfo;
        });

        System.out.println("======== 连接后结果 =======");
        frame.show(5);

    }

    @Test
    public void test_Function_07() {
        SDFrame.read(studentList).sortDesc(Student::getAge).addRowNumberCol(Student::setRank).sortAsc(Student::getRank)
            .show(30);
    }

    @Test
    public void test_Function_08() {
        // 所有需要的学校条目
        List<String> allDim = Arrays.asList("一中", "二中", "三中", "四中");
        // 根据学校字段和allDim比较去补充缺失的条目， 缺失的学校按照ReplenishFunction生成补充条目作为结果一起返回
        SDFrame.read(studentList)
            .replenish(Student::getSchool, allDim, (school) -> Student.builder().school(school).build()).show();

        SDFrame.read(studentList).replenish(Student::getSchool, Student::getLevel,
            (school, level) -> Student.builder().school(school).level(level).build()).show(30);

    }

    /**
     * 生成排名号，相同值排名一样，排名不连续 。 如： 1 2 2 2 5 6 7
     */
    @Test
    public void test_Function_09() {
        // 等价于 select ROW_NUMBER() over(partition by type order pv_count desc)
        SDFrame.read(dataList)
            .window(Window.groupBy(WebPvDto::getType).sortDesc(WebPvDto::getPvCount))
            .overRowNumberS(WebPvDto::setValue)
            .show(30);
    }

    /**
     * 生成排名号，相同值排名一样，排名连续 如 1 2 2 2 3 4 5
     */
    @Test
    public void test_Function_010() {
        // 等价于 select rank() over(partition by type order pv_count desc)
        SDFrame.read(dataList)
            .window(Window.groupBy(WebPvDto::getType).sortDesc(WebPvDto::getPvCount))
            .overRankS(WebPvDto::setValue)
            .show(30);
    }

    @Test
    public void test_Function_011() {
        // 等价于 select  DENSE_RANK() over(partition by type order pv_count desc)
        SDFrame.read(dataList)
            .window(Window.groupBy(WebPvDto::getType).sortDesc(WebPvDto::getPvCount))
            .overDenseRankS(WebPvDto::setValue)
            .show(30);
    }

    /**
     * 生成百分比排名号。 使用公式： (rank排名号-1) / (窗口行数-1)
     */
    @Test
    public void test_Function_012() {
        // 等价于 select  PERCENT_RANK() over(partition by type order pv_count desc)
        SDFrame.read(dataList)
            .defaultScale(6)
            .window(Window.groupBy(WebPvDto::getType).sortDesc(WebPvDto::getPvCount))
            .overPercentRankS(WebPvDto::setValue)
            .show(30);
    }

    @Test
    public void test_Function_013() {
        // 等价于 select  PERCENT_RANK() over(partition by type order pv_count desc)
        //  等价于SQL:  select count(*) over(partition by type order by pv_count desc rows between UNBOUNDED PRECEDING and CURRENT ROW)
        SDFrame.read(dataList)
            .window(Window.groupBy(WebPvDto::getType).sortDesc(WebPvDto::getPvCount).roundStartRow2CurrentRow())
            .overCountS(WebPvDto::setValue)
            .show(30);

    }
}
