package com.eng.gp.project.util.date;

import java.util.Random;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class PasswordHelper {

    private static final PasswordEncoder myEncoder = new ShaPasswordEncoder( 256 );
    private static final int passwordLength = 10;
    private static final String KEYSPACE = "346789ABCEFGHJKLMNPQRTWXYacdefghjkmnpqrtwxy!@#$%&";

    public static String getNewPassword() {

        char[] keySpace = KEYSPACE.toCharArray();
        char[] newPassword = new char[passwordLength];
        Random generator = new Random();

        for ( int n = 0 ; n < passwordLength ; n++ )
        {
            newPassword[n] = keySpace[generator.nextInt(keySpace.length)];
        }

        return new String(newPassword, 0, passwordLength);

    }

    public static String encodePassword(String userName, String password) {
        return myEncoder.encodePassword(password, userName);
    }

    public static void main(String args[]){
        System.out.println(PasswordHelper.encodePassword("gpadmin", "password"));
    }
}
