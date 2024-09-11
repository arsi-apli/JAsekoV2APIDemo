/*
 * Copyright 2024 ArSi (arsi_at_arsi_sk)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sk.arsi.aseko;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import sk.arsi.aseko.pojo.v2.operation.units.Variables;
import sk.arsi.aseko.pojo.v2.unit.NotificationConfiguration;
import sk.arsi.aseko.pojo.v2.units.Unit;

/**
 *
 * @author arsi
 */
public class AsekoTest {

    static {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    public AsekoTest() {
    }

    public sk.arsi.aseko.pojo.v2.login.Root login(String email, String password, String cloud) {
        try {
            URL url = new URL("https://auth.aseko.acs.aseko.cloud/auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            String jsonInputString = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"cloud\":\"" + cloud + "\"}";
            System.out.println(jsonInputString);
            try ( OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            String out = null;
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                out = sb.toString();
            }
            ObjectMapper om = new ObjectMapper();
            connection.disconnect();
            return om.readValue(out, sk.arsi.aseko.pojo.v2.login.Root.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readUnit(sk.arsi.aseko.pojo.v2.login.Root login, String serial) {
        sk.arsi.aseko.pojo.v2.operation.unit.Root query = new sk.arsi.aseko.pojo.v2.operation.unit.Root();
        query.operationName = "UnitDetail";
        query.query = "query UnitDetail($sn: String!) {\n"
                + "  unitBySerialNumber(serialNumber: $sn) {\n"
                + "    __typename\n"
                + "    ... on UnitNotFoundError {\n"
                + "      serialNumber\n"
                + "      __typename\n"
                + "    }\n"
                + "    ... on UnitAccessDeniedError {\n"
                + "      serialNumber\n"
                + "      __typename\n"
                + "    }\n"
                + "    ... on UnitNeverConnected {\n"
                + "      serialNumber\n"
                + "      name\n"
                + "      note\n"
                + "      statusMessages {\n"
                + "        __typename\n"
                + "        severity\n"
                + "        type\n"
                + "        message\n"
                + "        detail\n"
                + "      }\n"
                + "      __typename\n"
                + "    }\n"
                + "    ... on Unit {\n"
                + "      serialNumber\n"
                + "      name\n"
                + "      note\n"
                + "      brandName {\n"
                + "        __typename\n"
                + "        id\n"
                + "        primary\n"
                + "        secondary\n"
                + "      }\n"
                + "      statusMessages {\n"
                + "        __typename\n"
                + "        severity\n"
                + "        type\n"
                + "        message\n"
                + "        detail\n"
                + "      }\n"
                + "      heating {\n"
                + "        __typename\n"
                + "        lastReset\n"
                + "      }\n"
                + "      waterFilling {\n"
                + "        __typename\n"
                + "        id\n"
                + "        waterLevel\n"
                + "        lastReset\n"
                + "      }\n"
                + "      consumables {\n"
                + "        __typename\n"
                + "        ... on LiquidConsumable {\n"
                + "          type\n"
                + "          canister {\n"
                + "            __typename\n"
                + "            id\n"
                + "            hasWarning\n"
                + "          }\n"
                + "          tube {\n"
                + "            __typename\n"
                + "            id\n"
                + "            hasWarning\n"
                + "          }\n"
                + "          __typename\n"
                + "        }\n"
                + "        ... on ElectrolyzerConsumable {\n"
                + "          type\n"
                + "          electrode {\n"
                + "            __typename\n"
                + "            hasWarning\n"
                + "          }\n"
                + "          __typename\n"
                + "        }\n"
                + "      }\n"
                + "      notificationConfiguration {\n"
                + "        __typename\n"
                + "        id\n"
                + "        type\n"
                + "        name\n"
                + "        enabled\n"
                + "        lowWarningLevel\n"
                + "        highWarningLevel\n"
                + "        color\n"
                + "        currentValue\n"
                + "        suffix\n"
                + "        hasWarning\n"
                + "        possibleWarningLevels\n"
                + "      }\n"
                + "      unitModel {\n"
                + "        __typename\n"
                + "        id\n"
                + "        tabs {\n"
                + "          hideNotifications\n"
                + "          hideConsumables\n"
                + "          hideProtocolExport\n"
                + "          __typename\n"
                + "        }\n"
                + "      }\n"
                + "      __typename\n"
                + "    }\n"
                + "  }\n"
                + "}";
        query.variables = new sk.arsi.aseko.pojo.v2.operation.unit.Variables();
        query.variables.sn = serial;
        String out = null;
        out = readGraphql(login, query);

        try {
            ObjectMapper om = new ObjectMapper();
            sk.arsi.aseko.pojo.v2.unit.Root root = om.readValue(out, sk.arsi.aseko.pojo.v2.unit.Root.class);
            System.out.println("**********************************");
            System.out.println("Name & SN: " + root.data.unitBySerialNumber.name + " " + root.data.unitBySerialNumber.serialNumber);
            if (root.data.unitBySerialNumber.waterFilling != null) {
                System.out.println("Water level: " + root.data.unitBySerialNumber.waterFilling.waterLevel);
            }
            if (root.data.unitBySerialNumber.notificationConfiguration != null) {
                for (int i = 0; i < root.data.unitBySerialNumber.notificationConfiguration.size(); i++) {
                    NotificationConfiguration nf = root.data.unitBySerialNumber.notificationConfiguration.get(i);
                    if (nf.currentValue != null) {
                        System.out.println("Name: " + nf.name);
                        System.out.println("Value: " + nf.currentValue + nf.suffix);
                        System.out.println("Error: " + nf.hasWarning);
                        System.out.println("--------------");
                    }
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String readGraphql(sk.arsi.aseko.pojo.v2.login.Root login, Object query) {
        String out = null;
        try {
            URL url = new URL("https://graphql.acs.prod.aseko.cloud/graphql");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + login.token);
            ObjectMapper om = new ObjectMapper();
            StringWriter stringWriter = new StringWriter();
            om.writeValue(stringWriter, query);

            try ( OutputStream os = connection.getOutputStream()) {
                byte[] input = stringWriter.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                out = sb.toString();
            }
            connection.disconnect();
        } catch (IOException iOException) {
        }

        return out;
    }

    public List<Unit> readUnits(sk.arsi.aseko.pojo.v2.login.Root login) {
        List<Unit> items = new ArrayList<>();
        try {
            ObjectMapper om = new ObjectMapper();
            sk.arsi.aseko.pojo.v2.operation.units.Root query = new sk.arsi.aseko.pojo.v2.operation.units.Root();
            query.operationName = "UnitList";
            query.query = "fragment UnitFragment on Unit {\n"
                    + "  __typename\n"
                    + "  serialNumber\n"
                    + "  name\n"
                    + "  note\n"
                    + "  brandName {\n"
                    + "    id\n"
                    + "    primary\n"
                    + "    secondary\n"
                    + "    __typename\n"
                    + "  }\n"
                    + "  position\n"
                    + "  statusMessages {\n"
                    + "    __typename\n"
                    + "    type\n"
                    + "    severity\n"
                    + "    message\n"
                    + "  }\n"
                    + "  consumables {\n"
                    + "    __typename\n"
                    + "    ... on LiquidConsumable {\n"
                    + "      canister {\n"
                    + "        __typename\n"
                    + "        id\n"
                    + "        hasWarning\n"
                    + "      }\n"
                    + "      tube {\n"
                    + "        __typename\n"
                    + "        id\n"
                    + "        hasWarning\n"
                    + "      }\n"
                    + "      __typename\n"
                    + "    }\n"
                    + "    ... on ElectrolyzerConsumable {\n"
                    + "      electrode {\n"
                    + "        __typename\n"
                    + "        hasWarning\n"
                    + "      }\n"
                    + "      __typename\n"
                    + "    }\n"
                    + "  }\n"
                    + "  online\n"
                    + "  offlineFor\n"
                    + "  hasWarning\n"
                    + "  notificationConfiguration {\n"
                    + "    __typename\n"
                    + "    id\n"
                    + "    hasWarning\n"
                    + "  }\n"
                    + "  unitModel {\n"
                    + "    __typename\n"
                    + "    id\n"
                    + "    tabs {\n"
                    + "      hideNotifications\n"
                    + "      hideConsumables\n"
                    + "      __typename\n"
                    + "    }\n"
                    + "  }\n"
                    + "}\n"
                    + "\n"
                    + "fragment UnitNeverConnectedFragment on UnitNeverConnected {\n"
                    + "  __typename\n"
                    + "  serialNumber\n"
                    + "  name\n"
                    + "  note\n"
                    + "  position\n"
                    + "  statusMessages {\n"
                    + "    __typename\n"
                    + "    severity\n"
                    + "    type\n"
                    + "    message\n"
                    + "    detail\n"
                    + "  }\n"
                    + "}\n"
                    + "\n"
                    + "query UnitList($after: String, $first: Int, $search: String) {\n"
                    + "  units(after: $after, first: $first, searchQuery: $search) {\n"
                    + "    cursor\n"
                    + "    units {\n"
                    + "      ...UnitFragment\n"
                    + "      ...UnitNeverConnectedFragment\n"
                    + "      __typename\n"
                    + "    }\n"
                    + "    __typename\n"
                    + "  }\n"
                    + "}";
            query.variables = new Variables();
            query.variables.first = 15;
            query.variables.after = null;
            query.variables.search = "";

            sk.arsi.aseko.pojo.v2.units.Root root = om.readValue(readGraphql(login, query), sk.arsi.aseko.pojo.v2.units.Root.class);
            items = root.data.units.units;
            for (int i = 0; i < items.size(); i++) {
                Unit it = items.get(i);
                System.out.println("##################################");
                System.out.println("Name: " + it.name);
                System.out.println("Serial: " + it.serialNumber);
                if (it.brandName != null) {
                    System.out.println("Type: " + it.brandName.primary);
                }
            }
            System.out.println("##################################");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return items;
    }

}
