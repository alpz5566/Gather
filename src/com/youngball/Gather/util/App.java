package com.youngball.Gather.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

import com.youngball.Gather.domain.Page;

public class App {
	public static void main(String[] args) throws Exception {
		//深度复制
		/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(p1);
		oos.close();
		baos.close();
		byte[] data =  baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Page copy = (Page) ois.readObject();
		ois.close();
		bais.close();*/
		//String key = "q12_332";
		//System.out.println(Integer.parseInt(key.substring(1,key.indexOf("_"))));
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
	}
}
