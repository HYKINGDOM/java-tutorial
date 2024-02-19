package com.java.vavr.list;

import java.util.List;

public class ListFunctional {

    public void ofList() {
        List<Integer> integers = List.of(1, 2, 3, 4);

        fj.data.List<List<Integer>> list = fj.data.List.list(integers);

    }
}
