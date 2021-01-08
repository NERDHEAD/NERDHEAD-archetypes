package com.dev2.ylml.abstractView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.dev2.ylml.dto.ExcelDto;
import com.dev2.ylml.util.ApachePOIHelper;

@Component
public class ExcelDownload extends AbstractView {
	public ExcelDownload() {
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			ExcelDto dto = (ExcelDto)model.get("excelDto");
			ApachePOIHelper helper=new ApachePOIHelper(dto);
			
			setContentType(helper.getContentType());
			response.setContentType(getContentType());
			
			
			helper.downloadExel(request, response);
			
			// Don't close the stream - we didn't open it, so let the container handle it.
	        // http://stackoverflow.com/questions/1829784/should-i-close-the-servlet-outputstream
	}

}
