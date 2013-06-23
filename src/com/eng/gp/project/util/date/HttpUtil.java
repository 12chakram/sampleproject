package com.eng.gp.project.util.date;

public class HttpUtil {
	
	public static boolean isLocal(String address) {
        return address.equals("0:0:0:0:0:0:0:1") || address.equals("127.0.0.1");
    }

}
