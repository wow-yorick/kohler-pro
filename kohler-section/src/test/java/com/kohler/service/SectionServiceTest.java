/**
 * 
 */
package com.kohler.service;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/*.xml"})
public class SectionServiceTest {

	/**
	 * @throws java.lang.Exception
	 */
    @Autowired
    public SectionService sectionService;

   
    
	/**
	 * Test method for {@link com.kohler.service.impl.SectionServiceImpl#getSectionWithMap()}.
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testGetSectionWithMap() {

	}

}
