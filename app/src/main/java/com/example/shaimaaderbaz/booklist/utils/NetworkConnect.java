package com.example.shaimaaderbaz.booklist.utils;
import java.io.IOException;


/**
 * Created by Shaimaa Derbaz on 2/5/2018.
 */

public class NetworkConnect  {

    public static boolean  isConnected() throws InterruptedException, IOException
    {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec (command).waitFor() == 0);
    }
}
