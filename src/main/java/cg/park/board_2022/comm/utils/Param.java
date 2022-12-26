package cg.park.board_2022.comm.utils;

import java.util.HashMap;

public class Param extends HashMap {

    public Param () {}
    public Param (String key, String value) {
        super.put(key, value);
    }

    public Param set(String key, String value) {
        super.put(key, value);
        return this;
    }
    public Param set(String key, int value) {
        super.put(key, value);
        return this;
    }
    public Param set(int key, String value) {
        super.put(key, value);
        return this;
    }
    public Param set(int key, int value) {
        super.put(key, value);
        return this;
    }
    public Param set(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String code() {
        return (String) super.get("code");
    }

}
