package org.secureauth.sarestapi.data.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.secureauth.sarestapi.data.SystemInfo.SysInfo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemInfoResponse extends BaseResponse{

	private SysInfo sysInfo;

	public SysInfo getSysInfo() {
		return sysInfo;
	}

	public void setSysInfo(SysInfo sysInfo) {
		this.sysInfo = sysInfo;
	}
}
