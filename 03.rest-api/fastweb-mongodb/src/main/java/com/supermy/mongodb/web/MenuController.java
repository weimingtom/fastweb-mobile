package com.supermy.mongodb.web;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.supermy.mongodb.domain.Menu;
import com.supermy.mongodb.domain.News;
import com.supermy.mongodb.service.CommonDao;
import com.supermy.mongodb.service.MenuRepository;

/**
 * 菜单
 * 
 * @author james mo
 * 
 */
@Controller
@RequestMapping("menu")
public class MenuController {
	private final Logger logger = LoggerFactory
			.getLogger(MenuController.class);

	@Autowired
	private MenuRepository cs;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list/{currentPage}/{limit}", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public @ResponseBody
	List<Menu> getPersons(@PathVariable int currentPage,
			@PathVariable int limit) throws Exception {
		logger.debug("get news called");
		PageRequest pageRequest = new PageRequest(currentPage, limit);
		
		System.out.println(pageRequest.getPageNumber());
		System.out.println(pageRequest.getPageSize());
		
		Page<Menu> result = cs.findAll(pageRequest);
		return result.getContent();
	}

	/**
	 * 单个对象查询
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}")
	@Transactional(readOnly = true)
	public @ResponseBody
	Menu getPerson(@PathVariable("id") String id) {
		return cs.findOne(new ObjectId(id));
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	@Transactional(readOnly = false)
	public @ResponseBody
	Menu addPerson() {
		
		
		Integer integer = new Integer(new Date().getMinutes());
		Menu person = new Menu();
//		person.put("person", integer);
		
		
		return person;
	}

	/**
	 * 创建
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	Menu createPerson(@RequestBody Menu person) {
		logger.debug("create Menu called" + person);
		logger.debug("create Menu called", person);

		cs.save(person);

		return person;
	}

	/**
	 * 创建
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public @ResponseBody
	HashMap<String, Object> createPerson4Map(
			@RequestBody HashMap<String, Object> person) {
		System.out
				.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>create Menu called"
						+ person);
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>create Menu called",
				person);

		// cs.addPerson(person);
		//
		return person;

	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit")
	@Transactional(readOnly = true)
	public @ResponseBody
	Menu edit(@PathVariable("id") String id) {
		return cs.findOne(new ObjectId(id));

	}

	/**
	 * 更新
	 * 
	 * @param persons
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Menu updatePersons(@RequestBody Menu person, @PathVariable String id) {
		logger.debug("updatePersons called");
		cs.save(person);
		return person;

	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	boolean deletePersons(@PathVariable String id) {
		logger.debug("delete Menu called" + id);
		cs.delete(new ObjectId(id));
		return true;
	}

	/** 批量删除 */
	@RequestMapping(value = "/list/del", method = RequestMethod.PUT)
	public @ResponseBody
	boolean batchDelete(@RequestBody String[] items) {
		for (String e : items) {
			cs.delete(new ObjectId(e));
		}
		return true;
	}

	@Autowired
	private CommonDao personDao;

	/**
	 ** 上传单多文档
	 */
	@RequestMapping(value = "/upload")
	public String upload2(@RequestParam("file") MultipartFile files[], 	@RequestParam("text") String text 	) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>upload start:");

		logger.debug("[ text ] : " + text); // 打印 页面上的控件值
		
		//List<MultipartFile> files = file.getrequest.getFiles("file");
		
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>upload file count:"+files.length);
		
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				//存储文件到数据库
				String filename=StringUtils.isBlank(text)?files[i].getOriginalFilename():text;
				String id = personDao.save(files[i].getInputStream(),files[i].getContentType(),filename);
				//查询文件
				GridFSDBFile file1 = personDao.get(id);
				logger.debug(file1.getMetaData().toString());				
				
				//查询数据表里面的所有文件
				List<GridFSDBFile> file2s = personDao.listFiles();

				for (GridFSDBFile file2: file2s) {
					logger.debug(">>>>>>>>>>>>>>>>>>:"+file2);
				}

			}
			logger.debug("Ok");
		}
		return "index";
	}

}
