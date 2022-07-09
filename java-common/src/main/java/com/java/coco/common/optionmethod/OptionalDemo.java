package com.java.coco.common.optionmethod;

import java.util.Optional;

public class OptionalDemo {


    public static void main(String[] args) throws Exception  {
        Address address = new Address();
        //address.setCityName("安康");
        TestOptionUser testOptionUser = new TestOptionUser();
        testOptionUser.setAddress(address);
        OptionalDemo optionalDemo = new OptionalDemo();
//        String cityNameByUser = optionalDemo.getCityNameByUser(testOptionUser);
//        System.out.println(cityNameByUser);

        optionalDemo.ofNullableOption(testOptionUser);

    }


    public String getCityNameByUser(TestOptionUser testOptionUser) throws Exception {
        return Optional.ofNullable(testOptionUser)
                .map(TestOptionUser::getAddress)
                .map(Address::getCityName)
                .orElseThrow(() -> new Exception("取值错误"));
    }



    public void ofNullableOption(TestOptionUser testOptionUser){
        Optional.ofNullable(testOptionUser.getAddress().getCityName()).ifPresent(e -> System.out.println(e.toString()));
    }



}
