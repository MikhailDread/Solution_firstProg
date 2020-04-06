package ru.solutionfirstprog.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GeoIpServiceTest {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("188.123.230.11");
        Assert.assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
