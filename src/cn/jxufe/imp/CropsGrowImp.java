package cn.jxufe.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.ejb.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.CropsGrowDAO;
import cn.jxufe.entity.CropsGrow;
import cn.jxufe.service.CropsGrowService;

@Service
public class CropsGrowImp implements CropsGrowService {
	@Autowired
	CropsGrowDAO cropsGrowDAO;

	@Override
	public EasyUIData<?> findByStatus(int status, Pageable pageable) {
		Page<CropsGrow> page = cropsGrowDAO.findByStatus(status, pageable);
		EasyUIData<CropsGrow> easyUIData = new EasyUIData<CropsGrow>();
		easyUIData.setTotal(page.getTotalElements());
		easyUIData.setRows(page.getContent());
		return easyUIData;
	}

	@Override
	public Message save(CropsGrow cropsGrow) {
		Message message = new Message();
		try {
			cropsGrowDAO.save(cropsGrow);
			message.setCode(0);
			message.setMsg("保存成功");
		} catch (Exception e) {
			message.setCode(-10);
			message.setMsg("保存失败");
		}
		return message;
	}

	@Override
	public Message delete(CropsGrow cropsGrow) {
		Message message = new Message();
		try {
			cropsGrowDAO.delete(cropsGrow);
			message.setCode(0);
			message.setMsg("删除成功");
		} catch (Exception e) {
			message.setCode(-10);
			message.setMsg("删除失败");
		}
		return message;
	}

	public EasyUIData<?> findBySome(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CropsGrow> page = cropsGrowDAO.findAll(pageable);
		EasyUIData<CropsGrow> easyUIData = new EasyUIData<CropsGrow>();
		easyUIData.setTotal(page.getTotalElements());
		easyUIData.setRows(page.getContent());
		return easyUIData;
	}

	@Override
	public List<CropsGrow> findByCId(int cId) {
		return cropsGrowDAO.findByCId(cId);
	}

	@Override
	public CropsGrow findNextCrops(final long cId, int growStep) {
		CropsGrow cropsGrow = null;
		Specification specification = new Specification<CropsGrow>() {
			@Override
			public Predicate toPredicate(Root<CropsGrow> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("cId"), cId));

				List<Order> list = new ArrayList<>();
				list.add(new OrderImpl(root.get("status"), true));
				list.add(new OrderImpl(root.get("growStep"), true));
				criteriaQuery.orderBy(list);

				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
				return criteriaQuery.getGroupRestriction();
			}
		};
		List<CropsGrow> cropsGrowList = cropsGrowDAO.findAll(specification);

		for (int index = 0, len = cropsGrowList.size(); index < len; index++) {
			if (cropsGrowList.get(index).getGrowStep() == growStep) {
				if ((index + 1) < len) {
					cropsGrow = cropsGrowList.get(index + 1);
					break;
				}
			}
		}
		return cropsGrow;
	}

	@Override
	public CropsGrow findFirstCrops(final long cId) {
		CropsGrow cropsGrow = null;
		Specification specification = new Specification<CropsGrow>() {
			@Override
			public Predicate toPredicate(Root<CropsGrow> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("cId"), cId));

				List<Order> list = new ArrayList<>();
				list.add(new OrderImpl(root.get("status"), true));
				list.add(new OrderImpl(root.get("growStep"), true));
				criteriaQuery.orderBy(list);

				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
				return criteriaQuery.getGroupRestriction();
			}
		};
		List<CropsGrow> cropsGrowList = cropsGrowDAO.findAll(specification);
		if (cropsGrowList.size() > 0)
			cropsGrow = cropsGrowList.get(0);
		return cropsGrow;
	}

}
