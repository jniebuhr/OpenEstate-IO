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
package org.openestate.io.examples;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openestate.io.wis_it.WisItDocument;
import org.openestate.io.wis_it.WisItUtils;
import org.openestate.io.wis_it.xml.ObjectType;
import org.openestate.io.wis_it.xml.WIS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * Example for reading XML files for
 * <a href="http://wohnen-in-suedtirol.it">wohnen-in-suedtirol.it</a>.
 * <p>
 * This example illustrates how to read XML files for
 * <a href="wohnen-in-suedtirol.it">wohnen-in-suedtirol.it</a>.
 *
 * @since 1.0
 * @author Andreas Rudolph
 */
public class WisItReadingExample
{
  private final static Logger LOGGER = LoggerFactory.getLogger( WisItReadingExample.class );
  private final static String PACKAGE = "/org/openestate/io/examples";

  /**
   * Start the example application.
   *
   * @param args
   * command line arguments
   */
  public static void main( String[] args )
  {
    // init logging
    PropertyConfigurator.configure(
      WisItReadingExample.class.getResource( PACKAGE + "/log4j.properties" ) );

    // read example files, if no files were specified as command line arguments
    if (args.length<1)
    {
      try
      {
        read( WisItReadingExample.class.getResourceAsStream( PACKAGE + "/wis_it.xml" ) );
      }
      catch (Exception ex)
      {
        LOGGER.error( "Can't read example file!" );
        LOGGER.error( "> " + ex.getLocalizedMessage(), ex );
        System.exit( 2 );
      }
    }

    // read files, that were specified as command line arguments
    else
    {
      for (String arg : args)
      {
        try
        {
          read( new File( arg ) );
        }
        catch (Exception ex)
        {
          LOGGER.error( "Can't read file '" + arg + "'!" );
          LOGGER.error( "> " + ex.getLocalizedMessage(), ex );
          System.exit( 2 );
        }
      }
    }
  }

  /**
   * Read a {@link File} into a {@link WisItDocument} and print some of its
   * content to console.
   *
   * @param xmlFile
   * the file to read
   *
   * @throws SAXException
   * if the file is not readable by the XML parser
   *
   * @throws IOException
   * if the file is not readable
   *
   * @throws ParserConfigurationException
   * if the XML parser is improperly configured
   *
   * @throws JAXBException
   * if XML conversion into Java objects failed
   */
  protected static void read( File xmlFile ) throws SAXException, IOException, ParserConfigurationException, JAXBException
  {
    LOGGER.info( "process file: " + xmlFile.getAbsolutePath() );
    if (!xmlFile.isFile())
    {
      LOGGER.warn( "> provided file is invalid" );
      return;
    }
    WisItDocument doc = WisItUtils.createDocument( xmlFile );
    if (doc==null)
    {
      LOGGER.warn( "> provided XML is not supported" );
    }
    else
    {
      printToConsole( doc );
    }
  }

  /**
   * Read a {@link InputStream} into a {@link WisItDocument} and print some of
   * its content to console.
   *
   * @param xmlInputStream
   * the input stream to read
   *
   * @throws SAXException
   * if the file is not readable by the XML parser
   *
   * @throws IOException
   * if the file is not readable
   *
   * @throws ParserConfigurationException
   * if the XML parser is improperly configured
   *
   * @throws JAXBException
   * if XML conversion into Java objects failed
   */
  protected static void read( InputStream xmlInputStream ) throws SAXException, IOException, ParserConfigurationException, JAXBException
  {
    LOGGER.info( "process example file" );
    WisItDocument doc = WisItUtils.createDocument( xmlInputStream );
    if (doc==null)
    {
      LOGGER.warn( "> provided XML is not supported" );
    }
    else
    {
      printToConsole( doc );
    }
  }

  /**
   * Print some content of a {@link WisItDocument} to console.
   *
   * @param doc
   * the document to process
   *
   * @throws JAXBException
   * if XML conversion into Java objects failed
   */
  protected static void printToConsole( WisItDocument doc ) throws JAXBException
  {
    WIS wis = doc.toObject();

    // process objects
    if (wis.getOBJEKTE()!=null)
    {
      for (ObjectType obj : wis.getOBJEKTE().getOBJEKT())
      {
        // get object nr
        String objectNr = StringUtils.trimToNull( obj.getID() );
        if (objectNr==null) objectNr = "???";

        // get object description
        String objectInfo = StringUtils.trimToNull( obj.getINFODE() );
        if (objectInfo==null) objectInfo = obj.getINFOIT();
        if (objectInfo==null) objectInfo = "???";

        // print object informations to console
        LOGGER.info( "> found object '" + objectNr + "' "
          + "with title '" + objectInfo + "'" );
      }
    }
  }
}