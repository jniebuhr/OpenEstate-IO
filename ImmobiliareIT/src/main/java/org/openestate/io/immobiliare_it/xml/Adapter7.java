
package org.openestate.io.immobiliare_it.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter7
    extends XmlAdapter<String, Boolean>
{


    public Boolean unmarshal(String value) {
        return (org.openestate.io.immobiliare_it.ImmobiliareItUtils.parseYN(value));
    }

    public String marshal(Boolean value) {
        return (org.openestate.io.immobiliare_it.ImmobiliareItUtils.printYN(value));
    }

}
