/*
 * Copyright 2015 OpenEstate.org.
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
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.openestate.io.trovit.TrovitDocument;
import org.openestate.io.trovit.TrovitUtils;
import org.openestate.io.trovit.xml.Ad;
import org.openestate.io.trovit.xml.Trovit;
import org.xml.sax.SAXException;

/**
 * Example for XML reading.
 * <p>
 * This example illustrates how to read an Trovit document from a file.
 *
 * @author Andreas Rudolph
 */
public class TrovitReadingExample
{
  /**
   * Start the example application.
   *
   * @param args
   * command line arguments
   */
  public static void main( String[] args )
  {
    if (args.length<1)
    {
      System.out.println( "Please provide at least one Trovit file as argument!" );
      System.exit( 1 );
    }
    for (String arg : args)
    {
      try
      {
        read( new File( arg ) );
      }
      catch (Exception ex)
      {
        System.err.println( "The provided file is invalid!" );
        ex.printStackTrace( System.err );
        System.exit( 2 );
      }
    }
  }

  /**
   * Read an Trovit file into a {@link TransferDocument} and prints some of
   * their content to console.
   *
   * @param xmlFile
   * the XML file to read
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
    System.out.println( "process file: " + xmlFile.getAbsolutePath() );
    if (!xmlFile.isFile())
    {
      System.out.println( "> The provided file is invalid!" );
      return;
    }
    TrovitDocument doc = TrovitUtils.createDocument( xmlFile );
    if (doc==null)
    {
      System.out.println( "> provided XML is not supported" );
    }
    else
    {
      System.out.println( "> is transfer XML" );
      read( doc );
    }
  }

  /**
   * Print the content of a {@link TrovitDocument} to console.
   *
   * @param doc
   * the document to process
   *
   * @throws JAXBException
   * if XML conversion into Java objects failed
   */
  protected static void read( TrovitDocument doc ) throws JAXBException
  {
    Trovit trovit = doc.toObject();

    // process ads
    for (Ad ad : trovit.getAd())
    {
      // get object nr
      String objectNr = StringUtils.trimToNull( ad.getId() );
      if (objectNr==null) objectNr = "???";

      // get object title
      String objectTitle = StringUtils.trimToNull( ad.getTitle() );
      if (objectTitle==null) objectTitle = "???";

      // print object informations to console
      System.out.println( ">> property '" + objectNr + "' "
        + "with title '" + objectTitle + "'" );
    }
  }
}