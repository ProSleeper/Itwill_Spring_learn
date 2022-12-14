package com.jdbc.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {

	private static volatile FileManager fm = null;
	
	private FileManager() {
		fm = new FileManager();
	}
	
	public FileManager getInstance() {
		if(fm == null) {
			synchronized (FileManager.class) {
				if (fm == null) {
					fm = new FileManager();
				}
			}
		}
		return fm;
	}

	
	//내 생각에 있어야 할것 같은 거
	//파일 업로드
	public static boolean doFileUpload(HttpServletResponse response, 
			String saveFileName, String originalFileName, String path) {
		
		return true;
	}
	
	
	
	//파일 다운로드
	public static boolean doFileDownload(HttpServletResponse response, 
			String saveFileName, String originalFileName, String path) {
	
		try {
			String fullPath = path + File.separator + saveFileName;
			
			if(originalFileName == null || originalFileName.equals("")) {
				originalFileName = saveFileName;
			}
			
			//한글파일 이름 깨짐 방지
			originalFileName = new String(originalFileName.getBytes("euc-kr"), "ISO-8859-1");
			System.out.println(originalFileName);
			File f = new File(fullPath);
			
			if(!f.exists()) {
				return false;
			}
			
			//파일 다운로드
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;fileName=" + originalFileName);
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			OutputStream out = response.getOutputStream();
			
			int data;
			byte[] bytes = new byte[4096];
			while ((data = bis.read(bytes, 0, 4096)) != -1) {
				out.write(bytes, 0, data);
			}
			
			out.flush();
			out.close();
			bis.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	
	//파일 삭제
	public static void doFileDelete(String fileName, String path) {
		
		try {
			String filePath = path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists()){
				f.delete();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
}










