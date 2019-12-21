package com.example.demo.common.model;

import lombok.Data;

@Data
public class CommonFile {

	// fileId
	private String fileId;
	
	// 文件名
	private String fileName;
	
	// 文件路径
	private String filePath;

	// 扩展名
	private String ext;
	
	// 附件内容
	private byte[]  fileBlob;


}
