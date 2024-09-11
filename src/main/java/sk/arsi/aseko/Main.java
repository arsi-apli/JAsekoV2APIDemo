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

import java.util.List;
import sk.arsi.aseko.pojo.v2.login.Root;
import sk.arsi.aseko.pojo.v2.units.Unit;

/**
 *
 * @author arsi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final String email = "xxx@xx.xx";
    private static final String password = "xxxxx";
    //I pulled this by analyzing the original request in firefox, it is possible that yours will be different
    private static final String cloud = "01HXS50KTV7NRSVNHD617J4CKB";

    public static void main(String[] args) {
        // TODO code application logic here
        AsekoTest asekoTest = new AsekoTest();
        Root login = asekoTest.login(email, password, cloud);
        List<Unit> readUnits = asekoTest.readUnits(login);
        for (int i = 0; i < readUnits.size(); i++) {
            System.out.println("#######################################################################");
            Unit it = readUnits.get(i);
            asekoTest.readUnit(login, it.serialNumber);
            System.out.println("#######################################################################");
        }

    }

}
