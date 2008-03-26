/**
 * Created on 2007-3-26 23:18:11
 */
package cn.net.openid.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import cn.net.openid.dao.DaoFacade;
import cn.net.openid.domain.Attribute;
import cn.net.openid.domain.AttributeValue;

/**
 * @author sutra
 * 
 */
public class AttributeValueController extends SimpleFormController {
	private DaoFacade daoFacade;

	/**
	 * @param daoFacade
	 *            the daoFacade to set
	 */
	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}

	private Map<String, String> buildMap(List<AttributeValue> attributeValues) {
		Map<String, String> ret = new HashMap<String, String>();
		for (AttributeValue attributeValue : attributeValues) {
			ret.put(attributeValue.getAttribute().getId(), attributeValue
					.getValue());
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		List<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
		Collection<Attribute> attributes = this.daoFacade.getAttributes();
		Map<String, String> userAttributeValues = this.buildMap(this.daoFacade
				.getUserAttributeValues(userSession.getUserId()));
		for (Attribute attribute : attributes) {
			AttributeValue attributeValue = new AttributeValue();
			attributeValue.setAttribute(attribute);
			attributeValue.setValue(userAttributeValues.get(attributeValue
					.getAttribute().getId()));
			attributeValues.add(attributeValue);
		}
		return attributeValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("userSession");
		List<AttributeValue> attributeValues = (List<AttributeValue>) command;
		for (AttributeValue attributeValue : attributeValues) {
			attributeValue.setUser(this.daoFacade.getUser(userSession
					.getUserId()));
			String value = request.getParameter(attributeValue.getAttribute()
					.getId());
			attributeValue.setValue(value);
		}
		this.daoFacade.saveAttributeValues(attributeValues);
		return super.onSubmit(request, response, command, errors);
	}

}
