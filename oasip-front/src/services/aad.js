import {UserAgentApplication} from 'msal'

const msalConfig = {
  auth: {
    // 'Application (client) ID' of app registration in Azure portal - this value is a GUID
    clientId: "5eb9fc95-26f5-4156-9ae4-c03b7cb573ee",
    // Full directory URL, in the form of https://login.microsoftonline.com/<tenant-id>
    authority: "https://login.microsoftonline.com/6f4432dc-20d2-441d-b1db-ac3380ba633d/",
    // Full redirect URL, in form of http://localhost:3000
    redirectUri: "http://localhost:3000/",
  },
  cache: {
    cacheLocation: "localStorage", // This configures where your cache will be stored
    storeAuthStateInCookie: true, // Set this to "true" if you are having issues on IE11 or Edge
  },
}

const requestObj = {
  scopes: [ "User.Read"]
}

const myMSALobj = new UserAgentApplication(msalConfig)

const login = async () => {
  const authRes = await myMSALobj.loginPopup(requestObj)
  return authRes
}

export default {
  login
}