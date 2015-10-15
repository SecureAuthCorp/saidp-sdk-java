package org.secureauth.sarestapi.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rrowcliffe@secureauth.com
 *
 * <p>
 * Copyright 2015 SecureAuth Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */
@XmlRootElement(name="geoloc")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Geoloc {

    private String country;
    private String country_code;
    private String region;
    private String region_code;
    private String city;
    private String latitude;
    private String longtitude;
    private String internet_service_provider;
    private String organization;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getInternet_service_provider() {
        return internet_service_provider;
    }

    public void setInternet_service_provider(String internet_service_provider) {
        this.internet_service_provider = internet_service_provider;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t\t\t").append("Country:").append(country);
        stringBuilder.append("\n\t\t\t").append("Country Code:").append(country_code);
        stringBuilder.append("\n\t\t\t").append("Region:").append(region);
        stringBuilder.append("\n\t\t\t").append("Region Code:").append(region_code);
        stringBuilder.append("\n\t\t\t").append("City:").append(city);
        stringBuilder.append("\n\t\t\t").append("Latitude:").append(latitude);
        stringBuilder.append("\n\t\t\t").append("Longtitude:").append(longtitude);
        stringBuilder.append("\n\t\t\t").append("Internet Service Provider:").append(internet_service_provider);
        stringBuilder.append("\n\t\t\t").append("Organization:").append(organization);
        return stringBuilder.toString();
    }
}
