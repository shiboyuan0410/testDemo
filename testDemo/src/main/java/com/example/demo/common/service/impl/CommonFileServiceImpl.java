package com.example.demo.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.model.CommonFile;
import com.example.demo.common.service.CommonFileService;

@Service
public class CommonFileServiceImpl implements CommonFileService {

	@Override
	public JSONObject uploadFile(MultipartFile[] files) {
		OutputStream os = null;
		try {
			// 附件保存路径
			for (int i = 0; i < files.length; i++) {
				MultipartFile f = files[i];
				String oriFileName = f.getOriginalFilename();

				//判断文件名是否重复
				String name = oriFileName.substring(0, oriFileName.lastIndexOf('.'));

				CommonFile commonFile = new CommonFile();

				// 开始写入物理文件 向数据库中添加数据
				commonFile.setFileBlob(f.getBytes());
				// id
				commonFile.setFileId("1");

				// 附件名称
				commonFile.setFileName(name);

				File loaclFile = new File("f:\\cs\\"+oriFileName);
				os = new FileOutputStream(loaclFile,false);
				os.write(f.getBytes());

				System.out.println("--------------------");
				System.out.println(commonFile.toString());
				System.out.println("--------------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(os != null){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
