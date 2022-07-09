package com.java.util.javautil.function.sortBy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionSortBy {


    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        DomainUser domainUser1 = DomainUser.builder()
                .id(11L)
                .name("tony")
                .sex(Boolean.FALSE)
                .age(10)
                .build();

        DomainUser domainUser2 = DomainUser.builder()
                .id(41L)
                .name("Test")
                .sex(Boolean.TRUE)
                .age(50)
                .build();

        DomainUser domainUser3 = DomainUser.builder()
                .id(14L)
                .name("Mark")
                .sex(Boolean.FALSE)
                .age(34)
                .build();


        List<DomainUser> domainUsers = new ArrayList<>();
        domainUsers.add(domainUser1);
        domainUsers.add(domainUser2);
        domainUsers.add(domainUser3);

        String orderBy = "id";

//        FunctionSortBy functionSortBy = new FunctionSortBy();
//        functionSortBy.getMethodOrderBy("name");

        List<DomainUser> collect = domainUsers.stream().sorted(
                (u1, u2) -> extracted(orderBy, u1, u2)
        ).collect(Collectors.toList());

        collect.forEach(System.out::println);


    }

    private static int extracted(String orderBy, DomainUser u1, DomainUser u2) {
        int compareTo = 0;
        try {
            Field field = u1.getClass().getDeclaredField(orderBy);
            String className = field.getType().getSimpleName();
            String getMethodName = "get" + orderBy.substring(0, 1).toUpperCase() + orderBy.substring(1);
            Method method = u1.getClass().getMethod(getMethodName);

            if ("String".equals(className)) {
                compareTo = method.invoke(u1).toString().compareTo(method.invoke(u2).toString());
            }else if ("Long".equals(className)){
                compareTo = Long.compare(Long.parseLong(method.invoke(u1).toString()),Long.parseLong(method.invoke(u2).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compareTo;
    }


//    private Function<String,DomainUser> getMethodOrderBy(String orderBy) throws RuntimeException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
//        Field field = DomainUser.class.getDeclaredField(orderBy);
//
//        String className = field.getType().getSimpleName();
//        String getMethodName = "get" + orderBy.substring(0, 1).toUpperCase() + orderBy.substring(1);
//        Method method = DomainUser.class.getMethod(getMethodName);
//
//        return () -> DomainUser::getMethodName;
////        String toString = method.invoke(DomainUser.class).toString();toString


}
