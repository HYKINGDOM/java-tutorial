//package com.java.tutorial.project;
//
//import org.apache.tika.Tika;
//import org.apache.tika.exception.TikaException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@SpringBootTest
//public class SpringBootProjectApplicationTests {
//
//    @Autowired
//    private Tika tika;
//
//    @Test
//    void parseTest() throws TikaException, IOException {
//        Path path = Paths.get("L:\\Grafana配合Prometheus监控搭建.pdf");
//        File file = path.toFile();
//        String parse = tika.parseToString(file);
//        System.out.println(parse);
//    }
//}
