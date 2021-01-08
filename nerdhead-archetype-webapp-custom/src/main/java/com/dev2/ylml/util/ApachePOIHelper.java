package com.dev2.ylml.util;


import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.dev2.ylml.dto.ExcelDto;

public class ApachePOIHelper {
	// 굳이 DTO로 뺄 이유가 없어서 냅둠
	/*
	 * // DTO로 세팅 파일 던지는것도 고려 할 것 private String version; private String file_name;
	 * private String sheet_name = "Sheet1"; private String[] keys; private
	 * List<HashMap<String, Object>> data_list;
	 */

	private ExcelDto dto;

	public ApachePOIHelper(ExcelDto dto) {
		// 버전 설정 -> 디폴트 = xls
		// 파일 이름 설정
		// 저장 경로 설정
		// 시트 이름 설정 -> 디폴트 = Sheet1
		// 데이터 -> String[] keys, List<Hashmap<String, Object>>
		// Object에는 String, Integer, Calendar 등이 가능함
		setExcelDto(dto);
	}

	private void setExcelDto(ExcelDto dto) {
		if (checkDto(dto)) {
			this.dto = dto;
		} else {
			// TODO : 일단 귀찬아서 아무 예외 처리로 던지게 함 -> 추후 적당한 예외 처리 Class 만들 것
			throw new NoClassDefFoundError();
		}
	}

	private boolean checkDto(ExcelDto dto) {
		return ("xls".equals(dto.getVersion()) || "xlsx".equals(dto.getVersion())) 
				&& dto.getFile_name() != null
				&& dto.getKeys() != null 
				&& dto.getData_list() != null;
	}

	public void downloadExel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Workbook workbook = new ApachePOI().getExcel(dto);
		
		String sFilename = "";
        if(dto.getFile_name() != null){
            sFilename = dto.getFile_name();
        }else{
            sFilename = "download";
         }
		
		
		String header = request.getHeader("User-Agent");
        sFilename = sFilename.replaceAll("\r","").replaceAll("\n","");
        if(header.contains("MSIE") || header.contains("Trident") || header.contains("Chrome")){
            sFilename = URLEncoder.encode(sFilename,"UTF-8").replaceAll("\\+","%20");
            response.setHeader("Content-Disposition","attachment;filename="+sFilename+"."+dto.getVersion()+";");
        }else{
            sFilename = new String(sFilename.getBytes("UTF-8"),"ISO-8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+sFilename + "."+dto.getVersion()+";\"");
        }
		
		ServletOutputStream out = response.getOutputStream();
		out.flush();
		workbook.write(out);
		out.flush();
		
	}

	public String getContentType() {
		if ("xls".equals(dto.getVersion())) {
			return "application/vnd.ms-excel";
		} else {
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}
	}
}
