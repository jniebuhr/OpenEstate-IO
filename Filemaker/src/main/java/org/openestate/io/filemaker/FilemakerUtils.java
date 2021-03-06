/*
 * Copyright 2015-2017 OpenEstate.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openestate.io.filemaker;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.openestate.io.core.XmlUtils;
import org.openestate.io.core.XmlValidationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Some helper functions for the XML format of
 * <a href="http://www.filemaker.com/">Filemaker</a>.
 *
 * @since 1.0
 * @author Andreas Rudolph
 */
public class FilemakerUtils
{
  private final static Logger LOGGER = LoggerFactory.getLogger( FilemakerUtils.class );
  private static JAXBContext JAXB = null;

  /**
   * the latest implemented version of this format
   *
   * public final static String VERSION = "1.0";
   */

  /**
   * the XML target namespace of this format
   */
  public final static String NAMESPACE = StringUtils.EMPTY;

  /**
   * the package, where generated JAXB classes are located
   */
  public final static String PACKAGE = "org.openestate.io.filemaker.xml.result"
    + ":org.openestate.io.filemaker.xml.layout";

  /**
   * the factory for creation of JAXB objects in FMPXMLLAYOUT format
   */
  public final static org.openestate.io.filemaker.xml.layout.ObjectFactory FACTORY_LAYOUT = new org.openestate.io.filemaker.xml.layout.ObjectFactory();

  /**
   * the factory for creation of JAXB objects in FMPXMLRESULT format
   */
  public final static org.openestate.io.filemaker.xml.result.ObjectFactory FACTORY_RESULT = new org.openestate.io.filemaker.xml.result.ObjectFactory();

  private FilemakerUtils()
  {
  }

  /**
   * Creates a {@link FilemakerDocument} from an {@link InputStream}.
   *
   * @param input
   * XML input
   *
   * @return
   * created document or null, of the document is not supported by this format
   *
   * @throws SAXException
   * if XML is invalid
   *
   * @throws IOException
   * if reading failed
   *
   * @throws ParserConfigurationException
   * if the parser is not properly configured
   */
  public static FilemakerDocument createDocument( InputStream input ) throws SAXException, IOException, ParserConfigurationException
  {
    return createDocument( XmlUtils.newDocument( input, true ) );
  }

  /**
   * Creates a {@link FilemakerDocument} from a {@link File}.
   *
   * @param xmlFile
   * XML file
   *
   * @return
   * created document or null, of the document is not supported by this format
   *
   * @throws SAXException
   * if XML is invalid
   *
   * @throws IOException
   * if reading failed
   *
   * @throws ParserConfigurationException
   * if the parser is not properly configured
   */
  public static FilemakerDocument createDocument( File xmlFile ) throws SAXException, IOException, ParserConfigurationException
  {
    return createDocument( XmlUtils.newDocument( xmlFile, true ) );
  }

  /**
   * Creates a {@link FilemakerDocument} from a {@link String}.
   *
   * @param xmlString
   * XML string
   *
   * @return
   * created document or null, of the document is not supported by this format
   *
   * @throws SAXException
   * if XML is invalid
   *
   * @throws IOException
   * if reading failed
   *
   * @throws ParserConfigurationException
   * if the parser is not properly configured
   */
  public static FilemakerDocument createDocument( String xmlString ) throws SAXException, IOException, ParserConfigurationException
  {
    return createDocument( XmlUtils.newDocument( xmlString, true ) );
  }

  /**
   * Creates a {@link FilemakerDocument} from a {@link Document}.
   *
   * @param doc
   * XML document
   *
   * @return
   * created document or null, of the document is not supported by this format
   */
  public static FilemakerDocument createDocument( Document doc )
  {
    if (FilemakerResultDocument.isReadable( doc ))
      return new FilemakerResultDocument( doc );
    else if (FilemakerLayoutDocument.isReadable( doc ))
      return new FilemakerLayoutDocument( doc );
    else
      return null;
  }

  /**
   * Creates a {@link Marshaller} to write JAXB objects into XML.
   *
   * @return
   * created marshaller
   *
   * @throws JAXBException
   * if a problem with JAXB occured
   */
  public static Marshaller createMarshaller() throws JAXBException
  {
    return createMarshaller( Charset.defaultCharset().name(), true );
  }

  /**
   * Creates a {@link Marshaller} to write JAXB objects into XML.
   *
   * @param encoding
   * encoding of written XML
   *
   * @param formatted
   * if written XML is pretty printed
   *
   * @return
   * created marshaller
   *
   * @throws JAXBException
   * if a problem with JAXB occured
   */
  public static Marshaller createMarshaller( String encoding, boolean formatted ) throws JAXBException
  {
    Marshaller m = getContext().createMarshaller();
    m.setProperty( Marshaller.JAXB_ENCODING, encoding );
    m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, formatted );
    m.setEventHandler( new XmlValidationHandler() );
    return m;
  }

  /**
   * Creates a {@link Unmarshaller} to read JAXB objects from XML.
   *
   * @return
   * created unmarshaller
   *
   * @throws JAXBException
   * if a problem with JAXB occured
   */
  public static Unmarshaller createUnmarshaller() throws JAXBException
  {
    Unmarshaller m = getContext().createUnmarshaller();
    m.setEventHandler( new XmlValidationHandler() );
    return m;
  }

  /**
   * Returns the {@link JAXBContext} for this format.
   *
   * @return
   * context
   *
   * @throws JAXBException
   * if a problem with JAXB occured
   */
  public synchronized static JAXBContext getContext() throws JAXBException
  {
    if (JAXB==null) initContext( Thread.currentThread().getContextClassLoader() );
    return JAXB;
  }

  /**
   * Returns the {@link org.openestate.io.filemaker.xml.layout.ObjectFactory} for FMPXMLLAYOUT format.
   *
   * @return
   * object factory
   */
  public synchronized static org.openestate.io.filemaker.xml.layout.ObjectFactory getFactoryForLayout()
  {
    return FACTORY_LAYOUT;
  }

  /**
   * Returns the {@link org.openestate.io.filemaker.xml.result.ObjectFactory} for FMPXMLRESULT format.
   *
   * @return
   * object factory
   */
  public synchronized static org.openestate.io.filemaker.xml.result.ObjectFactory getFactoryForResult()
  {
    return FACTORY_RESULT;
  }

  /**
   * Intializes the {@link JAXBContext} for this format.
   *
   * @param classloader
   * the classloader to load the generated JAXB classes with
   *
   * @throws JAXBException
   * if a problem with JAXB occured
   */
  public synchronized static void initContext( ClassLoader classloader ) throws JAXBException
  {
    JAXB = JAXBContext.newInstance( PACKAGE, classloader );
  }

  public static Boolean parseBoolean( String value )
  {
    value = StringUtils.trimToNull( value );
    if ("yes".equalsIgnoreCase( value ))
      return true;
    if ("no".equalsIgnoreCase( value ))
      return false;
    return null;
  }

  public static String printBoolean( Boolean value )
  {
    if (Boolean.TRUE.equals( value ))
      return "YES";
    if (Boolean.FALSE.equals( value ))
      return "NO";
    throw new IllegalArgumentException( "Can't print boolean value!" );
  }
}