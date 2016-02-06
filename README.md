SAAccess
===========

SAAccess is a solution that allows access to SecureAuth's REST api. The intention
is to provide a easy and a standard method to to access the SecureAuth Appliance REST
service. Developers working with the REST API tend to write their own REST
client using either Jersey libraries or base java IO classes. SAAccess sdk
relies on a core class org.secureauth.sarestapi.SAAccess.

The SDK version 0.9.1. is written to support SecureAuth Appliance 8.2 and newer.

Requirements:
------------
* Requires Java 1.7 or Newer
* Requires gradle to build the package

Dependencies:
------------
Jersey 1.19
Commons Codec 1.10
SLF4J 1.7.13

Building:
--------
1. Fork the Repo to local machine
2. Run gradle distZip

Unzip $PROJECTHOME/build/distributions/saRestApi-sdk-x.x.x.zip
Copy the libs folder from the unzipped package in $PROJECTHOME/build/distributions to your classpath for use with any of your projects
Or add saRestApi-sdk as a dependency in your current project.

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
String applicationID = ".........";
String applicationKey = ".........";

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