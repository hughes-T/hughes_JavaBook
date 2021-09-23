package com.hughes.design.pattren.composite.general.safety;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹
 * @author hughes-T
 * @since 2021/9/23 10:25
 */
public class Folder extends Directory {

    private final List<Directory> dirs = new ArrayList<>();
    private final Integer level;

    public Folder(String name, int level) {
        super(name);
        this.level = level;
    }

    @Override
    public void show() {
        System.out.println(name);
        if (level == null) {
            return;
        }
        for (Directory dir : dirs) {
            for (int i = 0; i < level; i++) {
                dir.show();
            }
        }
    }

    public void addDir(Directory dir){
        dirs.add(dir);
    }

    public void removeDir(Directory dir){
        dirs.remove(dir);
    }

    public Directory getDir(int index){
        return dirs.get(index);
    }
}
