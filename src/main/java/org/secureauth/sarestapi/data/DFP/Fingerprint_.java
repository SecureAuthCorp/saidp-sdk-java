
package org.secureauth.sarestapi.data.DFP;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "uaBrowser",
    "uaString",
    "uaDevice",
    "uaEngine",
    "uaOS",
    "uaCPU",
    "uaPlatform",
    "language",
    "colorDepth",
    "pixelRatio",
    "screenResolution",
    "availableScreenResolution",
    "timezone",
    "timezoneOffset",
    "localStorage",
    "sessionStorage",
    "indexedDb",
    "addBehavior",
    "openDatabase",
    "cpuClass",
    "platform",
    "doNotTrack",
    "plugins",
    "canvas",
    "webGl",
    "adBlock",
    "userTamperLanguage",
    "userTamperScreenResolution",
    "userTamperOS",
    "userTamperBrowser",
    "touchSupport",
    "cookieSupport",
    "fonts"
})
public class Fingerprint_ {

    @JsonProperty("uaBrowser")
    private UaBrowser uaBrowser;
    @JsonProperty("uaString")
    private String uaString;
    @JsonProperty("uaDevice")
    private UaDevice uaDevice;
    @JsonProperty("uaEngine")
    private UaEngine uaEngine;
    @JsonProperty("uaOS")
    private UaOS uaOS;
    @JsonProperty("uaCPU")
    private UaCPU uaCPU;
    @JsonProperty("uaPlatform")
    private String uaPlatform;
    @JsonProperty("language")
    private String language;
    @JsonProperty("colorDepth")
    private Integer colorDepth;
    @JsonProperty("pixelRatio")
    private Integer pixelRatio;
    @JsonProperty("screenResolution")
    private String screenResolution;
    @JsonProperty("availableScreenResolution")
    private String availableScreenResolution;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("timezoneOffset")
    private Integer timezoneOffset;
    @JsonProperty("localStorage")
    private Boolean localStorage;
    @JsonProperty("sessionStorage")
    private Boolean sessionStorage;
    @JsonProperty("indexedDb")
    private Boolean indexedDb;
    @JsonProperty("addBehavior")
    private Boolean addBehavior;
    @JsonProperty("openDatabase")
    private Boolean openDatabase;
    @JsonProperty("cpuClass")
    private Object cpuClass;
    @JsonProperty("platform")
    private String platform;
    @JsonProperty("doNotTrack")
    private Object doNotTrack;
    @JsonProperty("plugins")
    private String plugins;
    @JsonProperty("canvas")
    private String canvas;
    @JsonProperty("webGl")
    private Object webGl;
    @JsonProperty("adBlock")
    private Boolean adBlock;
    @JsonProperty("userTamperLanguage")
    private Boolean userTamperLanguage;
    @JsonProperty("userTamperScreenResolution")
    private Boolean userTamperScreenResolution;
    @JsonProperty("userTamperOS")
    private Boolean userTamperOS;
    @JsonProperty("userTamperBrowser")
    private Boolean userTamperBrowser;
    @JsonProperty("touchSupport")
    private TouchSupport touchSupport;
    @JsonProperty("cookieSupport")
    private Boolean cookieSupport;
    @JsonProperty("fonts")
    private String fonts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uaBrowser")
    public UaBrowser getUaBrowser() {
        return uaBrowser;
    }

    @JsonProperty("uaBrowser")
    public void setUaBrowser(UaBrowser uaBrowser) {
        this.uaBrowser = uaBrowser;
    }

    @JsonProperty("uaString")
    public String getUaString() {
        return uaString;
    }

    @JsonProperty("uaString")
    public void setUaString(String uaString) {
        this.uaString = uaString;
    }

    @JsonProperty("uaDevice")
    public UaDevice getUaDevice() {
        return uaDevice;
    }

    @JsonProperty("uaDevice")
    public void setUaDevice(UaDevice uaDevice) {
        this.uaDevice = uaDevice;
    }

    @JsonProperty("uaEngine")
    public UaEngine getUaEngine() {
        return uaEngine;
    }

    @JsonProperty("uaEngine")
    public void setUaEngine(UaEngine uaEngine) {
        this.uaEngine = uaEngine;
    }

    @JsonProperty("uaOS")
    public UaOS getUaOS() {
        return uaOS;
    }

    @JsonProperty("uaOS")
    public void setUaOS(UaOS uaOS) {
        this.uaOS = uaOS;
    }

    @JsonProperty("uaCPU")
    public UaCPU getUaCPU() {
        return uaCPU;
    }

    @JsonProperty("uaCPU")
    public void setUaCPU(UaCPU uaCPU) {
        this.uaCPU = uaCPU;
    }

    @JsonProperty("uaPlatform")
    public String getUaPlatform() {
        return uaPlatform;
    }

