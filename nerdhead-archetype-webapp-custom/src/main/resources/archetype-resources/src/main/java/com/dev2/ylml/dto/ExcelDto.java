#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.dev2.ylml.dto;

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
