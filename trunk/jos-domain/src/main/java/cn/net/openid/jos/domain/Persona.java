/**
 * Created on 2008-3-5 下午09:40:34
 */
package cn.net.openid.jos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sutra Zhou
 * 
 */
public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1223541515197309353L;

	private String id;
	private User user;
	private String name;

	/**
	 * Any UTF-8 string that the End User wants to use as a nickname.
	 */
	private String nickname;

	private String email;

	/**
	 * UTF-8 string free text representation of the End User's full name.
	 */
	private String fullname;

	/**
	 * The End User's date of birth as YYYY-MM-DD. Any values whose
	 * representation uses fewer than the specified number of digits should be
	 * zero-padded. The length of this value MUST always be 10. If the End User
	 * user does not want to reveal any particular component of this value, it
	 * MUST be set to zero. For instance, if a End User wants to specify that
	 * his date of birth is in 1980, but not the month or day, the value
	 * returned SHALL be "1980-00-00".
	 */
	private Date dob;

	/**
	 * The End User's gender, "M" for male, "F" for female.
	 */
	private String gender;

	/**
	 * UTF-8 string free text that SHOULD conform to the End User's country's
	 * postal system.
	 */
	private String postcode;

	/**
	 * The End User's country of residence as specified by <a
	 * href="http://www.iso.org/iso/en/prods-services/iso3166ma/index.html">ISO3166</a>.
	 */
	private String country;

	/**
	 * End User's preferred language as specified by <a
	 * href="http://www.w3.org/WAI/ER/IG/ert/iso639.htm">ISO639</a>.
	 */
	private String language;

	/**
	 * ASCII string from <a
	 * href="http://www.twinsun.com/tz/tz-link.htm">TimeZone database</a> For
	 * example, "Europe/Paris" or "America/Los_Angeles".
	 */
	private String timezone;

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            要设置的 country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            要设置的 dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            要设置的 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            要设置的 fullname
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            要设置的 gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            要设置的 language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            要设置的 nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            要设置的 postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return timezone
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone
	 *            要设置的 timezone
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}
