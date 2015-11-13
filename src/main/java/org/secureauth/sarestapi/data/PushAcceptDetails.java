/**

 Copyright (c) 2015 SecureAuth Corporation
 All Rights Reserved

*/
package org.secureauth.sarestapi.data;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name="factoring")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PushAcceptDetails {
	private String company_name, application_description, enduser_ip;

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getApplication_description() {
		return application_description;
	}

	public void setApplication_description(String application_description) {
		this.application_description = application_description;
	}

	public String getEnduser_ip() {
		return enduser_ip;
	}

	public void setEnduser_ip(String enduser_ip) {
		this.enduser_ip = enduser_ip;
	}
	
    @Override
    public String toString() {
    	return "company_name=" + company_name + ", application_description="
    			+ application_description + ", enduser_ip=" + enduser_ip;
    }
}
