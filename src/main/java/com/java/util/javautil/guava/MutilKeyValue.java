package com.java.util.javautil.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class MutilKeyValue {

    public static void main(String[] args) {
        StudentScore t1 = new StudentScore("张三", "语文", "三年二班", 80D, "2021上");
        StudentScore t2 = new StudentScore("张三", "数学", "三年二班", 80D, "2021上");
        StudentScore t3 = new StudentScore("李四", "语文", "三年二班", 100D, "2021上");
        StudentScore t4 = new StudentScore("李四", "数学", "三年二班", 90D, "2021上");
        List<StudentScore> testScoreList = Lists.newArrayList(t1, t2, t3, t4);
        HashBasedTable<String, String, Double> objectObjectObjectHashBasedTable = HashBasedTable.create();

        for (StudentScore studentScore : testScoreList) {
            objectObjectObjectHashBasedTable.put(studentScore.getStudentName(), studentScore.getSubject(), studentScore.getScore());
        }
        //3.计算每个学生的总分
        Map<String, Map<String, Double>> studentSubjectScoreMap = objectObjectObjectHashBasedTable.rowMap();
        Map<String, Double> studentGrossScoreMap = Maps.newHashMap();
        for (Map.Entry<String, Map<String, Double>> entry : studentSubjectScoreMap.entrySet()) {
            String studentName = entry.getKey();
            // 总分
            double grossScore = entry.getValue().values().stream().mapToDouble(e -> e).sum();
            studentGrossScoreMap.put(studentName, grossScore);
        }
        // 4.计算班级总分平均分
        double grossScoreAvg = studentGrossScoreMap.values().stream().mapToDouble(e -> e).average().orElse(-1);
        // 5.计算科目平均分
        Map<String, Map<String, Double>> subjectStudentScoreMap = objectObjectObjectHashBasedTable.columnMap();
        Map<Object, Object> subjectScoreAvgMap = Maps.newHashMap();
        for (Map.Entry<String, Map<String, Double>> entry : subjectStudentScoreMap.entrySet()) {
            String subject = entry.getKey();
            // 科目平均分
            double subjectScoreAvg = entry.getValue().values().stream().mapToDouble(e -> e).average().orElse(-1);
            subjectScoreAvgMap.put(subject, subjectScoreAvg);
        }
        // 学生总分
        System.out.println(studentGrossScoreMap);
        // 班级科目平均分
        System.out.println(subjectScoreAvgMap);
        // 班级总分平均分
        System.out.println(grossScoreAvg);

    }


    public static class StudentScore {
        private String studentName;

        private String subject;

        private String semester;

        private Double score;

        private String classGrade;

        @Override
        public String toString() {
            return "StudentScore{" +
                    "studentName='" + studentName + '\'' +
                    ", subject='" + subject + '\'' +
                    ", semester='" + semester + '\'' +
                    ", score=" + score +
                    ", classGrade='" + classGrade + '\'' +
                    '}';
        }

        public StudentScore(String studentName, String subject, String semester, Double score, String classGrade) {
            this.studentName = studentName;
            this.subject = subject;
            this.semester = semester;
            this.score = score;
            this.classGrade = classGrade;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public String getClassGrade() {
            return classGrade;
        }

        public void setClassGrade(String classGrade) {
            this.classGrade = classGrade;
        }
    }
}