    @JsonProperty("uaPlatform")
    public void setUaPlatform(String uaPlatform) {
        this.uaPlatform = uaPlatform;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("colorDepth")
    public Integer getColorDepth() {
        return colorDepth;
    }

    @JsonProperty("colorDepth")
    public void setColorDepth(Integer colorDepth) {
        this.colorDepth = colorDepth;
    }

    @JsonProperty("pixelRatio")
    public Integer getPixelRatio() {
        return pixelRatio;
    }

    @JsonProperty("pixelRatio")
    public void setPixelRatio(Integer pixelRatio) {
        this.pixelRatio = pixelRatio;
    }

    @JsonProperty("screenResolution")
    public String getScreenResolution() {
        return screenResolution;
    }

    @JsonProperty("screenResolution")
    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    @JsonProperty("availableScreenResolution")
    public String getAvailableScreenResolution() {
        return availableScreenResolution;
    }

    @JsonProperty("availableScreenResolution")
    public void setAvailableScreenResolution(String availableScreenResolution) {
        this.availableScreenResolution = availableScreenResolution;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("timezoneOffset")
    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    @JsonProperty("timezoneOffset")
    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    @JsonProperty("localStorage")
    public Boolean getLocalStorage() {
        return localStorage;
    }

    @JsonProperty("localStorage")
    public void setLocalStorage(Boolean localStorage) {
        this.localStorage = localStorage;
    }

    @JsonProperty("sessionStorage")
    public Boolean getSessionStorage() {
        return sessionStorage;
    }

    @JsonProperty("sessionStorage")
    public void setSessionStorage(Boolean sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    @JsonProperty("indexedDb")
    public Boolean getIndexedDb() {
        return indexedDb;
    }

    @JsonProperty("indexedDb")
    public void setIndexedDb(Boolean indexedDb) {
        this.indexedDb = indexedDb;
    }

    @JsonProperty("addBehavior")
    public Boolean getAddBehavior() {
        return addBehavior;
    }

    @JsonProperty("addBehavior")
    public void setAddBehavior(Boolean addBehavior) {
        this.addBehavior = addBehavior;
    }

    @JsonProperty("openDatabase")
    public Boolean getOpenDatabase() {
        return openDatabase;
    }

    @JsonProperty("openDatabase")
    public void setOpenDatabase(Boolean openDatabase) {
        this.openDatabase = openDatabase;
    }

    @JsonProperty("cpuClass")
    public Object getCpuClass() {
        return cpuClass;
    }

    @JsonProperty("cpuClass")
    public void setCpuClass(Object cpuClass) {
        this.cpuClass = cpuClass;
    }

    @JsonProperty("platform")
    public String getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @JsonProperty("doNotTrack")
    public Object getDoNotTrack() {
        return doNotTrack;
    }

    @JsonProperty("doNotTrack")
    public void setDoNotTrack(Object doNotTrack) {
        this.doNotTrack = doNotTrack;
    }

    @JsonProperty("plugins")
    public String getPlugins() {
        return plugins;
    }

    @JsonProperty("plugins")
    public void setPlugins(String plugins) {
        this.plugins = plugins;
    }

    @JsonProperty("canvas")
    public String getCanvas() {
        return canvas;
    }

    @JsonProperty("canvas")
    public void setCanvas(String canvas) {
        this.canvas = canvas;
    }

    @JsonProperty("webGl")
    public Object getWebGl() {
        return webGl;
    }

    @JsonProperty("webGl")
    public void setWebGl(Object webGl) {
        this.webGl = webGl;
    }

    @JsonProperty("adBlock")
    public Boolean getAdBlock() {
        return adBlock;
    }

    @JsonProperty("adBlock")
    public void setAdBlock(Boolean adBlock) {
        this.adBlock = adBlock;
    }

    @JsonProperty("userTamperLanguage")
    public Boolean getUserTamperLanguage() {
        return userTamperLanguage;
    }

    @JsonProperty("userTamperLanguage")
    public void setUserTamperLanguage(Boolean userTamperLanguage) {
        this.userTamperLanguage = userTamperLanguage;
    }

    @JsonProperty("userTamperScreenResolution")
    public Boolean getUserTamperScreenResolution() {
        return userTamperScreenResolution;
    }

    @JsonProperty("userTamperScreenResolution")
    public void setUserTamperScreenResolution(Boolean userTamperScreenResolution) {
        this.userTamperScreenResolution = userTamperScreenResolution;
    }

    @JsonProperty("userTamperOS")
    public Boolean getUserTamperOS() {
        return userTamperOS;
    }

    @JsonProperty("userTamperOS")
    public void setUserTamperOS(Boolean userTamperOS) {
        this.userTamperOS = userTamperOS;
    }

    @JsonProperty("userTamperBrowser")
    public Boolean getUserTamperBrowser() {
        return userTamperBrowser;
    }

    @JsonProperty("userTamperBrowser")
    public void setUserTamperBrowser(Boolean userTamperBrowser) {
        this.userTamperBrowser = userTamperBrowser;
    }

    @JsonProperty("touchSupport")
    public TouchSupport getTouchSupport() {
        return touchSupport;
    }

    @JsonProperty("touchSupport")
    public void setTouchSupport(TouchSupport touchSupport) {
        this.touchSupport = touchSupport;
    }

    @JsonProperty("cookieSupport")
    public Boolean getCookieSupport() {
        return cookieSupport;
    }

    @JsonProperty("cookieSupport")
    public void setCookieSupport(Boolean cookieSupport) {
        this.cookieSupport = cookieSupport;
    }

    @JsonProperty("fonts")
    public String getFonts() {
        return fonts;
    }

    @JsonProperty("fonts")
    public void setFonts(String fonts) {
        this.fonts = fonts;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
