package org.secureauth.sarestapi.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by rrowcliffe on 4/21/16.
 */
public class XMLUtil {
    public XMLUtil(){}

    public static String convertObjectToXML(Object object){
        JAXBContext jaxbContext = null;
        Marshaller jaxbMarshaller = null;
        StringWriter stringWriter = new StringWriter();
        try {
            jaxbContext = JAXBContext.newInstance(object.getClass());
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(object, stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String xmlString = stringWriter.toString();

        return xmlString;

    }
}
