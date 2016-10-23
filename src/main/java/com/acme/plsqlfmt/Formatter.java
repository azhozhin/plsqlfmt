package com.acme.plsqlfmt;

import com.acme.plsqlfmt.Formatters.SQLClFormatter;
import com.acme.plsqlfmt.Formatters.SQLDevFormatter;
import com.acme.plsqlfmt.Helpers.FileHelper;
import com.acme.plsqlfmt.Helpers.FormatHelper;
import com.acme.plsqlfmt.Options.FormatterType;
import oracle.dbtools.raptor.proformatter.CodingStyleSQLOptionsBean;
import oracle.dbtools.raptor.proformatter.ICodingStyleSQLOptions;
import oracle.dbtools.raptor.proformatter.ICoreFormatter;

public class Formatter
{

    private FormatterType formatterType;
    private String codeStyleFilename;
    private String inputFilename;
    private String outputFilename;

    public Formatter(FormatterType formatterType, String codeStyleFilename, String inputFilename, String outputFilename)
    {
        this.formatterType = formatterType;
        this.codeStyleFilename = codeStyleFilename;
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    public void format()
    {
        ICoreFormatter formatter;
        switch (formatterType)
        {
            case SqlCl:
                formatter = new SQLClFormatter();
                break;
            case SqlDev:
                formatter = new SQLDevFormatter();
                break;
            default:
                throw new RuntimeException("formatterType=" + formatterType + " is not supported");
        }


        ICodingStyleSQLOptions codingStyleOptions = new CodingStyleSQLOptionsBean();
        FormatHelper.populateBean(codeStyleFilename, codingStyleOptions, "Item");

        String input = FileHelper.readAllText(inputFilename);
        String output = formatter.format(codingStyleOptions, input);
        FileHelper.writeAllText(outputFilename, output);
    }
}
