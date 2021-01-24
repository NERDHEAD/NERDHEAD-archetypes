package com.nerdhead.rest.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ExcelDto {
	private String version;
	private String file_name;
	private String sheet_name = "Sheet1";
	private String[] keys;
	private List<HashMap<String, String>> data_list;
	
	public void setVersion_XLS() {
		version="xls";
	}
	public void setVersion_XLSX() {
		version="xlsx";
	}
}
