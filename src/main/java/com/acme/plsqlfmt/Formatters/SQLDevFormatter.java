package com.acme.plsqlfmt.Formatters;

import SQLinForm_200.SQLForm;
import com.acme.plsqlfmt.Helpers.FormatHelper;
import oracle.dbtools.raptor.proformatter.ICodingStyleSQLOptions;
import oracle.dbtools.raptor.proformatter.ICoreFormatter;

public class SQLDevFormatter implements ICoreFormatter
{
    public SQLDevFormatter()
    {
    }

    public String format(ICodingStyleSQLOptions codeStyleOptions, String input)
    {

        SQLForm sqlForm = new SQLForm();
        setOptions(codeStyleOptions, sqlForm);

        String output = sqlForm.formatSQLAsString(input);
        return output;
    }

    private void setOptions(ICodingStyleSQLOptions codeStyleOptions, SQLForm sqlForm)
    {
        sqlForm.setCase(
                codeStyleOptions.getUppercase() == ICodingStyleSQLOptions.UPPERCASE_KEYWORDS_UPPERCASE,
                codeStyleOptions.getUppercase() == ICodingStyleSQLOptions.UPPERCASE_WHOLE_SQL_UPPERCASE);
        sqlForm.setLowerCase(codeStyleOptions.getUppercase() == ICodingStyleSQLOptions.UPPERCASE_WHOLE_SQL_LOWERCASE);
        sqlForm.setGraphLevel(false);
        sqlForm.setSuppressSpace(true);

        String quoteChar = "\'";
        if (codeStyleOptions.getQuoteChar() == ICodingStyleSQLOptions.QUOTECHAR_DOUBLE_QUOTE)
        {
            quoteChar = "\"";
        }

        sqlForm.setQuoteCharacter(quoteChar);
        sqlForm.setdoubleIndentionMasterKeyword(codeStyleOptions.getDblIndent());
        sqlForm.setAndOrIndention(codeStyleOptions.getIndentAnd());
        int numSpaces = codeStyleOptions.getNumSpaces();
        int numCommas = codeStyleOptions.getNumCommas();
        sqlForm.setIndention(numSpaces, codeStyleOptions.getUseTab());
        sqlForm.setNumCommas(numCommas);
        sqlForm.setLinebreakBeforeLineComment(codeStyleOptions.getBreakBeforeComment());
        sqlForm.setLinebreakBeforeConcat(codeStyleOptions.getBreakBeforeConcat());
        sqlForm.setLinebreakAfterConcat(codeStyleOptions.getBreakAfterConcat());
        sqlForm.setLineBreak(codeStyleOptions.getBreakBeforeComma(),
                codeStyleOptions.getBreakAfterComma(),
                codeStyleOptions.getBreakBeforeAnd(),
                codeStyleOptions.getBreakAfterAnd(),
                codeStyleOptions.getBreakSelectBracket(),
                codeStyleOptions.getBracketSpacingAndOrWhen());

        String formatLanguage = "SQL";
        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_SQL)
        {
            formatLanguage = "SQL";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_ASP_STRINGBUILD)
        {
            formatLanguage = "ASP StringBuilder";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_C__STRINGBUILDER)
        {
            formatLanguage = "C# StringBuilder";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_CONCATENATED_SQL)
        {
            formatLanguage = "Concatenated SQL";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_JAVA_STRINGBUFFER)
        {
            formatLanguage = "Java StringBuffer";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_JAVA_STRING)
        {
            formatLanguage = "Java String";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_JAVA_STRING_2)
        {
            formatLanguage = "Java String 2";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_MSSQL_STRING)
        {
            formatLanguage = "MSSQL String";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_PASCAL_STRING)
        {
            formatLanguage = "Pascal String";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_PHP_STRING)
        {
            formatLanguage = "PHP String";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_VB_STRING__1_)
        {
            formatLanguage = "VB String (1)";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_VB_STRING__2_)
        {
            formatLanguage = "VB String (2)";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_VB_STRING__3_)
        {
            formatLanguage = "VB String (3)";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_VB_STRING__4_)
        {
            formatLanguage = "VB String (4)";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_VB_STRINGBUILDER)
        {
            formatLanguage = "VB StringBuilder";
        }

        if (codeStyleOptions.getTargetSql() == ICodingStyleSQLOptions.TARGETSQL_HTML_CODE)
        {
            formatLanguage = "HTML code";
        }

        sqlForm.setVariableName("SQL");
        sqlForm.setFormatLanguage(formatLanguage);
        sqlForm.setOneLineSQL(codeStyleOptions.getBreakSchema() == ICodingStyleSQLOptions.BREAKSCHEMA_1_LINE_SQL);
        String sqlSourceEncloseChar = "\'";
        if (codeStyleOptions.getSqlSourceEnclosed() == ICodingStyleSQLOptions.SQLSOURCEENCLOSED_DOUBLE_QUOTE)
        {
            sqlSourceEncloseChar = "\"";
        }

        String sqlSourceEscapeChar = "\\";
        if (codeStyleOptions.getSqlSourceEscape() == ICodingStyleSQLOptions.SQLSOURCEESCAPE_DOUBLE_QUOTE)
        {
            sqlSourceEscapeChar = "\"";
        }

        if (codeStyleOptions.getSqlSourceEscape() == ICodingStyleSQLOptions.SQLSOURCEESCAPE_SINGLE_QUOTE)
        {
            sqlSourceEscapeChar = "\'";
        }

