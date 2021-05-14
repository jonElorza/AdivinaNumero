package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adivNumero")
public class AdivinaServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdivinaServlets() {
		super();
	}

	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//COmprobar si ha metido algun numero, sino resirigir y mandar mensage		
		String numero = request.getParameter("number");
		if(numero==null) {
			response.sendRedirect("/adivinaFront.jsp");
		}
		
		Cookie[] cookies = request.getCookies();
		String numeroAleat = null;
			
		if (cookies != null) {
			
			String valorCookie = null;
			String valorRecuperado=null;

			valorCookie = buscarCookie(cookies);
			
			if (valorCookie!=null) {
			valorRecuperado = valorCookie;
			}else {					
				valorRecuperado = crearCookie(request,response,numeroAleat);
			}
			
			numero = request.getParameter("number");
			
			resultado(numero,valorRecuperado,request,response);

		} else {
			
			
			crearCookie(request, response,numeroAleat);			
				
			cookies = request.getCookies();
			String valorCookie = null;
	
			numero = request.getParameter("number");
			
			resultado(numero,valorCookie,request,response);

		}		
				
		}
	
		private void resultado(String numero, String valorRecuperado,HttpServletRequest request, HttpServletResponse response) {
			if (numero.equals(valorRecuperado)) {
				request.setAttribute("adivinado", "enhorabuena has adivinado, el numero es" + valorRecuperado);
				borrarCookie(response);
				try {
					request.getRequestDispatcher("/adivinaFront.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				request.setAttribute("adivinado", "No has adivinado, intentalo otra vez");
				try {
					request.getRequestDispatcher("/adivinaFront.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}

		String crearCookie(HttpServletRequest request, HttpServletResponse response,String  numeroAleat){	
			
				numeroAleat = "" + Math.floor(Math.random() * 10);
				Cookie guardarNumero = new Cookie("adivNumero", numeroAleat);
				guardarNumero.setMaxAge(60 * 60 * 24);
				response.addCookie(guardarNumero);
				
				return numeroAleat;

	}
		
		String buscarCookie(Cookie[] cookies) {
			String valorCookie = null;
			for (Cookie c : cookies) {
				if ("adivNumero".equals(c.getName())) {
					valorCookie = c.getValue();
					break;
				}
			}
			return valorCookie;
			
			
		}
		
		void borrarCookie(HttpServletResponse response) {
			Cookie guardarNumero = new Cookie("adivNumero", "");
			guardarNumero.setMaxAge(0);
			response.addCookie(guardarNumero);
			
		}

		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
