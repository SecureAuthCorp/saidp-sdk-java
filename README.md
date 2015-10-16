SAAccess
===========

The SAAccess is a solution that allows access to SecureAuth's REST api. The intention
is to provide a easy and a standard method to to access the SecureAuth Appliance REST
service. Developers working with the REST API tend to write their own REST
client using either Jersey libraries or base java IO classes. SAAccess sdk
relies on a core class org.secureauth.sarestapi.SAAccess.

The SDK version 0.9.0. is written to support SecureAuth Appliance 8.1 and newer.

Requirements:
------------
Requires Java 1.6 or Newer
Requires ant to build the package

Building:
--------
1. Fork the Repo to local machine
2. Run ant -f build.xml

Add the saLib folder to your classpath for use with any of your projects

Usage:
-----

In order to use the SecureAuth Rest API you need to generate your Application ID and Application key from the Administration Console.
Each Realm has a unique ApplicationID and ApplicationKey. You can generate your realm's ID and Keys by selecting the realm you want to enable.
Then select Registration Methods and Scroll Down to Authentication API. Click the Generate App/Key button.

```java

String applianceHost = "example.domain.com";
String appliancePort = "443";
boolean applianceSSL = true;
String realm = "secureauth2";
String applicationID = "2c30646d6ec64d6bae351c0728cf594d";
String applicationKey = "6f03fdc29ff4dc17b95067bba0b68009d269193f77663d8a0a63c6a07079d626";

 /*
 This will create the instance of the SAAccess which is able to execute REST calls.
 */
SAAccess saAccess=new SAAccess(applianceHost, appliancePort, applianceSSL, realm, applicationID, applicationKey);


// To validate a user exists in your data store all you need to run the following.

System.out.println(saAccess.validateUser("USERNAME").toString());

ResponseObject validUser = saAccess.validateUser("USERNAME")
if(validUser != null){
            if(validUser.getStatus().equalsIgnoreCase("found")){
            System.out.println("Matched User")
            }
}

```


For support please email: rrowcliffe@secureauth.com