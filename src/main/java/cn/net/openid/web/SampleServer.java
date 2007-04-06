/**
 * 
 */
package cn.net.openid.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.openid.message.DirectError;
import net.openid.message.Message;
import net.openid.message.ParameterList;
import net.openid.server.ServerException;
import net.openid.server.ServerManager;

/**
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * 
 */
public class SampleServer {
	// instantiate a ServerManager object
	public ServerManager manager = new ServerManager();

	public SampleServer(String OPEndpointUrl) {
		manager.setOPEndpointUrl(OPEndpointUrl);
	}

	public String processRequest(HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws Exception {
		// extract the parameters from the request
		ParameterList request = new ParameterList(httpReq.getParameterMap());

		String mode = request.hasParameter("openid.mode") ? request
				.getParameterValue("openid.mode") : null;

		Message response;
		String responseText;

		if ("associate".equals(mode)) {
			// --- process an association request ---
			response = manager.associationResponse(request);
			responseText = response.keyValueFormEncoding();
		} else if ("checkid_setup".equals(mode)
				|| "checkid_immediate".equals(mode)) {
			// interact with the user and obtain data needed to continue
			List userData = userInteraction(request);

			String userSelectedId = (String) userData.get(0);
			String userSelectedClaimedId = (String) userData.get(1);
			Boolean authenticatedAndApproved = (Boolean) userData.get(2);

			// --- process an authentication request ---
			response = manager.authResponse(request, userSelectedId,
					userSelectedClaimedId, authenticatedAndApproved
							.booleanValue());

			if (response instanceof DirectError)
				return directResponse(httpResp, response.keyValueFormEncoding());
			else {
				// caller will need to decide which of the following to use:

				// option1: GET HTTP-redirect to the return_to URL
				return response.getDestinationUrl(true);

				// option2: HTML FORM Redirection
				// RequestDispatcher dispatcher =
				// getServletContext().getRequestDispatcher("formredirection.jsp");
				// httpReq.setAttribute("prameterMap",
				// response.getParameterMap());
				// httpReq.setAttribute("destinationUrl",
				// response.getDestinationUrl(false));
				// dispatcher.forward(request, response);
				// return null;
			}
		} else if ("check_authentication".equals(mode)) {
			// --- processing a verification request ---
			response = manager.verify(request);
			responseText = response.keyValueFormEncoding();
		} else {
			// --- error response ---
			response = DirectError.createDirectError("Unknown request");
			responseText = response.keyValueFormEncoding();
		}

		// return the result to the user
		return responseText;
	}

	private List userInteraction(ParameterList request) throws ServerException {
		throw new ServerException("User-interaction not implemented.");
	}

	private String directResponse(HttpServletResponse httpResp, String response)
			throws IOException {
		ServletOutputStream os = httpResp.getOutputStream();
		os.write(response.getBytes());
		os.close();

		return null;
	}
}
