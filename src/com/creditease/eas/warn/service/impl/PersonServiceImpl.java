package com.creditease.eas.warn.service.impl;
import org.springframework.stereotype.Service;

import com.creditease.eas.warn.service.PersonService;
@Service("personServiceImpl")
//@Service
public class PersonServiceImpl implements PersonService {
//	@Autowired
//	private PersonMapper personMapper;
//	@Autowired
//	private PersonDataMapper personDataMapper;
//	@Transactional
//	public void save(Person per) {
//		dao.save(per);
//	}
//
//	@Transactional
//	public void delete(Person per) {
//		dao.delete(per);
//	}
//
//	@Transactional
//	public void delete(Integer id) {
//		dao.delete(Person.class, id);
//	}
//
//	@Transactional
//	public void update(Person per) {
//		dao.update(per);
//	}
//	public PageBean queryPage(PageBean page) {
//		int totalCounts = personMapper.getTotalCounts();
//		page = new PageBean(PageConst.PAGESIZE,page.getCurPage(),totalCounts);
//		Map map = PageUtil.getMap(page);
//		List<Person> list = personMapper.queryPage(map);
//		page.setResult(list);
//		return page;
//	}
//	public void t() throws Exception{
//		List list = PersonDataQuery.getPersonDataList();
//		BaseUtil.insertAll(list, personDataMapper);
//		if(list!=null){
//			for (int i = 0; i < list.size(); i++) {
//				PersonData pd=(PersonData) list.get(i);
//				personDataMapper.insert(pd);
//			}
//		}
		//personMapper.insert(entity);
	//}
//
//	public Person findById(Integer id) {
//		return dao.findById(Person.class, id);
//	}
//	public List<Person> findAll() {
//		return dao.findAll(Person.class);
//	}
	
//	public static void main(String[] args)  throws Exception{
//		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//		PersonService personService = (PersonService)app.getBean("personServiceImpl");
//		personService.t();
//	}
}
