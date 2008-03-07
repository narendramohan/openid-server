/**
 * Created on 2006-10-15 下午09:20:29
 */
package cn.net.openid.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import cn.net.openid.dao.DaoFacade;
import cn.net.openid.domain.Password;
import cn.net.openid.domain.User;

/**
 * @author Shutra
 * 
 */
public class RegisterController extends SimpleFormController {

	private DaoFacade daoFacade;

	public RegisterController() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBindAndValidate(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		RegisterForm form = (RegisterForm) command;
		String username = form.getUsername();

		if (!errors.hasErrors()) {
			User user = this.daoFacade.getUserByUsername(username);
			if (user != null) {
				errors.rejectValue("username",
						"error.register.usernameAlreadyExists");
			}
		}

		super.onBindAndValidate(request, command, errors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(Object command, BindException errors)
			throws Exception {
		RegisterForm form = (RegisterForm) command;
		User user = new User();
		Password password = new Password();
		user.setUsername(form.getUsername());
		password.setUser(user);
		String passwordShaHex = DigestUtils.shaHex(form.getPassword());
		password.setPassword(form.getPassword());
		password.setPasswordShaHex(passwordShaHex);
		this.daoFacade.insertUser(user, password);
		return super.onSubmit(command, errors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {
		RegisterForm form = (RegisterForm) command;
		form.setUsername(request.getParameter("username"));
		return super.referenceData(request, command, errors);
	}

	/**
	 * @param daoFacade
	 *            the daoFacade to set
	 */
	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}

}
