package com.java.spring.bean.test;

import com.java.spring.bean.learn.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {

    public static void main(String[] args) {
        ApplicationContext ctx  =  new ClassPathXmlApplicationContext("Bean.xml");
        Person person  = ctx.getBean("person",Person.class);
        person.info();
    }
}
