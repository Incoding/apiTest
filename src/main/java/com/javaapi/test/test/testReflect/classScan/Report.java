package com.javaapi.test.test.testReflect.classScan;


import com.javaapi.test.test.testReflect.classScan.annotation.Define;
import lombok.Data;

/**
 * Created by user on 2018/11/18
 */
@Data
public class Report {

    @Define
    private String date;

    @Define
    private String client;
}
