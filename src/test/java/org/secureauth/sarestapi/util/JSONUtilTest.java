package org.secureauth.sarestapi.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.secureauth.sarestapi.data.DFP.DFP;

import static org.junit.jupiter.api.Assertions.*;

public class JSONUtilTest {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void getDFPFromJSONString() {

		String fingerprintJSON = "{\n" +
				"        \"fingerprint\" : {\"uaString\" : \"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0\",\n" +
				"        \"uaBrowser\" : {\n" +
				"            \"name\" : \"Firefox\",\n" +
				"            \"version\" : \"52.0\",\n" +
				"            \"major\" : \"52\"\n" +
				"        },\n" +
				"        \"uaDevice\" : {\n" +
				"            \"model\" : \"testmodel\",\n" +
				"            \"type\" : \"testtype\",\n" +
				"            \"vendor\" : \"testvendor\"\n" +
				"        },\n" +
				"        \"uaEngine\" : {\n" +
				"            \"name\" : \"Gecko\",\n" +
				"            \"version\" : \"52.0\"\n" +
				"        },\n" +
				"        \"uaOS\" : {\n" +
				"            \"name\" : \"Windows\",\n" +
				"            \"version\" : \"8.1\"\n" +
				"        },\n" +
				"        \"uaCPU\" : {\n" +
				"            \"architecture\" : \"amd64\"\n" +
				"        },\n" +
				"        \"uaPlatform\" : \"Win32\",\n" +
				"        \"language\" : \"en-US\",\n" +
				"        \"colorDepth\" : 24,\n" +
				"        \"pixelRatio\" : 1.0,\n" +
				"        \"screenResolution\" : \"2560x1440\",\n" +
				"        \"availableScreenResolution\" : \"2560x1400\",\n" +
				"        \"timezone\" : \"America/Los_Angeles\",\n" +
				"        \"timezoneOffset\" : 420,\n" +
				"        \"localStorage\" : true,\n" +
				"        \"sessionStorage\" : true,\n" +
				"        \"indexedDb\" : true,\n" +
				"        \"addBehavior\" : false,\n" +
				"        \"openDatabase\" : false,\n" +
				"        \"cpuClass\" : null,\n" +
				"        \"platform\" : \"Win32\",\n" +
				"        \"doNotTrack\" : \"unspecified\",\n" +
				"        \"plugins\" : \"\",\n" +
				"        \"canvas\" : \"812446969\",\n" +
				"        \"webGl\" : \"-1928114666\",\n" +
				"        \"adBlock\" : false,\n" +
				"        \"userTamperLanguage\" : false,\n" +
				"        \"userTamperScreenResolution\" : false,\n" +
				"        \"userTamperOS\" : false,\n" +
				"        \"userTamperBrowser\" : false,\n" +
				"        \"touchSupport\" : {\n" +
				"            \"maxTouchPoints\" : 0,\n" +
				"            \"touchEvent\" : false,\n" +
				"            \"touchStart\" : false\n" +
				"        },\n" +
				"        \"cookieSupport\" : true,\n" +
				"        \"fonts\" : \"Aharoni,Andalus,Angsana New,AngsanaUPC,Aparajita,Arabic Typesetting,Arial,Batang,BatangChe,Bauhaus 93,Bodoni 72,Bodoni 72 Oldstyle,Bodoni 72 Smallcaps,Bookshelf Symbol 7,Browallia New,BrowalliaUPC,Calibri,Cambria,Cambria Math,Candara,Comic Sans MS,Consolas,Constantia,Corbel,Cordia New,CordiaUPC,DaunPenh,David,DFKai-SB,DilleniaUPC,DokChampa,Dotum,DotumChe,Ebrima,English 111 Vivace BT,Estrangelo Edessa,EucrosiaUPC,Euphemia,FangSong,Franklin Gothic,FrankRuehl,FreesiaUPC,Gabriola,Gautami,Georgia,GeoSlab 703 Lt BT,GeoSlab 703 XBd BT,Gisha,Gulim,GulimChe,Gungsuh,GungsuhChe,Helvetica,Humanst 521 Cn BT,Impact,IrisUPC,Iskoola Pota,JasmineUPC,KaiTi,Kalinga,Kartika,Khmer UI,KodchiangUPC,Kokila,Lao UI,Latha,Leelawadee,Levenim MT,LilyUPC,Lucida Console,Lucida Sans Unicode,Malgun Gothic,Mangal,Marlett,Meiryo,Meiryo UI,Microsoft Himalaya,Microsoft JhengHei,Microsoft New Tai Lue,Microsoft PhagsPa,Microsoft Sans Serif,Microsoft Tai Le,Microsoft Uighur,Microsoft YaHei,Microsoft Yi Baiti,MingLiU,MingLiU_HKSCS,MingLiU_HKSCS-ExtB,MingLiU-ExtB,Miriam,Miriam Fixed,Modern No. 20,Mongolian Baiti,MoolBoran,MS Gothic,MS Mincho,MS PGothic,MS PMincho,MS Sans Serif,MS Serif,MS UI Gothic,MV Boli,Narkisim,NSimSun,Nyala,Palatino Linotype,Plantagenet Cherokee,PMingLiU,PMingLiU-ExtB,Raavi,Rod,Roman,Sakkal Majalla,Segoe Print,Segoe Script,Segoe UI,Segoe UI Symbol,Shonar Bangla,Shruti,SimHei,Simplified Arabic,Simplified Arabic Fixed,SimSun,SimSun-ExtB,Small Fonts,Sylfaen,Tahoma,Times,Times New Roman,Traditional Arabic,Trebuchet MS,Tunga,Univers CE 55 Medium,Utsaah,Vani,Verdana,Vijaya,Vrinda,Wingdings,Wingdings 2,Wingdings 3\",\n" +
				"        \"id\" : \"a31332450f284e9bbb1572e7c1c4927a\",\n" +
				"        \"userId\" : \"atest\",\n" +
				"        \"displayName\" : \"Windows - 8.1 - Firefox\",\n" +
				"        \"httpHeaders\" : {\n" +
				"            \"Accept\" : \"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\",\n" +
				"            \"AcceptCharSet\" : \"\",\n" +
				"            \"AcceptEncoding\" : \"gzip, deflate, br\",\n" +
				"            \"AcceptLanguage\" : \"en-US,en;q=0.5\"\n" +
				"        },\n" +
				"        \"hostAddress\" : \"172.16.17.171\",\n" +
				"        \"mobileDeviceId\" : \"\",\n" +
				"        \"mobileDeviceName\" : \"\",\n" +
				"        \"mobileDeviceComment\" : \"\",\n" +
				"        \"lastAccess\" : \"2017-05-08T20:28:18.4333144+00:00\",\n" +
				"        \"createdOn\" : \"2017-05-08T20:28:18.4333144+00:00\"},\n" +
				"        \"accept\" : \"asdasdf\",\n" +
				"        \"acceptCharset\" : \"bbbbbb\",\n" +
				"        \"acceptEncoding\" : \"cccc\",\n" +
				"        \"acceptLanguage\" : \"eeee\",\n" +
				"        \"deviceId\" : \"aaa\",\n" +
				"        \"deviceName\" : \"asdf\",\n" +
				"        \"deviceComment\" : \"\"\n" +
				"    }";

		DFP dfp = JSONUtil.getDFPFromJSONString(fingerprintJSON);
		assertEquals("Windows - 8.1 - Firefox", dfp.getFingerprint().getAdditionalProperties().get("displayName"));
		assertEquals("asdf", dfp.getAdditionalProperties().get("deviceName"));
	}
}