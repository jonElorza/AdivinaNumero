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

		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			
			
			String numero = request.getParameter("number");
			String valorCookie = null;

			for (Cookie c : cookies) {
				if ("adivNumero".equals(c.getName())) {
					valorCookie = c.getValue();
					break;
				}
			}
			
//Aqui me falta decirle si a encontrado el valor que yo queria, juego con esa variable, pero si no la ha encontrado al resultado le doy otra oopcion(La tengo que pensar). Esta en el ejemplo de javier por seacaso.			
// para borrar la cookie, la caducidad de la cookie la ponemos en 0			
			if (numero.equals(valorCookie)) {
				request.setAttribute("adivinado", "enhorabuena has adivinado, el numero es" + valorCookie);
				request.getRequestDispatcher("/adivinaFront.jsp").forward(request, response);
			} else {
				request.setAttribute("adivinado", "No has adivinado, intentalo otra vez");
				request.getRequestDispatcher("/adivinaFront.jsp").forward(request, response);
			}
		} else {
			
			String numeroAleat = "" + Math.floor(Math.random() * 10);
			Cookie guardarNumero = new Cookie("adivNumero", numeroAleat);
			guardarNumero.setMaxAge(60 * 60 * 24);
			response.addCookie(guardarNumero);
			
			String numero = request.getParameter("number");
			cookies = request.getCookies();
			String valorCookie = null;

			for (Cookie c : cookies) {
				if ("adivNumero".equals(c.getName())) {
					valorCookie = c.getValue();
					break;
				}
			}
			if (numero.equals(valorCookie)) {
				request.setAttribute("adivinado", "enhorabuena has adivinado, el numero es" + valorCookie);
				request.getRequestDispatcher("/adivinaFront.jsp").forward(request, response);
			} else {
				request.setAttribute("adivinado", "No has adivinado, intentalo otra vez");
				request.getRequestDispatcher("/adivinaFront.jsp").forward(request, response);
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
