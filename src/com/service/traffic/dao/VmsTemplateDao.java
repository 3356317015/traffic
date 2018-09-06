
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.VmsTemplate;


public class VmsTemplateDao extends BaseDao{
	public VmsTemplateDao(Connection conn){
		super(conn);
	}
	
	public VmsTemplate insert(VmsTemplate vmsTemplate, Map<String, Object> config){
		String pk = super.insert(vmsTemplate,config);
		vmsTemplate.setTemplateSeq(pk);
		return vmsTemplate;
	}
	
	public void update(VmsTemplate vmsTemplate, Map<String, Object> config){
		super.update(vmsTemplate,config);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String templateSeq){
		String strSql = "delete from vms_template where template_seq=?";
		List params = new ArrayList();
		params.add(templateSeq);
		super.executeSql(strSql, params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VmsTemplate> queryByOrganizeAndType(String organizeSeq, String templateType) {
		StringBuffer strSql = new StringBuffer("select * from vms_template where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != templateType && !"".equals(templateType)){
			strSql.append(" and template_type = ?");
			params.add(templateType);
		}
		List<VmsTemplate> vmsTemplates = (List<VmsTemplate>) super.queryAll(strSql.toString(),params,new VmsTemplate());
		return vmsTemplates;
	}

}