        sqlForm.setSourceSQL(codeStyleOptions.getSqlsourceCopied(), sqlSourceEncloseChar, sqlSourceEscapeChar);
        sqlForm.setti("Only for Administrator");
        sqlForm.setSourceSQLLanguage(FormatHelper.dbToFormatter(codeStyleOptions.getSourceSql()));
        int lineWidth = codeStyleOptions.getLineWidth();
        if (lineWidth < 30)
        {
            lineWidth = 30;
        }

        sqlForm.setPageWidth(lineWidth);
        sqlForm.setColor(false);
        sqlForm.setLineNumber(false);
        sqlForm.setLinebreakKeyword(codeStyleOptions.getBreakKeyword());
        sqlForm.setLinebreakCase(codeStyleOptions.getBreakCase());
        sqlForm.setLinebreakCaseThen(codeStyleOptions.getBreakCaseThen());
        sqlForm.setLinebreakCaseWhen(codeStyleOptions.getBreakCaseWhen());
        sqlForm.setLinebreakCaseAndOr(codeStyleOptions.getBreakCaseAndOr());
        sqlForm.setLinebreakCaseElse(codeStyleOptions.getBreakCaseElse());
        sqlForm.setLinebreakJoin(codeStyleOptions.getBreakJoin());
        sqlForm.setLinebreakJoinJoin(false);
        sqlForm.setLinebreakJoinOn(false);
        sqlForm.setAlignmentEqual(codeStyleOptions.getAlignEqual());
        sqlForm.setAlignmentOperator(codeStyleOptions.getAlignOperator());
        sqlForm.setAlignmentAs(codeStyleOptions.getAlignAs());
        sqlForm.setAlignmentComma(codeStyleOptions.getAlignComma());
        sqlForm.setAlignmentComment(codeStyleOptions.getAlignComment());
        sqlForm.setAlignmentConcat(codeStyleOptions.getAlignConcat());
        sqlForm.setAlignmentDeclaration(codeStyleOptions.getAlignDecl());
        sqlForm.setAlignmentKeyword(codeStyleOptions.getAlignKeyword());
        sqlForm.setSuppressComment(codeStyleOptions.getSuppressComment());
        sqlForm.setSuppressLinebreak(false);
        sqlForm.setReplaceComment(codeStyleOptions.getReplaceComment());
        sqlForm.setSuppressEmptyLine(codeStyleOptions.getMoreNewlines(), codeStyleOptions.getPreserveNewlines());
        int bracketSpacing = codeStyleOptions.getBracketSpacing();
        if (bracketSpacing == ICodingStyleSQLOptions.BRACKETSPACING_KEEP_UNCHANGED)
        {
            sqlForm.setBracketSpaces("unchangedSpaceInsideBracket");
        }

        if (bracketSpacing == ICodingStyleSQLOptions.BRACKETSPACING_NO_SPACES__MAX_C1__)
        {
            sqlForm.setBracketSpaces("noSpacesAroundBracket");
        }

        if (bracketSpacing == ICodingStyleSQLOptions.BRACKETSPACING_ONE_SPACE_INSIDE__MAX__C1___)
        {
            sqlForm.setBracketSpaces("oneSpaceInsideBracket");
        }

        if (bracketSpacing == ICodingStyleSQLOptions.BRACKETSPACING_ONE_SPACE_OUTSIDE__MAX__C1___)
        {
            sqlForm.setBracketSpaces("oneSpaceOutsideBracket");
        }
        if (bracketSpacing == ICodingStyleSQLOptions.BRACKETSPACING_ONE_SPACE_AROUND__MAX___C1____)
        {
            sqlForm.setBracketSpaces("oneSpaceAroundBracket");
        }

        int commaSpacing = codeStyleOptions.getCommaSpacing();
        if (commaSpacing == ICodingStyleSQLOptions.COMMASPACING_KEEP_UNCHANGED)
        {
            sqlForm.setCommaSpaces("unchangedSpaceComma");
        }

        if (commaSpacing == ICodingStyleSQLOptions.COMMASPACING_NO_SPACES__SELECT_A_B_C_)
        {
            sqlForm.setCommaSpaces("noSpacesAroundComma");
        }

        if (commaSpacing == ICodingStyleSQLOptions.COMMASPACING_ONE_SPACE_AFTER__SELECT_A__B__C_)
        {
            sqlForm.setCommaSpaces("oneSpaceAfterComma");
        }

        if (commaSpacing == ICodingStyleSQLOptions.COMMASPACING_ONE_SPACE_BEFORE__SELECT_A__B__C__)
        {
            sqlForm.setCommaSpaces("oneSpaceBeforeComma");
        }

        if (commaSpacing == ICodingStyleSQLOptions.COMMASPACING_ONE_SPACE_AROUND__SELECT_A___B___C__)
        {
            sqlForm.setCommaSpaces("oneSpaceAroundComma");
        }

        int operatorSpacing = codeStyleOptions.getOperatorSpacing();
        if (operatorSpacing == ICodingStyleSQLOptions.OPERATORSPACING_KEEP_UNCHANGED)
        {
            sqlForm.setEqualSpaces("unchangedSpaceEqual");
        }

        if (operatorSpacing == ICodingStyleSQLOptions.OPERATORSPACING_NO_SPACES__WHERE_A_B_C)
        {
            sqlForm.setEqualSpaces("noSpacesAroundEqual");
        }

        if (operatorSpacing == ICodingStyleSQLOptions.OPERATORSPACING_ONE_SPACE__WHERE_A___B___C)
        {
            sqlForm.setEqualSpaces("oneSpaceAroundEqual");
        }

        lineWidth = codeStyleOptions.getSmallSql();
        if (lineWidth < 0)
        {
            lineWidth = 80;
        }

        sqlForm.setSmallSQLWidth(lineWidth);
    }
}
