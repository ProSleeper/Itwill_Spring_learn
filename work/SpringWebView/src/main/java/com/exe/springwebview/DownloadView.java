package com.exe.springwebview;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


public class DownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String[] fileName = {"wordleServer (1).zip", "h2-setup-2019-10-14.exe"};
		
		response.setContentType("application/octet-steam");
		response.addHeader("Content-Disposition", "attachment;fileName=" + fileName[0] );
		
		String path = request.getSession().getServletContext().getRealPath("WEB-INF/files/" + fileName[0]);
		
//		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
//		
//		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
//	
//		int data;
//		while((data=bis.read()) != -1) {
//			bos.write(data);
//		
//		}
//		bis.close();
//		bos.close();
		
		FileInputStream fis = new FileInputStream(path);
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fis, os);
		
	}

	
	
	
}
