package org.cwatch.csn;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import eu.europa.emsa.csndc.pkg.DataPackageType;
import eu.europa.emsa.csndc.pkg.ObjectFactory;
import org.junit.Assert;
import org.junit.Test;



public class TestPkg {
	
	URL ds = TestPkg.class.getResource("/201405001_14MAY13095556-P2AS-201405001-PAN_PCK.xml");


	@Test
	public void test() throws Exception {
		// Create JAXB context for WMS 1.3.0
		JAXBContext context = JAXBContext
				.newInstance(
						ObjectFactory.class.getPackage().getName()
				);
		// Use the created JAXB context to construct an unmarshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();
//		unmarshaller.setSchema();
        JAXBElement<DataPackageType> root = unmarshaller.unmarshal(new StreamSource(ds.openStream()), DataPackageType.class);
		Assert.assertEquals("201405001_14MAY13095556-P2AS-201405001-PAN_DER", root.getValue().getPackageInfo().getPackageId());
	}
}