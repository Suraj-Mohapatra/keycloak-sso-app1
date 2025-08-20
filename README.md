# keycloak-sso-app1
A Java EE application running on tomcat 9.  

using keycloak v24.0.5 as it supports java specific adapters which are deprecated now. adpters download link is also avaiable till this version.  

v24.0.5 ensures correct integration, although you may upgrade its version or its adapters' versions (see maven repository) to work with Jakarta specifications.  

this implementation uses tomcat adapters to secure the application.  
see the web.xml, context.xml carefully.

start keycloak in dev mode as below,

```
kc.bat start-dev --http-port=9000  
or  
kc.bat start-dev --https-port=9443
```

visit https://www.keycloak.org/archive/documentation-24.0.html and open "Securing Applications and Services"
