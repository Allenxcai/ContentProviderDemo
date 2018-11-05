package com.allenxcai.contentproviderdemo.entity;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private int id;
    private String name;

    public static final String TABLE_NAME = "tb_chapter";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";

    private List<ChapterChild> children = new ArrayList<>();

    public Chapter() {
    }

    public Chapter(int id, String name) {
        this.name = name;
        this.id = id;
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

    public List<ChapterChild> getChildren() {
        return children;
    }

    public void setChildren(List<ChapterChild> children) {
        this.children = children;
    }

    //add children by chateritem
    public void addChild(ChapterChild chapterChild) {

        chapterChild.setPid(getId());
        children.add(chapterChild);
    }

    //add children by id&child name
    public void addChild(int id, String childName) {

        ChapterChild chapterChild = new ChapterChild(id, childName);
        chapterChild.setPid(getId());
        children.add(chapterChild);

    }
}
