package ru.courses.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp () {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("8.8.8.8");
        assertEquals(ipLocation, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }

    @Test
    public void testInvalidIp () {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("");
        assertEquals(ipLocation, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
    }
}
