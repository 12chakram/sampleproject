package com.eng.gp.project.util.date;

import java.util.*;

public class QuickPerformanceTimer {
    private String name;
    private long start;
    private long last;
    private SortedSet<String> labels = new TreeSet<String>();
    private Map<String,Long> times = new HashMap<String,Long>();

    public QuickPerformanceTimer(String name){
        this.name = name;
        start = System.currentTimeMillis();
        last = start;
    }

    public long elapsed()
    {
        return System.currentTimeMillis() - start;
    }

    public void mark(){
        last = System.currentTimeMillis();
    }
    public void mark(String str){
        long now = System.currentTimeMillis();
        long elapsed = now - last;
        labels.add(str);
        if(null!=times.get(str)){
            elapsed += times.get(str);
        }
        times.put(str,elapsed);
        last = now;
    }
    public String toString(){
        String str = "["+name;
        for(String label : labels){
            str +=" "+label+"="+times.get(label);
        }
        str += " ]";
        return str;
    }
}
