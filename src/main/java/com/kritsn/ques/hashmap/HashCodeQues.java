package com.kritsn.ques.hashmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 12, 2025
 */

public class HashCodeQues {
    public static void main(String[] args) {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Ques1
    ///////////////////////////////////////////////////////////////////////////
    public static void ques1(){
        Map<Emp, String> map = new HashMap<>();
//        map.put(new)
    }

    static class Emp{
        private int id;
        private String name;
        /*private List<Address> addresses;
        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }*/


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Address{
        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
