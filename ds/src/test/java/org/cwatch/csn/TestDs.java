package org.cwatch.csn;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
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
}