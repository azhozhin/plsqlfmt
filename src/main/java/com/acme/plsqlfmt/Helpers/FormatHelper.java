package com.acme.plsqlfmt.Helpers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class FormatHelper
{
    private static boolean result = true;

    public FormatHelper()
    {
    }

    public static boolean populateBean(String fname, Object cb, String item)
    {
        File f = new File(fname);

        try
        {
            return populateBean(f.toURI().toURL(), cb, item);
        }
        catch (MalformedURLException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public static boolean populateBean(URL u, Object cb, String item)
    {
        result = true;
        Method[] methods = cb.getClass().getDeclaredMethods();
        Map<String, Method> hm = new HashMap<>();

        for (Method x : methods)
        {
            String nl = x.getName();
            if (nl.startsWith("set"))
            {
                hm.put(nl, x);
            }
        }

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        Exception exception = null;

        try
        {
            Exception innerException;
            try
            {
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(u.openStream());
                NodeList itemList = doc.getElementsByTagName(item);

                for (int i = 0; i < itemList.getLength(); ++i)
                {
                    Node n = itemList.item(i);
                    Node nlkeyvalue = null;
                    Node nlvalue = null;
                    NodeList nk = n.getChildNodes();

                    for (int nlc = 0; nlc < nk.getLength(); ++nlc)
                    {
                        Node children = nk.item(nlc);
                        if (children.getNodeName().equals("Key"))
                        {
                            nlkeyvalue = children;
                        }

                        if (children.getNodeName().equals("Value"))
                        {
                            nlvalue = children;
                            break;
                        }
                    }

                    if (nlkeyvalue != null &&
                        nlvalue != null &&
                        nlkeyvalue.getFirstChild() != null &&
                        nlkeyvalue.getFirstChild().getNodeValue() != null &&
                        nlkeyvalue.getFirstChild().getNodeValue().equals("1:SQL"))
                    {
                        NodeList childNodes = nlvalue.getChildNodes();

                        for (int sti = 0; sti < childNodes.getLength(); ++sti)
                        {
                            try
                            {
                                String iae = childNodes.item(sti).getNodeName();
                                String methodName = "set" + iae.substring(0, 1).toUpperCase() + iae.substring(1);
                                Method method;
                                if (!methodName.equals("set#text") && !methodName.equals("setMemberOrderHashStructure"))
                                {
                                    method = hm.get(methodName);
                                    if (method == null)
                                    {
                                        //Logger.info((new com.acme.plsqlfmt.Helpers.FormatHelper()).getClass(), "the method: " + methodName +
                                        // " does not exist");
                                    }
                                    else
                                    {
                                        String value = childNodes.item(sti).getChildNodes().item(0).getNodeValue();
                                        if (iae.equals("name"))
                                        {
                                            method.invoke(cb, value);
                                            hm.remove(methodName);
                                        }
                                        else if (value != null && !value.equals(""))
                                        {
                                            switch (value)
                                            {
                                                case "true":
                                                    method.invoke(cb, Boolean.TRUE);
                                                    hm.remove(methodName);
                                                    break;
                                                case "false":
                                                    method.invoke(cb, Boolean.FALSE);
                                                    hm.remove(methodName);
                                                    break;
                                                default:
                                                    method.invoke(cb, Integer.parseInt(value));
                                                    hm.remove(methodName);
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                            catch (IllegalAccessException | InvocationTargetException ex)
                            {
                                exception = ex;
                            }
                            finally
                            {
                                if (exception != null)
                                {
                                    //Logger.fine((new com.acme.plsqlfmt.Helpers.FormatHelper()).getClass(), (Throwable) exception);
                                    result = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            catch (SAXParseException ex)
            {
                innerException = ex;
                if (ex.getException() != null)
                {
                    innerException = ex.getException();
                }

                exception = innerException;
                result = false;
            }
            catch (SAXException ex)
            {
                innerException = ex;
                if (ex.getException() != null)
                {
                    innerException = ex.getException();
                }

                exception = innerException;
            }
            catch (ParserConfigurationException ex)
            {
                exception = ex;
            }
            catch (IOException ex)
            {
                exception = ex;
            }
        }
        finally
        {
            if (exception != null)
            {
                throw new RuntimeException(exception);
            }

        }

        return result;
    }

    public static final String dbToFormatter(int db) {
        return db == 1?"Oracle":(db == 2?"DB2/UDB":(db == 3?"Sybase":(db == 4?"MSAccess":(db == 5?"SQL Server":(db == 6?"MYSQL":"Any SQL")))));
    }
}
