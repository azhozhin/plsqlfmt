package com.acme.plsqlfmt.Formatters;

import oracle.dbtools.app.Format;
import oracle.dbtools.raptor.proformatter.ICodingStyleSQLOptions;
import oracle.dbtools.raptor.proformatter.ICoreFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SQLClFormatter implements ICoreFormatter
{
    private final Logger log = LoggerFactory.getLogger(SQLClFormatter.class);

    @Override
    public String format(ICodingStyleSQLOptions codeStyleOptions, String input)
    {
        Map<String, Object> changed = adaptOptions(codeStyleOptions);
        Format format = new Format();
        Format.setOptions(changed);

        String output = null;
        try
        {
            output = format.format(input);
        }
        catch (Exception e)
        {

        }

        return output;
    }

    private Map<String, Object> adaptOptions(ICodingStyleSQLOptions codeStyleOptions)
    {
        Map<String, Object> options = new HashMap<>();
        options.put(Format.singleLineComments, Format.InlineComments.None);

        switch (codeStyleOptions.getUppercase())
        {
            case ICodingStyleSQLOptions.UPPERCASE_KEYWORDS_UPPERCASE:
                options.put(Format.kwCase, Format.Case.UPPER);
                options.put(Format.idCase, Format.Case.None);
                break;
            case ICodingStyleSQLOptions.UPPERCASE_WHOLE_SQL_UPPERCASE:
                options.put(Format.kwCase, Format.Case.UPPER);
                options.put(Format.idCase, Format.Case.UPPER);
                break;
            case ICodingStyleSQLOptions.UPPERCASE_WHOLE_SQL_LOWERCASE:
                options.put(Format.kwCase, Format.Case.lower);
                options.put(Format.idCase, Format.Case.lower);
                break;
            case ICodingStyleSQLOptions.UPPERCASE_NO_CHANGE:
                options.put(Format.kwCase, Format.Case.None);
                options.put(Format.idCase, Format.Case.None);
                break;
            default:
                log.warn("Uppercase=" + codeStyleOptions.getUppercase() + " is not supported");
        }

        options.put(Format.formatThreshold, 1);
        options.put(Format.alignTypeDecl, codeStyleOptions.getAlignDecl());
        options.put(Format.identSpaces, codeStyleOptions.getNumSpaces());
        options.put(Format.useTab, codeStyleOptions.getUseTab());

        if (codeStyleOptions.getBreakBeforeComma() && codeStyleOptions.getBreakAfterComma())
        {
            log.warn("BreakBeforeComma=true and BreakAfterComma=true not supported at the same time");
        }
        else if (codeStyleOptions.getBreakBeforeComma() || codeStyleOptions.getBreakAfterComma())
        {
            options.put(Format.breaksComma, codeStyleOptions.getBreakBeforeComma()
                                            ? Format.Breaks.Before
                                            : Format.Breaks.After);
        }
        else
        {
            options.put(Format.breaksComma, Format.Breaks.None);
        }

        if (codeStyleOptions.getBreakBeforeConcat() && codeStyleOptions.getBreakAfterConcat())
        {
            log.warn("BreakBeforeConcat=true and BreakAfterConcat=true not supported at the same time");
        }
        else if (codeStyleOptions.getBreakBeforeConcat() || codeStyleOptions.getBreakAfterConcat())
        {
            options.put(Format.breaksConcat, codeStyleOptions.getBreakBeforeConcat()
                                             ? Format.Breaks.Before
                                             : Format.Breaks.After);
        }
        else
        {
            options.put(Format.breaksConcat, Format.Breaks.None);
        }
        options.put(Format.commasPerLine, codeStyleOptions.getNumCommas());
        options.put(Format.maxCharLineSize, codeStyleOptions.getLineWidth());
        options.put(Format.forceLinebreaksBeforeComment, codeStyleOptions.getBreakBeforeComment());

        options.put(Format.breaksAroundLogicalConjunctions, Boolean.TRUE);
        options.put(Format.breaksAfterSelectFromWhere, Boolean.TRUE);
        options.put(Format.breakOnCompositeLogicalExpressions, Boolean.TRUE);
        options.put(Format.breakOnSubqueries, Boolean.TRUE);
        options.put(Format.extraLinesAfterSignificantStatements, Boolean.TRUE);

        options.put(Format.breakAnsiiJoin, codeStyleOptions.getBreakJoin());
        options.put(Format.breakAfterCase, codeStyleOptions.getBreakCase());
        options.put(Format.breakAfterWhen, codeStyleOptions.getBreakCaseWhen());
        options.put(Format.breakAfterThen, codeStyleOptions.getBreakCaseThen());
        options.put(Format.breakAfterElse, codeStyleOptions.getBreakCaseElse());

        switch (codeStyleOptions.getOperatorSpacing())
        {
            case ICodingStyleSQLOptions.OPERATORSPACING_NO_SPACES__WHERE_A_B_C:
                options.put(Format.spaceAroundOperators, Boolean.FALSE);
                break;
            case ICodingStyleSQLOptions.OPERATORSPACING_ONE_SPACE__WHERE_A___B___C:
                options.put(Format.spaceAroundOperators, Boolean.TRUE);
                break;
            case ICodingStyleSQLOptions.OPERATORSPACING_KEEP_UNCHANGED:
            default:
                log.warn("OperatorSpacing=" + codeStyleOptions.getOperatorSpacing() + " is not supported");
        }

        switch (codeStyleOptions.getCommaSpacing())
        {
            case ICodingStyleSQLOptions.COMMASPACING_NO_SPACES__SELECT_A_B_C_:
                options.put(Format.spaceAfterCommas, Boolean.FALSE);
                break;
            case ICodingStyleSQLOptions.COMMASPACING_ONE_SPACE_AFTER__SELECT_A__B__C_:
                options.put(Format.spaceAfterCommas, Boolean.TRUE);
                break;
            default:
                log.warn("CommaSpacing=" + codeStyleOptions.getCommaSpacing() + " is not supported");
        }

        switch (codeStyleOptions.getBracketSpacing())
        {
            case ICodingStyleSQLOptions.BRACKETSPACING_NO_SPACES__MAX_C1__:
                options.put(Format.spaceAroundBrackets, Format.Space.NoSpace);
                break;
            case ICodingStyleSQLOptions.BRACKETSPACING_ONE_SPACE_INSIDE__MAX__C1___:
                options.put(Format.spaceAroundBrackets, Format.Space.Inside);
                break;
            case ICodingStyleSQLOptions.BRACKETSPACING_ONE_SPACE_OUTSIDE__MAX__C1___:
                options.put(Format.spaceAroundBrackets, Format.Space.Outside);
                break;
            default:
                log.warn("WARN: BracketSpacing=" + codeStyleOptions.getBracketSpacing() + " is not supported");
        }

        return options;
    }
}
