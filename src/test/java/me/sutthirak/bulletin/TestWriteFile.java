package me.sutthirak.bulletin;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class TestWriteFile {

    @Test
    public void encodePasswordTest(){
        String sha256hex = DigestUtils.sha256Hex("1234");
        System.out.println(sha256hex);
    }

    @Test
    public void writeFile() throws IOException{
        String defaultPassword = DigestUtils.sha256Hex("1234");
        FileWriter fw = new FileWriter("out.sql");
        for (int i = 0; i < 100000; i++) {
            fw.write("insert into topic(title,description,writer_title,writer_email,writer_name,writer_password,created_date,number_of_view) values('Siam through the looking glass ("+(i+1)+")','Many pictures of Siam are currently owned and kept by foreigners abroad and have never been shown in Thailand, until now.',1,'test@mail.com','Somchai Jaidee','"+defaultPassword+"','2016-09-11',0);\n");
        }

        fw.close();
    }

}
