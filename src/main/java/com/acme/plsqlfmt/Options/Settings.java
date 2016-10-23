package com.acme.plsqlfmt.Options;

import com.beust.jcommander.Parameter;

public class Settings
{
    @Parameter(names = "-type",
            description = "formatter type")
    private FormatterType formatterType = FormatterType.SqlDev;

    @Parameter(names = "-style",
            description = "code style file from SQL Developer",
            required = true)
    private String codeStyleFilename;

    @Parameter(names = "-input",
            description = "input filename",
            required = true)
    private String inputFilename;

    @Parameter(names = "-output",
            description = "output filename",
            required = true)
    private String outputFilename;

    public FormatterType getFormatterType()
    {
        return formatterType;
    }

    public String getCodeStyleFilename()
    {
        return codeStyleFilename;
    }

    public String getInputFilename()
    {
        return inputFilename;
    }

    public String getOutputFilename()
    {
        return outputFilename;
    }

}

