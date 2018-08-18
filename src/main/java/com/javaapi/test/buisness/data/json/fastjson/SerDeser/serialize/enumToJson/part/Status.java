package com.javaapi.test.buisness.data.json.fastjson.SerDeser.serialize.enumToJson.part;

public enum Status {

    Ready(10),

    Completed(20);

    private int value;

    private Status(int value) {
        this.value = value;
    }

    public static Status create(int value) {
        switch (value) {
            case 10:
                return Status.Ready;
            case 20:
                return Status.Completed;
            default:
                throw new RuntimeException("code error");
        }
    }

    public static int value(Status status){
        return status.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}