package com.allenxcai.contentproviderdemo.entity;

public class ChapterChild {
    private int id;
    private String name;
    private int pid;

    public static final String TABLE_NAME = "tb_chapter_item";
    public static final String COL_ID = "_id";
    public static final String COL_PID = "pid";
    public static final String COL_NAME = "name";

    public ChapterChild() {
    }

    public ChapterChild(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
