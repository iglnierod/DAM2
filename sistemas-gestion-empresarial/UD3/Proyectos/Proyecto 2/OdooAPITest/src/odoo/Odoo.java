/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package odoo;

import java.net.MalformedURLException;
import java.net.URL;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author fermigo
 */
public class Odoo {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     * @throws org.apache.xmlrpc.XmlRpcException
     */
    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        odooTest();
    }

    private static void odooTest() throws MalformedURLException, XmlRpcException {

        final String url = "http://192.168.250.2:8069",
                db = "odoo",
                username = "fgfolgar@edu.xunta.gal",
                password = "51ca5b135e43a5bdf7179a5f56c05b3a33f101c9";

        final XmlRpcClient client = new XmlRpcClient();

        final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
        common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url)));
        client.execute(common_config, "version", emptyList());
        int uid = (int) client.execute(common_config, "authenticate", asList(db, username, password, emptyMap()));

        final XmlRpcClient models = new XmlRpcClient() {
            {
                setConfig(new XmlRpcClientConfigImpl() {
                    {
                        setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
                    }
                });
            }
        };
        List ids = asList((Object[]) models.execute("execute_kw", asList(
                db, uid, password,
                "res.partner", "search",
                asList(asList(
                        asList("is_company", "=", true)))
        )));

        List partnerResults = asList((Object[]) models.execute("execute_kw", asList(
                db, uid, password,
                "res.partner", "read",
                asList(ids),
                new HashMap() {
            {
                put("fields", asList("name", "street", "city", "phone", "mobile"));
            }
        }
        )));

        System.out.println(partnerResults.toString());
        for (int i = 0; i < partnerResults.size(); i++) {
            String register = partnerResults.get(i).toString();
            String city = getField("{city=", ", phone=", register);
            System.out.println(city);
            String phone = getField(", phone=", ", street=", register);
            System.out.println(phone);
            String street = getField(", street=", ", name=", register);
            System.out.println(street);
            String name = getField(", name=", ", mobile=", register);
            System.out.println(name);
            String mobile = getField(", mobile=", ", id=", register);
            System.out.println(mobile);

        }

    }

    private static String getField(String left, String right, String register) {
        int begin = register.lastIndexOf(left) + left.length();
        int end = register.lastIndexOf(right);
        return register.substring(begin, end);
    }

    private static void openTest() throws MalformedURLException, XmlRpcException {
        final XmlRpcClient client = new XmlRpcClient();
        final XmlRpcClientConfigImpl start_config = new XmlRpcClientConfigImpl();
        start_config.setServerURL(new URL("https://demo.odoo.com/start"));
        final Map<String, String> info = (Map<String, String>) client.execute(
                start_config, "start", emptyList());
        final String url = info.get("host"),
                db = info.get("database"),
                username = info.get("user"),
                password = info.get("password");
        System.out.println(url);
        final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
        common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", url)));
        client.execute(common_config, "version", emptyList());
        System.out.println(client.execute(common_config, "version", emptyList()));
    }

}
