OpenEstate-IO-OpenImmo 1.3
==========================

*OpenEstate-IO-OpenImmo* is a Java library to read and write real estate data in
the *OpenImmo* format (version 1.2.7b down to 1.1), that is used by a variety of
real estate software in Germany, Austria and Switzerland.


Features
--------

-   read XML data according to the specifications of
    *OpenImmo* 1.2.7b down to 1.1
    (see [`OpenImmoReadingExample.java`](https://github.com/OpenEstate/OpenEstate-IO/blob/v1.3/Examples/src/main/java/org/openestate/io/examples/OpenImmoReadingExample.java))
-   write XML data according to the specifications of
    *OpenImmo* 1.2.7b down to 1.1
    (see [`OpenImmoWritingExample.java`](https://github.com/OpenEstate/OpenEstate-IO/blob/v1.3/Examples/src/main/java/org/openestate/io/examples/OpenImmoWritingExample.java))


How to use
----------

Download the [latest release from GitHub](https://github.com/OpenEstate/OpenEstate-IO/releases/latest).
The provided archive contains all required files (compiled libraries,
dependencies, source code and documentations).

Alternatively you can integrate the library from
[Maven Central Repository](http://search.maven.org/#search|ga|1|org.openestate.io)
into your [Maven](http://maven.apache.org/) project. Just add the following
dependency to your projects `pom.xml`:

```xml
<dependency>
  <groupId>org.openestate.io</groupId>
  <artifactId>OpenEstate-IO-OpenImmo</artifactId>
  <version>1.3</version>
</dependency>
```

You can find further informations in the
[project wiki](https://github.com/OpenEstate/OpenEstate-IO/wiki/Usage-OpenImmo).
Some example classes for this format are available in the
[`Examples`](https://github.com/OpenEstate/OpenEstate-IO/tree/v1.3/Examples)
module.


Specifications
--------------

According to the [license of the *OpenImmo* format](TERMS.md) we're not allowed
to publish the specifications. Get in contact with
[*OpenImmo e.V.*](http://openimmo.de/) to obtain the specifications of the
*OpenImmo* format.


### Modifications to the original specification

In order to improve the generated Java classes, we've made the following
modifications to the original `openimmo_127b.xsd` schema:

-   added `<xsd:include schemaLocation="openimmo-feedback_125.xsd"/>`
    before `<xsd:element name="openimmo">`

-   removed `<xsd:choice>` from `<xsd:element name="geo">`
    and move its child elements into the parent `<xsd:sequence>`

-   removed `<xsd:choice>` from `<xsd:element name="kontaktperson">`
    and move its child elements into the parent `<xsd:sequence>`

The following changes were made to the original `openimmo-feedback_125.xsd`
schema:

-   removed `<xsd:element name="user_defined_extend"> ... </xsd:element>`
    because it is also defined in `openimmo_127.xsd`

To generate the Java classes via JAXB for yourself, copy the modified
`openimmo_127b.xsd` and `openimmo-feedback_125.xsd` into `src/main/xsd` and run
`mvn-jaxb-xjc.sh`.


Dependencies
------------

-   Java 7 or newer
-   [commons-codec 1.10](http://commons.apache.org/proper/commons-codec/)
-   [commons-io 2.5](http://commons.apache.org/proper/commons-io/)
-   [commons-lang 3.6](http://commons.apache.org/proper/commons-lang/)
-   [jaxb2-basics-runtime 0.11.1](https://github.com/highsource/jaxb2-basics)
-   [jaxen 1.1.6](http://jaxen.codehaus.org/)
-   [SLF4J 1.7.25](http://www.slf4j.org/)


Changelog
---------

Take a look at
[`CHANGELOG.md`](https://github.com/OpenEstate/OpenEstate-IO/blob/master/CHANGELOG.md)
for the full changelog.


License
-------

This library is licensed under the terms of
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).
Take a look at
[`LICENSE.txt`](https://github.com/OpenEstate/OpenEstate-IO/blob/v1.3/LICENSE.txt)
for the license text.


Further informations
--------------------

-   [*OpenEstate-IO* at GitHub](https://github.com/OpenEstate/OpenEstate-IO)
-   [Releases of *OpenEstate-IO*](https://github.com/OpenEstate/OpenEstate-IO/releases)
-   [Changelog of *OpenEstate-IO*](https://github.com/OpenEstate/OpenEstate-IO/blob/master/CHANGELOG.md)
-   [Javadocs of *OpenEstate-IO*](http://manual.openestate.org/OpenEstate-IO/)
-   [Validator for *OpenImmo*](http://validator.openestate.org/)
-   [*OpenImmo e.V.*](http://www.openimmo.de/)
