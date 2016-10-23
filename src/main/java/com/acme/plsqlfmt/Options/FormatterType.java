package com.acme.plsqlfmt.Options;

public enum FormatterType
{
    SqlCl,
    SqlDev;

    public static FormatterType fromString(String str)
    {
        for (FormatterType fmt : FormatterType.values())
        {
            if (fmt.toString().equalsIgnoreCase(str))
            {
                return fmt;
            }
        }

        return null;
    }
}
