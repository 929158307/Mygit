package cn.itcast.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.base.AreaDao;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.base.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;

	public void save(List<Area> list) {
		areaDao.save(list);
	}

	public Page<Area> pageQuery(Pageable pageable) {
		return areaDao.findAll(pageable);
	}

	public List<Area> findAll() {
		return areaDao.findAll();
	}
}
