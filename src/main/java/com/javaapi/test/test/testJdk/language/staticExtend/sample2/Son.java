package com.javaapi.test.test.testJdk.language.staticExtend.sample2;

class Son extends Parent {
    public static void getName() {
        name = "son";
        System.out.println(name);
    }

    public void getDesc() {
        desc = "son's Desc";
        System.out.println(desc);
    }
}