package net.hb.bbs;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class bbsController {
	private static final Logger logger = LoggerFactory.getLogger(bbsController.class);
	
	@Inject
	@Autowired
	bbsDAO dao;
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	HttpSession Session;
	
	@RequestMapping("/pop.do")
	public String pop() {
		return "pop";
	}
	
	@RequestMapping("/list.do")
	public ModelAndView board_select(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int total;
		String pnum="";
		int startpage=1,endpage=10;
		int pageNUM=1,start=1,end=10;
		int pagecount=1,temp=0;
		int Stotal;
		String skey ="", sval="" , returnpage = "";
		pnum=request.getParameter("pageNum");
		skey = request.getParameter("keyfield");
		sval= request.getParameter("keyword");  
		if(sval ==null || sval =="") {
			skey = ""; sval ="";
		}
		returnpage="&keyfield="+skey+"&keyword="+sval;
		if(pnum==null||pnum=="") {
			  pageNUM=1;
		}else {
			  pageNUM=Integer.parseInt(pnum);
		}
		start = (pageNUM-1)*10+1; 
		end = (pageNUM)*10;
		
		Stotal=dao.dbCount(skey,sval);
		
		total = dao.dbCount();
		
		if(total%10==0) {
			pagecount = Stotal/10;
		}else {
			pagecount = (Stotal/10)+1;
		}
		temp=(pageNUM-1)%10;
		startpage=pageNUM-temp;
		endpage=startpage+9;
		
		if(endpage>pagecount) {
			endpage=pagecount;
		}
		List<bbsDTO> list = dao.dbSelect(start,end,skey,sval);
		mav.addObject("naver",list);
		mav.addObject("total",total);
		mav.addObject("Stotal",Stotal);
		mav.addObject("startpage",startpage);
		mav.addObject("endpage",endpage);
		mav.addObject("pagecount",pagecount);
		mav.addObject("pageNUM",pageNUM);
		mav.addObject("sval",sval);
		mav.addObject("returnpage",returnpage);
		mav.setViewName("bbsList");
		return mav;
	}//end
	
	@RequestMapping("/write.do")
	public String write() {
		return "board";
	}
	
	@RequestMapping(value="/bbsinsert.do",  method = RequestMethod.POST)
	public String bbsWrite(bbsDTO dto) {
		
		String path=application.getRealPath("/resources/upload");
		String img=dto.getUpload_f().getOriginalFilename();
		File file = new File( path, img);
		String id = (String)Session.getAttribute("userid");
		dto.setId(id);
		try{ 
			dto.getUpload_f().transferTo(file);  
		}catch(Exception ex){
			System.out.println(ex);
		}
		dto.setImg_file_name(img); 
		dao.dbinsert(dto);
		return "redirect:/list.do";
	}
		

	public String getBrowser(HttpServletRequest request) {
	      String header = request.getHeader("User-Agent");
	 if (header.indexOf("MSIE") > -1) {
	     return "MSIE";
	 } else if (header.indexOf("Trident") > -1) {   // IE11 문자열 깨짐 방지
	     return "Trident";
	 } else if (header.indexOf("Chrome") > -1) {
	     return "Chrome";
	 } else if (header.indexOf("Opera") > -1) {
	     return "Opera";
	 } else if (header.indexOf("Safari") > -1) {
	     return "Safari";
	 }
	 return "Firefox";
	}

	  @RequestMapping("/download.do")
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
	      String browser = getBrowser(request);
	     
	      String dispositionPrefix = "attachment; filename=";
	      String encodedFilename = null;
	      
	      System.out.println("================================="+browser+"=============================");

	      if (browser.equals("MSIE")) {
	             encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	      } else if (browser.equals("Trident")) {       // IE11 문자열 깨짐 방지
	             encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	      } else if (browser.equals("Firefox")) {
	             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	             encodedFilename = URLDecoder.decode(encodedFilename);
	      } else if (browser.equals("Opera")) {
	             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	      } else if (browser.equals("Chrome")) {
	             StringBuffer sb = new StringBuffer();
	             for (int i = 0; i < filename.length(); i++) {
	                    char c = filename.charAt(i);
	                    if (c > '~') {
	                          sb.append(URLEncoder.encode("" + c, "UTF-8"));
	                    } else {
	                          sb.append(c);
	                    }
	             }
	             encodedFilename = sb.toString();
	      } else if (browser.equals("Safari")){
	             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
	             encodedFilename = URLDecoder.decode(encodedFilename);
	      }
	      else {
	             encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
	      }
	      response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
	      if ("Opera".equals(browser)){
	             response.setContentType("application/octet-stream;charset=UTF-8");
	      }
	}
	
	@RequestMapping("/detail.do")
	public ModelAndView detail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int data = Integer.parseInt(request.getParameter("idx"));
		bbsDTO dto = dao.dbDetail(data);
		mav.addObject("dto",dto);
		mav.setViewName("boardDetail");
		return mav;
	}
	
	@RequestMapping("/delete.do")
	public ModelAndView delete(HttpServletRequest request) {
		 ModelAndView mav = new ModelAndView( );
		int data = Integer.parseInt(request.getParameter("idx"));
		dao.dbDelete(data);
		mav.setViewName("redirect:/list.do");
		return mav;
	}
	
	@RequestMapping("/preEdit.do")
	public ModelAndView preEdit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView( );
		int data = Integer.parseInt(request.getParameter("idx"));
		bbsDTO dto = dao.dbDetail(data);
		mav.addObject("dto",dto);
		mav.setViewName("boardEdit");
		return mav;
	}
	
	@RequestMapping(value="/edit.do",  method = RequestMethod.POST)
	public String edit(bbsDTO dto) {
		String path=application.getRealPath("/resources/upload");
		String img=dto.getUpload_f().getOriginalFilename();
		File file = new File( path, img);
		try{ 
			dto.getUpload_f().transferTo(file);  
		}catch(Exception ex){
			System.out.println(ex);
		}
		dto.setImg_file_name(img); 
		dao.dbEdit(dto);
		return "redirect:/detail.do?idx="+dto.getBbs_idx();
	}
	  
	  
}
