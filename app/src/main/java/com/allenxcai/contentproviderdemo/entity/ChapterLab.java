package com.allenxcai.contentproviderdemo.entity;

import java.util.ArrayList;
import java.util.List;

public class ChapterLab {
    public static List<Chapter> generateDatas() {

        List<Chapter> chapters = new ArrayList<>();

        Chapter root1 = new Chapter(1, "凉菜");
        Chapter root2 = new Chapter(2, "小吃");
        Chapter root3 = new Chapter(3, "海鲜");
        Chapter root4 = new Chapter(4, "时令");

        root1.addChild(1, "aaaaaa");
        root1.addChild(2, "aaaaaa");
        root1.addChild(4, "aaaaaa");
        root1.addChild(8, "aaaaaa");
        root1.addChild(10, "aaaaaa");

        root2.addChild(11, "bbbbbb");
        root2.addChild(12, "bbbbbb");
        root2.addChild(13, "bbbbbb");
        root2.addChild(14, "bbbbbb");
        root2.addChild(15, "bbbbbb");


        root3.addChild(16, "cccccccccc");
        root3.addChild(17, "cccccccccc");
        root3.addChild(20, "cccccccccc");
        root3.addChild(21, "cccccccccc");
        root3.addChild(22, "cccccccccc");


        root4.addChild(25, "dddddddd");
        root4.addChild(26, "dddddddd");
        root4.addChild(27, "dddddddd");
        root4.addChild(28, "dddddddd");
        root4.addChild(30, "dddddddd");


        chapters.add(root1);
        chapters.add(root2);
        chapters.add(root3);
        chapters.add(root4);

        return chapters;

    }
}
