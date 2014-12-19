package org.cwatch.csn;

import java.io.File;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.europa.emsa.csndc.ds.ObjectFactory;
import eu.europa.emsa.csndc.ds.ShipType;


public class TestDs {
	
	URL ds = TestDs.class.getResource("/201405001_14MAY13095556-P2AS-201405001-PAN_DS_19.xml");


	@Test
	public void test() throws Exception {
		// Create JAXB context for WMS 1.3.0
		JAXBContext context = JAXBContext
				.newInstance(
						ObjectFactory.class.getPackage().getName() +":"+
                        net.opengis.gml.v_3_1_1.ObjectFactory.class.getPackage().getName()
				);
		// Use the created JAXB context to construct an unmarshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();
//		unmarshaller.setSchema();
        JAXBElement<ShipType> ship = unmarshaller.unmarshal(new StreamSource(ds.openStream()), ShipType.class);
		Assert.assertEquals("201405001_14MAY13095556-P2AS-201405001-PAN_DS_19", ship.getValue().getId());
	}

    @Ignore
    @Test
    public void validate() throws Exception{
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("src/main/resources/schemas/csndc/1_3_4/csndc_ds.xsd"));
        JAXBContext context = JAXBContext
                .newInstance(
                        ObjectFactory.class.getPackage().getName() + ":" +
                                net.opengis.gml.v_3_1_1.ObjectFactory.class.getPackage().getName()
                );
        // Use the created JAXB context to construct an unmarshaller
        Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(schema);
        JAXBElement<ShipType> ship = unmarshaller.unmarshal(new StreamSource(ds.openStream()), ShipType.class);

        Assert.assertEquals("201405001_14MAY13095556-P2AS-201405001-PAN_DS_19", ship.getValue().getId());

    }
}