package com.zp.gmall.module.system;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : zhengpanone
 * Date : 2025/8/29 15:52
 * Version : v1.0.0
 * Description:
 */


public class YearRangeUtils {

    /**
     * 通用年份区间合并方法
     *
     * @param list           原始对象列表
     * @param yearExtractor  提取年份的函数（例如 r -> r.getYear() 或 r -> r.getDate().getYear()）
     * @param groupExtractor 提取分组Key的函数（例如 r -> r.getUnit() + "#" + r.getGrade()）
     * @param formatter      格式化输出的函数（给定 区间字符串 + key，返回一行结果）
     * @param <T>            对象类型
     * @return 格式化好的字符串（每条结果一行）
     */
    public static <T> String mergeYearRanges(
            List<T> list,
            Function<T, Integer> yearExtractor,
            Function<T, String> groupExtractor,
            BiFunction<String, String, String> formatter
    ) {
        // 按 (groupKey) 分组，年份倒序
        Map<String, List<Integer>> grouped = list.stream()
                .collect(Collectors.groupingBy(
                        groupExtractor,
                        Collectors.mapping(yearExtractor, Collectors.toList())
                ));

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : grouped.entrySet()) {
            List<Integer> years = entry.getValue().stream()
                    .sorted(Comparator.reverseOrder())
                    .toList();

            int start = years.get(0), prev = years.get(0);
            for (int i = 1; i <= years.size(); i++) {
                if (i == years.size() || years.get(i) != prev - 1) {
                    String yearStr = (start == prev) ? String.valueOf(start) : prev + "-" + start;
                    result.add(formatter.apply(yearStr, entry.getKey()));
                    if (i < years.size()) {
                        start = years.get(i);
                    }
                }
                if (i < years.size()) {
                    prev = years.get(i);
                }
            }
        }

        // 保持倒序输出
        return String.join("\n", result);
    }


    public static void main(String[] args) {

        class A {
            int year;
            String unit;
            String grade;

            public A() {
            }

            public A(int year, String unit, String grade) {
                this.year = year;
                this.unit = unit;
                this.grade = grade;
            }

            public int getYear() {
                return year;
            }

            public String getUnit() {
                return unit;
            }

            public String getGrade() {
                return grade;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }
        }

        //class B {
        //    Date date;
        //    String company;
        //    String level;
        //}

        List<A> listA = Arrays.asList(
                new A(2025, "单位A", "优秀"),
                new A(2024, "单位A", "优秀"),
                new A(2024, "单位B", "优秀"),
                new A(2023, "单位B", "一般")
        );
        String resultA = YearRangeUtils.mergeYearRanges(
                listA,
                A::getYear,
                a -> a.getUnit() + "#" + a.getGrade(),
                (yearStr, key) -> {
                    String[] parts = key.split("#");
                    return yearStr + "，" + parts[0] + "，" + parts[1];
                }
        );

        System.out.println(resultA);
    }
}
