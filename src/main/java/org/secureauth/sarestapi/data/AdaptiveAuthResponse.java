package org.secureauth.sarestapi.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdaptiveAuthResponse extends ResponseObject {

    private String realm_workflow, suggested_action, redirect_url;

	public String getRealm_workflow() {
		return realm_workflow;
	}

	public void setRealm_workflow(String realm_workflow) {
		this.realm_workflow = realm_workflow;
	}

	public String getSuggested_action() {
		return suggested_action;
	}

	public void setSuggested_action(String suggested_action) {
		this.suggested_action = suggested_action;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
}
