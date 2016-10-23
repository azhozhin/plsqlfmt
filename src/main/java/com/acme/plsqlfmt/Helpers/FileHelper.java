package com.acme.plsqlfmt.Helpers;

import java.io.*;

public class FileHelper
{
    public static String readAllText(String filename)
    {

        try
        {
            File file = new File(filename);
            byte[] data;
            try (FileInputStream fis = new FileInputStream(file))
            {
                data = new byte[(int) file.length()];
                fis.read(data);
            }
            return new String(data, "UTF-8");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void writeAllText(String filename, String text)
    {
        try
        {
            try (PrintWriter out = new PrintWriter(filename))
            {
                out.print(text);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}
