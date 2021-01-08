package com.dev2.ylml.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev2.ylml.abstractView.ExcelDownload;
import com.dev2.ylml.dto.ExcelDto;


@Controller
public class SampleController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "index.do")
	public String index() {
		return "index";
	}
	
	
	
	
	/* 엑셀 다운로드 예제 */
	@Autowired
	ExcelDownload excelView;
	/* 1. ModelAndView로 excelView 객체를 생성
	 * 2. 테 | 스 | 트
	 * 	  1 | 2  | 3
	 *    4 | 5  | 6
	 *    을 넣는다면 
	 *    key[]에 {"테","스","트"}
	 *    한줄의 값을 hashmap에 <테, 1> <스, 2> <트, 3> 형식으로 담고  -> 해당 hashmap을 list에 담아서
	 *    ExcelDto에 setKeys(key), setData_list(list)해준다
	 *    ExcelDto에 버전과 파일네임을 설정해주고 excelView 객체에 등록 후 return 해주면
	 *    해당 주소(/downloadExcel.do)를 호출한 페이지에서 해당 list가 excel형식으로 다운로드하는 창이 뜨게 된다
	 *    
	 *    예제는 아래와 같다
	 * */
	@RequestMapping(value="/downloadExcel.do")
	public ModelAndView downloadExcel() throws Exception{
		if(excelView==null) {
			logger.info("downloadExcel -> excelView is null!!!");
		}
	    ModelAndView mav = new ModelAndView(excelView);
	    //테스트용 더미 데이터
	    String[] keys = {"테","스","트"};
	    List<HashMap<String, String>> data_list =
	    		new ArrayList<HashMap<String, String>>();
	    HashMap<String, String> value;
	    for (int i = 0; i < 5; i++) {
			value=new HashMap<>();
			for (int j = 0; j < keys.length; j++) {
				value.put(keys[j], Integer.toString(((i+1)*10+j+1)));
			}
			data_list.add(value);
		}
	    
	    ExcelDto excelDto=new ExcelDto();
	    excelDto.setVersion_XLSX();
	    excelDto.setFile_name("testExcel");
		excelDto.setKeys(keys);
		excelDto.setData_list(data_list);
	    
	    mav.addObject("excelDto", excelDto);
	    return mav;
	}
	
	
}
