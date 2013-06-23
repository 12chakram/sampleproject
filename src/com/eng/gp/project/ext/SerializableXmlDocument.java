package com.gridpoint.energy.datamodel.ext;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import static com.gridpoint.energy.util.Charsets.UTF_8;

/**
 * A wrapper to make {@link org.w3c.dom.Document} serializable. This is useful for modeling XML properties
 * that will needed to be mechanically remoted.
 *
 * The cost of document stringification will not be incurred until actually necessary.
 *
 * @author dhorlick
 */
public class SerializableXmlDocument implements Serializable, Cloneable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2617329723108572468L;
	private Document document;
    private String stringified;

    public SerializableXmlDocument()
    {
    }

    public SerializableXmlDocument(final Document designatedDocument)
    {
        setDocument(designatedDocument);
    }

    public Document getDocument() // TODO wrap for immutability
    {
        return document;
    }

    public void setDocument(final Document designatedDocument)
    {
        if (document!=designatedDocument)
        {
            document = designatedDocument;
            stringified = null;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException
    {
        objectOutputStream.writeObject(stringify());
        objectOutputStream.close();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException
    {
        stringified = (String) objectInputStream.readObject();
        objectInputStream.close();

        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);

        try
        {
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documentBuilder.newDocument();

            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(stringified.getBytes(UTF_8));
            document = documentBuilder.parse(byteArrayInputStream);
        }
        catch (SAXException e)
        {
            throw new IOException(e);
        }
        catch (ParserConfigurationException e)
        {
            throw new IllegalStateException(e); // mis-configuration
        }
    }


    public String stringify()
    {
        if (document==null)
            return null;

        if (stringified==null)
        {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final StreamResult streamResult = new StreamResult(byteArrayOutputStream);

            try
            {
                final TransformerFactory transformerFactory = TransformerFactory.newInstance();
                final Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
                transformer.transform(new DOMSource(document), streamResult);

                stringified = byteArrayOutputStream.toString(UTF_8.name());
            }
            catch (TransformerConfigurationException e)
            {
                throw new IllegalStateException(e); // mis-configuration
            }
            catch (TransformerException e)
            {
                throw new IllegalStateException(e); // mis-configuration
            }
            catch (UnsupportedEncodingException e)
            {
                throw new IllegalStateException(e); // mis-configuration
            }
        }

        return stringified;
    }

    public static SerializableXmlDocument build(final String stringified) throws SAXException
    {
        final SerializableXmlDocument serializableXmlDocument = new SerializableXmlDocument();

        try
        {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);

            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documentBuilder.newDocument();
			
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(stringified.getBytes(UTF_8));
            serializableXmlDocument.document = documentBuilder.parse(byteArrayInputStream);
            serializableXmlDocument.stringified = stringified;

            return serializableXmlDocument;
        }
        catch (ParserConfigurationException e)
        {
            throw new IllegalStateException(e); // mis-configuration
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalStateException(e); // mis-configuration
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e); // mis-configuration
        }
    }

    /**
     * @return stringified XML, or an excerpt thereof if it's enormous.
     */
    public String summarize()
    {
        return summarize(200);
    }

    public String summarize(final int maxLength)
    {
        final String strung = stringify();
        if (strung.length() <= maxLength)
        {
            return strung;
        }
        else
        {
            final StringBuilder stringBuilder = new StringBuilder();
            final int fragmentLength = (maxLength / 2) - 2;
            stringBuilder.append(strung.substring(0, fragmentLength));
            stringBuilder.append("...");
            stringBuilder.append(strung.substring(strung.length()-fragmentLength, strung.length()));
            return stringBuilder.toString();
        }
    }

    @Override
    public String toString()
    {
        return stringify();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other==null || !(other instanceof SerializableXmlDocument))
            return false;

        final SerializableXmlDocument otherDoc = (SerializableXmlDocument) other;

        final EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(stringify(), otherDoc.stringify());

        return equalsBuilder.isEquals();
    }

    @Override
    public int hashCode()
    {
        final HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 3);
        hashCodeBuilder.append(stringify());
        return hashCodeBuilder.toHashCode();
    }

    @Override
    public Object clone()
    {
        try
        {
            return build(stringify());
        }
        catch (SAXException e)
        {
            throw new IllegalStateException(e); // bug
        }
    }

    public Element findElementByXpath(final String xpath)
    {
        if (document==null || xpath==null)
            return null;

        final XPath xPathCompiler = XPathFactory.newInstance().newXPath();

        try
        {
            final Node result = (Node) xPathCompiler.compile(xpath).evaluate(document, XPathConstants.NODE);
            if (result==null || result.getNodeType()!=Node.ELEMENT_NODE)
                return null;
            return (Element) result;
        }
        catch (XPathExpressionException e)
        {
            throw new IllegalStateException(e); // Client provided bad xpath. Probably indicates a bug.
        }
    }
}
