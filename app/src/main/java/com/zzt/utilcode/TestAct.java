package com.zzt.utilcode;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Optional;


/**
 * @author: zeting
 * @date: 2021/6/18
 */
public class TestAct  extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Student student = new Student();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            String s = Optional.ofNullable(student)
                    .map(Student::getAddress)
                    .map(Address::getCity)
                    .map(City::getCode)
                    .map(Code::getIdCode)
                    .orElse("");
        }
    }

    class Student{
        Address address ;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    class Address{
        City city ;

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }
    }
    class City{
        Code code ;

        public Code getCode() {
            return code;
        }

        public void setCode(Code code) {
            this.code = code;
        }
    }
    class Code{
        String idCode ;

        public String getIdCode() {
            return idCode;
        }

        public void setIdCode(String idCode) {
            this.idCode = idCode;
        }
    }
}
