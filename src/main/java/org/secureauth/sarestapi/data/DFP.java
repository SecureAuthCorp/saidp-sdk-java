package org.secureauth.sarestapi.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author rrowcliffe@secureauth.com
 *
 *
Copyright (c) 2015, SecureAuth
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
**/

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DFP {

    private String fonts;
    private String plugins;
    private String timezone;
    private String video;
    private boolean local_storage;
    private boolean session_storage;
    private boolean ie_user_data;
    private boolean cookie_enabled;
    private String user_agent;
    private String accept;
    private String accept_charset;
    private String accept_encoding;
    private String accept_language;

    public String getFonts() {
        return fonts;
    }

    public void setFonts(String fonts) {
        this.fonts = fonts;
    }

    public String getPlugins() {
        return plugins;
    }

    public void setPlugins(String plugins) {
        this.plugins = plugins;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isLocal_storage() {
        return local_storage;
    }

    public void setLocal_storage(boolean local_storage) {
        this.local_storage = local_storage;
    }

    public boolean isSession_storage() {
        return session_storage;
    }

    public void setSession_storage(boolean session_storage) {
        this.session_storage = session_storage;
    }

    public boolean isIe_user_data() {
        return ie_user_data;
    }

    public void setIe_user_data(boolean ie_user_data) {
        this.ie_user_data = ie_user_data;
    }

    public boolean isCookie_enabled() {
        return cookie_enabled;
    }

    public void setCookie_enabled(boolean cookie_enabled) {
        this.cookie_enabled = cookie_enabled;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAccept_charset() {
        return accept_charset;
    }

    public void setAccept_charset(String accept_charset) {
        this.accept_charset = accept_charset;
    }

    public String getAccept_encoding() {
        return accept_encoding;
    }

    public void setAccept_encoding(String accept_encoding) {
        this.accept_encoding = accept_encoding;
    }

    public String getAccept_language() {
        return accept_language;
    }

    public void setAccept_language(String accept_language) {
        this.accept_language = accept_language;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t").append("Fonts:").append(fonts);
        stringBuilder.append("\n\t").append("Plugins:").append(plugins);
        stringBuilder.append("\n\t").append("TimeZone:").append(timezone);
        stringBuilder.append("\n\t").append("Video:").append(video);
        stringBuilder.append("\n\t").append("Local Storage:").append(local_storage);
        stringBuilder.append("\n\t").append("Session Storage:").append(session_storage);
        stringBuilder.append("\n\t").append("IE User Data:").append(ie_user_data);
        stringBuilder.append("\n\t").append("Cookie Enabled:").append(cookie_enabled);
        stringBuilder.append("\n\t").append("User Agent:").append(user_agent);
        stringBuilder.append("\n\t").append("Accept:").append(accept);
        stringBuilder.append("\n\t").append("Accept Charset:").append(accept_charset);
        stringBuilder.append("\n\t").append("Accept Encoding:").append(accept_encoding);
        stringBuilder.append("\n\t").append("Accept Language:").append(accept_language);
        return stringBuilder.toString();
    }

}
