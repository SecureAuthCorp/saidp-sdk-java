package org.secureauth.sarestapi.data;

import com.fasterxml.jackson.annotation.JsonInclude;


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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DFPValidateResponse {

    private String fingerprint_id;
    private String fingerprint_name;
    private double score;
    private double match_score;
    private double update_score;
    private String status;
    private String message;

    public String getFingerprint_id() {
        return fingerprint_id;
    }

    public void setFingerprint_id(String fingerprint_id) {
        this.fingerprint_id = fingerprint_id;
    }

    public String getFingerprint_name() {
        return fingerprint_name;
    }

    public void setFingerprint_name(String fingerprint_name) {
        this.fingerprint_name = fingerprint_name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getMatch_score() {
        return match_score;
    }

    public void setMatch_score(double match_score) {
        this.match_score = match_score;
    }

    public double getUpdate_score() {
        return update_score;
    }

    public void setUpdate_score(double update_score) {
        this.update_score = update_score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t").append("FingerPrint ID:").append(fingerprint_id);
        stringBuilder.append("\n\t").append("FingerPrint Name:").append(fingerprint_name);
        stringBuilder.append("\n\t").append("Score:").append(score);
        stringBuilder.append("\n\t").append("Match Score:").append(match_score);
        stringBuilder.append("\n\t").append("Update Score:").append(update_score);
        stringBuilder.append("\n\t").append("Status:").append(status);
        stringBuilder.append("\n\t").append("Message:").append(message);
        return stringBuilder.toString();
    }
}
