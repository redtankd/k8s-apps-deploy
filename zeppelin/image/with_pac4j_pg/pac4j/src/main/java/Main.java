import net.minidev.json.JSONObject;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.JWT;
import org.pac4j.oidc.config.OidcConfiguration;

import java.text.ParseException;

public class Main {
 

    public static void main(String[] args) throws Exception {
        var id_token_string = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkMTBlZDllMC01NDUzLTQ3YjktOGEwMS1jZTQ2ZmE3YjJhODYifQ.eyJleHAiOjE2NDgxMTMzMTMsImlhdCI6MTY0ODExMzAxMywiYXV0aF90aW1lIjoxNjQ4MTEzMDEyLCJqdGkiOiJmYjNjM2ZlYi1lNDRmLTRlMmYtYmFhNC01MTUwYmMwOGJjNjAiLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwODEvcmVhbG1zL3plcHBlbGluIiwiYXVkIjoiemVwcGVsaW4iLCJzdWIiOiI4ZTJjNTVlNC03OTliLTRmMTgtODdiNS1hMmQ1NTI0NDA4NTciLCJ0eXAiOiJJRCIsImF6cCI6InplcHBlbGluIiwic2Vzc2lvbl9zdGF0ZSI6IjU4NzljYmE2LTMzYTAtNDJkNi1hY2ZjLTA0YjliY2FiNDBkMSIsImF0X2hhc2giOiJRMEU2UEliQ0h6eVRkdTVMWElKRVZBIiwiYWNyIjoiMSIsInNpZCI6IjU4NzljYmE2LTMzYTAtNDJkNi1hY2ZjLTA0YjliY2FiNDBkMSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiejEifQ.7c5rcFO5UYOxevhutWR_V5se-HThxnaEzfNtUt5cDwg";



        JWT idToken;
                        
        idToken = JWTParser.parse(id_token_string);
                        
        System.out.println(idToken.getHeader());

        System.out.println(idToken.getParsedString());

        System.out.println(idToken.getJWTClaimsSet());

        var oidcConfig = new OidcConfiguration();
        oidcConfig.setClientId("zeppelin");
        oidcConfig.setSecret("d10ed9e0-5453-47b9-8a01-ce46fa7b2a86");
        oidcConfig.setDiscoveryURI("http://127.0.0.1:8081/realms/zeppelin/.well-known/openid-configuration");
        oidcConfig.setPreferredJwsAlgorithmAsString("ES256");

        var validator = oidcConfig.findTokenValidator();

        // for (var v: validator.getIdTokenValidators()) {
        //     System.out.println(v);
        // }

        System.out.println(validator.validate(idToken, null));
    }

}