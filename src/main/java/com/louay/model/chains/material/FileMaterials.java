package com.louay.model.chains.material;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;

@Component
@Scope("prototype")
public class FileMaterials extends Materials {
    private byte[] file;

    @Autowired
    public FileMaterials(UserFactoryProducer userFactoryProducer, Courses course) {
        super(userFactoryProducer, course);
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public StringBuilder getBase64() {
        StringBuilder stringBase46 = new StringBuilder();
        stringBase46.append(Base64.getEncoder().encodeToString(this.file));

        return stringBase46;
    }

    @Override
    public String toString() {
        return super.toString() + ", FileMaterials{" +
                "file=" + Arrays.toString(file) +
                '}';
    }
}
