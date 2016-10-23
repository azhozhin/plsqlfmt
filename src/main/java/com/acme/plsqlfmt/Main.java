package com.acme.plsqlfmt;

import com.acme.plsqlfmt.Options.Settings;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception
    {
        log.info("Start");
        Settings s = parseSettings(args);

        Formatter fmt = new Formatter(
                s.getFormatterType(),
                s.getCodeStyleFilename(),
                s.getInputFilename(),
                s.getOutputFilename()
        );

        log.info("Formatting");
        fmt.format();
        log.info("Done");
    }

    private static Settings parseSettings(String[] args)
    {
        Settings settings = new Settings();
        JCommander commander = new JCommander(settings);
        try
        {
            commander.parse(args);
        }
        catch (ParameterException e)
        {
            e.printStackTrace();
            commander.usage();
            System.exit(-1);
        }

        return settings;
    }

}
