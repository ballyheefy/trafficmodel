package com.jonathan.testmodel;

import org.junit.jupiter.api.Test;

import com.jonathan.helper.Network_XML_Helper;

class Network_XML_HelperTest {

	@Test
	void test() {
		assert Network_XML_Helper.getInstance().buildTheModel();
	}

}
